package com.melgarejo.backend.response;

import java.util.HashMap;

public class ResponseRest {

    private HashMap<String, String> metada = new HashMap<>();

    public HashMap<String, String> getMetada() {
        return metada;
    }

    public void setMetada(String type, String code, String date) {
            HashMap<String, String> response = new HashMap<String,String>();
            response.put("type", type);
            response.put("code",code);
            response.put("date",date);

    }
}
