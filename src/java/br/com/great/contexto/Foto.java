package br.com.great.contexto;
public class Foto extends Objeto {
    private int foto_id, mecsimples_id;
    private String arqimage;

    public int getFoto_id() {
        return foto_id;
    }

    public void setFoto_id(int foto_id) {
        this.foto_id = foto_id;
    }

    public int getMecsimples_id() {
        return mecsimples_id;
    }

    public void setMecsimples_id(int mecsimples_id) {
        this.mecsimples_id = mecsimples_id;
    }

    public String getArqimage() {
        return arqimage;
    }

    public void setArqimage(String arqimage) {
        this.arqimage = arqimage;
    }
    
    

}
