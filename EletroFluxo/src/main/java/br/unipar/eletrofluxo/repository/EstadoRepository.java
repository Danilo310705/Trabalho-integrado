/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository;

import br.unipar.eletrofluxo.infraestructure.ConnectionFactory;
import br.unipar.eletrofluxo.model.Estado;
import br.unipar.eletrofluxo.model.Pais;
import br.unipar.eletrofluxo.repository.interfaces.EstadoRepositoryInterface;
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
public class EstadoRepository implements EstadoRepositoryInterface{
    private static final String INSERT =
        "INSERT INTO estado (estado, sigla, id_pais) VALUES (?, ?, ?)";

    private static final String UPDATE =
        "UPDATE estado SET estado = ?, sigla = ?, id_pais = ? WHERE id = ?";

    private static final String DELETE =
        "DELETE FROM estado WHERE id = ?";

    private static final String FIND_BY_ID =
        "SELECT id, estado, sigla, id_pais FROM estado WHERE id = ?";

    private static final String FIND_ALL =
        "SELECT id, estado, sigla, id_pais FROM estado ORDER BY estado";    
    
    public Estado inserir(Estado estado) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, estado.getEstado());
            pstm.setString(2, estado.getSigla());
            pstm.setLong(3, estado.getPais().getId());

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                estado.setId(rs.getLong(1));
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return estado;
    }
    
    public Estado atualizar(Estado estado) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(UPDATE);

            pstm.setString(1, estado.getEstado());
            pstm.setString(2, estado.getSigla());
            pstm.setLong(3, estado.getPais().getId());
            pstm.setLong(4, estado.getId());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return estado;
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
    
    public Estado findById(Long id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Estado estado = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_BY_ID);

            pstm.setLong(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {

                estado = new Estado();

                estado.setId(rs.getLong("id"));
                estado.setEstado(rs.getString("estado"));
                estado.setSigla(rs.getString("sigla"));

                Pais pais = new Pais();
                pais.setId(rs.getLong("id_pais"));

                estado.setPais(pais);
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return estado;
    }
    
    public ArrayList<Estado> listarTodos() throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ArrayList<Estado> listaEstados = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_ALL);

            rs = pstm.executeQuery();

            while (rs.next()) {

                Estado estado = new Estado();

                estado.setId(rs.getLong("id"));
                estado.setEstado(rs.getString("estado"));
                estado.setSigla(rs.getString("sigla"));

                Pais pais = new Pais();
                pais.setId(rs.getLong("id_pais"));

                estado.setPais(pais);

                listaEstados.add(estado);
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return listaEstados;
    }
}
