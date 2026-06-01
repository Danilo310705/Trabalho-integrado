/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.ItemServico;

/**
 *
 * @author Usuario
 */
public class ItemServicoService {
    public void validar(ItemServico itemServico) throws ValidacaoNegocioException{
        if(itemServico.getQuantidade()<= 0){
            throw new ValidacaoNegocioException("Quantidade de servicos nao pode ser nula ou negativa");
        }
    }
}
