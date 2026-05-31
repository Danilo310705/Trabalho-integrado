/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.ItemProduto;

/**
 *
 * @author Usuario
 */
public class ItemProdutoService{
    
    public void validar(ItemProduto itemProduto) throws ValidacaoNegocioException{
        if(itemProduto.getQuantidade()<1){
            throw new ValidacaoNegocioException("Nao pode colocar quantidade nula ou negativa");
        }
        if(itemProduto.getQuantidade()> itemProduto.getProduto().getQuantidade()){
            throw new ValidacaoNegocioException("Quantidade selecionada é maior que a do estoque");
        }
        itemProduto.getProduto().setQuantidade(
            itemProduto.getProduto().getQuantidade() - itemProduto.getQuantidade()
        );
    }
}
