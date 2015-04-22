/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.dao;

import br.com.great.contexto.Jogador;
import br.com.great.contexto.Posicao;
import br.com.great.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado
 * com entidade jogadores
 *
 * @author carleandro
 */
public class JogadoresDAO extends ConnectionFactory {

    private static JogadoresDAO instance;

    /**
     *
     * Método responsável por criar uma instancia da classe JogadoresDAO
     * (Singleton)
     *
     * @return Static
     * @author Carleandro Noleto
     * @since 27/12/2014
     * @version 1.0
     */
    public static JogadoresDAO getInstance() {
        if (instance == null) {
            instance = new JogadoresDAO();
        }
        return instance;
    }

    /**
     *
     * Método responsável por listar todos os jogadores do banco
     *
     * @return ArrayList do tipo Jogador
     * @author Carleandro Noleto
     * @since 27/12/2014
     * @version 1.0
     */
    public ArrayList<Jogador> listarTodos() {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Jogador> jogadores = null;

        conexao = criarConexao();

        try {
            jogadores = new ArrayList<Jogador>();
            pstmt = conexao.prepareStatement("select * from jogadores order by id");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Jogador jogador = new Jogador();

                jogador.setId(rs.getInt("id"));
                jogador.setLogin(rs.getString("email"));
                jogador.setSenha(rs.getString("password"));
                jogador.setIdDispositivo(rs.getString("iddispositivo"));
                jogador.setPosicao(new Posicao(0, 0));
                jogadores.add(jogador);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os jogadores: " + e);
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return jogadores;
    }

    /**
     *
     * Método responsável por fazer o login do jogador
     *
     * @param email String
     * @param password String
     * @return Jogador Dados do jogador
     * @author Carleandro Noleto
     * @since 27/11/2014
     * @version 1.0
     */
    public Jogador login(String email, String password) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conexao = criarConexao();
        Jogador jogador = new Jogador();
        try {

            pstmt = conexao.prepareStatement("select * from jogadores where email ='" + email + "' AND password= '" + password + "'");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                jogador.setId(rs.getInt("id"));
                jogador.setLogin(rs.getString("email"));
                jogador.setSenha(rs.getString("password"));
                jogador.setIdDispositivo(rs.getString("iddispositivo"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao realizar login: " + e);
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return jogador;
    }

    /**
     *
     * Método responsável por get em todos os dados do jogador
     *
     * @param id String
     * @return Jogadores Dados do jogador
     * @author Carleandro Noleto
     * @since 27/11/2014
     * @version 1.0
     */
    public Jogador getJogador(int id) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Jogador jogador = null;
        conexao = criarConexao();
        try {
            pstmt = conexao.prepareStatement("select * from jogadores where id = " + id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                jogador = new Jogador();

                jogador.setId(rs.getInt("id"));
                jogador.setLogin(rs.getString("email"));
                jogador.setSenha(rs.getString("password"));
                jogador.setIdDispositivo(rs.getString("iddispositivo"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar dados de um jogador: " + e);
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return jogador;
    }

    /**
     * Método responsável cadastrar um jogador
     *
     * @param email String
     * @param password String
     * @return String Mensagem true ou false
     * @author Carleandro Noleto
     * @since 27/11/2014
     * @version 1.0
     */
    public int cadastrar(String email, String password) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Jogador jogador = null;
        int result =0;
        try {
            conexao = criarConexao();
            pstmt = conexao.prepareStatement("select * from jogadores where email='" + email + "'");
            rs = pstmt.executeQuery();
            if (rs.next()) {//existi pelo menos um registro nessa lista
                result = -1;
            } else {
                String sql = "INSERT INTO  jogadores(`email` ,`password`) VALUES (?, ?)";
                pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                rs.next();
                result = (int) rs.getLong(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar jogador" + e);
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return result;
    }

    /**
     * Metodo responsavel por verificar e alterar se o usuario estiver com outro
     * dispositivo
     *
     * @param jogador_id id do jogador
     * @param device_id id que idencifica o dispositivo atraves de msg GCM
     * @return String Mensagem tru e false
     */
    public boolean registroDevice(int jogador_id, String device_id) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean mensagem = false;
        conexao = criarConexao();
        try {
            pstmt = conexao.prepareStatement("UPDATE jogadores SET iddispositivo ='" + device_id + "' WHERE id = " + jogador_id);
            pstmt.executeUpdate();
            mensagem = true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar deviceRegsID dos jogadores: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return mensagem;
    }

}
