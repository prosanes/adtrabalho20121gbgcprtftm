package br.ufrj.dcc.modelo;

import java.util.ArrayList;

/**
 * Classe que armazena os resultados de cada rodada.
 */
public class Resultado {
	
	private ArrayList<Double> t1 = new ArrayList<Double>();
	private ArrayList<Double> t2 = new ArrayList<Double>();
	private ArrayList<Double> w1 = new ArrayList<Double>();
	private ArrayList<Double> w2 = new ArrayList<Double>();
	private ArrayList<Double> nq1 = new ArrayList<Double>();
	private ArrayList<Double> nq2 = new ArrayList<Double>();
	private ArrayList<Double> n1 = new ArrayList<Double>();
	private ArrayList<Double> n2 = new ArrayList<Double>();
	private ArrayList<Double> varianciaW1 = new ArrayList<Double>();
	private ArrayList<Double> varianciaW2 = new ArrayList<Double>();
		
	public Resultado() {
	}

	/**
	 * Retorna um array com todos valores obtidos em cada uma das rodadas. <br>
	 * T1 representa quanto tempo o cliente demorou para sair da fila1 e ir para
	 * fila2.
	 * 
	 * @return Array com os valores médios de T1 de cada rodada.
	 */
	public ArrayList<Double> getT1() {
		// retorna todo o array para fazer o cálculo da esperança de T1
		return t1;
	}

	/**
	 * Retorna um array com todos os valores obtidos em cada uma das rodadas.<br>
	 * T2 representa quanto tempo o cliente demorou para sair da fila2 e ir
	 * embora.
	 * 
	 * @return Array com os valores médios de T2 de cada rodada.
	 */
	public ArrayList<Double> getT2() {
		// retorna todo o array para poder fazer o cálculo da esperança de T2
		return t2;
	}

	/**
	 * Retorna um array com todos os valores obtidos em cada uma das rodadas. <br>
	 * W1 representa quanto tempo o Freguês ficou espereando na fila1.
	 * 
	 * @return Array com os valores médios de W1 de cada rodada.
	 */
	public ArrayList<Double> getW1() {
		// retorna todo o array para fazer o cálculo da esperança de W1
		return w1;
	}

	/**
	 * Retorna um array com todos os valores obtidos em cada uma das rodadas. <br>
	 * W2 representa quanto tempo o Freguês ficou espereando na fila.
	 * 
	 * @return Array com os valores médios de W2 de cada rodada.
	 */
	public ArrayList<Double> getW2() {
		// retorna todo o array para fazer o cálculo da esperança de W2
		return w2;
	}

	/**
	 * Retorna um array com todos os valores obtidos em cada uma das rodadas. <br>
	 * Nq1 representa a quantidade de pessoas que ficaram esperando em média na fila1.
	 * 
	 * @return Array com os valores médios de Nq1 de cada rodada.
	 */
	public ArrayList<Double> getNq1() {
		// retorna todo o array para fazer o cálculo da esperança de Nq1
		return nq1;
	}

	/**
	 * Retorna um array com todos os valores obtidos em cada uma das rodadas. <br>
	 * Nq2 representa a quantidade de pessoas que ficaram esperando em média na fila2.
	 * 
	 * @return Array com os valores médios de Nq2 de cada rodada.
	 */
	public ArrayList<Double> getNq2() {
		// retorna todo o array para poder fazer o cálculo da esperança de Nq2
		return nq2;
	}

	/**
	 * Adicona ao array criado o valor do T1 da rodata atual.<br>
	 * O valor T1 é o tempo médio total que um Freguês gasta para sair da fila1
	 * e ser servido.
	 * 
	 * @param t1 Valor do tempo total médio gasto na fila1 na rodata atual.
	 */
	public void setT1(Double t1) {
		// adiciona ao array o valor médio obtido na última rodada de T1
		this.t1.add(t1);
	}

	/**
	 * Adicona ao array criado o valor do T2 da rodata atual. <br>
	 * O valor T2 é o tempo médio total que um Freguês gasta para sair da fila2 e ser servido.
	 * 
	 * @param t2 valor Valor do tempo total médio gasto na fila 2 na rodata atual.
	 */
	public void setT2(Double t2) {
		// adiciona ao array o valor médio obtido na última rodada de T@
		this.t2.add(t2);
	}

	/**
	 * Adicona ao array criado o valor de W1 da rodada atual. <br> 
	 * O valor W1 é o tempo médio de espera na fila1.
	 * 
	 * @param w1 Valor do tempo de espera médio da fila1 na rodata atual.
	 */
	public void setW1(Double w1) {
		// adiciona ao array o valor médio obtido na última rodada de W1
		this.w1.add(w1);
	}

	/**
	 * Adicona ao array criado o valor de W2 da rodata atual. <br> 
	 * O valor W2 é o tempo médio de espera na fila2.
	 * 
	 * @param w2 Valor do tempo de espera médio da fila2 na rodata atual.
	 */
	public void setW2(Double w2) {
		// adiciona ao array o valor médio obtido na última rodada de W2
		this.w2.add(w2);
	}

	/**
	 * Adicona ao array criado o valor de Nq1 da rodada atual. <br>
	 * O valor Nq1 é o número médio de Freguêses da fila1.
	 * 
	 * @param nq1 Valor Nq1 é o número médio de Freguêses da fila1 na rodada atual.
	 */
	public void setNq1(Double nq1) {
		// adiciona ao array o valor médio obtido na última rodada de Nq1
		this.nq1.add(nq1);
	}

	/**
	 * Adicona ao array criado o valor de Nq2 da rodada atual. <br>
	 * O valor Nq2 é o número médio de Freguêses da fila2.
	 * 
	 * @param nq2 Valor Nq2 é o número médio de Freguêses da fila2 na rodada atual.
	 */
	public void setNq2(Double nq2) {
		// adiciona ao array o valor médio obtido na última rodada de Nq2
		this.nq2.add(nq2);
	}

	/**
	 * Retorna um array com todos os valores obtidos em cada uma das rodadas. <br>
	 * N1 representa a quantidade de pessoas no sistema 1 (fila 1 e serviço 1).
	 * 
	 * @return Array com os valores médios de N1 de cada rodada.
	 */
	public ArrayList<Double> getN1() {
		return n1;
	}

	/**
	 * Adicona ao array criado o valor de N1 da rodada atual. <br>
	 * O valor N1 é o número médio de Freguêses na fila 1 e serviço.
	 * 
	 * @param n Número médio de Freguêses na fila 1 e serviço 1.
	 */
	public void setN1(Double n1) {
		this.n1.add(n1);
	}

	/**
	 * Retorna um array com todos os valores obtidos em cada uma das rodadas. <br>
	 * N2 representa a quantidade de pessoas no sistema 2 (fila 2 e serviço 2).
	 * 
	 * @return Array com os valores médios de N2 de cada rodada.
	 */
	public ArrayList<Double> getN2() {
		return n2;
	}

	/**
	 * Adicona ao array criado o valor de N2 da rodada atual. <br>
	 * O valor N2 é o número médio de Freguêses na fila 2 e serviço 2.
	 * 
	 * @param n2 Número médio de Freguêses na fila 2 e serviço 2.
	 */
	public void setN2(Double n2) {
		this.n2.add(n2);
	}
	/**
	 * Retorna um array com todos os valores obtidos em cada uma das rodadas. <br>
	 * A variância mede como está a dispersão do valores de W1.
	 * 
	 * @return Array com as variâncias de W1 de cada rodada
	 */
	public ArrayList<Double> getVarianciaW1() {
		return varianciaW1;
	}
	/**
	 * Adicona ao array criado o valor da variância de W1  da rodada atual. <br>
	 * A variância mede como está a dispersão do valores de W1.
	 * 
	 * @param varianciaW1 o valor da variância de W1  da rodada atual.
	 */
	public void setVarianciaW1(Double varianciaW1) {
		this.varianciaW1.add(varianciaW1);
	}
	/**
	 * Retorna um array com todos os valores obtidos em cada uma das rodadas. <br>
	 * A variância mede como está a dispersão do valores de W2.
	 * 
	 * @return Array com as variâncias de W2 de cada rodada
	 */
	public ArrayList<Double> getVarianciaW2() {
		return varianciaW2;
	}
	/**
	 * Adicona ao array criado o valor da variância de W2  da rodada atual. <br>
	 * A variância mede como está a dispersão do valores de W2.
	 * 
	 * @param varianciaW2 o valor da variância de W1  da rodada atual.
	 */
	public void setVarianciaW2(Double varianciaW2) {
		this.varianciaW2.add(varianciaW2);
	}

}
