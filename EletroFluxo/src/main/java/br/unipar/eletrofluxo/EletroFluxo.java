/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.unipar.eletrofluxo;

import br.unipar.eletrofluxo.enums.StatusClienteEnum;
import br.unipar.eletrofluxo.enums.StatusOsEnum;
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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class EletroFluxo {

    public static void main(String[] args) {
        
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
        parafuso.setUnidade("un");
        parafuso.setFornecedor("Carloparuso");
        parafuso.setPrecoUnitario(0.50);
        parafuso.setQuantidade(500);
        
        Produto porca = new Produto();
        porca.setNome("Porca");
        porca.setUnidade("un");
        porca.setFornecedor("Carloparuso");
        porca.setPrecoUnitario(0.10);
        porca.setQuantidade(1000);     
        
        Produto fio = new Produto();
        fio.setNome("Fio");
        fio.setUnidade("mt");
        fio.setFornecedor("fioZera do Tiao");
        fio.setPrecoUnitario(0.70);
        fio.setQuantidade(200);  
        
        Produto clipe = new Produto();
        clipe.setNome("clipe");
        clipe.setUnidade("un");
        clipe.setFornecedor("fioZera do Tiao");
        clipe.setPrecoUnitario(0.75);
        clipe.setQuantidade(150);  
        
        
        //CLIENTES
        
        ClienteFisico cliente1 = new ClienteFisico();
        cliente1.setNome("Ricardo");
        cliente1.setCpf("120.324.956-78");
        cliente1.setEmail("gurilaosafaso@gmail.com");
        cliente1.setStatus(StatusClienteEnum.ATIVO);
        cliente1.setTelefone("05002026007");
        cliente1.setObservacoes("");
        
        ClienteJuridico cliente2 = new ClienteJuridico();
        cliente2.setNome("LTDA dos LTDA");
        cliente2.setNomeFantasia("o Super Dos LTDA");
        cliente2.setCnpj("12.123.324/0001-59");
        cliente2.setEmail("ltadaxsemlimites@gmail.com");
        cliente2.setStatus(StatusClienteEnum.ATIVO);
        cliente2.setTelefone("1903824756");
        cliente2.setObservacoes("so a vista");
        
        // enderecos
        
        Endereco endereco1Cliente1 = new Endereco();
        endereco1Cliente1.setCliente(cliente1);
        endereco1Cliente1.setCidade(toledo);
        endereco1Cliente1.setBairro("Pinheiriho");
        endereco1Cliente1.setRua("Fontana");
        endereco1Cliente1.setNumero(1252);
        
        Endereco endereco2Cliente1 = new Endereco();
        endereco2Cliente1.setCliente(cliente1);
        endereco2Cliente1.setCidade(Cascavel);
        endereco2Cliente1.setBairro("N sei nome das de la");
        endereco2Cliente1.setRua("muito menos");
        endereco2Cliente1.setNumero(532);
        
        Endereco endereco1Cliente2 = new Endereco();
        endereco1Cliente2.setCliente(cliente2);
        endereco1Cliente2.setCidade(toledo);
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
        Os1.setDataAbertura(null);
        Os1.setDataTermino(null);
        Os1.setDescricao("Ricardão quiqué faze a fiação da casa dele");
        Os1.setStatus(StatusOsEnum.ABERTURA);
        Os1.setProdutos(produtosOs1);
        Os1.setServicos(servicosOs1);
        Os1.gettotalOs();
        
        /*System.out.println(parafuso);
        System.out.println(cliente1);
        System.out.println(cliente2);
        System.out.println(produtosOs1);
        System.out.println(Os1);*/
        JOptionPane.showMessageDialog(null, Os1);
        
    }
}
