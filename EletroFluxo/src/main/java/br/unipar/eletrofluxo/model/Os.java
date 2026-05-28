/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.model;

import br.unipar.eletrofluxo.enums.StatusOsEnum;
import br.unipar.eletrofluxo.enums.ZonaEnum;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Os {
    private Cliente cliente;
    private Date dataAbertura;
    private Date dataTermino;
    private StatusOsEnum status;
    private String descricao;
    private ZonaEnum zona;
    private Double totalProdutos;
    private ArrayList<ItemProduto> produtos;
    private ArrayList<ItemServico> servicos;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusOsEnum getStatus() {
        return status;
    }

    public void setStatus(StatusOsEnum status) {
        this.status = status;
    }

    public ZonaEnum getZona() {
        return zona;
    }

    public void setZona(ZonaEnum zona) {
        this.zona = zona;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ItemProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<ItemProduto> produtos) {
        this.produtos = produtos;
    }
    
    public void addProdutos(ItemProduto produto){
        produtos.add(produto);
    }

    public ArrayList<ItemServico> getServicos() {
        return servicos;
    }

    public void setServicos(ArrayList<ItemServico> servicos) {
        this.servicos = servicos;
    }
    
    public void addServicos(ItemServico servico){
        servicos.add(servico);
    }

    public Double gettotalProdutos() {
        totalProdutos = 0.0;      
        for(ItemProduto produto : produtos){
            totalProdutos += produto.getSubTotal();
        }
        return totalProdutos;
    }

    public void settotalProdutos(Double valorTotal) {
        this.totalProdutos = valorTotal;
    }
    
    

    @Override
    public String toString() {
        return "Os{" + "cliente=" + cliente + "\n dataAbertura=" + dataAbertura + ", dataTermino=" + dataTermino + ", status=" + status + "\n descricao=" + descricao + "\n\n produtos=\n" + produtos + "\n\n servicos=" + servicos + "}\n\n\n sub total: "+ totalProdutos;
    }
    



    
}
