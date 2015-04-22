/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.dao;

import br.com.great.contexto.Bloqueado;
import br.com.great.contexto.Mecanica;
import br.com.great.contexto.MecanicaComposta;
import br.com.great.contexto.Missao;
import br.com.great.contexto.Posicao;
import br.com.great.contexto.Pronto;
import br.com.great.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado
 * com entidade missoes
 *
 * @author carleandro
 */
public class MissoesDAO extends ConnectionFactory {

    private static MissoesDAO instance;

    /**
     *
     * Método responsável por criar uma instancia da classe MissoesDAO
     * (Singleton)
     *
     * @return static
     * @author Carleandro Noleto
     * @since 10/12/2014
     * @version 1.0
     */
    public static MissoesDAO getInstance() {
        if (instance == null) {
            instance = new MissoesDAO();
        }
        return instance;
    }

    public ArrayList<Missao> getMissaoJogo(int jogoConfiguracao) {
        ArrayList<Missao> missoes = new ArrayList<Missao>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conexao = criarConexao();
        try {
            String sql = "SELECT * FROM confimissao_has_missoes "
                    + " LEFT JOIN missoes on (missoes.id = confimissao_has_missoes.missoes_id) "
                    + " WHERE confimissao_has_missoes.confimissao_id = " + jogoConfiguracao;
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Missao missao = new Missao();
                missao.setId(rs.getInt("missoes.id"));
                missao.setNome(rs.getString("nome"));
                missao.setPosicaoInicial(new Posicao(rs.getDouble("latitude"), rs.getDouble("longitude")));
                ArrayList<Mecanica> mecanicas = new ArrayList<Mecanica>();
                mecanicas.addAll(MecanicasDAO.getInstance().getMecSimplesMissao(rs.getInt("missoes.id")));
                mecanicas.addAll(MecanicasDAO.getInstance().getMecCompostaMissao(rs.getInt("missoes.id")));
                missao.setMecanicas(mecanicas);
                missoes.add(missao);
            }
        } catch (SQLException e) {
            System.out.println("Erro no getMissaoJogo: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        //carrega os requisitos de cada missao
        carregaRequisitos(missoes);
        return missoes;
    }

    private void carregaRequisitos(ArrayList<Missao> missoes) {
        for (int i = 0; i < missoes.size(); i++) {
            missoes.get(i).setReqMissao(carregaRequisitosMissoes(missoes.get(i).getId(), missoes));
            for (int j = 0; j < missoes.get(i).getMecanicas().size(); j++) {
                if (missoes.get(i).getMecanicas().get(j).getTipo() == 0) {
                    missoes.get(i).getMecanicas().get(j).setReqMecanicas(MecanicasDAO.getInstance().mecanicaReq(
                            missoes.get(i).getMecanicas().get(j), missoes.get(i).getMecanicas()));
                } else if (missoes.get(i).getMecanicas().get(j).getTipo() == 1) {
                    missoes.get(i).getMecanicas().get(j).setReqMecanicas(MecanicasDAO.getInstance().mecanicaReq(
                            missoes.get(i).getMecanicas().get(j), missoes.get(i).getMecanicas()));
                    ((MecanicaComposta) missoes.get(i).getMecanicas().get(j)).setMecanica(preecherReqMecanicas(missoes.get(i).getMecanicas().get(j), missoes.get(i).getMecanicas()));
                }

                if (missoes.get(i).getMecanicas().get(j).getReqMecanicas().isEmpty()) {
                    missoes.get(i).getMecanicas().get(j).setEstado(new Pronto());
                } else {
                    missoes.get(i).getMecanicas().get(j).setEstado(new Bloqueado());
                }
            }
        }
    }

    private ArrayList<Mecanica> preecherReqMecanicas(Mecanica mecanica, ArrayList<Mecanica> mecanicas) {
        ArrayList<Mecanica> mecanicasAux = ((MecanicaComposta) mecanica).getMecanica();
        for (int i = 0; i < mecanicasAux.size(); i++) {
            if (mecanicasAux.get(i).getTipo() == 0) {
                mecanicasAux.get(i).setReqMecanicas(MecanicasDAO.getInstance().mecanicaReq(
                        mecanicasAux.get(i), mecanicasAux));
                if (mecanicasAux.get(i).getReqMecanicas().isEmpty()) {
                    mecanicasAux.get(i).setEstado(new Pronto());
                } else {
                    mecanicasAux.get(i).setEstado(new Bloqueado());
                }
            } else if (mecanicasAux.get(i).getTipo() == 1) {
                preecherReqMecanicas(mecanicasAux.get(i), mecanicas);
            }

        }
        return mecanicasAux;
    }

    private ArrayList<Missao> carregaRequisitosMissoes(int missao_id_requisitos, ArrayList<Missao> missoes) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conexao = criarConexao();
        ArrayList<Missao> missoes_requisitos = new ArrayList<Missao>();
        try {
            String sql = "SELECT * FROM reqmissao WHERE reqmissao.missoes_id = " + missao_id_requisitos;
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int req_id = rs.getInt("req_id");
                missoes_requisitos.add(getMissao(req_id, missoes));
            }
        } catch (SQLException e) {
            System.out.println("Erro no getReqMissao: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return missoes_requisitos;
    }

    private Missao getMissao(int missao_id, ArrayList<Missao> missoes) {
        for (int i = 0; i < missoes.size(); i++) {
            if (missoes.get(i).getId() == missao_id) {
                return missoes.get(i);
            }
        }
        return null;
    }

}
