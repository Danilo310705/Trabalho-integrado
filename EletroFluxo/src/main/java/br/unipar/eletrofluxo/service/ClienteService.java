/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.NaoEncontradoException;
import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.Cliente;
import br.unipar.eletrofluxo.repository.ClienteRepository;
import br.unipar.eletrofluxo.repository.interfaces.ClienteRepositoryInterface;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ClienteService {

    private ClienteRepositoryInterface clienteRepositoryInterface;

    public ClienteService() {
        this.clienteRepositoryInterface = new ClienteRepository();
    }

    public Cliente inserir(Cliente cliente)throws ValidacaoNegocioException, SQLException {

        validar(cliente);

        return clienteRepositoryInterface.inserir(cliente);
    }

    public Cliente atualizar(Cliente cliente)throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (cliente == null) {
            throw new ValidacaoNegocioException("Obrigatório informar o cliente");
        }

        if (cliente.getId() == null) {
            throw new ValidacaoNegocioException("Obrigatório informar o ID do cliente");
        }

        Cliente clienteValidacao = clienteRepositoryInterface.findById(cliente.getId());

        if (clienteValidacao == null) {
            throw new NaoEncontradoException("Cliente não encontrado");
        }

        validar(cliente);

        return clienteRepositoryInterface.atualizar(cliente);
    }

    public void deletar(Long id)throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (id == null) {
            throw new ValidacaoNegocioException("Obrigatório informar o ID do cliente");
        }

        Cliente cliente = clienteRepositoryInterface.findById(id);

        if (cliente == null) {
            throw new NaoEncontradoException("Cliente não encontrado");
        }

        clienteRepositoryInterface.deletar(id);
    }

    public Cliente findById(Long id) throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (id == null) {
            throw new ValidacaoNegocioException("Obrigatório informar o ID do cliente");
        }

        Cliente cliente = clienteRepositoryInterface.findById(id);

        if (cliente == null) {
            throw new NaoEncontradoException("Cliente não encontrado");
        }

        return cliente;
    }

    public ArrayList<Cliente> listarTodos() throws SQLException {

        return clienteRepositoryInterface.listarTodos();
    }

    public void validar(Cliente cliente) throws ValidacaoNegocioException {

        if (cliente == null) {
            throw new ValidacaoNegocioException( "Cliente não informado");
        }

        if (cliente.getNome() == null || cliente.getNome().isBlank()) {

            throw new ValidacaoNegocioException("O nome do cliente não pode ser nulo");
        }

        if (cliente.getNome().length() < 2) {

            throw new ValidacaoNegocioException("O nome do cliente deve ter mais de 2 caracteres");
        }

        if (cliente.getNome().length() > 40) {

            throw new ValidacaoNegocioException("O nome do cliente deve ter menos de 40 caracteres");
        }

        if (cliente.getObservacoes() != null && cliente.getObservacoes().length() > 100) {

            throw new ValidacaoNegocioException("As observações devem ter menos de 100 caracteres");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()) {

            throw new ValidacaoNegocioException("Necessário telefone para contato");
        }

        if (cliente.getTelefone().length() < 10 || cliente.getTelefone().length() > 11) {

            throw new ValidacaoNegocioException("Telefone inválido");
        }

        if (!cliente.getTelefone().matches("\\d+")) {

            throw new ValidacaoNegocioException("Telefone deve conter apenas números");
        }

        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {

            throw new ValidacaoNegocioException("Informe o email");
        }

        if (!cliente.getEmail().contains("@")) {

            throw new ValidacaoNegocioException("Email deve conter @");
        }

        if (!cliente.getEmail().contains(".")) {

            throw new ValidacaoNegocioException("Email inválido");
        }

        if (cliente.getEmail().length() > 30) {

            throw new ValidacaoNegocioException("Email muito longo");
        }

        if (cliente.getStatus() == null) {

            throw new ValidacaoNegocioException("Status inválido");
        }
    }
}
