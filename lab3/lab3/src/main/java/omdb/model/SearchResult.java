
package omdb.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Search",
    "totalResults",
    "Response"
})
public class SearchResult {

    @JsonProperty("Search")
    private List<SearchObject> Search = null;
    @JsonProperty("totalResults")
    private String totalResults;
    @JsonProperty("Response")
    private String Response;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Search")
    public List<SearchObject> getSearch() {
        return Search;
    }

    @JsonProperty("Search")
    public void setSearch(List<SearchObject> search) {
        this.Search = search;
    }

    @JsonProperty("totalResults")
    public String getTotalResults() {
        return totalResults;
    }

    @JsonProperty("totalResults")
    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    @JsonProperty("Response")
    public String getResponse() {
        return Response;
    }

    @JsonProperty("Response")
    public void setResponse(String response) {
        this.Response = response;
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
