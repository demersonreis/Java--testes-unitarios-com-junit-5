package br.com.treinaweb.twbiblioteca.services;

import java.time.LocalDate;
import java.util.List;

import br.com.treinaweb.twbiblioteca.models.Cliente;
import br.com.treinaweb.twbiblioteca.models.Emprestimo;
import br.com.treinaweb.twbiblioteca.models.Obra;

public class EmprestimoService {

	// private static final int DIAS_PARA_DEVOLUCAO = 3;

	public Emprestimo novo(Cliente cliente, List<Obra> obras) {
		if (cliente == null) {
			throw new IllegalArgumentException("Cliente não pode ser nulo");
		}

		if (obras == null || obras.isEmpty()) {
			throw new IllegalArgumentException("Obras não pode ser nula nem vazia");
		}
		var emprestimo = new Emprestimo();

		var dataEmprestimo = LocalDate.now();
		var diasParaDevolucao = cliente.getReputacao().obterDiasParaDevolucao();
		var dataDevolucao = LocalDate.now().plusDays(diasParaDevolucao);

		emprestimo.setCliente(cliente);
		emprestimo.setLivros(obras);
		emprestimo.setDataEmprestimo(dataEmprestimo);
		emprestimo.setDataDevolucao(dataDevolucao);

		return emprestimo;
	}
}
