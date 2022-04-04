package api.petstore.tests.swagger.delete.positive;

import api.petstore.models.pet.PetModel;
import api.petstore.tests.swagger.requests.PetRequests;
import api.petstore.tests.swagger.requests.Specifications;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.common.pet.PetFactory;

import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.PET;
import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.PET_STRICT;
import static api.petstore.tests.swagger.instances.urls.BaseURLs.*;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static utils.common.enums.PetTypeVar.*;

public class DeletePositiveTest {

    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeDeletePetByID() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec,pet);
    }

    @Test
    public static void deletePetByIDPositiveTest() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.delete(spec);
        Assert.assertEquals(response.getStatusCode(), SC_OK, "Couldn't delete existing pet by id");
    }
}