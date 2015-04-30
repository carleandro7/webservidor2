package br.com.great.contexto;
import br.com.great.dao.EngajamentoDAO;
import java.util.ArrayList;

public class ConfiguracaoMissao {

	private ArrayList<Missao> missao;

	private ArrayList<Grupo> grupo;

	private Jogo jogo;

    public ArrayList<Missao> getMissao() {
        return missao;
    }

    public void setMissao(ArrayList<Missao> missao) {
        this.missao = missao;
    }

    public ArrayList<Grupo> getGrupo() {
        if(grupo == null){
            grupo = new ArrayList<Grupo>();
        }
        return grupo;
    }

    public void setGrupo(ArrayList<Grupo> grupo) {
        this.grupo = grupo;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
        
    public boolean getGrupo(int grupo_id){
        for(int i=0;i<grupo.size();i++){
            if(grupo.get(i).getId() == grupo_id){
                return true;
            }
        }
        return false;
    }
    
    public Grupo getGrupoLista(int grupo_id){
        for(int i=0;i<grupo.size();i++){
            if(grupo.get(i).getId() == grupo_id){
                return grupo.get(i);
            }
        }
        return null;
    }

    
    public ArrayList<Mecanica> getMecanicasALL(Grupo grupo, boolean reqMecanica){
        ArrayList<Mecanica> mecanicasAux = new ArrayList<Mecanica>();
        for(int i=0;i<missao.size();i++){
             Engajamento engajamento = EngajamentoDAO.getInstance().getEngajamento(missao.get(i), grupo);
             mecanicasAux.addAll(missao.get(i).getMecanicasALL(engajamento.getEstado(), reqMecanica));
        }
        return mecanicasAux;
    }

}
