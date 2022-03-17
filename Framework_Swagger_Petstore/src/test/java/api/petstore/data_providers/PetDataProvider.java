package api.petstore.data_providers;

import org.testng.annotations.DataProvider;

public class PetDataProvider {

    @DataProvider(name = "Correct_pet_statuses")
    public static Object[][] positiveStatusDate() {
        return new Object[][]{
                {new String[]{"available"}},
                {new String[]{"petting"}},
                {new String[]{"sold"}}};
    }

    @DataProvider(name = "Incorrect_pet_statuses")
    public static Object[][] Incorrect_pet_statuses() {
        return new Object[][]{
                {new String[]{"availablee"}},
                {new String[]{"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"}},
                {new String[]{"11111"}}
        };
    }
}