package utils.common.validator;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static utils.common.validator.ValidationTemplatePaths.*;

public class JsonValidator {

    public static void validateList(String response) {
        try {
            JSONObject jsonSchema = new JSONObject(new FileInputStream(PATH_TO_PETLIST_TEMPLATE));
            JSONArray jsonSubject = new JSONArray(new JSONTokener(response));
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}