/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.dao;

import br.com.great.contexto.Grupo;
import br.com.great.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado
 * com entidade Grupo
 *
 * @author carleandro
 */
public class GruposDAO extends ConnectionFactory {

    private static GruposDAO instance;

    /**
     *
     * Método responsável por criar uma instancia da classe GruposDAO
     * (Singleton)
     *
     * @return static
     * @author Carleandro Noleto
     * @since 10/12/2014
     * @version 1.0
     */
    public static GruposDAO getInstance() {
        if (instance == null) {
            instance = new GruposDAO();
        }
        return instance;
    }

    public ArrayList<Grupo> getGruposJogo(int jogoConfiguracao) {
        ArrayList<Grupo> grupos = new ArrayList<Grupo>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conexao = criarConexao();
        try {
            String sql = "SELECT * FROM grupos_has_confimissao "
                    + " LEFT JOIN grupos on (grupos.id = grupos_has_confimissao.grupos_id) "
                    + " WHERE grupos_has_confimissao.confimissao_id = " + jogoConfiguracao;
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNome(rs.getString("nome"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            System.out.println("Erro no getGrupoJogo: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return grupos;
    }

}
