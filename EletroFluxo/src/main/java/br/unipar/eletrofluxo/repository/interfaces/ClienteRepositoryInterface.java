/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository.interfaces;

import br.unipar.eletrofluxo.model.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface ClienteRepositoryInterface {

    public Cliente inserir(Cliente cliente) throws SQLException;

    public Cliente atualizar(Cliente cliente) throws SQLException;

    public void deletar(Long id) throws SQLException;

    public Cliente findById(Long id) throws SQLException;

    public ArrayList<Cliente> listarTodos() throws SQLException;

}
