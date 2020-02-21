/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Omdb;

import Omdb.model.FilmObject;
import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

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
}
