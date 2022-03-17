package api.petstore.swagger.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {

    public static RequestSpecification requestSpecification(String url, String path) {
        return requestSpecification(url, path, ContentType.JSON, ContentType.JSON);
    }

    private static RequestSpecification requestSpecification(String url, String path, ContentType sentType,
                                                             ContentType acceptType) {
        return new RequestSpecBuilder()
                .setContentType(sentType)
                .setAccept(acceptType)
                .setBaseUri(url)
                .setBasePath(path)
                .build()
                .log().all();
    }
}