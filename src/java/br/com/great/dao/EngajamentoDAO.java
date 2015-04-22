/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.dao;

import br.com.great.contexto.Bloqueado;
import br.com.great.contexto.ConfiguracaoMissao;
import br.com.great.contexto.Engajamento;
import br.com.great.contexto.Grupo;
import br.com.great.contexto.Missao;
import br.com.great.contexto.Pronto;
import br.com.great.factory.ConnectionFactory;
import br.com.great.gerenciamento.PlayJogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carleandro
 */
public class EngajamentoDAO extends ConnectionFactory {

    private static EngajamentoDAO instance;

    /**
     *
     * Método responsável por criar uma instancia da classe EngajamentoDAO
     * (Singleton)
     *
     * @author Carleandro Noleto
     * @return static
     * @since 14/01/2015
     * @version 1.0
     */
    public static EngajamentoDAO getInstance() {
        if (instance == null) {
            instance = new EngajamentoDAO();
        }
        return instance;
    }

    public int getEngamentoEstado(int grupo_id, int missao_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conexao = criarConexao();
        try {
            String sql = "SELECT * FROM engajamento WHERE engajamento.missoes_id=" + missao_id + " AND engajamento.grupos_id=" + grupo_id;
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("engajamento.estado");
            }
        } catch (SQLException e) {
            System.out.println("Erro em getEngamentoEstado : " + e);
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }

        return -1;
    }
    
   public Engajamento getEngajamento(Missao missao, Grupo grupo){
       for(int i=0; i<PlayJogo.getEngajamento().size(); i++){
           if(PlayJogo.getEngajamento().get(i).getMissao() == missao && PlayJogo.getEngajamento().get(i).getGrupo() == grupo){
               return PlayJogo.getEngajamento().get(i);
           }
       }
       return null;
   } 
   public void setEngajamento(ConfiguracaoMissao configMissao){
        for(int i=0; i<configMissao.getGrupo().size();i++){
            for(int j=0; j<configMissao.getMissao().size();j++){
                Engajamento e = new Engajamento();
                e.setGrupo(configMissao.getGrupo().get(i));
                e.setMissao(configMissao.getMissao().get(j));
                if(configMissao.getMissao().get(j).getReqMissao().isEmpty()){
                    setEngajamento(0, configMissao.getMissao().get(j).getId(), configMissao.getGrupo().get(i).getId());    
                    e.setEstado(new Pronto());
                }else{
                    setEngajamento(2, configMissao.getMissao().get(j).getId(), configMissao.getGrupo().get(i).getId());
                    e.setEstado(new Bloqueado());
                }
                PlayJogo.getEngajamento().add(e);
            }
        }
    }

    private boolean setEngajamento(int estado, int missao_id, int grupo_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conexao = criarConexao();
        try {
            String sql = "INSERT INTO engajamento (estado, missoes_id, grupos_id) VALUES ("+estado+", "+missao_id+", "+grupo_id+")";
            System.out.println(sql);
            pstmt = conexao.prepareStatement(sql);
            pstmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar engajamento : " + e);
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return false;
    }

}
