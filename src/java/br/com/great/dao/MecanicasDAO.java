/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.dao;

import br.com.great.contexto.CapturarObjeto;
import br.com.great.contexto.DeixarObjeto;
import br.com.great.contexto.Foto;
import br.com.great.contexto.IrLocal;
import br.com.great.contexto.Mecanica;
import br.com.great.contexto.MecanicaComposta;
import br.com.great.contexto.MecanicaSimples;
import br.com.great.contexto.Posicao;
import br.com.great.contexto.Som;
import br.com.great.contexto.Texto;
import br.com.great.contexto.Video;
import br.com.great.contexto.VisualizarObjeto;
import br.com.great.factory.ConnectionFactory;
import br.com.great.gerenciamento.PlayJogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado
 * com entidade mecanica
 *
 * @author carleandro
 */
public class MecanicasDAO extends ConnectionFactory {

    private static MecanicasDAO instance;

    /**
     *
     * Método responsável por criar uma instancia da classe MecanicasDAO
     * (Singleton)
     *
     * @return static
     * @author Carleandro Noleto
     * @since 10/12/2014
     * @version 1.0
     */
    public static MecanicasDAO getInstance() {
        if (instance == null) {
            instance = new MecanicasDAO();
        }
        return instance;
    }

    /**
     *
     * Método responsável por listar todos as Mecsimples das missoes de um jogo
     * do banco
     *
     * @param missao_id String
     * @param mecanicas
     * @return JSONArray
     * @author Carleandro Noleto
     * @since 10/12/2014
     * @version 1.0
     */
    public ArrayList<Mecanica> getMecCompostaMissao(int missao_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conexao = criarConexao();
        ArrayList<Mecanica> mecanicas = new ArrayList<Mecanica>();
        try {
            String sql = "SELECT * FROM mecanica "
                    + " LEFT JOIN meccomposta ON "
                    + " (meccomposta.mecanica_id = mecanica.id) "
                    + " WHERE mecanica.missoes_id = " + missao_id + " AND mecanica.tipo = 1 "
                    + " HAVING 0=(SELECT count(*) from composta "
                    + " where composta.mecanica_id = mecanica.id)";

            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MecanicaComposta mecComposta = new MecanicaComposta();
                mecComposta.setMeccomposta_id(rs.getInt("meccomposta.id"));
                mecComposta.setNome(rs.getString("mecanica.nome"));
                mecComposta.setTipo(rs.getInt("mecanica.tipo"));
                mecComposta.setMecanica(getMecComposta(rs.getInt("meccomposta.id")));
                mecComposta.setId(rs.getInt("mecanica.id"));
                mecanicas.add(mecComposta);
                PlayJogo.getMecCompostas().add(mecComposta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar todas as mecanicas: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return mecanicas;
    }

    /**
     *
     * Método responsável por listar todos as Mecsimples das missoes de um jogo
     * do banco
     *
     * @param meccomposta_id String
     * @return JSONArray
     * @author Carleandro Noleto
     * @since 10/12/2014
     * @version 1.0
     */
    public ArrayList<Mecanica> getMecComposta(int meccomposta_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Mecanica> mecanicas = new ArrayList<Mecanica>();
        Connection conexao = criarConexao();
        try {
            String sql = "SELECT * FROM composta "
                    + " LEFT JOIN mecanica ON (mecanica.id = composta.mecanica_id) "
                    + " LEFT JOIN mecsimples ON (mecsimples.mecanica_id = mecanica.id) "
                    + " WHERE composta.meccomposta_id = " + meccomposta_id + " AND mecanica.tipo = 0";

            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MecanicaSimples mecSimples = null;
                String tipo = rs.getString("mecsimples.tipo");
                if (tipo.equals("vtextos") || tipo.equals("ctextos") || tipo.equals("dtextos")) {
                    mecSimples = mecTexto(rs, tipo);
                } else if (tipo.equals("vfotos") || tipo.equals("cfotos") || tipo.equals("dfotos")) {
                    mecSimples = mecFotos(rs, tipo);
                } else if (tipo.equals("vvideos") || tipo.equals("cvideos") || tipo.equals("dvideos")) {
                    mecSimples = mecVideos(rs, tipo);
                } else if (tipo.equals("vsons") || tipo.equals("csons") || tipo.equals("dsons")) {
                    mecSimples = mecSons(rs, tipo);
                } else if (tipo.equals("irlocais")) {
                    IrLocal irlocal = getIrLocal(rs, new IrLocaisDAO().getMecIrLocais(rs.getInt("mecsimples.id")));
                    mecSimples = irlocal;
                }

                if (mecSimples != null) {
                    mecanicas.add(mecSimples);
                    PlayJogo.getMecSimples().add(mecSimples);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar todas as mecanicas: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return mecanicas;
    }

    /**
     *
     * Método responsável por listar todos as Mecsimples das missoes de um jogo
     * do banco
     *
     * @param missao_id String
     * @param mecanicas
     * @return JSONArray
     * @author Carleandro Noleto
     * @since 10/12/2014
     * @version 1.0
     */
    public ArrayList<Mecanica> getMecSimplesMissao(int missao_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conexao = criarConexao();
        ArrayList<Mecanica> mecanicas = new ArrayList<Mecanica>();
        try {
            String sql = "SELECT * FROM mecanica LEFT JOIN mecsimples ON "
                    + " (mecsimples.mecanica_id = mecanica.id) "
                    + " WHERE mecanica.missoes_id = " + missao_id + " AND mecanica.tipo = 0 "
                    + "HAVING 0=(SELECT count(*) from composta "
                    + " where composta.mecanica_id = mecanica.id)";

            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MecanicaSimples mecSimples = null;
                String tipo = rs.getString("mecsimples.tipo");
                if (tipo.equals("vtextos") || tipo.equals("ctextos") || tipo.equals("dtextos")) {
                    mecSimples = mecTexto(rs, tipo);
                } else if (tipo.equals("vfotos") || tipo.equals("cfotos") || tipo.equals("dfotos")) {
                    mecSimples = mecFotos(rs, tipo);
                } else if (tipo.equals("vvideos") || tipo.equals("cvideos") || tipo.equals("dvideos")) {
                    mecSimples = mecVideos(rs, tipo);
                } else if (tipo.equals("vsons") || tipo.equals("csons") || tipo.equals("dsons")) {
                    mecSimples = mecSons(rs, tipo);
                } else if (tipo.equals("irlocais")) {
                    IrLocal irlocal = getIrLocal(rs, new IrLocaisDAO().getMecIrLocais(rs.getInt("mecsimples.id")));
                    mecSimples = irlocal;
                }

                if (mecSimples != null) {
                    mecanicas.add(mecSimples);
                    PlayJogo.getMecSimples().add(mecSimples);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar todas as mecanicas: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return mecanicas;
    }

    private MecanicaSimples mecTexto(ResultSet rs, String tipo) throws SQLException {
        Texto texto = new TextosDAO().getMecCTextos(rs.getInt("mecsimples.id"));
        MecanicaSimples mecSimples = getTipo(rs, tipo);
        mecSimples.setObjeto(texto);
        return mecSimples;
    }

    private MecanicaSimples mecFotos(ResultSet rs, String tipo) throws SQLException {
        Foto foto = new FotosDAO().getMecCFotos(rs.getInt("mecsimples.id"));
        MecanicaSimples mecSimples = getTipo(rs, tipo);
        mecSimples.setObjeto(foto);
        return mecSimples;
    }

    private MecanicaSimples mecVideos(ResultSet rs, String tipo) throws SQLException {
        Video video = new VideosDAO().getMecCVideos(rs.getInt("mecsimples.id"));
        MecanicaSimples mecSimples = getTipo(rs, tipo);
        mecSimples.setObjeto(video);
        return mecSimples;
    }

    private MecanicaSimples mecSons(ResultSet rs, String tipo) throws SQLException {
        Som som = new SonsDAO().getMecCSons(rs.getInt("mecsimples.id"));
        MecanicaSimples mecSimples = getTipo(rs, tipo);
        mecSimples.setObjeto(som);
        return mecSimples;
    }

    private MecanicaSimples getTipo(ResultSet rs, String tipo) {
        MecanicaSimples mecSimples = null;
        switch (tipo.charAt(0)) {
            case 'v':
                mecSimples = getVisualizarObjeto(rs);
                break;
            case 'c':
                mecSimples = getCapturarObjeto(rs);
                break;
            case 'd':
                mecSimples = getDeixarObjeto(rs);
                break;
        }
        return mecSimples;
    }

    private CapturarObjeto getCapturarObjeto(ResultSet rs) {
        CapturarObjeto capturarObjeto = new CapturarObjeto();
        try {
            capturarObjeto.setId(rs.getInt("mecanica.id"));
            capturarObjeto.setNome(rs.getString("mecanica.nome"));
            capturarObjeto.setTipo(rs.getInt("mecanica.tipo"));
            capturarObjeto.setMecsimples_id(rs.getInt("mecsimples.id"));
            capturarObjeto.setTempo(rs.getTime("mecsimples.tempo"));
            capturarObjeto.setTiposimples(rs.getString("mecsimples.tipo"));
            capturarObjeto.setVisivel(rs.getInt("mecsimples.visivel"));
            capturarObjeto.setVibrar(rs.getInt("mecsimples.vibrar"));
            capturarObjeto.setLife(rs.getInt("mecsimples.life"));
        } catch (SQLException ex) {
            Logger.getLogger(MecanicasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return capturarObjeto;
    }

    private VisualizarObjeto getVisualizarObjeto(ResultSet rs) {
        VisualizarObjeto visualizarObjeto = new VisualizarObjeto();
        try {
            visualizarObjeto.setId(rs.getInt("mecanica.id"));
            visualizarObjeto.setNome(rs.getString("mecanica.nome"));
            visualizarObjeto.setTipo(rs.getInt("mecanica.tipo"));
            visualizarObjeto.setMecsimples_id(rs.getInt("mecsimples.id"));
            visualizarObjeto.setTempo(rs.getTime("mecsimples.tempo"));
            visualizarObjeto.setTiposimples(rs.getString("mecsimples.tipo"));
            visualizarObjeto.setVisivel(rs.getInt("mecsimples.visivel"));
            visualizarObjeto.setVibrar(rs.getInt("mecsimples.vibrar"));
            visualizarObjeto.setLife(rs.getInt("mecsimples.life"));
        } catch (SQLException ex) {
            Logger.getLogger(MecanicasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return visualizarObjeto;
    }

    private DeixarObjeto getDeixarObjeto(ResultSet rs) {
        DeixarObjeto deixarObjeto = new DeixarObjeto();
        try {
            deixarObjeto.setId(rs.getInt("mecanica.id"));
            deixarObjeto.setNome(rs.getString("mecanica.nome"));
            deixarObjeto.setTipo(rs.getInt("mecanica.tipo"));
            deixarObjeto.setMecsimples_id(rs.getInt("mecsimples.id"));
            deixarObjeto.setTempo(rs.getTime("mecsimples.tempo"));
            deixarObjeto.setTiposimples(rs.getString("mecsimples.tipo"));
            deixarObjeto.setVibrar(rs.getInt("mecsimples.vibrar"));
            deixarObjeto.setLife(rs.getInt("mecsimples.life"));
        } catch (SQLException ex) {
            Logger.getLogger(MecanicasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deixarObjeto;
    }

    private IrLocal getIrLocal(ResultSet rs, IrLocal irLocal) {
        try {
            irLocal.setId(rs.getInt("mecanica.id"));
            irLocal.setNome(rs.getString("mecanica.nome"));
            irLocal.setTipo(rs.getInt("mecanica.tipo"));
            irLocal.setMecsimples_id(rs.getInt("mecsimples.id"));
            irLocal.setTempo(rs.getTime("mecsimples.tempo"));
            irLocal.setTiposimples(rs.getString("mecsimples.tipo"));
            irLocal.setVisivel(rs.getInt("mecsimples.visivel"));
            irLocal.setLife(rs.getInt("mecsimples.life"));
        } catch (SQLException ex) {
            Logger.getLogger(MecanicasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return irLocal;
    }

    public ArrayList<Mecanica> mecanicaReq(Mecanica mecanica, ArrayList<Mecanica> mecanicas) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conexao = criarConexao();
        ArrayList<Mecanica> mecanicas_requisitos = new ArrayList<Mecanica>();
        try {
            String sql = "SELECT * FROM reqmecanica WHERE reqmecanica.mecanica_id = " + mecanica.getId();
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int req_id = rs.getInt("req_id");
                mecanicas_requisitos.add(getMecanica(req_id, mecanicas));
            }
        } catch (SQLException e) {
            System.out.println("Erro no getReqRequisitos: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
        }
        return mecanicas_requisitos;
    }

    private Mecanica getMecanica(int missao_id, ArrayList<Mecanica> mecanicas) {
        for (int i = 0; i < mecanicas.size(); i++) {
            if (mecanicas.get(i).getId() == missao_id) {
                return mecanicas.get(i);
            }
        }
        return null;
    }

}
