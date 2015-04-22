/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.dao;

import br.com.great.contexto.ConfiguracaoMissao;
import br.com.great.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author carleandro
 */
public class ConfiMissaoDAO extends ConnectionFactory {

    private static ConfiMissaoDAO instance;

    /**
     *
     * Método responsável por criar uma instancia da classe ConfiMissaoDAO
     * (Singleton)
     *
     * @author Carleandro Noleto
     * @return static
     * @since 14/01/2015
     * @version 1.0
     */
    public static ConfiMissaoDAO getInstance() {
        if (instance == null) {
            instance = new ConfiMissaoDAO();
        }
        return instance;
    }

    public ArrayList<ConfiguracaoMissao> getConfiMissao(int jogo_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conexao = criarConexao();
        ArrayList<ConfiguracaoMissao> confiMissoes = new ArrayList<ConfiguracaoMissao>();
        try {
            String sql = "select * from confimissao where confimissao.jogos_id =" + jogo_id;
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ConfiguracaoMissao confiMissao = new ConfiguracaoMissao();
                confiMissao.setGrupo(GruposDAO.getInstance().getGruposJogo(rs.getInt("id")));
                confiMissao.setMissao(MissoesDAO.getInstance().getMissaoJogo(rs.getInt("id")));
                EngajamentoDAO.getInstance().setEngajamento(confiMissao);
                confiMissoes.add(confiMissao);
            }
        } catch (SQLException e) {
            System.out.println("Erro em configMissao : " + e);
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }

        return confiMissoes;
    }
   
}
