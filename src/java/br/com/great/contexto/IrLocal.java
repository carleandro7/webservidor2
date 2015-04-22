package br.com.great.contexto;

public class IrLocal extends MecanicaSimples {
    private int irlocal_id, mecsimples_id;
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
    
    public int getMecsimples_id() {
        return mecsimples_id;
    }

    public void setMecsimples_id(int mecsimples_id) {
        this.mecsimples_id = mecsimples_id;
    }

    @Override
    public void executar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

