/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository.interfaces;

import br.unipar.eletrofluxo.model.Servico;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ServicoRepositoryInterface {

    public Servico inserir(Servico servico) throws SQLException;

    public Servico atualizar(Servico servico) throws SQLException;

    public void deletar(Long id) throws SQLException;

    public Servico findById(Long id) throws SQLException;

    public ArrayList<Servico> listarTodos() throws SQLException;

}