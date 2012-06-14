package br.ufrj.dcc.modelo;


public class Configuracao {
	
	private int fasetransiente;
	private int numerorodadas;
	private int tamanhorodadas;
	private double utilizacao;
	private int casoInterrupcao;
	private AttrFila attrFila1;
	private AttrFila attrFila2;
	

	/** 
	 * Construtor da classe de configurações.
	 * 
	 * @param fasetransiente   Quantidade de cliente que não farão parte do cálculo estatístico de  uma rodada.
	 * @param numerorodadas    Quantidade de rodadas que vamos realizar para mostrar o resultado da simulação.
	 * @param tamanhorodadas   Quantidade de eventos de chegada que vamos criar. 
	 * @param taxaservico      Taxa com que o servidor atende os cliente.
	 * @param utilizacao	   Taxa de  utilização do sistema.
	 * @param casoInterrupcao	   Qual é o tipo de comportamento após interrução de um cliente da fila 1 quando um cliente da fila 2 está no sistema.
	 * @param attrFila1		   Objeto que traz o atributo da fila 1.
	 * @param attrFila2	       Objeto que traz os atributos da fila 2.
	 */	
	public Configuracao(int fasetransiente, int numerorodadas, int tamanhorodadas, double utilizacao, int casoInterrupcao, AttrFila attrFila1, AttrFila attrFila2)
	{
		this.fasetransiente = fasetransiente;
		this.numerorodadas = numerorodadas;
		this.tamanhorodadas = tamanhorodadas;
		this.utilizacao = utilizacao;
		this.casoInterrupcao = casoInterrupcao;
		this.attrFila1 = attrFila1;
		this.attrFila2 = attrFila2;
	}
	
	/**
	 * Método que retorna o valor da fase transiente.
	 * 
	 * @return Retorna o valor da fase transiente.
	 */
	public int getFasetransiente() {
		return fasetransiente;
	}

	/**
	 * Método que retorna o valor do números de rodadas.
	 * 
	 * @return Retorna o valor do números de rodadas.
	 */
	public int getNumerorodadas() {
		return numerorodadas;
	}

	/**
	 * Método que retorna o valor do tamanho da rodada.
	 * 
	 * @return Retorna o valor do tamanho da rodada.
	 */
	public int getTamanhorodadas() {
		return tamanhorodadas;
	}

	/**
	 * Método que retorna o tipo da interrupção :  1 é o CASO 1 - O cliente interrumpido vai para o começo da fila 2 é o CASO 2 - O cliente interrompido vai para o final da fila
	 * 
	 * @return Retorna o valor do tipo de Interrupção
	 */
	public int getCasoInterrupcao() {
		return casoInterrupcao;
	}

	/**
	 * Método que retorna o valor da utilização do sistema.
	 * 
	 * @return Retorna o valor da utilização do sistema.
	 */
	public double getUtilizacao() {
		return utilizacao;
	}

	/**
	 * Método que retorna os dados de configuração da fila 1
	 * 
	 * @return Retorna os dados de configuração da fila 1
	 */
	public AttrFila getAttrFila1() {
		return attrFila1;
	}

	/**
	 * Método que retorna os dados de configuração da fila 1
	 * 
	 * @return Retorna os dados de configuração da fila 1
	 */
	public AttrFila getAttrFila2() {
		return attrFila2;
	}
}