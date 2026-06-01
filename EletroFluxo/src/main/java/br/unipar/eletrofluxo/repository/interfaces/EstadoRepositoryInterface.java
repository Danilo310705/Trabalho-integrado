/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository.interfaces;

import br.unipar.eletrofluxo.model.Estado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface EstadoRepositoryInterface {

    public Estado inserir(Estado estado) throws SQLException;

    public Estado atualizar(Estado estado) throws SQLException;

    public void deletar(Long id) throws SQLException;

    public Estado findById(Long id) throws SQLException;

    public ArrayList<Estado> listarTodos() throws SQLException;

}
