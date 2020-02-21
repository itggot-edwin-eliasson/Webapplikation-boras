
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
    "Title",
    "Year",
    "Rated",
    "Released",
    "Runtime",
    "Genre",
    "Director",
    "Writer",
    "Actors",
    "Plot",
    "Language",
    "Country",
    "Awards",
    "Poster",
    "Ratings",
    "Metascore",
    "imdbRating",
    "imdbVotes",
    "imdbID",
    "Type",
    "DVD",
    "BoxOffice",
    "Production",
    "Website",
    "Response"
})
public class FilmObject {

    @JsonProperty("Title")
    private String Title;
    @JsonProperty("Year")
    private String Year;
    @JsonProperty("Rated")
    private String Rated;
    @JsonProperty("Released")
    private String Released;
    @JsonProperty("Runtime")
    private String Runtime;
    @JsonProperty("Genre")
    private String Genre;
    @JsonProperty("Director")
    private String Director;
    @JsonProperty("Writer")
    private String Writer;
    @JsonProperty("Actors")
    private String Actors;
    @JsonProperty("Plot")
    private String Plot;
    @JsonProperty("Language")
    private String Language;
    @JsonProperty("Country")
    private String Country;
    @JsonProperty("Awards")
    private String Awards;
    @JsonProperty("Poster")
    private String Poster;
    @JsonProperty("Ratings")
    private List<Rating> Ratings = null;
    @JsonProperty("Metascore")
    private String Metascore;
    @JsonProperty("imdbRating")
    private String imdbRating;
    @JsonProperty("imdbVotes")
    private String imdbVotes;
    @JsonProperty("imdbID")
    private String imdbID;
    @JsonProperty("Type")
    private String Type;
    @JsonProperty("DVD")
    private String DVD;
    @JsonProperty("BoxOffice")
    private String BoxOffice;
    @JsonProperty("Production")
    private String Production;
    @JsonProperty("Website")
    private String Website;
    @JsonProperty("Response")
    private String Response;
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

    @JsonProperty("Rated")
    public String getRated() {
        return Rated;
    }

    @JsonProperty("Rated")
    public void setRated(String rated) {
        this.Rated = rated;
    }

    @JsonProperty("Released")
    public String getReleased() {
        return Released;
    }

    @JsonProperty("Released")
    public void setReleased(String released) {
        this.Released = released;
    }

    @JsonProperty("Runtime")
    public String getRuntime() {
        return Runtime;
    }

    @JsonProperty("Runtime")
    public void setRuntime(String runtime) {
        this.Runtime = runtime;
    }

    @JsonProperty("Genre")
    public String getGenre() {
        return Genre;
    }

    @JsonProperty("Genre")
    public void setGenre(String genre) {
        this.Genre = genre;
    }

    @JsonProperty("Director")
    public String getDirector() {
        return Director;
    }

    @JsonProperty("Director")
    public void setDirector(String director) {
        this.Director = director;
    }

    @JsonProperty("Writer")
    public String getWriter() {
        return Writer;
    }

    @JsonProperty("Writer")
    public void setWriter(String writer) {
        this.Writer = writer;
    }

    @JsonProperty("Actors")
    public String getActors() {
        return Actors;
    }

    @JsonProperty("Actors")
    public void setActors(String actors) {
        this.Actors = actors;
    }

    @JsonProperty("Plot")
    public String getPlot() {
        return Plot;
    }

    @JsonProperty("Plot")
    public void setPlot(String plot) {
        this.Plot = plot;
    }

    @JsonProperty("Language")
    public String getLanguage() {
        return Language;
    }

    @JsonProperty("Language")
    public void setLanguage(String language) {
        this.Language = language;
    }

    @JsonProperty("Country")
    public String getCountry() {
        return Country;
    }

    @JsonProperty("Country")
    public void setCountry(String country) {
        this.Country = country;
    }

    @JsonProperty("Awards")
    public String getAwards() {
        return Awards;
    }

    @JsonProperty("Awards")
    public void setAwards(String awards) {
        this.Awards = awards;
    }

    @JsonProperty("Poster")
    public String getPoster() {
        return Poster;
    }

    @JsonProperty("Poster")
    public void setPoster(String poster) {
        this.Poster = poster;
    }

    @JsonProperty("Ratings")
    public List<Rating> getRatings() {
        return Ratings;
    }

    @JsonProperty("Ratings")
    public void setRatings(List<Rating> ratings) {
        this.Ratings = ratings;
    }

    @JsonProperty("Metascore")
    public String getMetascore() {
        return Metascore;
    }

    @JsonProperty("Metascore")
    public void setMetascore(String metascore) {
        this.Metascore = metascore;
    }

    @JsonProperty("imdbRating")
    public String getImdbRating() {
        return imdbRating;
    }

    @JsonProperty("imdbRating")
    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    @JsonProperty("imdbVotes")
    public String getImdbVotes() {
        return imdbVotes;
    }

    @JsonProperty("imdbVotes")
    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    @JsonProperty("imdbID")
    public String getImdbID() {
        return imdbID;
    }

    @JsonProperty("imdbID")
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @JsonProperty("Type")
    public String getType() {
        return Type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.Type = type;
    }

    @JsonProperty("DVD")
    public String getDVD() {
        return DVD;
    }

    @JsonProperty("DVD")
    public void setDVD(String dVD) {
        this.DVD = dVD;
    }

    @JsonProperty("BoxOffice")
    public String getBoxOffice() {
        return BoxOffice;
    }

    @JsonProperty("BoxOffice")
    public void setBoxOffice(String boxOffice) {
        this.BoxOffice = boxOffice;
    }

    @JsonProperty("Production")
    public String getProduction() {
        return Production;
    }

    @JsonProperty("Production")
    public void setProduction(String production) {
        this.Production = production;
    }

    @JsonProperty("Website")
    public String getWebsite() {
        return Website;
    }

    @JsonProperty("Website")
    public void setWebsite(String website) {
        this.Website = website;
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
