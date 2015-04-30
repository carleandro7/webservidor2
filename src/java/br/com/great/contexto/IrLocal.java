package br.com.great.contexto;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class IrLocal extends MecanicaSimples {
    private int irlocal_id, jogador_id;
    private Posicao posicao;

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public int getIrlocal_id() {
        return irlocal_id;
    }

    public void setIrlocal_id(int irlocal_id) {
        this.irlocal_id = irlocal_id;
    }

    public int getJogador_id() {
        return jogador_id;
    }

    public void setJogador_id(int jogador_id) {
        this.jogador_id = jogador_id;
    }


    @Override
    public Objeto executar(JSONObject jObj) {
        try {
            int jogador = jObj.getInt("jogador_id");
            double latitude = Double.valueOf(jObj.getString("latitude"));
            double longitude = Double.valueOf(jObj.getString("longitude"));
            this.posicao = new Posicao(latitude, longitude);
            this.jogador_id = jogador;
            return objeto;
        } catch (JSONException ex) {
            Logger.getLogger(CapturarObjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

