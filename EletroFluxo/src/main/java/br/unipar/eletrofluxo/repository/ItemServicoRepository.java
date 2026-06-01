/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository;

import br.unipar.eletrofluxo.infraestructure.ConnectionFactory;
import br.unipar.eletrofluxo.model.ItemServico;
import br.unipar.eletrofluxo.repository.interfaces.ItemServicoRepositoryInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ItemServicoRepository implements ItemServicoRepositoryInterface{
    private static final String INSERT =
        "INSERT INTO item_servico "
        + "(id_servico, id_os, quant, subtotal) "
        + "VALUES (?, ?, ?, ?)";

    private static final String UPDATE =
        "UPDATE item_servico SET quant = ?, subtotal = ? "
        + "WHERE id_servico = ? AND id_os = ?";

    private static final String DELETE =
        "DELETE FROM item_servico "
        + "WHERE id_servico = ? AND id_os = ?";

    private static final String FIND_BY_ID =
        "SELECT * FROM item_servico "
        + "WHERE id_servico = ? AND id_os = ?";

    private static final String FIND_ALL =
        "SELECT * FROM item_servico";
    
    public ItemServico inserir(ItemServico itemServico)throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(INSERT);

            pstm.setLong(1,
                    itemServico.getServico().getId());

            pstm.setLong(2,
                    itemServico.getOs().getId());

            pstm.setInt(3,
                    itemServico.getQuantidade());

            pstm.setDouble(4,
                    itemServico.getSubTotal());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return itemServico;
    }
    
    public ItemServico atualizar(ItemServico itemServico)throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(UPDATE);

            pstm.setInt(1,
                    itemServico.getQuantidade());

            pstm.setDouble(2,
                    itemServico.getSubTotal());

            pstm.setLong(3,
                    itemServico.getServico().getId());

            pstm.setLong(4,
                    itemServico.getOs().getId());

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return itemServico;
    }
    
    public void deletar(Long idServico,Long idOs) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(DELETE);

            pstm.setLong(1, idServico);
            pstm.setLong(2, idOs);

            pstm.executeUpdate();

        } finally {

            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }
    }
    
    public ItemServico findById(Long idServico,Long idOs) throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ItemServico itemServico = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_BY_ID);

            pstm.setLong(1, idServico);
            pstm.setLong(2, idOs);

            rs = pstm.executeQuery();

            if (rs.next()) {

                itemServico = new ItemServico();

                ServicoRepository servicoRepository =
                        new ServicoRepository();

                OsRepository osRepository =
                        new OsRepository();

                itemServico.setServico(
                        servicoRepository.findById(
                                rs.getLong("id_servico")));

                itemServico.setOs(
                        osRepository.findById(
                                rs.getLong("id_os")));

                itemServico.setQuantidade(
                        rs.getInt("quant"));

                itemServico.setSubTotal(
                        rs.getDouble("subtotal"));
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return itemServico;
    }
    
    public ArrayList<ItemServico> listarTodos()throws SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        ArrayList<ItemServico> lista =
                new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(FIND_ALL);

            rs = pstm.executeQuery();

            ServicoRepository servicoRepository =
                    new ServicoRepository();

            OsRepository osRepository =
                    new OsRepository();

            while (rs.next()) {

                ItemServico itemServico =
                        new ItemServico();

                itemServico.setServico(
                        servicoRepository.findById(
                                rs.getLong("id_servico")));

                itemServico.setOs(
                        osRepository.findById(
                                rs.getLong("id_os")));

                itemServico.setQuantidade(
                        rs.getInt("quant"));

                itemServico.setSubTotal(
                        rs.getDouble("subtotal"));

                lista.add(itemServico);
            }

        } finally {

            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();

        }

        return lista;
    }
}
