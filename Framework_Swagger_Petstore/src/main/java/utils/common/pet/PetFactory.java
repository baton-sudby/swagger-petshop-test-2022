package utils.common.pet;

import api.petstore.models.pet.PetModel;
import utils.common.pet.types.BasePet;
import utils.common.pet.types.FullPet;

import static utils.common.enums.PetTypeVar.*;

public class PetFactory {

    public static PetModel createNewPet(Enum petType) {
        if (BASE_PET.equals(petType)) {
            return new BasePet().createPet();
        } else if (FULFILLED_PET.equals(petType)) {
            return new FullPet().createPet();
        }
        throw new RuntimeException(petType + " is unknown");
    }
}