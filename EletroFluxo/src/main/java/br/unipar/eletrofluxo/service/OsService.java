/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.ItemProduto;
import br.unipar.eletrofluxo.model.Os;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class OsService {
    public void validar(Os os) throws ValidacaoNegocioException{
        if (os.getCliente() == null) {
            throw new ValidacaoNegocioException("Cliente é obrigatório");
        }
        
        if(os.getDescricao().length()>60){
            throw new ValidacaoNegocioException("Descricao deve ter no maximo 60 caracteres");
        }
        
        if (os.getEndereco() == null) {
            throw new ValidacaoNegocioException("Endereço é obrigatório");
        }
        
        if(os.getStatus()== null){
            throw new ValidacaoNegocioException("O status é obrigatório");
        }
        
        if (os.getDataAbertura() == null) {
            throw new ValidacaoNegocioException("Data de abertura obrigatória");
        }

        if (os.getDataTermino() != null && os.getDataTermino().before(os.getDataAbertura())) {
            throw new ValidacaoNegocioException("Data de término inválida");
        }       
        
        if(os.getZona()==null){
            throw new ValidacaoNegocioException("O Zona é obrigatório");
        }
    }
    
    /*
    private Cliente cliente;
    private StatusOsEnum status;    
    private Date dataAbertura;
    private Date dataTermino;
    private Endereco endereco;*/
}
