/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository;

import br.unipar.eletrofluxo.enums.StatusOsEnum;
import br.unipar.eletrofluxo.enums.ZonaEnum;
import br.unipar.eletrofluxo.infraestructure.ConnectionFactory;
import br.unipar.eletrofluxo.repository.interfaces.OsRepositoryInterface;
import br.unipar.eletrofluxo.model.Os;
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
public class OsRepository implements OsRepositoryInterface{
    private static final String INSERT =
        "INSERT INTO os (descricao, status, data_abertura, data_fechamento, "
        + "valor_total, zona, id_cliente, id_endereco) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE =
        "UPDATE os SET descricao = ?, status = ?, "
        + "data_abertura = ?, data_fechamento = ?, "
        + "valor_total = ?, zona = ?, id_cliente = ?, "
        + "id_endereco = ? WHERE id = ?";

    private static final String DELETE =
        "DELETE FROM os WHERE id = ?";

    private static final String FIND_BY_ID =
        "SELECT * FROM os WHERE id = ?";

    private static final String FIND_ALL =
        "SELECT * FROM os ORDER BY id";
    
    public Os inserir(Os os) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(
                    INSERT,
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, os.getDescricao());
            pstm.setString(2, os.getStatus().toString());
            pstm.setDate(3,
                    new java.sql.Date(
                            os.getDataAbertura().getTime()));

            pstm.setDate(4,
                    new java.sql.Date(
                            os.getDataTermino().getTime()));

            pstm.setDouble(5, os.getTotalOs());

            pstm.setString(6, os.getZona().toString());

            pstm.setLong(7,
                    os.getCliente().getId());

            pstm.setLong(8,
                    os.getEndereco().getId());

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                os.setId(rs.getLong("id"));
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return os;
    }
    
    public Os atualizar(Os os) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(UPDATE);

            pstm.setString(1, os.getDescricao());
            pstm.setString(2, os.getStatus().toString());

            pstm.setDate(3,
                    new java.sql.Date(
                            os.getDataAbertura().getTime()));

            pstm.setDate(4,
                    new java.sql.Date(
                            os.getDataTermino().getTime()));

            pstm.setDouble(5, os.getTotalOs());

            pstm.setString(6, os.getZona().toString());

            pstm.setLong(7,
                    os.getCliente().getId());

            pstm.setLong(8,
                    os.getEndereco().getId());

            pstm.setLong(9, os.getId());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return os;
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
    
    public Os findById(Long id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Os os = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_BY_ID);
            pstm.setLong(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {

                os = new Os();

                os.setId(rs.getLong("id"));
                os.setDescricao(rs.getString("descricao"));
                os.setStatus(
                        StatusOsEnum.valueOf(
                                rs.getString("status")));

                os.setDataAbertura(
                        rs.getDate("data_abertura"));

                os.setDataTermino(
                        rs.getDate("data_fechamento"));

                os.setTotalOs(
                        rs.getDouble("valor_total"));

                os.setZona(
                        ZonaEnum.valueOf(
                                rs.getString("zona")));

                ClienteRepository clienteRepository =
                        new ClienteRepository();

                EnderecoRepository enderecoRepository =
                        new EnderecoRepository();

                os.setCliente(
                        clienteRepository.findById(
                                rs.getLong("id_cliente")));

                os.setEndereco(
                        enderecoRepository.findById(
                                rs.getLong("id_endereco")));
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return os;
    }
    
    public ArrayList<Os> listarTodos() throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ArrayList<Os> listaOs = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_ALL);

            rs = pstm.executeQuery();

            ClienteRepository clienteRepository =
                    new ClienteRepository();

            EnderecoRepository enderecoRepository =
                    new EnderecoRepository();

            while (rs.next()) {

                Os os = new Os();

                os.setId(rs.getLong("id"));

                os.setDescricao(
                        rs.getString("descricao"));

                os.setStatus(
                        StatusOsEnum.valueOf(
                                rs.getString("status")));

                os.setDataAbertura(
                        rs.getDate("data_abertura"));

                os.setDataTermino(
                        rs.getDate("data_fechamento"));

                os.setTotalOs(
                        rs.getDouble("valor_total"));

                os.setZona(
                        ZonaEnum.valueOf(
                                rs.getString("zona")));

                os.setCliente(
                        clienteRepository.findById(
                                rs.getLong("id_cliente")));

                os.setEndereco(
                        enderecoRepository.findById(
                                rs.getLong("id_endereco")));

                listaOs.add(os);
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return listaOs;
    }
}
