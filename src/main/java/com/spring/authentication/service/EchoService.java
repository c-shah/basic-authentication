package com.spring.authentication.service;

import com.google.gson.Gson;
import com.spring.authentication.dao.EchoRequest;
import com.spring.authentication.dao.EchoResponse;
import spark.Request;
import spark.Response;

import java.util.Base64;

public class EchoService {

    public static final String userName = "testU";
    public static final String password = "testP";

    public static EchoResponse processEcho(Request request, Response response) throws Exception {
        EchoRequest echoRequest = new Gson().fromJson(request.body(), EchoRequest.class);
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.message = "Response : " + echoRequest.message;
        return echoResponse;
    }

    public static EchoResponse processEchoSecure(Request request, Response response) throws Exception {
        EchoRequest echoRequest = new Gson().fromJson(request.body(), EchoRequest.class);
        EchoResponse echoResponse = new EchoResponse();

        String header = request.headers("Authorization");
        assert header.substring(0, 6).equals("Basic ");
        String basicAuthEncoded = header.substring(6);
        String basicAuthAsString = new String( Base64.getDecoder().decode(basicAuthEncoded.getBytes()));
        if( basicAuthAsString.equals(userName + ":" + password ) ) {
            echoResponse.message = "Response : " + echoRequest.message + " : passed ";
        } else {
            echoResponse.message = "Response : " + echoRequest.message + " : failed ";
        }
        return echoResponse;
    }

}
