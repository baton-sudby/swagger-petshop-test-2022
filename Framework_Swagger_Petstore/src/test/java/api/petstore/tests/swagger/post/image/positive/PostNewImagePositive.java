package api.petstore.tests.swagger.post.image.positive;

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

import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.PET;
import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.PET_STRICT;
import static api.petstore.tests.swagger.instances.endpoints.PetStoreEndpoints.UPLOAD_IMAGE;
import static api.petstore.tests.swagger.instances.file_paths.Paths.IMAGE_FILE_PATH;
import static api.petstore.tests.swagger.instances.urls.BaseURLs.BASE_URL;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static utils.common.enums.PetTypeVar.*;

public class PostNewImagePositive {

    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeClassPostNewImageTests() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec, pet);
    }

    @SneakyThrows
    @Test
    public static void postPEtImagePositive() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId() +
                UPLOAD_IMAGE, new File(IMAGE_FILE_PATH));
        Response response = PetRequests.post(spec);
        Assert.assertEquals(response.getStatusCode(), SC_OK, "Image doesn't upload to server");
    }
}
