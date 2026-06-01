/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.NaoEncontradoException;
import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.Os;
import br.unipar.eletrofluxo.repository.OsRepository;
import br.unipar.eletrofluxo.repository.interfaces.OsRepositoryInterface;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class OsService {

    private OsRepositoryInterface osRepository;

    public OsService() {
        this.osRepository = new OsRepository();
    }

    public Os inserir(Os os) throws ValidacaoNegocioException, SQLException {

        validar(os);

        return osRepository.inserir(os);
    }

    public Os atualizar(Os os) throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (os == null) {
            throw new ValidacaoNegocioException("Informe a OS");
        }

        if (os.getId() == null) {
            throw new ValidacaoNegocioException("Informe o ID da OS");
        }

        Os osBanco = osRepository.findById(os.getId());

        if (osBanco == null) {
            throw new NaoEncontradoException("OS não encontrada");
        }

        validar(os);

        return osRepository.atualizar(os);
    }

    public void deletar(Long id) throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (id == null) {
            throw new ValidacaoNegocioException("Informe o ID da OS");
        }

        Os os =osRepository.findById(id);

        if (os == null) {
            throw new NaoEncontradoException("OS não encontrada");
        }

        osRepository.deletar(id);
    }

    public Os findById(Long id)throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (id == null) {
            throw new ValidacaoNegocioException("Informe o ID da OS");
        }

        Os os =osRepository.findById(id);

        if (os == null) {
            throw new NaoEncontradoException("OS não encontrada");
        }

        return os;
    }

    public ArrayList<Os> listarTodos()throws SQLException {

        return osRepository.listarTodos();
    }

    public void validar(Os os)
            throws ValidacaoNegocioException {

        if (os == null) {
            throw new ValidacaoNegocioException("Informe a OS");
        }

        if (os.getCliente() == null) {
            throw new ValidacaoNegocioException("Cliente é obrigatório");
        }

        if (os.getDescricao() == null
                || os.getDescricao().isBlank()) {
            throw new ValidacaoNegocioException("Descrição é obrigatória");
        }

        if (os.getDescricao().length() > 60) {
            throw new ValidacaoNegocioException("Descrição deve ter no máximo 60 caracteres");
        }

        if (os.getEndereco() == null) {
            throw new ValidacaoNegocioException("Endereço é obrigatório");
        }

        if (os.getStatus() == null) {
            throw new ValidacaoNegocioException("Status é obrigatório");
        }

        if (os.getDataAbertura() == null) {
            throw new ValidacaoNegocioException("Data de abertura obrigatória");
        }

        if (os.getDataTermino() != null
                && os.getDataTermino().before(os.getDataAbertura())) {

            throw new ValidacaoNegocioException("Data de término inválida");
        }

        if (os.getZona() == null) {
            throw new ValidacaoNegocioException("Zona é obrigatória");
        }
    }
}
