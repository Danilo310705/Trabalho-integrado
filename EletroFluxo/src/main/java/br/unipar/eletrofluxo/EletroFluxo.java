/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.unipar.eletrofluxo;

import br.unipar.eletrofluxo.enums.StatusEnum;
import br.unipar.eletrofluxo.model.ClienteFisico;
import br.unipar.eletrofluxo.model.ClienteJuridico;
import br.unipar.eletrofluxo.model.Os;
import br.unipar.eletrofluxo.model.Produto;
import br.unipar.eletrofluxo.model.ProdutoOs;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class EletroFluxo {

    public static void main(String[] args) {
        
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
        cliente1.setEndereco("la onde judas bateu as botas");
        cliente1.setStatus(StatusEnum.ABERTURA);
        cliente1.setTelefone("05002026007");
        cliente1.setObservacoes("");
        
        ClienteJuridico cliente2 = new ClienteJuridico();
        cliente2.setNome("LTDA dos LTDA");
        cliente2.setNomeFantasia("o Super Dos LTDA");
        cliente2.setCnpj("12.123.324/0001-59");
        cliente2.setEmail("ltadaxsemlimites@gmail.com");
        cliente2.setEndereco("bem ali, o");
        cliente2.setStatus(StatusEnum.ABERTURA);
        cliente2.setTelefone("1903824756");
        cliente2.setObservacoes("so a vista");
        
        //PRODUTOS QUE VAO NA ORDEM DE SERVIÇO COM A LISTA
        
        ProdutoOs parafusoOs1 = new ProdutoOs();
        parafusoOs1.setProdutoOs(parafuso);
        parafusoOs1.setQuantidade(10);
        parafusoOs1.getSubTotal();
        
        ProdutoOs porcaOs1 = new ProdutoOs();
        porcaOs1.setProdutoOs(porca);
        porcaOs1.setQuantidade(10);
        porcaOs1.getSubTotal();
        
        
        ArrayList<ProdutoOs> produtosOs1 = new ArrayList<>();
        produtosOs1.add(parafusoOs1);
        produtosOs1.add(porcaOs1);
        
        Os servico1 = new Os();
        servico1.setCliente(cliente1);
        servico1.setDataAbertura(null);
        servico1.setDataTermino(null);
        servico1.setDescricao("Ricardão quiqué faze a fiação da casa dele");
        servico1.setStatus(StatusEnum.ABERTURA);
        servico1.setProdutos(/*por enquanto*/ null);
        
        /*System.out.println(parafuso);
        System.out.println(cliente1);
        System.out.println(cliente2);
        System.out.println(produtosOs1);*/
        System.out.println(servico1);
        
    }
}
