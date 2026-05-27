/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.model;

/**
 *
 * @author danilo310705
 */
public class ProdutoOs {
    private Produto produtoOs;
    private Integer quantidade;
    private Double subTotal;
    
    
    public Produto getProdutoOs() {
        return produtoOs;
    }

    public void setProdutoOs(Produto produtoOs) {
        this.produtoOs = produtoOs;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public Double getSubTotal(){
        return subTotal = quantidade * produtoOs.getPrecoUnitario();
    }

    @Override
    public String toString() {
        return "ProdutoOs{" + "produtoOs=" + produtoOs.getNome() + ", quantidade=" + quantidade + ", subTotal=" + subTotal + "}\n";
    }

    
    
}
