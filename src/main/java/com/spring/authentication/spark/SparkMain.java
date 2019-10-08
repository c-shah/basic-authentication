package com.spring.authentication.spark;

import com.spring.authentication.service.EchoService;
import com.spring.authentication.service.EnvironmentService;
import com.spring.authentication.service.HRService;
import com.spring.authentication.service.JsonTransformer;

import java.util.Map;
import static spark.Spark.*;


public class SparkMain {


    public static void main(String args[]) throws Exception {

        Map<String, String> environment = EnvironmentService.getEnvironmentMap();
        String port = environment.get("PORT") != null ? environment.get("PORT") : "8000";
        port(Integer.parseInt(port));
        
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/hr/employees", (request, response) -> {
            response.type("application/json");
            return HRService.getAllEmployees(request, response);
        }, new JsonTransformer());
        get("/hr/employee/:columnName/:columnValue", (request, response) -> {
            response.type("application/json");
            return HRService.getEmployeesByColumn(request, response);
        }, new JsonTransformer());
        get("/", (request, response) -> {
            response.type("text/html");
            return "user /echo or /echoSecure and POSt";
        });
        get("/echo", (request, response) -> {
            response.type("application/json");
            return EchoService.processEcho(request, response);
        }, new JsonTransformer());
        get("/echodelay", (request, response) -> {
            response.type("application/json");
            return EchoService.processEchoDelay(request, response);
        }, new JsonTransformer());
        get("/echoSecure", (request, response) -> {
            response.type("application/json");
            return EchoService.processEchoSecure(request, response);
        }, new JsonTransformer());
        post("/echo", (request, response) -> {
            response.type("application/json");
            return EchoService.processEcho(request, response);
        }, new JsonTransformer());
        post("/echoSecure", (request, response) -> {
            response.type("application/json");
            return EchoService.processEchoSecure(request, response);
        }, new JsonTransformer());
    }
}
