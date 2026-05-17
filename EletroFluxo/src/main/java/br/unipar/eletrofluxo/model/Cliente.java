/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.model;

import br.unipar.eletrofluxo.enums.StatusEnum;

/**
 *
 * @author Usuario
 */
public abstract class Cliente {
    private String nome;
    private String endereco;//acho que isso vai virar um array
    private String observacoes;
    private String telefone;
    private String email;
    private StatusEnum status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", endereco=" + endereco + ", observacoes=" + observacoes + ", telefone=" + telefone + ", email=" + email + ", status=" + status + '}';
    }
    
    
    
}
