package api.petstore.tests.swagger.post.pet.positive;

import api.petstore.models.pet.PetModel;
import api.petstore.tests.swagger.requests.PetRequests;
import api.petstore.tests.swagger.requests.Specifications;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.reports.adaptors.specflow.SpecflowScenario;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.common.pet.PetFactory;
import utils.common.validator.JsonValidator;

import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.*;
import static api.petstore.tests.swagger.instances.urls.BaseURLs.*;
import static javax.servlet.http.HttpServletResponse.*;
import static utils.common.enums.PetTypeVar.*;

public class PostNewPetPositive {

    @Test
    public static void postNewPetWithRequiredFieldsPositive() {
        PetModel pet = PetFactory.createNewPet(BASE_PET);

        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        Response response = PetRequests.post(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "pet creation failed");
        JsonValidator.validateObject(response);
    }

    @Test
    public static void postNewPetWithAllFieldsPositive() {
        PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

        RequestSpecification spec = Specifications.requestSpecification(BASE_URL,PET);
        Response response = PetRequests.post(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Pet creation failed");
        JsonValidator.validateObject(response);
    }
}