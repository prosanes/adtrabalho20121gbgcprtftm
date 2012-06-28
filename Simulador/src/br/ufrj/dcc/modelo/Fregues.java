package br.ufrj.dcc.modelo;

import br.ufrj.dcc.modelo.enumarator.CorCliente;

import br.ufrj.dcc.modelo.enumarator.FilaOrigem;
import br.ufrj.dcc.modelo.fila.Fila;


/**
 * Classe que representa o frêgues no sistema.
 * 
 */
public class Fregues {
	
	private CorCliente cor;
	private double instanteChegadaFila1;
	private double instanteSaidaFila1;
	private double instanteChegadaFila2;
	private double instanteSaidaFila2;
	private double instanteSaidaSistema;
	private double servicoResidual = 0.0;
	private double tempoInicialDeEntradaNaFila2;
	private FilaOrigem filaAtual;

	private boolean foiInterrompido;
	/**
	 * Retorna o valor do campo cor do Freguês.
	 * 
	 * @return Retorna o valor do campo cor do Freguês que pode ser TRANSIENTE ou NAOTRANSIENTE.
	 */
	public CorCliente getCor() {
		return cor;
	}

	/**
	 * Preenche o valor do campo cor do Freguês.
	 * 
	 * @param cor Recebe como parâmetro a cor do Freguês.
	 */
	public void setCor(CorCliente cor) {
		// preenchendo a variável cor do freguês com o valor passado como parâmetro
		this.cor = cor;
	}

	/**
	 * Retorna o valor do campo instanteChegadaFila1 do Freguês.
	 * 
	 * @return Retorna o valor do campo de instante de chegada na fila1 do Freguês.
	 */
	public double getInstanteChegadaFila1() {
		// retorna o instante de chegada do freguês na fila 1
		return instanteChegadaFila1;
	}

	/**
	 * Preenche o valor do campo instante de chegada na fila1 do Freguês.
	 * 
	 * @param instanteChegadaFila1 Recebe como parâmetro o instante de chegada na fila1 do Freguês.
	 */
	public void setInstanteChegadaFila1(double instanteChegadaFila1) {
		// coloca na variável instante de chegada na fila1 do freguês o valor passado como parâmetro
		this.instanteChegadaFila1 = instanteChegadaFila1;
	}

	/**
	 * Retorna o valor do campo instante de saída da fila1 do Freguês.
	 * 
	 * @return Retorna o valor do instante de saída da fila1 do Freguês.
	 */
	public double getInstanteSaidaFila1() {
		// retorna o instante de saída do freguês da fila1
		return instanteSaidaFila1;
	}

	/**
	 * Preenche o valor do campo instante de saída da fila1 do Freguês.
	 * 
	 * @param instanteSaidaFila1 Recebe como parâmetro o instante de saída da fila1 do Freguês.
	 */
	public void setInstanteSaidaFila1(double instanteSaidaFila1) {
		// coloca na variável o instante de saída da fila1 do freguês o valor
		// informado como parâmetro
		this.instanteSaidaFila1 = instanteSaidaFila1;
	}

	/**
	 * Retorna o valor do campo instante de chegada da fila2 do Freguês.
	 * 
	 * @return Retorna o valor do instante de chegada da fila2 do Freguês.
	 */
	public double getInstanteChegadaFila2() {
		// retorna o instante de chegada do freguês na fila2
		return instanteChegadaFila2;
	}

	/**
	 * Preenche o valor do campo instante de chegada na fila2 do Freguês.
	 * 
	 * @param instanteChegadaFila2 Recebe como parâmetro o instante de chegada na fila2 do Freguês.
	 */
	public void setInstanteChegadaFila2(double instanteChegadaFila2) {
		// coloca na variável instante de chegada na fila2 do freguês o valor
		// passado como parâmetro
		this.instanteChegadaFila2 = instanteChegadaFila2;
	}

	/**
	 * Retorna o valor do campo instante de saída da fila2 do Freguês.
	 * 
	 * @return Retorna o valor do instante de saída da fila2 do Freguês.
	 */
	public double getInstanteSaidaFila2() {
		// retorna o instante de saída do freguês da fila2
		return instanteSaidaFila2;
	}

	/**
	 * Preenche o valor do campo instante de saída da fila2 do Freguês.
	 * 
	 * @param instanteSaidaFila2 Recebe como parâmetro o instante de saída da fila2 do Freguês.
	 */
	public void setInstanteSaidaFila2(double instanteSaidaFila2) {
		// coloca na variável o instante de saída da fila2 do freguês o valor
		// informado como parâmetro
		this.instanteSaidaFila2 = instanteSaidaFila2;
	}

	/**
	 * Retorna o valor do campo instante de saída do sistema do Freguês.
	 * 
	 * @return Retorna o valor do instante de saída do sistema do Freguês.
	 */
	public double getInstanteSaidaSistema() {
		// retorna o instante de saída do sistemado do freguês
		return instanteSaidaSistema;
	}

	/**
	 * Preenche o valor do campo instante de saída da fila2 do Freguês.
	 * 
	 * @param instanteSaidaSistema Recebe como parâmetro o instante de saída da fila2 do Freguês.
	 */
	public void setInstanteSaidaSistema(double instanteSaidaSistema) {
		// coloca na variável o instante de saída do sistema do freguês o valor
		// passado como parâmetro
		this.instanteSaidaSistema = instanteSaidaSistema;
	}

	/**
	 * Retorna o valor do campo serviço residual do Freguês.
	 * Esse valor só será preenchido quando o cliente for interrompido.
	 * 
	 * @return Retorna o valor do serviço residual do Freguês.
	 */
	public double getServicoResidual() {
		// retorna o quanto falta para o freguês acabar o servido freguês.
		return servicoResidual;
	}

	/**
	 * Preenche o valor do campo serviço residual do Freguês.
	 * 
	 * @param servicoResidual Recebe como parâmetro o serviço residual do Freguês.
	 */
	public void setServicoResidual(double servicoResidual) {
		// coloca na variável o serviço residual do freguês o valor passado como parâmetro
		this.servicoResidual = servicoResidual;
	}

	/**
	 * Retorna o valor do campo filaAtual do Freguês.
	 * Pode ser FILA1 ou FILA2 , utilizado para saber o que fazer com o freguês após ele ter sido removido do servido.
	 *  
	 * @return Retorna o valor da fila atual do Freguês.
	 */
	public FilaOrigem getFilaAtual() {
		// retorna qual o valor da fila atual do freguês que 
		return filaAtual;
	}

	/**
	 * Preenche o valor do campo fila atual do Freguês.
	 * 
	 * @param filaAtual Recebe como parâmetro a fila atual do Freguês.
	 */
	public void setFilaAtual(FilaOrigem filaAtual) {
		// coloca na variável filaAtual do freguês o valor passado como parâmetro
		this.filaAtual = filaAtual;
	}

	/**
	 * Retorna o tempo em que o Fregues entrou na fila2.
	 * 
	 * @return Tempo que o fregues entrou na fila2.
	 */
	public double getTempoInicialDeEntradaNaFila2() {
		return tempoInicialDeEntradaNaFila2;
	}

	/**
	 * Preenche o tempo em que o Freguês entrou na fila2.
	 * 
	 * @param tempoInicialDeEntradaNaFila2 Recebe como parâmetro o tempo inicial de entrada na fila2 do Freguês.
	 */
	public void setTempoInicialDeEntradaNaFila2(double tempoInicialDeEntradaNaFila2) {
		this.tempoInicialDeEntradaNaFila2 = tempoInicialDeEntradaNaFila2;
	}

	/**
	 * Método utilizado para fazer comparação entre dois clientes, o da prória
	 * instância e outro recebido como parâmetro.
	 * 
	 * @param f1 Recebe como parâmetro um fregues que eu desejo compara.
	 * @return Retorna um inteiro. <br>
	 * 		Se for positivo significa que este cliente chegou depois do fregues f1. <br> 
	 * 		Se for negativo significa que este cliente chegou antes do fregues f1. <br>
	 * 		Se for zero significa que chegaram ao mesmo tempo. <br>
	 */
	public int compareTo(Fregues f1) {
		// retorna um valor > 0 se o próprio objeto é maior que o passado como
		// parâmetro ou seja se o this depois de f1
		// retorna um valor > 0 se o próprio objeto é menor que o passado como
		// parâmetro ou seja se o this antes de f1
		// retorna 0 se os dois são iguais ou seja this ocorre junto com f1 mas
		// isso tem probabilidade nula de ocorer.
		return (int) (this.getInstanteChegadaFila2() - f1.getInstanteChegadaFila2());
	}

	/**
	 * Trata a chegada de um cliente no sistema usando a própria instância da classe.
	 * 
	 * @param fila1 Fila na qual vou adicionar o cliente quando ele chegar no sistema.
	 * @param horaAtual Hora atual do sistema.
	 */
	public void chegarNoSistema(Fila fila1, double horaAtual) {
		// coloca o Freguês na fila1
		this.setFilaAtual(FilaOrigem.FILA1);
		// preenche o instante de chegada na fila 1
		this.setInstanteChegadaFila1(horaAtual);
		// adiciona o freguês a fila1.
		fila1.addFregues(this);
	}
}
