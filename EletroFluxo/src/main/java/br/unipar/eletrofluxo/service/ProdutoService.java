/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.Produto;

/**
 *
 * @author Usuario
 */
public class ProdutoService {
    
    public void validar(Produto produto) throws ValidacaoNegocioException{
        if(produto.getNome().isBlank()){
            throw new ValidacaoNegocioException("Informe o nome do produto");
        }
        
        if(produto.getNome().length()< 1){
            throw new ValidacaoNegocioException("O produto nao posde ter menos de 1 caracter");
            
        }
        
        if(produto.getNome().length()>=40){
            throw new ValidacaoNegocioException("O produto nao posde ter mais de 40 caracter");
        }
        
        if(produto.getPrecoUnitario()<= 0.0){
            throw new ValidacaoNegocioException("valor não pode ser nula ou negativo");
        }
        
        if(produto.getUnidade() == null){
            throw new ValidacaoNegocioException("Necessario informar a unidade");
        }
    }
    
}
