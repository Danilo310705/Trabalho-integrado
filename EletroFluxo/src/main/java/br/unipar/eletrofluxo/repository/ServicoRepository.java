/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository;

import br.unipar.eletrofluxo.infraestructure.ConnectionFactory;
import br.unipar.eletrofluxo.model.Servico;
import br.unipar.eletrofluxo.repository.interfaces.ServicoRepositoryInterface;
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
public class ServicoRepository implements ServicoRepositoryInterface{
    private static final String INSERT =
        "INSERT INTO servico (nome, descricao, mao_obra) VALUES (?, ?, ?)";

    private static final String UPDATE =
        "UPDATE servico SET nome = ?, descricao = ?, mao_obra = ? WHERE id = ?";

    private static final String DELETE =
        "DELETE FROM servico WHERE id = ?";

    private static final String FIND_BY_ID =
        "SELECT id, nome, descricao, mao_obra FROM servico WHERE id = ?";

    private static final String FIND_ALL =
        "SELECT id, nome, descricao, mao_obra FROM servico ORDER BY nome";
    
    public Servico inserir(Servico servico) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, servico.getNome());
            pstm.setString(2, servico.getDescricao());
            pstm.setDouble(3, servico.getValorServico());

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                servico.setId(rs.getLong(1));
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return servico;
    }
    public Servico atualizar(Servico servico) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(UPDATE);

            pstm.setString(1, servico.getNome());
            pstm.setString(2, servico.getDescricao());
            pstm.setDouble(3, servico.getValorServico());
            pstm.setLong(4, servico.getId());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return servico;
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
    public Servico findById(Long id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Servico servico = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_BY_ID);

            pstm.setLong(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {

                servico = new Servico();

                servico.setId(rs.getLong("id"));
                servico.setNome(rs.getString("nome"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setValorServico(rs.getDouble("mao_obra"));

            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return servico;
    }
    
    public ArrayList<Servico> listarTodos() throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ArrayList<Servico> listaServicos = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_ALL);

            rs = pstm.executeQuery();

            while (rs.next()) {

                Servico servico = new Servico();

                servico.setId(rs.getLong("id"));
                servico.setNome(rs.getString("nome"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setValorServico(rs.getDouble("mao_obra"));

                listaServicos.add(servico);
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return listaServicos;
    }
}
