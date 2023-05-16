package com.melgarejo.backend.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {

    private ArrayList<HashMap<String, String>> metadata= new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }
    public void setMetadata(String type, String code, String date) {
            HashMap<String, String> response = new HashMap<String,String>();
            response.put("type", type);
            response.put("code",code);
            response.put("date",date);

            metadata.add(response);
    }
}
