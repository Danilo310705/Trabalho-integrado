/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.model;

/**
 *
 * @author Usuario
 */
public class Servico {
    private String nome;
    private String descricao;
    private Double valorServic;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorServic() {
        return valorServic;
    }

    public void setValorServic(Double valorServic) {
        this.valorServic = valorServic;
    }

    @Override
    public String toString() {
        return "Servicos{" + "nome=" + nome + ", descricao=" + descricao + ", valorServic=" + valorServic + '}';
    }
    
    
}
