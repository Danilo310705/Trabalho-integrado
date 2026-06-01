/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository.interfaces;

import br.unipar.eletrofluxo.model.ItemProduto;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface ItemProdutoRepositoryInterface {

    public ItemProduto inserir(ItemProduto itemProduto,
            Long idOs) throws SQLException;

    public ItemProduto atualizar(ItemProduto itemProduto,
            Long idOs) throws SQLException;

    public void deletar(Long idProduto,
            Long idOs) throws SQLException;

    public ItemProduto findById(Long idProduto,
            Long idOs) throws SQLException;

    public ArrayList<ItemProduto> listarTodos()
            throws SQLException;

}