package api.petstore.tests.swagger.get.id.negative;

import api.petstore.data_providers.PetDataProvider;
import api.petstore.models.pet.PetModel;
import api.petstore.tests.swagger.requests.PetRequests;
import api.petstore.tests.swagger.requests.Specifications;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.common.pet.PetFactory;

import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.*;
import static api.petstore.tests.swagger.instances.urls.BaseURLs.*;
import static javax.servlet.http.HttpServletResponse.*;
import static utils.common.enums.PetTypeVar.*;

public class GetByIDNegativeTest {

    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeGetByIDTest() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec, pet);
        RequestSpecification specToDelete = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        PetRequests.delete(specToDelete);
    }

    @Test
    public static void getPetByNotExistingIdNegative() {
        RequestSpecification specToDelete = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        PetRequests.delete(specToDelete);
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Not existing pet exists");
    }

    @Test(dataProvider = "Incorrect_id_for_pets", dataProviderClass = PetDataProvider.class)
    public static void getPetByIncorrectIdNegative(String id) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + id);
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Incompatible type of id works out");
    }
}