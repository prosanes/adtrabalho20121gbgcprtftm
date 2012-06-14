package br.ufrj.dcc.controle;

import java.util.ArrayList;

import br.ufrj.dcc.modelo.Configuracao;
import br.ufrj.dcc.modelo.Evento;
import br.ufrj.dcc.modelo.Fregues;
import br.ufrj.dcc.modelo.Resultado;
import br.ufrj.dcc.modelo.Servidor;
import br.ufrj.dcc.modelo.enumarator.CorCliente;
import br.ufrj.dcc.modelo.fila.FCFS;
import br.ufrj.dcc.modelo.fila.Fila;
import br.ufrj.dcc.modelo.fila.LCFS;

public class Rodada {

	private Resultado resultado;
	private Configuracao config;
	private Fila fila1 = null;
	private Fila fila2 = null;
	GerenciadoEventos gerenciador = null;
	Fregues freguesAntigo;
	
	public ArrayList<Double> listaTempos = new ArrayList<Double>();

	/**
	 * Construtor da classe.
	 * 
	 * @param config Recebe como parâmetro o objeto configuração que contém os valores dos parâmetros preenchidos pelo usuário.
	 * @param resultado Objeto que coleta o resultado de cada rodada.
	 */
	public Rodada(Configuracao config, Resultado resultado) {
		this.config = config;
		this.resultado = resultado;
	}

	/**
	 * Método que efetivamente realiza a rodada de simulação
	 * 
	 */
	public void simulacao() {
		//crio o gerenciador dos eventos dessa rodada
		gerenciador = new GerenciadoEventos();
		//crio o servidor do sistema passando como parâmetro a taxa de serviço e o gerenciador de eventos
		Servidor server = new Servidor(config.getAttrFila1(),config.getAttrFila2(), gerenciador);
		//esta variável passa armazenar o próximo evento.
		Evento evento = null;
		//variável que irá armazenar o instante atual no sistema
		double horaAtual = 0; 

		if (config.getAttrFila2().tipo.equalsIgnoreCase("FCFS")) {
			// cria a fila 2 com disciplina FCFS , a cada rodada criamos a fila de novo.
			fila2 = new FCFS(server);
			fila2.setTipoDeInterrupcao(config.getCasoInterrupcao());
		} else {
			// cria a fila 2 com disciplina LCFS , a cada rodada criamos a fila de novo.
			fila2 = new LCFS(server);
			fila2.setTipoDeInterrupcao(config.getCasoInterrupcao());
		}

		if (config.getAttrFila1().tipo.equalsIgnoreCase("FCFS")) {
			// cria a fila 1 com disciplina FCFS , a cada rodada criamos a fila de novo.
			fila1 = new FCFS(config.getUtilizacao() / 2.0, server, fila2);
		} else {
			// cria a fila 1 com disciplina LCFS , a cada rodada criamos a fila de novo.
			fila1 = new LCFS(config.getUtilizacao() / 2.0, server, fila2);
		}

		if(config.getFasetransiente() > 0) {
			//gera um evento de chegada para começar a simulação e o marca como sendo da fase transiente.
			gerenciador.geraEventoDeChegada(0.0, fila1, CorCliente.TRANSIENTE);
		} else {
			//gera um evento de chegada para começar a simulação e o marca como sendo da fase não transiente.
			gerenciador.geraEventoDeChegada(0.0, fila1, CorCliente.NAOTRANSIENTE);
		}
		//double tempo = 0;
		double tempoInicial = 0;
		
		//variável que armazena a quantidade de chegadas  no sistema.
		int i = 1;
		while((evento = gerenciador.pegarProximoEvento()) != null) {
			//coloca na variável horaAtual o instante de ocorrência do evento

			horaAtual = evento.getHoraOcorrencia();
			//se o evento for de chegada
			if (evento.isEventoChegada()) {
				// se a variável que armazena a quantidade de chegadas no
				// sistema for menor que o tamanho da rodada, ou seja a
				// quantidade de chegadas no sistema
				if(i <= config.getTamanhorodadas()) {
					evento.getFregues().chegarNoSistema(fila1, horaAtual);

					// se a quantidade de chegadas for menor que o tamanho da fase transiente
					if (i < config.getFasetransiente() - 1) {
						// gero um evento de chegada marcando o cliente como transiente.
						gerenciador.geraEventoDeChegada(horaAtual, fila1, CorCliente.TRANSIENTE);
						tempoInicial = horaAtual;
					} else {
						// senão gero um evento de chegada mas marco o cliente
						// como não transiente e a partir de agora será computada
						// a estatística
						gerenciador.geraEventoDeChegada(horaAtual, fila1, CorCliente.NAOTRANSIENTE);

						//insiro na lista de tempos
						listaTempos.add(horaAtual);
						
					}
					// incrementa a quantidade de chegadas
					i++;
					
				} else {
					// se já tiver gerado chegadas suficiente seto o próximo evento de chegada para null.
					gerenciador.proximoEventoChegada = null;
				}
			} else if (evento.isEventoSaida()) {
				// trata o evento de saída e devolvo a área para fazer o cálculo de Nq1 e Nq2
				evento.getServidor().finish(fila1, fila2, horaAtual);
			}			
		}
		// pega os resultados desta rodada.
		server.geraResultado(resultado, config, horaAtual, tempoInicial);
	}
}
