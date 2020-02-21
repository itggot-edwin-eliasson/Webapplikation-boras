/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omdb;

import omdb.model.FilmObject;
import com.google.gson.Gson;
import java.util.List;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import omdb.model.SearchObject;
import omdb.model.SearchResult;

/**
 *
 * @author pontus53
 */
public class OmdbService {

    private static final String KEY = "b410e072"; // TODO: need to hide this key

    public static FilmObject getFilmObjectFromId(String filmId) {
        HttpResponse<JsonNode> response = Unirest.post("http://www.omdbapi.com/?")
                .header("accept", "application/json")
                .queryString("apikey", KEY)
                .queryString("i", filmId)
                .asJson();

        Gson gson = new Gson();
        return gson.fromJson(response.getBody().toString(), FilmObject.class);
    }
    
    // Returns the first page with maximum 10 results
    public static List<SearchObject> getSearchObjectsFromSearchString(String searchValue) {
        HttpResponse<JsonNode> response = Unirest.post("http://www.omdbapi.com/?")
                .header("accept", "application/json")
                .queryString("apikey", KEY)
                .queryString("s", searchValue)
                .asJson();

        Gson gson = new Gson();
        SearchResult searchObject = gson.fromJson(response.getBody().toString(), SearchResult.class);
        
        return searchObject.getSearch();
    }
    
    // Returns the chosen page
    public static List<SearchObject> getSearchObjectsFromSearchString(String searchValue, int pageNumber) {
        HttpResponse<JsonNode> response = Unirest.post("http://www.omdbapi.com/?")
                .header("accept", "application/json")
                .queryString("apikey", KEY)
                .queryString("s", searchValue)
                .queryString("page", pageNumber)
                .asJson();

        Gson gson = new Gson();
        SearchResult searchObject = gson.fromJson(response.getBody().toString(), SearchResult.class);
        
        return searchObject.getSearch();
    }
}
