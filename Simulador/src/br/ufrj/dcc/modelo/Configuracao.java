package br.ufrj.dcc.modelo;

public class Configuracao {
	
	private int fasetransiente;
	private int numerorodadas;
	private int tamanhorodadas;
	private double utilizacao;
	private double taxaservico;
	private double media1;
	private double dp1;
	private String distribuicaoServidor1;
	private double taxaservico2;
	private double media2;
	private double dp2;
	private String distribuicaoServidor2;
	
	private String fila1;
	private String fila2;
	private int tipoDeInterrupcaoFila2;
	

	/** 
	 * Construtor da classe de configurações.
	 * 
	 * @param fasetransiente   Quantidade de cliente que não farão parte do cálculo estatístico de  uma rodada.
	 * @param numerorodadas    Quantidade de rodadas que vamos realizar para mostrar o resultado da simulação.
	 * @param tamanhorodadas   Quantidade de eventos de chegada que vamos criar. 
	 * @param taxaservico      Taxa com que o servidor atende os cliente.
	 * @param utilizacao	   Taxa de  utilização do sistema.
	 * @param fila1			Disciplina de atendimento da fila1.
	 * @param fila2	        Disciplina de atendimento da fila2.   
	 */
	public Configuracao(int fasetransiente, int numerorodadas, int tamanhorodadas, double taxaservico, 
			double utilizacao, String fila1, String fila2) {
		// armazena o tamanho da fase transiente
		this.fasetransiente = fasetransiente;
		// quantidade de rodadas que serão realizadas para poder se achar o
		// resultado da simulação
		this.numerorodadas = numerorodadas;
		// quantidade de chegadas que serão criadas
		this.tamanhorodadas = tamanhorodadas;
		// taxa de serviço que será repassada ao servidor
		this.taxaservico = taxaservico;
		// igual a rô ou probabilidade do servidor ocupado ou utilização do
		// sistema
		this.utilizacao = utilizacao;
		// disciplina de atendimento da fila 1
		this.fila1 = fila1;
		// disciplina de atendimento da fila 2
		this.fila2 = fila2;
	}
	
	public Configuracao(int fasetransiente, int numerorodadas, int tamanhorodadas, double taxaservico, double media1, double dp1, 
			String distribuicaoServidor1, double taxaservico2, double media2, double dp2, 
			String distribuicaoServidor2,  double utilizacao, 
			String fila1, String fila2, int tipoDeInterrupcaoFila2) {
		this(fasetransiente, numerorodadas, tamanhorodadas, taxaservico, utilizacao, fila1, fila2);
		this.media1 = media1;
		this.dp1 = dp1;
		this.tipoDeInterrupcaoFila2 = tipoDeInterrupcaoFila2;
		this.taxaservico2 = taxaservico2;
		this.media2 = media2;
		this.dp2 = dp2;
		this.distribuicaoServidor1 = distribuicaoServidor1;
		this.distribuicaoServidor2 = distribuicaoServidor2;
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
	 * Método que retorna o valor da taxa de serviço.
	 * 
	 * @return Retorna o valor da taxa de serviço
	 */
	public double getTaxaservico() {
		return taxaservico;
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
	 * Método que retorna o valor da disciplina de atendimento da fila1.
	 * 
	 * @return Retorna o valor da disciplina de atendimento da fila1.
	 */
	public String getFila1() {
		return fila1;
	}

	/**
	 * Método que retorna o valor da disciplina de atendimento da fila2.
	 * 
	 * @return Retorna o valor da disciplina de atendimento da fila2.
	 */
	public String getFila2() {
		return fila2;
	}
	
	public String getDistribuicaoServidor1() {
		return distribuicaoServidor1;
	}

	public void setDistribuicaoServidor1(String distribuicaoServidor1) {
		this.distribuicaoServidor1 = distribuicaoServidor1;
	}

	public double getTaxaservico2() {
		return taxaservico2;
	}

	public void setTaxaservico2(double taxaservico2) {
		this.taxaservico2 = taxaservico2;
	}

	public String getDistribuicaoServidor2() {
		return distribuicaoServidor2;
	}

	public void setDistribuicaoServidor2(String distribuicaoServidor2) {
		this.distribuicaoServidor2 = distribuicaoServidor2;
	}

	public int getTipoDeInterrupcaoFila2() {
		return tipoDeInterrupcaoFila2;
	}

	public void setTipoDeInterrupcaoFila2(int tipoDeInterrupcaoFila2) {
		this.tipoDeInterrupcaoFila2 = tipoDeInterrupcaoFila2;
	}

	public double getMedia1() {
		return media1;
	}

	public void setMedia1(double media1) {
		this.media1 = media1;
	}

	public double getDp1() {
		return dp1;
	}

	public void setDp1(double dp1) {
		this.dp1 = dp1;
	}

	public double getMedia2() {
		return media2;
	}

	public void setMedia2(double media2) {
		this.media2 = media2;
	}

	public double getDp2() {
		return dp2;
	}

	public void setDp2(double dp2) {
		this.dp2 = dp2;
	}
}
