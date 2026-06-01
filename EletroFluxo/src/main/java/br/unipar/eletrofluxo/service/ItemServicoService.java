/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.NaoEncontradoException;
import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.ItemServico;
import br.unipar.eletrofluxo.repository.ItemServicoRepository;
import br.unipar.eletrofluxo.repository.interfaces.ItemServicoRepositoryInterface;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ItemServicoService {

    private ItemServicoRepositoryInterface itemServicoRepository;

    public ItemServicoService() {
        this.itemServicoRepository = new ItemServicoRepository();
    }

    public ItemServico inserir(ItemServico itemServico)throws ValidacaoNegocioException,
            SQLException {

        validar(itemServico);

        return itemServicoRepository.inserir(itemServico);
    }

    public ItemServico atualizar(ItemServico itemServico)throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (itemServico == null) {
            throw new ValidacaoNegocioException("Informe o item de serviço");
        }

        if (itemServico.getServico() == null|| itemServico.getServico().getId() == null) {

            throw new ValidacaoNegocioException("Informe o serviço");
        }

        if (itemServico.getOs() == null || itemServico.getOs().getId() == null) {

            throw new ValidacaoNegocioException("Informe a OS");
        }

        ItemServico itemBanco =itemServicoRepository.findById(itemServico.getServico().getId(),itemServico.getOs().getId());

        if (itemBanco == null) {
            throw new NaoEncontradoException("Item de serviço não encontrado");
        }

        validar(itemServico);

        return itemServicoRepository.atualizar(itemServico);
    }

    public void deletar(Long idServico,Long idOs) throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (idServico == null) {
            throw new ValidacaoNegocioException("Informe o ID do serviço");
        }

        if (idOs == null) {
            throw new ValidacaoNegocioException("Informe o ID da OS");
        }

        ItemServico itemBanco = itemServicoRepository.findById(idServico,idOs);

        if (itemBanco == null) {
            throw new NaoEncontradoException("Item de serviço não encontrado");
        }

        itemServicoRepository.deletar(idServico,idOs);
    }

    public ItemServico findById(Long idServico,Long idOs)throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (idServico == null) {
            throw new ValidacaoNegocioException("Informe o ID do serviço");
        }

        if (idOs == null) {
            throw new ValidacaoNegocioException("Informe o ID da OS");
        }

        ItemServico itemServico =itemServicoRepository.findById(idServico,idOs);

        if (itemServico == null) {
            throw new NaoEncontradoException("Item de serviço não encontrado");
        }

        return itemServico;
    }

    public ArrayList<ItemServico> listarTodos()throws SQLException {

        return itemServicoRepository.listarTodos();
    }

    public void validar(ItemServico itemServico)throws ValidacaoNegocioException {

        if (itemServico == null) {
            throw new ValidacaoNegocioException("Informe o item de serviço");
        }

        if (itemServico.getServico() == null) {
            throw new ValidacaoNegocioException("Informe o serviço");
        }

        if (itemServico.getOs() == null) {
            throw new ValidacaoNegocioException("Informe a OS");
        }

        if (itemServico.getQuantidade() == null || itemServico.getQuantidade() <= 0) {

            throw new ValidacaoNegocioException("Quantidade de serviços não pode ser nula ou negativa");
        }
    }
}
