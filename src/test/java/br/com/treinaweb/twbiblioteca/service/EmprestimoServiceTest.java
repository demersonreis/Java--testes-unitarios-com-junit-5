package br.com.treinaweb.twbiblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.treinaweb.twbiblioteca.builders.ClienteBuilder;
import br.com.treinaweb.twbiblioteca.builders.ObraBuilder;
import br.com.treinaweb.twbiblioteca.enums.Reputacao;
import br.com.treinaweb.twbiblioteca.enums.Tipo;
import br.com.treinaweb.twbiblioteca.models.Autor;
import br.com.treinaweb.twbiblioteca.models.Cliente;
import br.com.treinaweb.twbiblioteca.models.Obra;
import br.com.treinaweb.twbiblioteca.services.EmprestimoService;

public class EmprestimoServiceTest {

	private EmprestimoService emprestimoService;

	@BeforeEach
	void setUp() {
		emprestimoService = new EmprestimoService();
	}

	@Test
	void quandoMetodoNovoForChamadoDeveRetornaUmEmprestimo() {
		// Cenario de Teste
		var cliente = ClienteBuilder.builder().build();
		var obra = ObraBuilder.builder().build();
		// Execução
		var emprestimo = emprestimoService.novo(cliente, List.of(obra));

		// Verificação
		assertEquals(cliente, emprestimo.getCliente());
		assertEquals(List.of(obra), emprestimo.getLivros());
		assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
		assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());

	}

	@Test
	void quandoMetodoNovoForChamadoComClienteDeReputacaoRuimDeveRetornarUmEmprestimoComDevolucaoParaUmDia() {
		// Cenario de Teste
		var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "000.000.000-00", Reputacao.RUIM);
		var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
		var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

		// Execução
		var emprestimo = emprestimoService.novo(cliente, List.of(obra));

		// Verificação
		assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
	}

	@Test
	void quandoMetodoNovoForChamadoComClienteDeReputacaoRegularDeveRetornarUmEmprestimoComDevolucaoParaTresDias() {
		// Cenario de Teste
		var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "000.000.000-00", Reputacao.REGULAR);
		var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
		var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

		// Execução
		var emprestimo = emprestimoService.novo(cliente, List.of(obra));

		// Verificação
		assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
	}

	@Test
	void quandoMetodoNovoForChamadoComClienteDeReputacaoBoaDeveRetornarUmEmprestimoComDevolucaoParaCincoDias() {
		// Cenario de Teste
		var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "000.000.000-00", Reputacao.BOA);
		var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
		var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

		// Execução
		var emprestimo = emprestimoService.novo(cliente, List.of(obra));

		// Verificação
		assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
	}

	@Test
	void quandoMetodoNovoForChamadoComObraNulaDeveLancarUmaExcecaoDoTipoArgumentException() {
		// Cenario de Teste
		var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "000.000.000-00", Reputacao.REGULAR);

		var mensagemEsperada = "Obras não pode ser nula nem vazia";

		var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, null));
		assertEquals(mensagemEsperada, exception.getMessage());
	}

	@Test
	void quandoMetodoNovoForChamadoComObraVziaDeveLancarUmaExcecaoDoTipoArgumentException() {
		// Cenario de Teste
		var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "000.000.000-00", Reputacao.REGULAR);

		var obras = new ArrayList<Obra>();
		var mensagemEsperada = "Obras não pode ser nula nem vazia";

		var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, obras));
		assertEquals(mensagemEsperada, exception.getMessage());
	}

	@Test
	void quandoMetodoNovoForChamadoComClienteNuloDeveLancarUmaExcecaoDoTipoArgumentException() {
		// Cenario de Teste
		var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
		var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

		var mensagemEsperada = "Cliente não pode ser nulo";

		var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(null, List.of(obra)));
		assertEquals(mensagemEsperada, exception.getMessage());
	}

}
