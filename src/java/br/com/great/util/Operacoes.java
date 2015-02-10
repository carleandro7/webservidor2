/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author carleandro
 */
public class Operacoes {
    
    public JSONObject toJSONObject(String[] key, String[] value){
        JSONObject jobj = new JSONObject();
        int i =0;
        try {
            for(String chave: key){
                    jobj.put(chave, value[i]);
                i++;
            }
        } catch (JSONException ex) {
           System.err.println("Erro toJSONObject:"+ex.getMessage());
        }
        return jobj;
    }
    public JSONArray toJSONArray(String[][] key, String[][] value){
       JSONArray json = new JSONArray();
       for(int j=0; j<key.length; j++){ 
           json.put(toJSONObject(key[j], value[j]));
       }
       return json;
    }
    
    public JSONObject toJSONObject(JSONArray json, int posObjeto){
        JSONObject jobj = null;
        try {
            jobj = json.getJSONObject(posObjeto);
        } catch (JSONException ex) {
            System.err.println("Erro toJSONObject:"+ex.getMessage());
        }
        return jobj;
    }
    public String toJSONObject(JSONArray json, int posObjeto, String key){
        String value ="";
        try {
            JSONObject jobj = json.getJSONObject(posObjeto);
            value = jobj.getString(key);
        } catch (JSONException ex) {
            System.err.println("Error toJSONObject:"+ex.getMessage());
        }
        return value;
    }
}
