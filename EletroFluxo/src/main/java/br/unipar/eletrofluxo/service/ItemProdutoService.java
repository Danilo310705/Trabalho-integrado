/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.NaoEncontradoException;
import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.ItemProduto;
import br.unipar.eletrofluxo.repository.ItemProdutoRepository;
import br.unipar.eletrofluxo.repository.interfaces.ItemProdutoRepositoryInterface;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ItemProdutoService {

    private ItemProdutoRepositoryInterface itemProdutoRepository;

    public ItemProdutoService() {
        this.itemProdutoRepository =
                new ItemProdutoRepository();
    }

    public ItemProduto inserir(ItemProduto itemProduto)throws ValidacaoNegocioException,SQLException {

        validar(itemProduto);

        return itemProdutoRepository.inserir(itemProduto,itemProduto.getOs().getId());
    }

    public ItemProduto atualizar(ItemProduto itemProduto)
            throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (itemProduto == null) {
            throw new ValidacaoNegocioException(
                    "Informe o item do produto");
        }

        if (itemProduto.getProduto() == null
                || itemProduto.getProduto().getId() == null) {

            throw new ValidacaoNegocioException(
                    "Informe o produto");
        }

        if (itemProduto.getOs() == null
                || itemProduto.getOs().getId() == null) {

            throw new ValidacaoNegocioException(
                    "Informe a OS");
        }

        ItemProduto itemBanco =
                itemProdutoRepository.findById(
                        itemProduto.getProduto().getId(),
                        itemProduto.getOs().getId());

        if (itemBanco == null) {
            throw new NaoEncontradoException(
                    "Item de produto não encontrado");
        }

        validar(itemProduto);

        return itemProdutoRepository.inserir(itemProduto,itemProduto.getOs().getId());
    }

    public void deletar(Long idProduto,Long idOs)throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (idProduto == null) {
            throw new ValidacaoNegocioException("Informe o ID do produto");
        }

        if (idOs == null) {
            throw new ValidacaoNegocioException("Informe o ID da OS");
        }

        ItemProduto itemBanco = itemProdutoRepository.findById(idProduto,idOs);

        if (itemBanco == null) {
            throw new NaoEncontradoException("Item de produto não encontrado");
        }

        itemProdutoRepository.deletar(idProduto,idOs);
    }

    public ItemProduto findById(Long idProduto,Long idOs)throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (idProduto == null) {
            throw new ValidacaoNegocioException("Informe o ID do produto");
        }

        if (idOs == null) {
            throw new ValidacaoNegocioException("Informe o ID da OS");
        }

        ItemProduto itemProduto = itemProdutoRepository.findById(idProduto,idOs);

        if (itemProduto == null) {
            throw new NaoEncontradoException("Item de produto não encontrado");
        }

        return itemProduto;
    }

    public ArrayList<ItemProduto> listarTodos()throws SQLException {

        return itemProdutoRepository.listarTodos();
    }

    public void validar(ItemProduto itemProduto)throws ValidacaoNegocioException {

        if (itemProduto == null) {
            throw new ValidacaoNegocioException("Informe o item do produto");
        }

        if (itemProduto.getProduto() == null) {
            throw new ValidacaoNegocioException("Informe o produto");
        }

        if (itemProduto.getOs() == null) {
            throw new ValidacaoNegocioException("Informe a OS");
        }

        if (itemProduto.getQuantidade() == null || itemProduto.getQuantidade() < 1) {

            throw new ValidacaoNegocioException("Não pode colocar quantidade nula ou negativa");
        }

        if (itemProduto.getQuantidade()> itemProduto.getProduto().getQuantidade()) {

            throw new ValidacaoNegocioException("Quantidade selecionada é maior que a do estoque");
        }
    }
}