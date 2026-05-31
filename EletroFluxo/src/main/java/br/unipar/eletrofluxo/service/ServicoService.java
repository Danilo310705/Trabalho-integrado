/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.Servico;

/**
 *
 * @author Usuario
 */
public class ServicoService {
    
    public void validar(Servico servico) throws ValidacaoNegocioException{
        if(servico.getNome().isBlank()){
            throw new ValidacaoNegocioException("O nome do servico não pode ser nulo");
        }
        
        if(servico.getNome().length()<5){
            throw new ValidacaoNegocioException("O nome do servico deve te mas que 5 caracteres");
        }
        
        if(servico.getNome().length()>40){
            throw new ValidacaoNegocioException("O nome do servico deve te menos que 40 caracteres");
        }
        
        if(servico.getDescricao().isBlank()){
            throw new ValidacaoNegocioException("A descricao do servico não pode ser nulo");
        }
        
        if(servico.getDescricao().length()<5){
            throw new ValidacaoNegocioException("A descricao do servico deve te mas que 5 caracteres");
        }
        
        if(servico.getDescricao().length()>100){
            throw new ValidacaoNegocioException("A descricao do servico deve te menos que 40 caracteres");
        }
        
        if(servico.getValorServico()<=0){
            throw new ValidacaoNegocioException("O valor do serviço nao pode ser 0 ou negativo");
        }
        
    }
}
