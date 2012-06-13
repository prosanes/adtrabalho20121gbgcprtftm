package br.ufrj.dcc.modelo.fila;

import java.util.Collection;

import br.ufrj.dcc.controle.GeradorAleatorio;
import br.ufrj.dcc.modelo.Fregues;
import br.ufrj.dcc.modelo.Servidor;

/**
 * Classe que representa as filas.
 *
 */
public abstract class Fila {

	protected double lambda=0.0;
	protected Fila preemptiva;
	protected Servidor servidor;
	protected Collection<Fregues> fregueses;

	protected int numeroTotalDeChegadas;
	protected int numeroFreguesesNaFila;
	
	protected int tipoDeInterrupcao;


	/**
	 * Construtor utilizado para a fila2 porque nela não há chegadas.
	 * 
	 * @param servidor Servido em que o freguês irá ser tratado.
	 */
	public Fila(Servidor servidor) {
		numeroTotalDeChegadas = 0;
		this.servidor = servidor;
	}

	/**
	 * Construtor utilizado para a fila1. 
	 * 
	 * @param lambda Taxa de chegada dos fregueses ao sistema.
	 * @param servidor Servidor que atende está fila.
	 * @param preemptiva Fila na qual tenho maior prioridade e posso tirar o cliente dessa do serviço.
	 */
	public Fila(double lambda, Servidor servidor, Fila preemptiva) {
		// taxa de chegada de fregueses ao sistema
		this.lambda = lambda;
		// amazena o servidor
		this.servidor = servidor;
		// a fila qual tenho maior prioridade
		this.preemptiva = preemptiva;
		// número de chegadas ao sistema
		numeroTotalDeChegadas = 0;
		// número que estão no sistema no momento
		numeroFreguesesNaFila = 0;
	}

	// metódos que deveram ser implementados pelas classes que herdam desta, que são FCFS e LCFS
	public abstract Fregues removerParaAtendimento();

	// metódos que deveram ser implementados pelas classes que herdam desta, que são FCFS e LCFS
	public abstract void addFregues(Fregues fregues);

	// metódos que deveram ser implementados pelas classes que herdam desta, que são FCFS e LCFS
	public abstract void addFreguesResidual(Fregues fregues);

	/**
	 * Método utilizado apenas pela fila 1 pois cálcula quando irá ocorrer o
	 * próximo evento de chegada.
	 * 
	 * @return Retorna o instante de chegada do cliente.
	 * 
	 */
	public Double calculaProximaChegada() {
		if (lambda != 0.0) {
			// gera um chegada apenas se a fila for a 1
			return GeradorAleatorio.getInstance().getGeraAmostra(this.lambda);
		}
		return 0.0;
	}

	/**
	 * Diz se a fila está vazia ou não
	 * 
	 * @return Retorna true se está vazia ou false se não está vazia.
	 * 
	 */
	public boolean isEmpty() {
		// retorna true e a fila está vazia e false senão
		return fregueses.isEmpty();
	}

	/**
	 * Diz quantos clientes estão na fila 
	 * 
	 * @return Retorna um inteiro dizendo a quantidade de clientes que estão na fila no instante da chamada.
	 * 
	 */
	public int size() {
		// quantidade de fregueses na fila no momento que chama esta função
		return fregueses.size();
	}

	/**
	 * Diz quantos clientes estão no sistema desde o ínicio da rodada.
	 * 
	 * @return Retorna um inteiro dizendo a quantidade de clientes chegaram no
	 *         sistema desde o íncio da rodada.
	 * 
	 */
	public int getNumeroTotalDeChegadas() {
		// retorna o número total de chegadas
		return numeroTotalDeChegadas;
	}

	/**
	 * 
	 * Retorna a taxa de chegada dos freguêses para a fila1. O valor calculado 
	 * graças ao xml que informa a utilização e sabendo que rô é igual a 
	 * 2*lambda. <br> Caso a fila seja a fila2 será retornado 0.0
	 * 
	 * @return Retorna a taxa com que os fregueses chegam ao sistema.
	 * */
	public double getLambda() {
		// retorna a taxa de chegada dos freguês para fila um o valor calculado
		// graças ao xml que informa a utilização e sabendo que rô é igual a
		// 2*lambda
		// caso a fila seja a fila2 será retornado 0.0
		return lambda;
	}

	/**
	 * Diz quantos clientes estão na fila no instante da chamada 
	 * 
	 * @return Retorna um inteiro dizendo a quantidade de clientes estão na fila
	 * */
	public int getNumeroFreguesesNaFila() {
		// quantidade de fregueses na fila no momento que chama esta função
		return numeroFreguesesNaFila;
	}

	/**
	 * Preenche o valor da variável numeroFreguesesNaFila 
	 * 
	 * @param numeroFreguesesNaFila Recebe o quantidade de cliente na fila.
	 * */
	public void setNumeroFreguesesNaFila(int numeroFreguesesNaFila) {
		// coloca na variável o valor passado como parâmetro.
		this.numeroFreguesesNaFila = numeroFreguesesNaFila;
	}

	/**
	 * 
	 * @return retorna o servidor.
	 */
	public Servidor getServidor() {
		return servidor;
	}


	public int getTipoDeInterrupcao() {
		return tipoDeInterrupcao;
	}

	public void setTipoDeInterrupcao(int tipoDeInterrupcao) {
		this.tipoDeInterrupcao = tipoDeInterrupcao;
	}


}
