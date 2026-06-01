/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.eletrofluxo.service;

import br.unipar.eletrofluxo.exceptions.NaoEncontradoException;
import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.Produto;
import br.unipar.eletrofluxo.repository.ProdutoRepository;
import br.unipar.eletrofluxo.repository.interfaces.ProdutoRepositoryInterface;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
 class ProdutoService {

    private ProdutoRepositoryInterface produtoRepository;

    public ProdutoService() {
        this.produtoRepository = new ProdutoRepository();
    }

    public Produto inserir(Produto produto) throws ValidacaoNegocioException, SQLException {

        validar(produto);

        return produtoRepository.inserir(produto);
    }

    public Produto atualizar(Produto produto)throws ValidacaoNegocioException,
             NaoEncontradoException,
            SQLException {

        if (produto == null) {
            throw new ValidacaoNegocioException("Informe o produto");
        }

        if (produto.getId() == null) {
            throw new ValidacaoNegocioException("Informe o ID do produto");
        }

        Produto produtoBanco = produtoRepository.findById(produto.getId());

        if (produtoBanco == null) {
            throw new NaoEncontradoException("Produto não encontrado");
        }

        validar(produto);

        return produtoRepository.atualizar(produto);
    }

    public void deletar(Long id)throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (id == null) {
            throw new ValidacaoNegocioException("Informe o ID do produto");
        }

        Produto produto = produtoRepository.findById(id);

        if (produto == null) {
            throw new NaoEncontradoException("Produto não encontrado");
        }

        produtoRepository.deletar(id);
    }

    public Produto findById(Long id)throws ValidacaoNegocioException,
            NaoEncontradoException,
            SQLException {

        if (id == null) {
            throw new ValidacaoNegocioException("Informe o ID do produto");
        }

        Produto produto = produtoRepository.findById(id);

        if (produto == null) {
            throw new NaoEncontradoException("Produto não encontrado");
        }

        return produto;
    }

    public ArrayList<Produto> listarTodos() throws SQLException {

        return produtoRepository.listarTodos();
    }

    public void validar(Produto produto)throws ValidacaoNegocioException {

        if (produto == null) {
            throw new ValidacaoNegocioException("Informe o produto");
        }

        if (produto.getNome() == null || produto.getNome().isBlank()) {

            throw new ValidacaoNegocioException("Informe o nome do produto");
        }

        if (produto.getNome().length() < 1) {

            throw new ValidacaoNegocioException("O produto não pode ter menos de 1 caractere");
        }

        if (produto.getNome().length() > 40) {

            throw new ValidacaoNegocioException("O produto não pode ter mais de 40 caracteres");
        }

        if (produto.getPrecoUnitario() == null || produto.getPrecoUnitario() <= 0) {

            throw new ValidacaoNegocioException("Valor não pode ser nulo ou negativo");
        }

        if (produto.getUnidade() == null) {

            throw new ValidacaoNegocioException("Necessário informar a unidade");
        }
    }
}