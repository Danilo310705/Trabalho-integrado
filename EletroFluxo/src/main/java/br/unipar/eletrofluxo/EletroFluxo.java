/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.unipar.eletrofluxo;

import br.unipar.eletrofluxo.enums.StatusClienteEnum;
import br.unipar.eletrofluxo.enums.StatusOsEnum;
import br.unipar.eletrofluxo.enums.UnidadeProdutoEnum;
import br.unipar.eletrofluxo.enums.ZonaEnum;
import br.unipar.eletrofluxo.exceptions.ValidacaoNegocioException;
import br.unipar.eletrofluxo.model.Cidade;
import br.unipar.eletrofluxo.model.ClienteFisico;
import br.unipar.eletrofluxo.model.ClienteJuridico;
import br.unipar.eletrofluxo.model.Endereco;
import br.unipar.eletrofluxo.model.Estado;
import br.unipar.eletrofluxo.model.Os;
import br.unipar.eletrofluxo.model.Produto;
import br.unipar.eletrofluxo.model.ItemProduto;
import br.unipar.eletrofluxo.model.ItemServico;
import br.unipar.eletrofluxo.model.Pais;
import br.unipar.eletrofluxo.model.Servico;
import br.unipar.eletrofluxo.repository.ProdutoRepository;
import br.unipar.eletrofluxo.service.ClienteFisicoService;
import br.unipar.eletrofluxo.service.ClienteJuridicoService;
import br.unipar.eletrofluxo.service.ClienteService;
import br.unipar.eletrofluxo.service.ProdutoService;
import br.unipar.eletrofluxo.service.ItemProdutoService;
import br.unipar.eletrofluxo.service.ItemServicoService;
import br.unipar.eletrofluxo.service.OsService;
import br.unipar.eletrofluxo.service.ServicoService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class EletroFluxo {

    public static void main(String[] args) {
        
        try{
            //paises, estados e cidades
            Pais brasil = new Pais();
            brasil.setPais("Brasil");
            brasil.setSigla("BR");

            Estado parana = new Estado();
            parana.setPais(brasil);
            parana.setEstado("Parana");
            parana.setSigla("PR");

            Cidade toledo = new Cidade();
            toledo.setEstado(parana);
            toledo.setCidade("Toledo");

            Cidade Cascavel = new Cidade();
            Cascavel.setEstado(parana);
            Cascavel.setCidade("Cascavel");


            //PRODUTOS NO ESTOQUE

            Produto parafuso = new Produto();
            parafuso.setNome("Parafuso");
            parafuso.setUnidade(UnidadeProdutoEnum.UN);
            parafuso.setPrecoUnitario(0.50);
            parafuso.setQuantidade(500);

            Produto porca = new Produto();
            porca.setNome("Porca");
            porca.setUnidade(UnidadeProdutoEnum.UN);
            porca.setPrecoUnitario(0.10);
            porca.setQuantidade(1000);     

            Produto fio = new Produto();
            fio.setNome("Fio");
            fio.setUnidade(UnidadeProdutoEnum.MT);
            fio.setPrecoUnitario(0.70);
            fio.setQuantidade(200);  

            Produto clipe = new Produto();
            clipe.setNome("clipe");
            clipe.setUnidade(UnidadeProdutoEnum.PC);
            clipe.setPrecoUnitario(0.75);
            clipe.setQuantidade(150);  


            //CLIENTES

            ClienteFisico cliente1 = new ClienteFisico();
            cliente1.setNome("Ricardo");
            cliente1.setCpf("12032495678");
            cliente1.setEmail("gurilaosafaso@gmail.com");
            cliente1.setStatus(StatusClienteEnum.ATIVO);
            cliente1.setTelefone("05002026007");
            cliente1.setObservacoes("");

            ClienteJuridico cliente2 = new ClienteJuridico();
            cliente2.setNome("LTDA dos LTDA");
            cliente2.setCnpj("12123324000159");
            cliente2.setEmail("ltadaxsemlimites@gmail.com");
            cliente2.setStatus(StatusClienteEnum.ATIVO);
            cliente2.setTelefone("1903824756");
            cliente2.setObservacoes("so a vista");

            // enderecos

            Endereco endereco1Cliente1 = new Endereco();
            endereco1Cliente1.setCliente(cliente1);
            endereco1Cliente1.setCidade(toledo);
            endereco1Cliente1.setCep("12353234");
            endereco1Cliente1.setBairro("Pinheiriho");
            endereco1Cliente1.setRua("Fontana");
            endereco1Cliente1.setNumero(1252);

            Endereco endereco2Cliente1 = new Endereco();
            endereco2Cliente1.setCliente(cliente1);
            endereco2Cliente1.setCidade(Cascavel);
            endereco1Cliente1.setCep("23452345");        
            endereco2Cliente1.setBairro("N sei nome das de la");
            endereco2Cliente1.setRua("muito menos");
            endereco2Cliente1.setNumero(532);

            Endereco endereco1Cliente2 = new Endereco();
            endereco1Cliente2.setCliente(cliente2);
            endereco1Cliente2.setCidade(toledo);
            endereco1Cliente1.setCep("12352345");   
            endereco1Cliente2.setBairro("Pinheiriho");
            endereco1Cliente2.setRua("Fontana");
            endereco1Cliente2.setNumero(1222);

            //PRODUTOS QUE VAO NA ORDEM DE SERVIÇO COM A LISTA

            ItemProduto parafusoOs1 = new ItemProduto();
            parafusoOs1.setProduto(parafuso);
            parafusoOs1.setQuantidade(10);
            parafusoOs1.getSubTotal();

            ItemProduto porcaOs1 = new ItemProduto();
            porcaOs1.setProduto(porca);
            porcaOs1.setQuantidade(10);
            porcaOs1.getSubTotal();

            ItemProduto fioOs1 = new ItemProduto();
            fioOs1.setProduto(fio);
            fioOs1.setQuantidade(100);
            fioOs1.getSubTotal();

            ItemProduto clipeOs1 = new ItemProduto();
            clipeOs1.setProduto(clipe);
            clipeOs1.setQuantidade(8);
            clipeOs1.getSubTotal();




            ArrayList<ItemProduto> produtosOs1 = new ArrayList<>();
            produtosOs1.add(parafusoOs1);
            produtosOs1.add(porcaOs1);
            produtosOs1.add(fioOs1);
            produtosOs1.add(clipeOs1);


            // servicos que vao na ordem de servico

            Servico subfio = new Servico();
            subfio.setNome("susbtituicao de cabo de energia");
            subfio.setDescricao("sera trocado uma linha morta");
            subfio.setValorServico(1200.0);

            ItemServico subfioOs1 = new ItemServico();
            subfioOs1.setServico(subfio);
            subfioOs1.setQuantidade(2);

            Servico trocaDeDifusor = new Servico();
            trocaDeDifusor.setNome("troca de Difusor");
            trocaDeDifusor.setDescricao("troca de difusor de 120 alguma coisa para 300 alguma coisa, em linha viva");
            trocaDeDifusor.setValorServico(5000.0);

            ItemServico trocaDeDifusorOs1 = new ItemServico();
            trocaDeDifusorOs1.setServico(trocaDeDifusor);
            trocaDeDifusorOs1.setQuantidade(1);

            ArrayList<ItemServico> servicosOs1 = new ArrayList<>();
            servicosOs1.add(subfioOs1);
            servicosOs1.add(trocaDeDifusorOs1);

            Os Os1 = new Os();
            Os1.setCliente(cliente1);
            Os1.setDataAbertura(new Date());
            Os1.setDataTermino(new Date());
            Os1.setDescricao("Ricardão quiqué faze a fiação da casa dele");
            Os1.setStatus(StatusOsEnum.ABERTURA);
            Os1.setZona(ZonaEnum.Urbana);
            Os1.setEndereco(endereco1Cliente2);
            Os1.setProdutos(produtosOs1);
            Os1.setServicos(servicosOs1);
            Os1.getTotalOs();
            
            ProdutoService produtoService = new ProdutoService();
            ItemProdutoService itemProdutoService = new ItemProdutoService();
            ServicoService servicoService = new ServicoService(); 
            ItemServicoService itemServicoService = new ItemServicoService();
            ClienteService clienteService = new ClienteService();
            ClienteFisicoService clienteFisicoService = new ClienteFisicoService();
            ClienteJuridicoService clienteJuridicoService = new ClienteJuridicoService();
            OsService osService = new OsService();
            
            produtoService.validar(parafuso);
            produtoService.validar(porca);
            produtoService.validar(fio);
            produtoService.validar(clipe);

            itemProdutoService.validar(parafusoOs1);
            itemProdutoService.validar(porcaOs1);
            itemProdutoService.validar(fioOs1);
            itemProdutoService.validar(clipeOs1);

            servicoService.validar(subfio);
            servicoService.validar(trocaDeDifusor);

            itemServicoService.validar(subfioOs1);
            itemServicoService.validar(trocaDeDifusorOs1);

            clienteService.validar(cliente1);
            clienteFisicoService.validar(cliente1);

            clienteService.validar(cliente2);
            clienteJuridicoService.validar(cliente2);

            osService.validar(Os1);


            ProdutoRepository produtoRepository = new ProdutoRepository();
            //produtoRepository.inserir(clipe);
            //produtoRepository.deletar(3L);
            //Produto p = produtoRepository.findById(4L);
            //Produto produto = produtoRepository.findById(4L);
            //produto.setQuantidade(999);
            
            //JOptionPane.showMessageDialog(null, produto);
        
        } catch(ValidacaoNegocioException exception){
            JOptionPane.showMessageDialog(null,"Ocorreu um erro ao executar o sistema: " + exception.getMessage());
        }/*catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erro de banco: " + e.getMessage());
        }*/finally {
            JOptionPane.showMessageDialog(null, "encerrando cessao");
        }
    }
}

            /*Produto teste = new Produto();
            teste.setNome("teste");
            teste.setUnidade(UnidadeProdutoEnum.UN);
            teste.setPrecoUnitario(5.0);
            teste.setQuantidade(500);
            
            ItemProduto teste1 = new ItemProduto();
            teste1.setProduto(teste);
            teste1.setQuantidade(500);
            teste1.getSubTotal();
            
            Servico teste2 = new Servico();
            teste2.setDescricao("12345 caracteres");
            teste2.setNome("Teste2");
            teste2.setValorServico(1.0);
            
            ItemServico teste3 = new ItemServico();
            teste3.setServico(teste2);
            teste3.setQuantidade(1);
            teste3.getSubTotal();
            
            ClienteFisico teste4 = new ClienteFisico();
            teste4.setNome("Ricardo");
            teste4.setCpf("12036595679");
            teste4.setEmail("gurilaosafaso@gmail.com");
            teste4.setStatus(StatusClienteEnum.ATIVO);
            teste4.setTelefone("0500202601");
            teste4.setObservacoes("");
            
            ClienteJuridico teste5 = new ClienteJuridico();
            teste5.setNome("Ricardo");
            teste5.setNomeFantasia("isso é o fantasia de teste");
            teste5.setCnpj("12036595671239");
            teste5.setEmail("gurilaosafaso@gmail.com");
            teste5.setStatus(StatusClienteEnum.ATIVO);
            teste5.setTelefone("0500202601");
            teste5.setObservacoes("");
            
            ArrayList<ItemProduto> teste6 = new ArrayList<>();
            teste6.add(teste1);
            
            ArrayList<ItemServico> teste7 = new ArrayList<>();
            teste7.add(teste3);
            
            Os teste8 = new Os();
            teste8.setCliente(teste4);
            teste8.setDataAbertura(new Date());
            teste8.setDataTermino(null);
            teste8.setDescricao("edrfgdfg");
            teste8.setEndereco(endereco1Cliente2);
            teste8.setStatus(StatusOsEnum.ABERTURA);
            teste8.setZona(ZonaEnum.Urbana);
            teste8.setProdutos(teste6);
            teste8.setServicos(teste7);
            teste8.getTotalOs();
            

            produtoService.validar(teste);            
            itemProdutoService.validar(teste1);
            servicoService.validar(teste2);
            itemServicoService.validar(teste3);
            clienteService.validar(teste5);
            clienteJuridicoService.validar(teste5);
            clienteService.validar(teste4);
            clienteFisicoService.validar(teste4);
            osService.validar(teste8);*/