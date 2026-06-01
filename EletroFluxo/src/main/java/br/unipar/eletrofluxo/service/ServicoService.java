/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.NaoEncontradoException;
import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.Servico;
import br.unipar.eletrofluxo.repository.ServicoRepository;
import br.unipar.eletrofluxo.repository.interfaces.ServicoRepositoryInterface;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ServicoService {

    private ServicoRepositoryInterface servicoRepository;

    public ServicoService() {
        this.servicoRepository = new ServicoRepository();
    }

    public Servico inserir(Servico servico) throws ValidacaoNegocioException, SQLException {

        validar(servico);

        return servicoRepository.inserir(servico);
    }

    public Servico atualizar(Servico servico)throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (servico == null) {
            throw new ValidacaoNegocioException("Informe o serviço");
        }

        if (servico.getId() == null) {
            throw new ValidacaoNegocioException("Informe o ID do serviço");
        }

        Servico servicoBanco = servicoRepository.findById(servico.getId());

        if (servicoBanco == null) {
            throw new NaoEncontradoException("Serviço não encontrado");
        }

        validar(servico);

        return servicoRepository.atualizar(servico);
    }

    public void deletar(Long id) throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (id == null) {
            throw new ValidacaoNegocioException("Informe o ID do serviço");
        }

        Servico servico = servicoRepository.findById(id);

        if (servico == null) {
            throw new NaoEncontradoException("Serviço não encontrado");
        }

        servicoRepository.deletar(id);
    }

    public Servico findById(Long id)
            throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (id == null) {
            throw new ValidacaoNegocioException("Informe o ID do serviço");
        }

        Servico servico =servicoRepository.findById(id);

        if (servico == null) {
            throw new NaoEncontradoException("Serviço não encontrado");
        }

        return servico;
    }

    public ArrayList<Servico> listarTodos()throws SQLException {

        return servicoRepository.listarTodos();
    }

    public void validar(Servico servico)
            throws ValidacaoNegocioException {

        if (servico == null) {
            throw new ValidacaoNegocioException("Informe o serviço");
        }

        if (servico.getNome() == null
                || servico.getNome().isBlank()) {
            throw new ValidacaoNegocioException("O nome do serviço não pode ser nulo");
        }

        if (servico.getNome().length() < 5) {
            throw new ValidacaoNegocioException("O nome do serviço deve ter mais que 5 caracteres");
        }

        if (servico.getNome().length() > 40) {
            throw new ValidacaoNegocioException("O nome do serviço deve ter menos que 40 caracteres");
        }

        if (servico.getDescricao() == null
                || servico.getDescricao().isBlank()) {
            throw new ValidacaoNegocioException("A descrição do serviço não pode ser nula");
        }

        if (servico.getDescricao().length() < 5) {
            throw new ValidacaoNegocioException("A descrição do serviço deve ter mais que 5 caracteres");
        }

        if (servico.getDescricao().length() > 100) {
            throw new ValidacaoNegocioException("A descrição do serviço deve ter menos que 100 caracteres");
        }

        if (servico.getValorServico() == null
                || servico.getValorServico() <= 0) {
            throw new ValidacaoNegocioException("O valor do serviço não pode ser nulo ou negativo");
        }
    }
}
