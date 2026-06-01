/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository;

import br.unipar.eletrofluxo.infraestructure.ConnectionFactory;
import br.unipar.eletrofluxo.model.Cidade;
import br.unipar.eletrofluxo.model.Estado;
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
public class CidadeRepository {
    private static final String INSERT =
        "INSERT INTO cidade (cidade, id_estado) VALUES (?, ?);";

    private static final String UPDATE =
        "UPDATE cidade SET cidade = ?, id_estado = ? "
        + "WHERE id = ?;";

    private static final String DELETE =
        "DELETE FROM cidade WHERE id = ?;";

    private static final String FIND_BY_ID =
        "SELECT id, cidade, id_estado FROM cidade WHERE id = ?;";

    private static final String FIND_ALL =
        "SELECT id, cidade, id_estado FROM cidade ORDER BY cidade;";
    
    public Cidade inserir(Cidade cidade) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, cidade.getCidade());
            pstm.setLong(2, cidade.getEstado().getId());

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                cidade.setId(rs.getLong(1));
            }

        } finally {

            if (conn != null) conn.close();
            if (pstm != null) pstm.close();
            if (rs != null) rs.close();

        }

        return cidade;
    }
    
    public Cidade atualizar(Cidade cidade) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(UPDATE);

            pstm.setString(1, cidade.getCidade());
            pstm.setLong(2, cidade.getEstado().getId());
            pstm.setLong(3, cidade.getId());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return cidade;
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

        public Cidade findById(Long id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Cidade cidade = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_BY_ID);

            pstm.setLong(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {

                cidade = new Cidade();

                cidade.setId(rs.getLong("id"));
                cidade.setCidade(rs.getString("cidade"));

                Estado estado = new Estado();
                estado.setId(rs.getLong("id_estado"));

                cidade.setEstado(estado);
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return cidade;
    }

    public ArrayList<Cidade> listarTodos() throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ArrayList<Cidade> listaCidades = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_ALL);

            rs = pstm.executeQuery();

            while (rs.next()) {

                Cidade cidade = new Cidade();

                cidade.setId(rs.getLong("id"));
                cidade.setCidade(rs.getString("cidade"));

                Estado estado = new Estado();
                estado.setId(rs.getLong("id_estado"));

                cidade.setEstado(estado);

                listaCidades.add(cidade);
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return listaCidades;
    }
        
}
