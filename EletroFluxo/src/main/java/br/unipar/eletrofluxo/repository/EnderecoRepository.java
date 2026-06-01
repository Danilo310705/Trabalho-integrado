/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository;

import br.unipar.eletrofluxo.infraestructure.ConnectionFactory;
import br.unipar.eletrofluxo.model.Cidade;
import br.unipar.eletrofluxo.model.ClienteFisico;
import br.unipar.eletrofluxo.model.Endereco;
import br.unipar.eletrofluxo.repository.interfaces.EnderecoRepositoryInterface;
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
public class EnderecoRepository implements EnderecoRepositoryInterface{

    private static final String INSERT =
        "INSERT INTO endereco (rua, bairro, numero, cep, id_cliente, id_cidade) "
        + "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String UPDATE =
        "UPDATE endereco SET rua = ?, bairro = ?, numero = ?, "
        + "cep = ?, id_cliente = ?, id_cidade = ? WHERE id = ?;";

    private static final String DELETE =
        "DELETE FROM endereco WHERE id = ?;";

    private static final String FIND_BY_ID =
        "SELECT id, rua, bairro, numero, cep, id_cliente, id_cidade "
        + "FROM endereco WHERE id = ?;";

    private static final String FIND_ALL =
        "SELECT id, rua, bairro, numero, cep, id_cliente, id_cidade "
        + "FROM endereco ORDER BY rua;";    
    
    
 public Endereco inserir(Endereco endereco) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(
                    INSERT,
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, endereco.getRua());
            pstm.setString(2, endereco.getBairro());
            pstm.setInt(3, endereco.getNumero());
            pstm.setInt(4, Integer.parseInt(endereco.getCep()));
            pstm.setLong(5, endereco.getCliente().getId());
            pstm.setLong(6, endereco.getCidade().getId());

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                endereco.setId(rs.getLong("id"));
            }

        } finally {

            if (conn != null) conn.close();
            if (pstm != null) pstm.close();
            if (rs != null) rs.close();

        }

        return endereco;
    }

  public Endereco atualizar(Endereco endereco) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(UPDATE);

            pstm.setString(1, endereco.getRua());
            pstm.setString(2, endereco.getBairro());
            pstm.setInt(3, endereco.getNumero());
            pstm.setInt(4, Integer.parseInt(endereco.getCep()));
            pstm.setLong(5, endereco.getCliente().getId());
            pstm.setLong(6, endereco.getCidade().getId());
            pstm.setLong(7, endereco.getId());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return endereco;
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

    public Endereco findById(Long id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Endereco endereco = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_BY_ID);

            pstm.setLong(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {

                endereco = new Endereco();

                endereco.setId(rs.getLong("id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setCep(String.valueOf(rs.getInt("cep")));

                Cidade cidade = new Cidade();
                cidade.setId(rs.getLong("id_cidade"));

                ClienteFisico cliente = new ClienteFisico();
                cliente.setId(rs.getLong("id_cliente"));

                endereco.setCidade(cidade);
                endereco.setCliente(cliente);

            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return endereco;
    }    

    public ArrayList<Endereco> listarTodos() throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ArrayList<Endereco> listaEnderecos = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_ALL);

            rs = pstm.executeQuery();

            while (rs.next()) {

                Endereco endereco = new Endereco();

                endereco.setId(rs.getLong("id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setCep(String.valueOf(rs.getInt("cep")));

                Cidade cidade = new Cidade();
                cidade.setId(rs.getLong("id_cidade"));

                ClienteFisico cliente = new ClienteFisico();
                cliente.setId(rs.getLong("id_cliente"));

                endereco.setCidade(cidade);
                endereco.setCliente(cliente);

                listaEnderecos.add(endereco);
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return listaEnderecos;
    }    
}
