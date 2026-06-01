/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.ClienteFisico;

/**
 *
 * @author Usuario
 */
public class ClienteFisicoService extends ClienteService{
    public void validar(ClienteFisico clienteFisico) throws ValidacaoNegocioException{
        if(clienteFisico.getCpf() == null || clienteFisico.getCpf().isBlank()){
            throw new ValidacaoNegocioException("O cpf é obrigatorio");
        }
        if(clienteFisico.getCpf().length() != 11){
            throw new ValidacaoNegocioException("cpf invalido, 11 caracteres");
        }
        if(!clienteFisico.getCpf().matches("\\d+")){
            throw new ValidacaoNegocioException("cpf invalido deve conter apenas numeros");
        }
        
    }
}
