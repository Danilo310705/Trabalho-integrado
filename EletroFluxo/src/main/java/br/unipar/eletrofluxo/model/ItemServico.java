/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.model;

/**
 *
 * @author Usuario
 */
public class ItemServico {
    private Servico servico;
    private Integer quantidade;
    private Double subTotal;

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public Double getSubTotal(){
        return subTotal = quantidade * servico.getValorServico();
    }

    @Override
    public String toString() {
        return "{"+ "servico=" + servico + ", quantidade=" + quantidade + "\n subTotal= "+ subTotal +"}\n";
    }
    
    
}
