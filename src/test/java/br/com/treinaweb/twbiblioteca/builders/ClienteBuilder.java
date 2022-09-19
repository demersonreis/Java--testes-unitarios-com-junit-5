package br.com.treinaweb.twbiblioteca.builders;

import java.time.LocalDate;

import br.com.treinaweb.twbiblioteca.enums.Reputacao;
import br.com.treinaweb.twbiblioteca.models.Cliente;

public class ClienteBuilder {
	private Cliente cliente;

	public static ClienteBuilder builder() {

		var builder = new ClienteBuilder();
		var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "000.000.000-00", Reputacao.RUIM);

		builder.cliente = cliente;
		return builder;

	}
	
	public Cliente build() {
		return cliente;
	}
}
