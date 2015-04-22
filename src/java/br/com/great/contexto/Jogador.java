package br.com.great.contexto;
import java.util.Collection;

public class Jogador {

	private String nome, login, senha, idDispositivo;
        private Posicao posicao;

	private int id;

	private Collection<DadosPersonagem> dadosPersonagem;

	private Collection<Grupo> grupo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Collection<DadosPersonagem> getDadosPersonagem() {
        return dadosPersonagem;
    }

    public void setDadosPersonagem(Collection<DadosPersonagem> dadosPersonagem) {
        this.dadosPersonagem = dadosPersonagem;
    }

    public Collection<Grupo> getGrupo() {
        return grupo;
    }

    public void setGrupo(Collection<Grupo> grupo) {
        this.grupo = grupo;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
    
    public void setLocalizacao(double latitude, double longitude){
        this.posicao.setLatitude(latitude);
        this.posicao.setLongitude(longitude);
    }
    
}
