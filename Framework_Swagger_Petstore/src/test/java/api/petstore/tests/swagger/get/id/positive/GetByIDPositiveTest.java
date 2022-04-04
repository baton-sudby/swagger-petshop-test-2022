package api.petstore.tests.swagger.get.id.positive;

import api.petstore.models.pet.PetModel;
import api.petstore.tests.swagger.requests.PetRequests;
import api.petstore.tests.swagger.requests.Specifications;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.common.pet.PetFactory;
import utils.common.validator.JsonValidator;

import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.*;
import static api.petstore.tests.swagger.instances.urls.BaseURLs.*;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static utils.common.enums.PetTypeVar.*;

public class GetByIDPositiveTest {

    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeGetPetByID() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec, pet);
    }

    @Test
    public static void getPetByIDPositive() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_OK, "Couldn't get pet by id");
        JsonValidator.validateObject(response);
    }
}