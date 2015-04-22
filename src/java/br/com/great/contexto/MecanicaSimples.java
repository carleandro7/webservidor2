package br.com.great.contexto;

import java.sql.Time;

public abstract class MecanicaSimples extends Mecanica {

    protected Objeto objeto;
    protected int visivel, mecsimples_id;
    protected String tiposimples;
    protected Time tempo;

    public abstract void executar();

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

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public Objeto getObjeto() {
        return objeto;
    }

}
