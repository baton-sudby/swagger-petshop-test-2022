package api.petstore.models.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name"})
public class Category {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;
}