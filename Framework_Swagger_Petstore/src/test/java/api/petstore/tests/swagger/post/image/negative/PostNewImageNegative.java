package api.petstore.tests.swagger.post.image.negative;

import api.petstore.data_providers.PetDataProvider;
import api.petstore.models.pet.PetModel;
import api.petstore.tests.swagger.requests.PetRequests;
import api.petstore.tests.swagger.requests.Specifications;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.common.pet.PetFactory;

import java.io.File;

import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.*;
import static api.petstore.tests.swagger.instances.urls.BaseURLs.*;
import static javax.servlet.http.HttpServletResponse.*;
import static utils.common.enums.PetTypeVar.*;

public class PostNewImageNegative {

    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeClassPostNewImageTests() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec, pet);
    }

    @SneakyThrows
    @Test(dataProvider = "Path_to_files_to_add_images", dataProviderClass = PetDataProvider.class)
    public static void postPetImageNegative(String path) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId() +
                UPLOAD_IMAGE , new File(path));
        Response response = PetRequests.post(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_ACCEPTABLE, "Incompatible type of file has been uploaded");
    }
}