package br.com.great.contexto;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class CapturarObjeto extends MecanicaSimples {

    private int jogador_id, addMecJogador = 0;
    private Posicao posicao;

    public int getJogador_id() {
        return jogador_id;
    }

    public void setJogador_id(int jogador_id) {
        this.jogador_id = jogador_id;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public int getAddMecJogador() {
        return addMecJogador;
    }

    public void setAddMecJogador(int addMecJogador) {
        this.addMecJogador = addMecJogador;
    }

    @Override
    public Objeto executar(JSONObject jObj) {
        try {
            int jogador = jObj.getInt("jogador_id");
            double latitude = jObj.getDouble("latitude");
            double longitude = jObj.getDouble("longitude");
            this.posicao = new Posicao(latitude, longitude);
            this.jogador_id = jogador;
            boolean exeSucesso = false;
            if (objeto instanceof Foto) {
                exeSucesso = capFoto(jObj);
            } else if (objeto instanceof Som) {
                exeSucesso = capSom(jObj);
            } else if (objeto instanceof Video) {
                exeSucesso = capVideo(jObj);
            } else if (objeto instanceof Texto) {
                exeSucesso = capTexto(jObj);
            }
            if (exeSucesso) {
                return objeto;
            }
        } catch (JSONException ex) {
            Logger.getLogger(CapturarObjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private boolean capTexto(JSONObject jObj) {
        try {
            ((Texto) objeto).setTexto(jObj.getString("texto"));
            return true;
        } catch (JSONException ex) {
            Logger.getLogger(CapturarObjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean capFoto(JSONObject jObj) {
        try {
            ((Foto) objeto).setArqimage(jObj.getString("arquivo"));
            return true;
        } catch (JSONException ex) {
            Logger.getLogger(CapturarObjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean capVideo(JSONObject jObj) {
        try {
            ((Video) objeto).setArqVideo(jObj.getString("arquivo"));
            return true;
        } catch (JSONException ex) {
            Logger.getLogger(CapturarObjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean capSom(JSONObject jObj) {
        try {
            ((Som) objeto).setArqSom(jObj.getString("arquivo"));
            return true;
        } catch (JSONException ex) {
            Logger.getLogger(CapturarObjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
