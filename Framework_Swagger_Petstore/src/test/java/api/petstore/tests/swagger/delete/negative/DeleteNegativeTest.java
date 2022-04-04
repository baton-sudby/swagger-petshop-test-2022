package api.petstore.tests.swagger.delete.negative;

import api.petstore.models.pet.PetModel;
import api.petstore.tests.swagger.requests.PetRequests;
import api.petstore.tests.swagger.requests.Specifications;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.common.pet.PetFactory;

import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.*;
import static api.petstore.tests.swagger.instances.urls.BaseURLs.*;
import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static utils.common.enums.PetTypeVar.*;

public class DeleteNegativeTest {

    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeDeleteNegativeTest() {
        RequestSpecification specToCreate = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(specToCreate, pet);
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        PetRequests.delete(spec);
    }

    @Test
    public static void deleteNotExistingPetByIDNegativeTest() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.delete(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Delete not existing pet has done successfully");
    }

    @Test
    public static void sendDeleteRequestWithEmptyQueryTest() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT);
        Response response = PetRequests.delete(spec);
        Assert.assertEquals(response.getStatusCode(), SC_METHOD_NOT_ALLOWED, "Empty query doesn't invoke an error");
    }

    @Test
    public static void sendDeleteRequestWithIncorrectDataTypeQueryTest() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT);
        Response response = PetRequests.delete(spec);
        Assert.assertEquals(response.getStatusCode(), SC_METHOD_NOT_ALLOWED, "Incorrect datatype doesn't invoke an error");
    }
}