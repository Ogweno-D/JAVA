package  com.example;

import  org.json.JSONException;
import  org.json.JSONObject;

public class JsonProcessor implements DataProcessor {

    /**
     * Processes the input JSON data and returns a formatted string.
     *
     * @param inputData The JSON data to be processed.
     * @return A formatted string representation of the JSON data.
     */

    @Override
    public String process(String inputData){

        try {
            JSONObject jsonobject = new JSONObject(inputData);
             return jsonobject.toString(4);
            
        } catch (JSONException e) {
            return "Invalid JSON data: " + e.getMessage();
        }
    }
}