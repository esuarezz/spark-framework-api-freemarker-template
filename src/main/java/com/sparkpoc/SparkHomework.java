package com.sparkpoc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class SparkHomework {
    private static final Logger logger = LoggerFactory.getLogger("logger");

    public static void main(String[] args) throws UnknownHostException {

        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(
                SparkHomework.class, "/");


        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request,
                                 final Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("example.ftl");

                    Map<String, String> answerMap = new HashMap<String, String>();
                    answerMap.put("who", sayHiWorld());
                    logger.info("who is -> "+sayHiWorld());
                    helloTemplate.process(answerMap, writer);
                } catch (Exception e) {
                    logger.error("Exception: ", e);
                    halt(500);
                }
                return writer;
            }
        });
    }

    private static String sayHiWorld() {
        return " World";
    }
}
