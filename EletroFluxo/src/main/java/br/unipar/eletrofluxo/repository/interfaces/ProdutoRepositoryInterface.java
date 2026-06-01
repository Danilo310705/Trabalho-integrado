/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository.interfaces;

import br.unipar.eletrofluxo.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface ProdutoRepositoryInterface {

    public Produto inserir(Produto produto) throws SQLException;

    public Produto atualizar(Produto produto) throws SQLException;

    public void deletar(Long id) throws SQLException;

    public Produto findById(Long id) throws SQLException;

    public ArrayList<Produto> listarTodos() throws SQLException;
}
