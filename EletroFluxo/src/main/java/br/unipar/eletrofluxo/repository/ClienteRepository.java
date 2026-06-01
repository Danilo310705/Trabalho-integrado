    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository;

import br.unipar.eletrofluxo.enums.StatusClienteEnum;
import br.unipar.eletrofluxo.infraestructure.ConnectionFactory;
import br.unipar.eletrofluxo.model.Cliente;
import br.unipar.eletrofluxo.model.ClienteFisico;
import br.unipar.eletrofluxo.model.ClienteJuridico;
import br.unipar.eletrofluxo.repository.interfaces.ClienteRepositoryInterface;
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
public class ClienteRepository implements ClienteRepositoryInterface{
    private static final String INSERT =
    "INSERT INTO cliente (nome, documento, obs, telefone, email, status) "
    + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE =
        "UPDATE cliente SET nome = ?, documento = ?, obs = ?, "
        + "telefone = ?, email = ?, status = ? WHERE id = ?";

    private static final String DELETE =
        "DELETE FROM cliente WHERE id = ?";

    private static final String FIND_BY_ID =
        "SELECT * FROM cliente WHERE id = ?";

    private static final String FIND_ALL =
        "SELECT * FROM cliente ORDER BY nome";
    
    public Cliente inserir(Cliente cliente) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);

            String documento;

            if (cliente instanceof ClienteFisico) {
                documento =
                        ((ClienteFisico) cliente).getCpf();
            } else {
                documento =
                        ((ClienteJuridico) cliente).getCnpj();
            }

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, documento);
            pstm.setString(3, cliente.getObservacoes());
            pstm.setString(4, cliente.getTelefone());
            pstm.setString(5, cliente.getEmail());
            pstm.setString(6,
                    cliente.getStatus().toString());

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                cliente.setId(rs.getLong("id"));
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return cliente;
    }    
    
    public Cliente atualizar(Cliente cliente)throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(UPDATE);

            String documento;

            if (cliente instanceof ClienteFisico) {
                documento =
                        ((ClienteFisico) cliente).getCpf();
            } else {
                documento =
                        ((ClienteJuridico) cliente).getCnpj();
            }

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, documento);
            pstm.setString(3, cliente.getObservacoes());
            pstm.setString(4, cliente.getTelefone());
            pstm.setString(5, cliente.getEmail());
            pstm.setString(6,
                    cliente.getStatus().toString());

            pstm.setLong(7, cliente.getId());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return cliente;
    }
    
    public Cliente findById(Long id)throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Cliente cliente = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_BY_ID);

            pstm.setLong(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {

                String documento =
                        rs.getString("documento");

                if (documento.length() == 11) {

                    ClienteFisico cf =
                            new ClienteFisico();

                    cf.setCpf(documento);

                    cliente = cf;

                } else {

                    ClienteJuridico cj =
                            new ClienteJuridico();

                    cj.setCnpj(documento);

                    cliente = cj;

                }

                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setObservacoes(rs.getString("obs"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setStatus(StatusClienteEnum.valueOf(rs.getString("status")));

            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return cliente;
    }
    
    public void deletar(Long id)throws SQLException {

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
    
    public ArrayList<Cliente> listarTodos() throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_ALL);

            rs = pstm.executeQuery();

            while (rs.next()) {

                Cliente cliente;

                String documento = rs.getString("documento");

                if (documento.length() == 11) {

                    ClienteFisico clienteFisico = new ClienteFisico();

                    clienteFisico.setCpf(documento);

                    cliente = clienteFisico;

                } else {

                    ClienteJuridico clienteJuridico = new ClienteJuridico();

                    clienteJuridico.setCnpj(documento);

                    cliente = clienteJuridico;

                }

                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setObservacoes(rs.getString("obs"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setStatus(StatusClienteEnum.valueOf(rs.getString("status")));

                listaClientes.add(cliente);

            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return listaClientes;

    }
}
