package omdb.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "imdbID",
    "Poster"
})
public class SearchObject {

    @JsonProperty("imdbID")
    private String imdbID;
    @JsonProperty("Poster")
    private String Poster;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

   

    @JsonProperty("imdbID")
    public String getImdbID() {
        return imdbID;
    }

    @JsonProperty("imdbID")
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @JsonProperty("Poster")
    public String getPoster() {
        return Poster;
    }

    @JsonProperty("Poster")
    public void setPoster(String poster) {
        this.Poster = poster;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
