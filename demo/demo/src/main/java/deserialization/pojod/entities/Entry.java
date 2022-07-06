package deserialization.pojod.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Entry {
    @JsonProperty
    private String API;
    @JsonProperty
    private String Description;
    @JsonProperty
    private String Auth;
    @JsonProperty
    private Boolean HTTPS;
    @JsonProperty
    private String Cors;
    @JsonProperty
    private String Link;
    @JsonProperty
    private String Category;
}
