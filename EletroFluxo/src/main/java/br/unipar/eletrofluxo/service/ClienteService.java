/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.Cliente;

/**
 *
 * @author Usuario
 */
public class ClienteService {
    
    public void validar(Cliente cliente) throws ValidacaoNegocioException{
        if(cliente.getNome()== null || cliente.getNome().isBlank()){
            throw new ValidacaoNegocioException("O nome do cliente nao pode ser nulo");
        }
        if(cliente.getNome().length()<2){
            throw new ValidacaoNegocioException("O nome do cliente dever ter mais de 2 caracteres");
        }
        if(cliente.getNome().length()>40){
            throw new ValidacaoNegocioException("O nome do cliente dever ter menos de 40 caracteres");
        }

        if(cliente.getObservacoes().length()>100){
            throw new ValidacaoNegocioException("As observacoes do cliente dever ter menos de 100 caracteres");
        }
        
        if(cliente.getTelefone()== null || cliente.getTelefone().isBlank()){
            throw new ValidacaoNegocioException("nessessario telefone para contato");
        }
        if(cliente.getTelefone().length()<10 || cliente.getTelefone().length()>11){
            throw new ValidacaoNegocioException("Telefone inválido");
        }
        if(!cliente.getTelefone().matches("\\d+")){
            throw new ValidacaoNegocioException("O telefone deve ter apenas numeros");
        }
        
        if(cliente.getEmail() == null || cliente.getEmail().isBlank()){
            throw new ValidacaoNegocioException("Informe o email para contato");
        }
        if (!cliente.getEmail().contains("@")) {
            throw new ValidacaoNegocioException(
                    "Email deve conter @");
        }

        if (!cliente.getEmail().contains(".")) {
            throw new ValidacaoNegocioException(
                    "Email deve conter domínio válido");
        }
        if(cliente.getEmail().length()>30){
            throw new ValidacaoNegocioException("Email muito longo, menos de 30 caracteres");            
        }
        
        if(cliente.getStatus() == null){
            throw new ValidacaoNegocioException("Istatus invalido");                        
        }
        
    }
}
