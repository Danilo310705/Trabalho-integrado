/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.model;

import br.unipar.eletrofluxo.enums.UnidadeProdutoEnum;

/**
 *
 * @author Usuario
 */
public class Produto {
    private String nome;
    private Integer quantidade;
    private Double precoUnitario;
    private UnidadeProdutoEnum unidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public UnidadeProdutoEnum getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeProdutoEnum unidade) {
        this.unidade = unidade;
    }

    @Override
    public String toString() {
        return "Produtos{" + "nome=" + nome + ", quantidade=" + quantidade + ", precoUnitario=" + precoUnitario + ", unidade=" + unidade + '}';
    }
    
    
}
