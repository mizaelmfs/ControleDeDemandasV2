/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samf.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mizael
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_controle_demanda?useSSL=false";
    private static final String DRIVER_CLASS_SQLMYSQL = "com.mysql.jdbc.Driver";
    private static final String LOGIN = "root";
    private static final String SENHA = "";

    public static Connection Conectar() {

        try {
            Class.forName(DRIVER_CLASS_SQLMYSQL);
            return DriverManager.getConnection(URL, LOGIN, SENHA);
        } catch (ClassNotFoundException e) {
             JOptionPane.showMessageDialog(null, "Ocorreu um erro ao abrir a conexão");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void close(Connection conn, PreparedStatement pstm, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }

            if (pstm != null) {
                pstm.close();
            }

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao fechar conexão");
        }
    }
}
