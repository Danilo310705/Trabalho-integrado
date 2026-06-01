/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository.interfaces;

import br.unipar.eletrofluxo.model.Pais;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaisRepositoryInterface {

    public Pais inserir(Pais pais) throws SQLException;

    public Pais atualizar(Pais pais) throws SQLException;

    public void deletar(Long id) throws SQLException;

    public Pais findById(Long id) throws SQLException;

    public ArrayList<Pais> listarTodos() throws SQLException;

}
