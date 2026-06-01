/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.repository.interfaces;

import br.unipar.eletrofluxo.model.ItemServico;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface ItemServicoRepositoryInterface {

    public ItemServico inserir(ItemServico itemServico) throws SQLException;

    public ItemServico atualizar(ItemServico itemServico) throws SQLException;

    public void deletar(Long idServico, Long idOs) throws SQLException;

    public ItemServico findById(Long idServico, Long idOs) throws SQLException;

    public ArrayList<ItemServico> listarTodos() throws SQLException;

}
