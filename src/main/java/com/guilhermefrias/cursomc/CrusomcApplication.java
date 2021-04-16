package com.guilhermefrias.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guilhermefrias.cursomc.domain.Categoria;
import com.guilhermefrias.cursomc.domain.Cidade;
import com.guilhermefrias.cursomc.domain.Cliente;
import com.guilhermefrias.cursomc.domain.Endereco;
import com.guilhermefrias.cursomc.domain.Estado;
import com.guilhermefrias.cursomc.domain.ItemPedido;
import com.guilhermefrias.cursomc.domain.Pagamento;
import com.guilhermefrias.cursomc.domain.PagamentoComBoleto;
import com.guilhermefrias.cursomc.domain.PagamentoComCartao;
import com.guilhermefrias.cursomc.domain.Pedido;
import com.guilhermefrias.cursomc.domain.Produto;
import com.guilhermefrias.cursomc.domain.enums.EstadoPagamento;
import com.guilhermefrias.cursomc.domain.enums.TipoCliente;
import com.guilhermefrias.cursomc.repositories.CategoriaRepository;
import com.guilhermefrias.cursomc.repositories.CidadeRepository;
import com.guilhermefrias.cursomc.repositories.ClienteRepository;
import com.guilhermefrias.cursomc.repositories.EnderecoRepository;
import com.guilhermefrias.cursomc.repositories.EstadoRepository;
import com.guilhermefrias.cursomc.repositories.ItemPedidoRepository;
import com.guilhermefrias.cursomc.repositories.PagamentoRepository;
import com.guilhermefrias.cursomc.repositories.PedidoRepository;
import com.guilhermefrias.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CrusomcApplication implements CommandLineRunner
{
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired 
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CrusomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p3 = new Produto(null,"Impressora", 800.00);
		Produto p2 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"Sao Paulo");
		Cidade c1 = new Cidade(null,"Uberlandia", est1);
		Cidade c2 = new Cidade(null,"Sao Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678911",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("12345678", "98765432"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "105", "sala 900", "Centro", "3877012", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Rio Branco", "200", "apto 201", "Jardim", "38220834", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("15/04/2021 10:31"),cli1, e1) ;
		Pedido ped2 = new Pedido(null, sdf.parse("16/04/2021 18:30"),cli1, e2 );
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/04/2021 00:00"), null);
		ped2.setPagamento(pagto2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00 );
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	
	}

}
