package br.ufrj.dcc.controle;

import br.ufrj.dcc.modelo.Evento;
import br.ufrj.dcc.modelo.Fregues;
import br.ufrj.dcc.modelo.Servidor;
import br.ufrj.dcc.modelo.enumarator.CorCliente;
import br.ufrj.dcc.modelo.enumarator.FilaOrigem;
import br.ufrj.dcc.modelo.enumarator.TipoEvento;
import br.ufrj.dcc.modelo.fila.Fila;

/**
 * Classe que gerência todos os eventos do sistema que podem ser: CHEGADA, 
 * SAÍDA ou INTERRUPCAO.
 * 
 */
public class GerenciadoEventos {

	Evento proximoEventoSaida = null;
	Evento proximoEventoChegada = null;

	/**
	 * Método que serve para gerar um evento de saída, que consiste num evento
	 * onde o cliente sai da sua fila e vai para o servidor.
	 * 
	 * @param horaAtual Instante atual no sistema
	 * @param cliente O cliente que está gerando este evento
	 * @param server O servidor ao qual será destinado o cliente. Este parâmetro
	 *            serve basicamente para fazer o cálculo do tempo de serviço.
	 */
	public void geraEventoSaida(double horaAtual, Fregues cliente, Servidor server) {
		double tempoServico = server.tempoServico(cliente.getFilaAtual());
		// verifica se o cliente veio da fila1
		if (cliente.getFilaAtual() == FilaOrigem.FILA1) {
			cliente.setTempoInicialDeEntradaNaFila2(horaAtual + tempoServico);
			// preenche o campo instânte de saida do freguês da fila1
			cliente.setInstanteSaidaFila1(horaAtual);
			// preenche o instande de chegada na fila 2 com o tempo atual mais o
			// tempo do serviço
			cliente.setInstanteChegadaFila2(horaAtual + tempoServico);
		} else {// se a origem do cliente for a fila2

			// preenchemos o instante de saída da fila2 como o instante atual
			cliente.setInstanteSaidaFila2(horaAtual);
			// preenchemos o instante de saída do cliente do sistem com o tempo
			// atual mais o tempo de serviço
			cliente.setInstanteSaidaSistema(horaAtual + tempoServico);
		}
		// cria efetivamente o evento de saída do sistema e coloca esse evento
		// como o próximo evento de saída.
		proximoEventoSaida = new Evento(horaAtual + tempoServico, TipoEvento.SAIDA, server);
		// preenche o tempo de serviço do evento no servidor
		server.setTerminoServico(horaAtual + tempoServico);
	}

	/**
	 * Método que serve para realizar a chegada de um Freguês ao sistema.
	 * 
	 * @param horaAtual Instante atual no sistema
	 * @param Fila A fila a qual o cliente entra ao chegar no sistema que no
	 *            nosso caso chamamos de fila1
	 * @param transiente Este parâmetro serve para determinar a cor do cliente que no
	 *            nosso caso pode ser "TRANSIENTE" ou "NAOTRANSIENTE".
	 */
	public void geraEventoDeChegada(double horaAtual, Fila fila, CorCliente transiente) {
		// cálcula quando chegará um cliente no sistema
		double tempoAteChegada = fila.calculaProximaChegada();
		// cria um objeto da Classe Fregues chamado fregues
		Fregues fregues = new Fregues();
		// preenche o campo cor do objeto com o valor passado como parâmetro.
		fregues.setCor(transiente);
		// cria efetivamente o evento e o coloca como próximo evento de chegada
		proximoEventoChegada = new Evento(horaAtual + tempoAteChegada, TipoEvento.CHEGADA, fregues);
	}

	/**
	 * Método de gerencia um evento de saída quando o cliente já sofreu uma
	 * interrupção, este evento ocorre quando há um chegada no sistema e tem um
	 * Freguês da fila2 no servidor gerando um resíduo de serviço para o mesmo.
	 * 
	 * @param horaAtual Instante atual no sistema
	 * @param residuo Quanto falta para o Freguês que está no servidor para
	 *            completar o serviço
	 * @param cliente Freguês que está chegando ao sistema
	 * @param servidor O servidor do sistema
	 */
	public void geraEventoDeInterrupcao(double horaAtual, double residuo, Fregues cliente, Servidor server) {
		// atualiza o campo do fregues com o novo instante que irá sair da fila2
		cliente.setInstanteSaidaFila2(horaAtual);
		// atualiza o valor do campo com a instante atual mais o resíduo do serviço
		cliente.setInstanteSaidaSistema(horaAtual + residuo);
		// coloca este evento como o próximo evento de saída.
		proximoEventoSaida = new Evento(horaAtual + residuo, TipoEvento.SAIDA, server);
		// preenche o tempo de serviço do evento no servidor
		server.setTerminoServico(horaAtual + residuo);
	}

	/**
	 * Método que retorna o próximo evento a ser tratado.
	 * 
	 * @return Retorna o próximo evento a ser tratado.
	 */
	public Evento pegarProximoEvento() {
		// se não tem nehum evento para ser tratado retorno null
		if (proximoEventoChegada == null && proximoEventoSaida == null) {
			return null;
		}
		// se não tenho um evento de saída, então retorno o meu evento de chegada
		if (proximoEventoSaida == null) {
			return proximoEventoChegada;
		}
		// se não tenho um evento de chegada, então retorno o meu evento de saída
		if (proximoEventoChegada == null) {
			// pego o evento de saída
			Evento evento = proximoEventoSaida;
			// coloco o valor null na variável
			proximoEventoSaida = null;
			// retorno o evento
			return evento;
		}
		if (proximoEventoChegada.getHoraOcorrencia() < proximoEventoSaida.getHoraOcorrencia()) {
			// se o evento de chegada ocorrer antes do de saída, retorno o de chegada
			return proximoEventoChegada;
		}
		// se o evento de saída ocorrer antes do de chegada, retorno o de saída
		Evento evento = proximoEventoSaida;
		// coloco o valor null na variável
		proximoEventoSaida = null;
		// retorno o evento
		return evento;
	}

	/**
	 * Retorna o próximo evento de saida
	 * 
	 * @return Retorna uma variável do tipo Evento.
	 */
	public Evento getProximoEventoSaida() {
		// retorna o próximo evento de saída.
		return proximoEventoSaida;
	}
}
