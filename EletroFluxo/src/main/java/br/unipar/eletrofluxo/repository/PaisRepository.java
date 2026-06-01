/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository;

import br.unipar.eletrofluxo.infraestructure.ConnectionFactory;
import br.unipar.eletrofluxo.model.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class PaisRepository {
    private static final String INSERT =
        "INSERT INTO pais (pais, sigla) VALUES (?, ?)";

    private static final String UPDATE =
        "UPDATE pais SET pais = ?, sigla = ? WHERE id = ?";

    private static final String DELETE =
        "DELETE FROM pais WHERE id = ?";

    private static final String FIND_BY_ID =
        "SELECT id, pais, sigla FROM pais WHERE id = ?";

    private static final String FIND_ALL =
        "SELECT id, pais, sigla FROM pais ORDER BY pais";
    
    public Pais inserir(Pais pais) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(
                    INSERT,
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, pais.getPais());
            pstm.setString(2, pais.getSigla());

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                pais.setId(rs.getLong(1));
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return pais;
    }
    public Pais atualizar(Pais pais) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(UPDATE);

            pstm.setString(1, pais.getPais());
            pstm.setString(2, pais.getSigla());
            pstm.setLong(3, pais.getId());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return pais;
    }
    public void deletar(Long id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(DELETE);

            pstm.setLong(1, id);

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

    }
    public Pais findById(Long id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Pais pais = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_BY_ID);

            pstm.setLong(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {

                pais = new Pais();

                pais.setId(rs.getLong("id"));
                pais.setPais(rs.getString("pais"));
                pais.setSigla(rs.getString("sigla"));

            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return pais;
    }
    public ArrayList<Pais> listarTodos() throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ArrayList<Pais> listaPaises = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_ALL);

            rs = pstm.executeQuery();

            while (rs.next()) {

                Pais pais = new Pais();

                pais.setId(rs.getLong("id"));
                pais.setPais(rs.getString("pais"));
                pais.setSigla(rs.getString("sigla"));

                listaPaises.add(pais);

            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return listaPaises;
    }
}
