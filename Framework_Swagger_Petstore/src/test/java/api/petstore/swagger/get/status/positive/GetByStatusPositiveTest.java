package api.petstore.swagger.get.status.positive;

import api.petstore.data_providers.PetDataProvider;
import api.petstore.models.pet.PetModel;
import api.petstore.swagger.requests.PetRequests;
import api.petstore.swagger.requests.Specifications;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.common.validator.JsonValidator;

import java.util.List;

import static api.petstore.swagger.instances.endpoints.PetStoreEndpoints.*;
import static api.petstore.swagger.instances.queries.PetQueries.*;
import static api.petstore.swagger.instances.urls.BaseURLs.*;
import static org.apache.http.HttpStatus.*;

public class GetByStatusPositiveTest {

    @Test(dataProvider = "Correct_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusCorrectStatuses(String[] status) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PETS_BY_STATUS);
        Response response = PetRequests.get(spec.queryParam(PET_STATUS, status[0]));
        Assert.assertEquals(response.getStatusCode(), SC_OK);
        JsonValidator.validateList(response.asString());
    }

    @SneakyThrows
    @Test(dataProvider = "Correct_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void responseBodyHasStatusAccordingRequest(String[] status) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PETS_BY_STATUS);
        Response response = PetRequests.get(spec.queryParam(PET_STATUS, status[0]));
        JsonMapper mapper = new JsonMapper();
        List<PetModel> myObjects = mapper.readValue(response.asString(), new TypeReference<>(){});
        for (PetModel pet : myObjects) {
            Assert.assertEquals(pet.getStatus(), status[0], "One of objects has incorrect parameters");
        }
    }
}