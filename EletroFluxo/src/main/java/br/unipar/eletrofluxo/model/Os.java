/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.model;

import br.unipar.eletrofluxo.enums.StatusEnum;
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
    private StatusEnum status;
    private String descricao;
    private ArrayList<ProdutoOs> produtos;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
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

    public ArrayList<ProdutoOs> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<ProdutoOs> produtos) {
        this.produtos = produtos;
    }
    
    public void addProdutos(ProdutoOs produto){
        produtos.add(produto);
    }

    
}
