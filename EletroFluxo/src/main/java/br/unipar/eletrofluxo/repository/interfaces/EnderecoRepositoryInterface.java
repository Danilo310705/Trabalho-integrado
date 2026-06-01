/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository.interfaces;

import br.unipar.eletrofluxo.model.Endereco;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EnderecoRepositoryInterface {

    public Endereco inserir(Endereco endereco) throws SQLException;

    public Endereco atualizar(Endereco endereco) throws SQLException;

    public void deletar(Long id) throws SQLException;

    public Endereco findById(Long id) throws SQLException;

    public ArrayList<Endereco> listarTodos() throws SQLException;
}

