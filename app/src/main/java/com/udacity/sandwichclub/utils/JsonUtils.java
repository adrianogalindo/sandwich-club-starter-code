package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonSandwich = new JSONObject(json);
            JSONObject jsonName = jsonSandwich.getJSONObject( "name");

            String mainName = jsonName.getString("mainName");
            JSONArray alsoKnownArray = jsonName.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = jsonArrayToJavaArrayList(alsoKnownArray);
            String placeOfOrigin = jsonSandwich.optString("placeOfOrigin");
            String description = jsonSandwich.getString("description");
            String image = jsonSandwich.getString("image");
            JSONArray ingredientsArray = jsonSandwich.getJSONArray("ingredients");
            List<String> ingredients = jsonArrayToJavaArrayList(ingredientsArray);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> jsonArrayToJavaArrayList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>(jsonArray.length());
        for (int i=0; i<jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
}
