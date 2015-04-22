package br.com.great.contexto;

public class CapturarObjeto extends MecanicaSimples {


    private int jogador_id;
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

    @Override
    public void executar() {
       
    }

}

