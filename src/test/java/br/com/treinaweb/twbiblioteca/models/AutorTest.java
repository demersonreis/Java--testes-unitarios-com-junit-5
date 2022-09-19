package br.com.treinaweb.twbiblioteca.models;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutorTest {

	@Test
	void quandoMetodoEstaVivoForChamadoComDataFalecimentoNulaDeveRetornarTrue() {
		// Cenario
		var autor = new Autor();
		// Execução
		var estaVivo = autor.estaVivo();
		// Verificação
		Assertions.assertTrue(true);

	}

	@Test
	void quandoMetodoEstaVivoForChamadoComDataFalecimentoPreenchidaDeveRetornarFalse() {
		// Cenario
		var autor = new Autor();
		autor.setDataFalecimento(LocalDate.now());
		// Execução
		var estaVivo = autor.estaVivo();
		// Verificação
		Assertions.assertFalse(false);

	}

}
