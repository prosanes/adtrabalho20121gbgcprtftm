package br.ufrj.dcc.modelo;

import br.ufrj.dcc.controle.GeradorAleatorio;
import br.ufrj.dcc.controle.GerenciadoEventos;
import br.ufrj.dcc.modelo.enumarator.CorCliente;
import br.ufrj.dcc.modelo.enumarator.FilaOrigem;
import br.ufrj.dcc.modelo.fila.Fila;


/**
 * Classe que representa o servidor do nosso sistema.
 */
public class Servidor {
	
	AttrFila attrFila1;
	AttrFila attrFila2;
		
	// quanto tempo o cliente vai demorar para acabar o serviço
	private double terminoServico;
	// armazena o cliente que está sendo atendido
	private Fregues sendoAtendido = null;
	// geranciado dos eventos
	private GerenciadoEventos gerenciador;
	// armazena a soma dos W1 dos clientes
	private double somaw1 = 0.0;
	// armazena a soma dos W2 dos clientes
	private double somaw2 = 0.0;
	// armazena a soma dos T1 dos clientes
	private double somat1 = 0.0;
	// armazena a soma dos T2 dos clientes
	private double somat2 = 0.0;
	//armazena a soma dos quadrados dos tempos de espera na fila 1 para o cálculo da variância de W1
	private double somaQuadradosW1 = 0.0;
	//armazena a soma dos quadrados dos tempos de espera na fila 2 para o cálculo da variância de W2
	private double somaQuadradosW2 = 0.0;
	//armazena a quantidade de fregueses que tiveram as estatísticas coletadas
	private double n = 0;

	/**
	 * Construtor da classe.
	 * 
	 * @param attrFila1 atributos da fila1
	 * @param attrFila2 atributos da fila2
	 * @param gerenciador Classe que gerencia os eventos de saída e chegada do sistema.
	 */
	public Servidor(AttrFila attrFila1,AttrFila attrFila2, GerenciadoEventos gerenciador) {
		this.attrFila1 = attrFila1;
		this.attrFila2 = attrFila2;		
		this.gerenciador = gerenciador;
	}
	
	/**
	 * Método que gera quanto tempo demorará o serviço de um determinado cliente.
	 * 
	 * @return Retorna o tempo de serviço.
	 */
//	public double tempoServico() {
//		// gera o tempo de serviço de um determinado cliente
//		return GeradorAleatorio.getInstance().getGeraAmostra(mi);
//	}
	
	public double tempoServico(FilaOrigem origem) { 
		//gera o tempo de serviço de um determinado cliente
		double ret = 0;
		switch (origem) {
		case FILA1:
			if(attrFila1.distribuicaoServidor.equalsIgnoreCase("normal"))
			{
				ret = GeradorAleatorio.getInstance().getGeraAmostra(attrFila1.media, attrFila1.desvioPadrao);
			}
			else
			{
				ret = GeradorAleatorio.getInstance().getGeraAmostra(attrFila1.txServico, attrFila1.distribuicaoServidor);
			}
			break;
		case FILA2:
			if(attrFila2.distribuicaoServidor.equalsIgnoreCase("normal"))
			{
				ret = GeradorAleatorio.getInstance().getGeraAmostra(attrFila2.media, attrFila2.desvioPadrao);
			}
			else
			{
				ret =  GeradorAleatorio.getInstance().getGeraAmostra(attrFila2.txServico, attrFila2.distribuicaoServidor);
			}
			break;
		}
		return ret;
		
	}

	/**
	 * Método que diz se o servidor está ocioso ou não.
	 * 
	 * @return Retorna true se o sevidor está ocioso e false se está ocupado.
	 */
	public boolean isOcioso() {
		// verifica se o servidor está vazio e retorna true se está e false se não.
		return sendoAtendido == null;
	}

	/**
	 * Método que recebe um cliente e o trata.
	 * 
	 * @param fregues Fregues que será atendido pelo servidor.
	 * @param horaAtual Instante em que o sistema se encontra.
	 */
	public void receive(Fregues fregues, double horaAtual) {
		// coloca em em atendimento o freguês recebido com parâmetro
		sendoAtendido = fregues;
		// pega o tempo de serviço residual
		double residuo = this.sendoAtendido.getServicoResidual();
		// se for zero gera um evento de saída normal
		if (residuo == 0.0) {
			gerenciador.geraEventoSaida(horaAtual, sendoAtendido, this);
		} else {// se tiver resíduo de serviço gera uma interrupção.
			gerenciador.geraEventoDeInterrupcao(horaAtual, residuo, sendoAtendido, this);
		}
	}

	/**
	 * Retorna o Freguês que está sendo atendido pelo servidor.
	 * 
	 * @return Retorna o Freguês em atendimento.
	 */
	public Fregues getFregues() {
		// retorno o freguês que está sendo atendido.
		return this.sendoAtendido;
	}

	/**
	 * Retorna de qual fila veio o Freguês.
	 * 
	 * @return Retorna a váriavel filaAtual da Classe Fregues.
	 */
	public FilaOrigem getFilaOrigem() {
		// retorno a fila atual do freguês
		return sendoAtendido.getFilaAtual();
	}

	/**
	 * Método que verifica se tem alguém para ser servido na fila1 ou fila2.
	 * 
	 * @param fila1 Fila1 de maior prioridade no sistema. Clientes dessa fila
	 *            devem ser atendido primeiro.
	 * @param fila2 Todo cliente da fila1 depois de ser tratado vem para fila2
	 * @param horaAtual Instante em o sistema anterior.
	 *
	 * @return Retorna a área do gráfico para fazer o cálculo dos números
	 *         médios de clientes nas filas.
	 */
	public void finish(Fila fila1, Fila fila2, double horaAtual) {
		// cliente antigo agora é o cliente em atendimento
		Fregues clienteAntigo = this.sendoAtendido;
		// se a fila não estiver vazia
		if (!fila1.isEmpty()) {
			// pego o cliente que está na fila 1 para ser atendido porque é a
			// de maior prioridade
			receive(fila1.removerParaAtendimento(), horaAtual);

		} else if (!fila2.isEmpty()) {// caso a fila2 não estiver vazia e não
									 // tem niguém na fila1 pega um cliente da fila2 para servir

			// pegando o cliente
			receive(fila2.removerParaAtendimento(), horaAtual);
		} else {
			// se as duas filas estão vazias põem o servidor ocioso
			sendoAtendido = null;
		}
		// chamo quando o meu freguês acabou de ser servido
		servicoCompleto(clienteAntigo, fila2);
	}

	/**
	 * Este método é executado quando um cliente acabou o seu serviço.
	 * 
	 * @param clienteAntigo O cliente que estava sendo servido.
	 * @param fila Recebe a fila como parâmetro porque se a fila de origem do
	 *            cliente for a fila1 tenho que coloca-lo na fila2.
	 */
	private void servicoCompleto(Fregues clienteAntigo, Fila fila) {
		// se existe algum cliente que acabou o serviço
		if (clienteAntigo != null) {
			// se o cliente que acabou de ser servido veio da fila1
			if (clienteAntigo.getFilaAtual() == FilaOrigem.FILA1) {
				// coloca no campo filaAtual a fila2
				clienteAntigo.setFilaAtual(FilaOrigem.FILA2);
				// e adiciono o cliente na fila2
				fila.addFregues(clienteAntigo);
			} else {// se a fila de origem do cliente é a fila2

				// se o cliente não for transiente começo a somas o w1, w2, t1 e t2 de cada cliente.
				if (clienteAntigo.getCor() == CorCliente.NAOTRANSIENTE) {
					double w1 = clienteAntigo.getInstanteSaidaFila1() - clienteAntigo.getInstanteChegadaFila1();
					double w2 = clienteAntigo.getInstanteSaidaFila2() - clienteAntigo.getInstanteChegadaFila2();
					somaw1 += w1;
					somaw2 += w2;
					somat1 += clienteAntigo.getTempoInicialDeEntradaNaFila2() - clienteAntigo.getInstanteChegadaFila1();
					somat2 += clienteAntigo.getInstanteSaidaSistema() - clienteAntigo.getTempoInicialDeEntradaNaFila2();
					somaQuadradosW1 += w1*w1;
					somaQuadradosW2 += w2*w2;
					n++;
				}
			}
		}
	}

	/**
	 * Trata um evento de interrupção.
	 * 
	 * @param fregues O cliente que acabou de chegar ao sistema e que gerou a interrupção.
	 * @param fila Fila que devo devolver o Freguês que está em serviço.
	 */
	public void trataInterrupcao(Fregues fregues, Fila fila) {
		// pega quando acabaria o evento de saída
		double horaTermino = gerenciador.getProximoEventoSaida().getHoraOcorrencia();
		// coloca no campo servicoResidual do freguês que sofreu a interrupção
		// quanto tempo falta para acabar o serviço
		this.getFregues().setServicoResidual(horaTermino - fregues.getInstanteChegadaFila1());
		// soma o primeiro intervalo de tempo que o freguês ficou na fila2 para
		// armazenar o próximo intervalo de tempo
		if (this.getFregues().getCor().equals(CorCliente.NAOTRANSIENTE)) {
			Double w2 = this.getFregues().getInstanteSaidaFila2() - this.getFregues().getInstanteChegadaFila2();;
			somaw2 += w2;
			somaQuadradosW2 += w2*w2;
		}
		// atualiza o instante de chegada na fila2 para o tempo atual
		this.getFregues().setInstanteChegadaFila2(fregues.getInstanteChegadaFila1());
		// devolve o freguês interrompido para a fila2
		fila.addFreguesResidual(this.getFregues());
		// começa a tratar o freguês que acabou de ser recebido
		this.receive(fregues, fregues.getInstanteChegadaFila1());
	}

	/**
	 * Gera os resultados dessa rodada da simulação.
	 * 
	 * @param resultado Objeto que armazena o resultado das rodadas.
	 * @param config Objeto que armazena os parâmetros que podem variar no programa.
	 * @param areas Áreas calculadas na rodada para achar o Nq1 e Nq2.
	 * @param horaAnterior Último instante de um evento de saída gerado.
	 */
	public void geraResultado(Resultado resultado, Configuracao config, double horaAtual, double tempoInicial) {
		Double mediaW1 = somaw1 / (config.getTamanhorodadas() - config.getFasetransiente());
		Double mediaW2 = somaw2 / (config.getTamanhorodadas() - config.getFasetransiente());
		
		// adiciona ao array W1 o valor obtido nessa rodada
		resultado.setW1(mediaW1);
		// adiciona ao array W2 o valor obtido nessa rodada
		resultado.setW2(mediaW2);
		// adiciona ao array T1 o valor obtido nessa rodada
		resultado.setT1(somat1 / (config.getTamanhorodadas() - config.getFasetransiente()));
		// adiciona ao array T2 o valor obtido nessa rodada
		resultado.setT2(somat2 / (config.getTamanhorodadas() - config.getFasetransiente()));
		
		// cálculo do número médio de pessoas na fila1 - E[Nq1]
		resultado.setNq1((config.getTamanhorodadas() - config.getFasetransiente()) 
				/ (horaAtual - tempoInicial) * resultado.getW1().get(resultado.getW1().size() - 1));
		
		// cálculo do número médio de pessoas na fila2 - E[Nq2]
		resultado.setNq2((config.getTamanhorodadas() - config.getFasetransiente())
				/ (horaAtual - tempoInicial) * resultado.getW2().get(resultado.getW2().size() - 1));
		// cálculo do número médio de pessoas no sistema 1 - E[N1]
		resultado.setN1((config.getTamanhorodadas() - config.getFasetransiente())
				/ (horaAtual - tempoInicial)
				* resultado.getT1().get(resultado.getT1().size() - 1));
		// cálculo do número médio de pessoas no sistema 2 - E[N2]
		resultado.setN2((config.getTamanhorodadas() - config.getFasetransiente())
				/ (horaAtual - tempoInicial)
				* resultado.getT2().get(resultado.getT2().size() - 1));
		//cálculo da variância de W1
		resultado.setVarianciaW1(calculaVariancia(mediaW1,true));
		//cálculo da variância de W2
		resultado.setVarianciaW2(calculaVariancia(mediaW2,false));
	}

	/**
	 * @return Retorna qual é o tempo de serviço do cliente.
	 * 
	 */
	public double getTerminoServico() {
		// retorna o valor da váriavel término do serviço
		return terminoServico;
	}

	/**
	 * Preenche o valor do campo terminoServico.
	 * 
	 * @param terminoServico Novo tempo de serviço.
	 * 
	 */
	public void setTerminoServico(double terminoServico) {
		// preenche a variável terminoServico com o valor informado como parâmetro
		this.terminoServico = terminoServico;
	}
	
	/**
	 * Cálcula a variância
	 * 
	 * @param medias Coleção de valores que serão usados para fazer o cálcula da variância.
	 * @return retorna a variância.
	 */
	private Double calculaVariancia(Double media, Boolean isVarianciaW1) {
		if(isVarianciaW1) {
			return (this.somaQuadradosW1 - media*media*this.n)/(this.n-1);
		}
		else {
			return (this.somaQuadradosW2 - media*media*this.n)/(this.n-1);
		}
	}
}
