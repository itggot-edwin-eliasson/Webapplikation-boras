/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omdb;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import omdb.model.SearchObject;
import omdb.model.SearchResult;

public abstract class OmdbService {

    private static final String KEY = "b410e072"; // TODO: need to hide this key


    // Returns the first page with maximum 10 results and ignores films without posters
    public static List<SearchObject> getSearchObjectsFromSearchString(String searchValue) {
        try {
            HttpResponse<JsonNode> response = Unirest.post("http://www.omdbapi.com/?")
                    .header("accept", "application/json")
                    .queryString("apikey", KEY)
                    .queryString("s", searchValue)
                    .asJson();

            Gson gson = new Gson();
            SearchResult searchObject = gson.fromJson(response.getBody().toString(), SearchResult.class);

            return searchObject.getSearch().stream().filter(object -> !object.getPoster().equals("N/A")).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("ERROR: Could not query the API! " + e.getMessage());
        }

        return new ArrayList<SearchObject>();
    }

    // Returns the chosen page and ignores films without posters
    public static List<SearchObject> getSearchObjectsFromSearchString(String searchValue, int pageNumber) {
        try {
            HttpResponse<JsonNode> response = Unirest.post("http://www.omdbapi.com/?")
                    .header("accept", "application/json")
                    .queryString("apikey", KEY)
                    .queryString("s", searchValue)
                    .queryString("page", pageNumber)
                    .asJson();

            Gson gson = new Gson();
            SearchResult searchObject = gson.fromJson(response.getBody().toString(), SearchResult.class);

            return searchObject.getSearch().stream().filter(object -> !object.getPoster().equals("N/A")).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("ERROR: Could not query the API! " + e.getMessage());
        }

        return new ArrayList<SearchObject>();
    }
}
