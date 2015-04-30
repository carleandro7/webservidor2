package br.com.great.contexto;

import java.sql.Time;
import org.json.JSONObject;

public abstract class MecanicaSimples extends Mecanica {

    protected Objeto objeto;
    protected int visivel, vibrar, mecsimples_id, life;
    protected String tiposimples;
    protected Time tempo;

    public abstract Objeto executar(JSONObject jObj);

    public int getVisivel() {
        return visivel;
    }

    public void setVisivel(int visivel) {
        this.visivel = visivel;
    }

    public int getMecsimples_id() {
        return mecsimples_id;
    }

    public void setMecsimples_id(int mecsimples_id) {
        this.mecsimples_id = mecsimples_id;
    }

    public String getTiposimples() {
        return tiposimples;
    }

    public void setTiposimples(String tiposimples) {
        this.tiposimples = tiposimples;
    }

    public Time getTempo() {
        return tempo;
    }

    public void setTempo(Time tempo) {
        this.tempo = tempo;
    }

    public int getVibrar() {
        return vibrar;
    }

    public void setVibrar(int vibrar) {
        this.vibrar = vibrar;
    }
    
    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    

}
