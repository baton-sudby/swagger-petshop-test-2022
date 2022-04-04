package api.petstore.tests.swagger.put.positive;

import api.petstore.models.pet.PetModel;
import api.petstore.tests.swagger.requests.PetRequests;
import api.petstore.tests.swagger.requests.Specifications;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.common.pet.PetFactory;
import utils.common.validator.JsonValidator;

import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.PET;
import static api.petstore.tests.swagger.instances.urls.BaseURLs.BASE_URL;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static utils.common.enums.PetTypeVar.*;

public class PutNewPetInfoPositiveTest {

    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @Test
    public static void putNewPetInfoPositive() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        Response response = PetRequests.put(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Setting new info has failed");
        JsonValidator.validateObject(response);
    }
}