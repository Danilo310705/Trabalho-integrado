/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.ClienteJuridico;

/**
 *
 * @author Usuario
 */
public class ClienteJuridicoService {
    public void validar(ClienteJuridico clienteJuridico) throws ValidacaoNegocioException{
        if(clienteJuridico.getCnpj()== null || clienteJuridico.getCnpj().isBlank()){
            throw new ValidacaoNegocioException("O cnpj é obrigatorio");
        }
        if(clienteJuridico.getCnpj().length() != 14){
            throw new ValidacaoNegocioException("cnpj invalido, 14 caracteres");
        }
        if(!clienteJuridico.getCnpj().matches("\\d+")){
            throw new ValidacaoNegocioException("");            
        }
    }
}
