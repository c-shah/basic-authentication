package com.spring.authentication.spark;

import com.spring.authentication.service.EchoService;
import com.spring.authentication.service.EnvironmentService;
import com.spring.authentication.service.JsonTransformer;

import java.util.Map;
import static spark.Spark.*;


public class SparkMain {

    public static void main(String args[]) throws Exception {

        Map<String, String> environment = EnvironmentService.getEnvironmentMap();
        String port = environment.get("PORT") != null ? environment.get("PORT") : "8000";
        port(Integer.parseInt(port));
        get("/", (request, response) -> {
            response.type("text/html");
            return "user /echo or /echoSecure";
        });
        get("/echo", (request, response) -> {
            response.type("application/json");
            return EchoService.processEcho(request, response);
        }, new JsonTransformer());
        get("/echoSecure", (request, response) -> {
            response.type("application/json");
            return EchoService.processEchoSecure(request, response);
        }, new JsonTransformer());
    }
}
