package api.petstore.swagger.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetRequests {

    public static Response get(RequestSpecification specification) {
        return RestAssured.given()
                .spec(specification)
                .when()
                .get()
                .then()
                .log()
                .all()
                .extract().response();
    }
}