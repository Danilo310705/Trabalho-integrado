/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository;

import br.unipar.eletrofluxo.infraestructure.ConnectionFactory;
import br.unipar.eletrofluxo.model.ItemProduto;
import br.unipar.eletrofluxo.repository.interfaces.ItemProdutoRepositoryInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ItemProdutoRepository implements ItemProdutoRepositoryInterface{
    private static final String INSERT =
    "INSERT INTO item_produto "
    + "(id_produto, id_os, quant, subtotal) "
    + "VALUES (?, ?, ?, ?)";

    private static final String UPDATE =
        "UPDATE item_produto "
        + "SET quant = ?, subtotal = ? "
        + "WHERE id_produto = ? AND id_os = ?";

    private static final String DELETE =
        "DELETE FROM item_produto "
        + "WHERE id_produto = ? AND id_os = ?";

    private static final String FIND_BY_ID =
        "SELECT * FROM item_produto "
        + "WHERE id_produto = ? AND id_os = ?";

    private static final String FIND_ALL =
        "SELECT * FROM item_produto";
    
    public ItemProduto inserir(ItemProduto itemProduto,Long idOs) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(INSERT);

            pstm.setLong(1,itemProduto.getProduto().getId());

            pstm.setLong(2,idOs);

            pstm.setInt(3,itemProduto.getQuantidade());

            pstm.setDouble(4,itemProduto.getSubTotal());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return itemProduto;
    }
    
    public ItemProduto atualizar(ItemProduto itemProduto,Long idOs) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(UPDATE);

            pstm.setInt(1,
                    itemProduto.getQuantidade());

            pstm.setDouble(2,
                    itemProduto.getSubTotal());

            pstm.setLong(3,
                    itemProduto.getProduto().getId());

            pstm.setLong(4,
                    idOs);

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return itemProduto;
    }
    
    public void deletar(Long idProduto,Long idOs) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(DELETE);

            pstm.setLong(1, idProduto);
            pstm.setLong(2, idOs);

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }
    }
    
    public ItemProduto findById(Long idProduto,Long idOs) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ItemProduto itemProduto = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_BY_ID);

            pstm.setLong(1, idProduto);
            pstm.setLong(2, idOs);

            rs = pstm.executeQuery();

            if (rs.next()) {

                itemProduto = new ItemProduto();

                ProdutoRepository produtoRepository =
                        new ProdutoRepository();

                itemProduto.setProduto(
                        produtoRepository.findById(
                                rs.getLong("id_produto")));

                itemProduto.setQuantidade(
                        rs.getInt("quant"));
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return itemProduto;
    }
    
    public ArrayList<ItemProduto> listarTodos()
        throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ArrayList<ItemProduto> lista =
                new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_ALL);

            rs = pstm.executeQuery();

            ProdutoRepository produtoRepository =
                    new ProdutoRepository();

            while (rs.next()) {

                ItemProduto itemProduto =
                        new ItemProduto();

                itemProduto.setProduto(
                        produtoRepository.findById(
                                rs.getLong("id_produto")));

                itemProduto.setQuantidade(
                        rs.getInt("quant"));

                lista.add(itemProduto);
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return lista;
    }
}
