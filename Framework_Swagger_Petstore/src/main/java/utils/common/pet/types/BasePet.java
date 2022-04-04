package utils.common.pet.types;

import api.petstore.models.pet.PetModel;
import org.eclipse.jetty.util.ajax.JSON;
import utils.common.generator.Generator;
import utils.common.pet.IPet;

import java.util.List;

public class BasePet implements IPet {

    public PetModel createPet() {
        PetModel pet = new PetModel();
        pet.setName(Generator.getRandomString(5));
        List<String> urls = List.of(Generator.getRandomString(15));
        pet.setPhotoUrls(urls);
        return pet;
    }
}