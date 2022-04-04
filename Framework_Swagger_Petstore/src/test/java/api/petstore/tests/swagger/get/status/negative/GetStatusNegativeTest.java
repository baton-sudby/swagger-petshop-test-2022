package api.petstore.tests.swagger.get.status.negative;

import api.petstore.data_providers.PetDataProvider;
import api.petstore.models.pet.PetModel;
import api.petstore.tests.swagger.requests.PetRequests;
import api.petstore.tests.swagger.requests.Specifications;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.*;
import static api.petstore.tests.swagger.instances.queries.PetQueries.*;
import static api.petstore.tests.swagger.instances.urls.BaseURLs.*;

public class GetStatusNegativeTest {

    @SneakyThrows
    @Test(dataProvider = "Incorrect_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusCorrectStatuses(String[] status) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PETS_BY_STATUS);
        Response response = PetRequests.get(spec.queryParam(PET_STATUS, status[0]));
        JsonMapper mapper = new JsonMapper();
        List<PetModel> myObjects = mapper.readValue(response.asString(), new TypeReference<>() {});
        Assert.assertEquals(myObjects.size(), 0, "Pet statuses list is not empty");
    }
}