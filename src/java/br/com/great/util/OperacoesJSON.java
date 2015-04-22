/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe responsavel com Operacores com JSON
 * @author carleandro
 */
public class OperacoesJSON {
    /**
     * Método responsável por receber um conjunto de chaves com valores retornar objetos json
     * @param key String[]
     * @param value String[]
     * @return JSONObject
     */
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
    public JSONObject toJSONObject(String key, String value){
        JSONObject jobj = new JSONObject();
        try {
             jobj.put(key, value);
        } catch (JSONException ex) {
           System.err.println("Erro toJSONObject:"+ex.getMessage());
        }
        return jobj;
    }
    /**
     * Método responsável por receber um conjunto de chaves com valores retornar jsonArray
     * @param key String[]
     * @param value String[]
     * @return JSONArray
     */
    public JSONArray toJSONArray(String[][] key, String[][] value){
       JSONArray json = new JSONArray();
       for(int j=0; j<key.length; j++){ 
           json.put(toJSONObject(key[j], value[j]));
       }
       return json;
    }
    public JSONArray toJSONArray(String key, String value){
       JSONArray json = new JSONArray();
           json.put(toJSONObject(key, value));
       return json;
    }
    
    /**
     * Método responsável por receber jsonArray e retornar objetos json da posicao X
     * @param json JSONArray
     * @param posObjeto int
     * @return JSONObject
     */
    public JSONObject toJSONObject(JSONArray json, int posObjeto){
        JSONObject jobj = null;
        try {
            jobj = json.getJSONObject(posObjeto);
        } catch (JSONException ex) {
            System.err.println("Erro toJSONObject:"+ex.getMessage());
        }
        return jobj;
    }
    
    /**
     * Método responsável por receber jsonArray e retornar um item do objeto json da posicao X
     * @param json JSONArray
     * @param posObjeto int
     * @param key String
     * @return String 
     */
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
