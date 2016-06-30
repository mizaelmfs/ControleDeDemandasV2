/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samf.app.dao;

import com.samf.app.entity.Demanda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mizael
 */
public class DemandaDao implements IDemandaDao {

    private static final String SQL_SAVE = "insert into Demandas(datas,codigo,concluido,tramitado,"
            + "setor,descricao)"
            + "values(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update Demandas set "
            + "datas = ?, codigo=?, concluido = ?, tramitado = ?, setor = ?, descricao = ? where id = ?";

    private static final String SQL_REMOVE = "delete from Demandas where id=?";

    private static final String SQL_FIND_ALL = "select * from Demandas order by datas desc";

    private static final String SQL_FIND_BUCAR = "select * from demandas where codigo like ? or codigo like ? "
            + "or concluido like ? or tramitado like ? or setor like ? or descricao like ? ";
    
    @Override
    public int save(Demanda demanda) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int retorno = 0;

        java.sql.Date dataSql = new java.sql.Date(demanda.getData().getTime());

        try {
            conn = DBConnection.Conectar();
            pstm = conn.prepareStatement(SQL_SAVE);

            pstm.setDate(1, dataSql);
            pstm.setLong(2, demanda.getCodigo());
            pstm.setString(3, demanda.getConcluido());
            pstm.setString(4, demanda.getTramitado());
            pstm.setString(5, demanda.getSetor());
            pstm.setString(6, demanda.getDescricao());

            retorno = pstm.executeUpdate();

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
            }
        } finally {
            DBConnection.close(conn, pstm, null);
        }
        return retorno;
    }

    @Override
    public int update(Demanda demanda) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int retorno = 0;

        java.sql.Date dataSql = new java.sql.Date(demanda.getData().getTime());

        try {
            conn = DBConnection.Conectar();
            pstm = conn.prepareStatement(SQL_UPDATE);

            pstm.setDate(1, dataSql);
            pstm.setLong(2, demanda.getCodigo());
            pstm.setString(3, demanda.getConcluido());
            pstm.setString(4, demanda.getTramitado());
            pstm.setString(5, demanda.getSetor());
            pstm.setString(6, demanda.getDescricao());
            pstm.setLong(7, demanda.getId());

            retorno = pstm.executeUpdate();

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
            }
        } finally {
            DBConnection.close(conn, pstm, null);
        }
        return retorno;
    }

    @Override
    public int remove(long id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int retorno = 0;

        try {
            conn = DBConnection.Conectar();
            pstm = conn.prepareStatement(SQL_REMOVE);

            pstm.setLong(1, id);
            retorno = pstm.executeUpdate();

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
            }
        } finally {
            DBConnection.close(conn, pstm, null);
        }
        return retorno;
    }

    @Override
    public List<Demanda> findAll() {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Demanda> demandas = new ArrayList<>();
        ResultSet rs = null;

        try {
            conn = DBConnection.Conectar();
            pstm = conn.prepareStatement(SQL_FIND_ALL);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Demanda demanda = new Demanda();
                demanda.setId(rs.getLong("id"));
                demanda.setData(rs.getDate("datas"));
                demanda.setCodigo(rs.getLong("codigo"));
                demanda.setConcluido(rs.getString("concluido"));
                demanda.setTramitado(rs.getString("tramitado"));
                demanda.setSetor(rs.getString("setor"));
                demanda.setDescricao(rs.getString("descricao"));

                demandas.add(demanda);
            }

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
            }
        } finally {
            DBConnection.close(conn, pstm, rs);
        }
        return demandas;
    }

    @Override
    public List<Demanda> findFirst() {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Demanda> demandas = new ArrayList<>();
        ResultSet rs = null;
        int contador = 0;
        try {
            conn = DBConnection.Conectar();
            pstm = conn.prepareStatement(SQL_FIND_ALL);
            rs = pstm.executeQuery();

            while (rs.next() && contador != 20) {
                Demanda demanda = new Demanda();
                demanda.setId(rs.getLong("id"));
                demanda.setData(rs.getDate("datas"));
                demanda.setCodigo(rs.getLong("codigo"));
                demanda.setConcluido(rs.getString("concluido"));
                demanda.setTramitado(rs.getString("tramitado"));
                demanda.setSetor(rs.getString("setor"));
                demanda.setDescricao(rs.getString("descricao"));

                demandas.add(demanda);
                contador++;
            }

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
            }
        } finally {
            DBConnection.close(conn, pstm, rs);
        }
        return demandas;
    }

    @Override
    public List<Demanda> findMonth(int mes, int ano) {
        String SQL_FIND_MONTH = "select * from Demandas where Month(datas)=" + mes + " and year(datas) = " + ano + "";

        Connection conn = null;
        Statement stm = null;
        List<Demanda> demandas = new ArrayList<>();
        ResultSet rs = null;

        try {
            conn = DBConnection.Conectar();
            stm = conn.createStatement();

            rs = stm.executeQuery(SQL_FIND_MONTH);

            while (rs.next()) {
                Demanda demanda = new Demanda();
                demanda.setId(rs.getLong("id"));
                demanda.setData(rs.getDate("datas"));
                demanda.setCodigo(rs.getLong("codigo"));
                demanda.setConcluido(rs.getString("concluido"));
                demanda.setTramitado(rs.getString("tramitado"));
                demanda.setSetor(rs.getString("setor"));
                demanda.setDescricao(rs.getString("descricao"));

                demandas.add(demanda);

            }
            stm.close();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
            }
        } finally {
            DBConnection.close(conn, null, rs);
        }
        return demandas;
    }

    @Override
    public List<Demanda> find(String objeto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Demanda> demandas = new ArrayList<>();
        ResultSet rs = null;

        try {
            conn = DBConnection.Conectar();
            pstm = conn.prepareStatement(SQL_FIND_BUCAR);
            
            pstm.setString(1, objeto +"%");
            pstm.setString(2, objeto +"%");
            pstm.setString(3, objeto +"%");
            pstm.setString(4, objeto +"%");
            pstm.setString(5, objeto +"%");
            pstm.setString(6, objeto +"%");
                 
            rs = pstm.executeQuery();

            while (rs.next()) {
                Demanda demanda = new Demanda();
                demanda.setId(rs.getLong("id"));
                demanda.setData(rs.getDate("datas"));
                demanda.setCodigo(rs.getLong("codigo"));
                demanda.setConcluido(rs.getString("concluido"));
                demanda.setTramitado(rs.getString("tramitado"));
                demanda.setSetor(rs.getString("setor"));
                demanda.setDescricao(rs.getString("descricao"));

                demandas.add(demanda);
            }

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
            }
        } finally {
            DBConnection.close(conn, pstm, rs);
        }
        return demandas;
    }
}
