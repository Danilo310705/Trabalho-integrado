/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository;

import br.unipar.eletrofluxo.enums.UnidadeProdutoEnum;
import br.unipar.eletrofluxo.infraestructure.ConnectionFactory;
import br.unipar.eletrofluxo.model.Produto;
import br.unipar.eletrofluxo.repository.interfaces.ProdutoRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutoRepository implements ProdutoRepositoryInterface{

    private static final String INSERT =
            "INSERT INTO produto "
            + "(nome, quant, preco_unitario, unidade) "
            + "VALUES (?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE produto "
            + "SET nome = ?, quant = ?, preco_unitario = ?, unidade = ? "
            + "WHERE id = ?";

    private static final String DELETE =
            "DELETE FROM produto WHERE id = ?";

    private static final String FIND_BY_ID =
            "SELECT id, nome, quant, preco_unitario, unidade "
            + "FROM produto WHERE id = ?";

    private static final String FIND_ALL =
            "SELECT id, nome, quant, preco_unitario, unidade "
            + "FROM produto ORDER BY nome";

    public Produto inserir(Produto produto) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(
                    INSERT,
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getQuantidade());
            pstm.setDouble(3, produto.getPrecoUnitario());
            pstm.setString(4, produto.getUnidade().toString());

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

             if (rs.next()) produto.setId(rs.getLong("id"));

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return produto;
    }

    public Produto atualizar(Produto produto) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(UPDATE);

            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getQuantidade());
            pstm.setDouble(3, produto.getPrecoUnitario());
            pstm.setString(4, produto.getUnidade().toString());

            pstm.setLong(5, produto.getId());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return produto;
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

    public Produto findById(Long id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Produto produto = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_BY_ID);

            pstm.setLong(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {

                produto = new Produto();

                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quant"));
                produto.setPrecoUnitario(rs.getDouble("preco_unitario"));
                produto.setUnidade(
                        UnidadeProdutoEnum.valueOf(
                                rs.getString("unidade")));

            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return produto;
    }

    public ArrayList<Produto> listarTodos() throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ArrayList<Produto> listaProdutos =
                new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_ALL);

            rs = pstm.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quant"));
                produto.setPrecoUnitario(
                        rs.getDouble("preco_unitario"));

                produto.setUnidade(
                        UnidadeProdutoEnum.valueOf(
                                rs.getString("unidade")));

                listaProdutos.add(produto);
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return listaProdutos;
    }
}