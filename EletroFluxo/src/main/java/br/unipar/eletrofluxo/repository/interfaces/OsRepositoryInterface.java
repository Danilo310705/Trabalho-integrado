/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository.interfaces;

import br.unipar.eletrofluxo.model.Os;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OsRepositoryInterface {

    public Os inserir(Os os) throws SQLException;

    public Os atualizar(Os os) throws SQLException;

    public void deletar(Long id) throws SQLException;

    public Os findById(Long id) throws SQLException;

    public ArrayList<Os> listarTodos() throws SQLException;

}
