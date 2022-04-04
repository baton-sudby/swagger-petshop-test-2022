package api.petstore.data_providers;

import org.testng.annotations.DataProvider;

import static api.petstore.tests.swagger.instances.file_paths.Paths.*;

public class PetDataProvider {

    @DataProvider(name = "Correct_pet_statuses")
    public static Object[][] positiveStatusDate() {
        return new Object[][]{
                {new String[]{"available"}},
                {new String[]{"petting"}},
                {new String[]{"sold"}}};
    }

    @DataProvider(name = "Incorrect_pet_statuses")
    public static Object[][] getIncorrectPetStatuses() {
        return new Object[][]{
                {new String[]{"availablee"}},
                {new String[]{"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"}},
                {new String[]{"11111"}}
        };
    }

    @DataProvider(name = "Incorrect_id_for_pets")
    public static Object[][] getIncorrectIDForPets() {
        return new Object[][]{
                {"qwe"},
                {"12we"}
        };
    }
    @DataProvider(name = "Path_to_files_to_add_images")
    public static Object[][] getPathToFiles() {
        return new Object[][]{
                {TEXT_FILE_PATH},
                {DOC_FILE_PATH},
                {HTML_FILE_PATH}
        };
    }
}