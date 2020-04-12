/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omdb.model;

/**
 *
 * @author ogge
 */
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
    "Title",
    "Year",
    "Poster",
    "imdbRating",
    "Type",
    "Plot"
})
public class FilmObject {

    @JsonProperty("Title")
    private String Title;
    @JsonProperty("Year")
    private String Year;
    @JsonProperty("Poster")
    private String Poster;
    @JsonProperty("imdbRating")
    private String imdbRating;
    @JsonProperty("Type")
    private String Type;
    @JsonProperty("Plot")
    private String Plot;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Title")
    public String getTitle() {
        return Title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.Title = title;
    }

    @JsonProperty("Year")
    public String getYear() {
        return Year;
    }

    @JsonProperty("Year")
    public void setYear(String year) {
        this.Year = year;
    }

    @JsonProperty("Poster")
    public String getPoster() {
        return Poster;
    }

    @JsonProperty("Poster")
    public void setPoster(String poster) {
        this.Poster = poster;
    }

    @JsonProperty("imdbRating")
    public String getImdbRating() {
        return imdbRating;
    }

    @JsonProperty("imdbRating")
    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    @JsonProperty("Type")
    public String getType() {
        return Type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.Type = type;
    }
    
    @JsonProperty("Plot")
    public String getPlot() {
        return Plot;
    }

    @JsonProperty("Plot")
    public void setPlot(String plot) {
        this.Plot = plot;
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
