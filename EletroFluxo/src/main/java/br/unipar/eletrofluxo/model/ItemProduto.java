/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.model;

/**
 *
 * @author danilo310705
 */
public class ItemProduto {
    private Produto produto;
    private Integer quantidade;
    private Double subTotal;
    
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public Double getSubTotal(){
        return subTotal = quantidade * produto.getPrecoUnitario();
    }

    @Override
    public String toString() {
        return "Produto{" + "produto=" + produto.getNome() + ", quantidade=" + quantidade + ", subTotal=" + subTotal + "}\n";
    }

    
    
}
