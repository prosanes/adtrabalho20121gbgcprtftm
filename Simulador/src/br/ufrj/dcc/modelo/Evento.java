package br.ufrj.dcc.modelo;

import br.ufrj.dcc.modelo.enumarator.TipoEvento;

/**
 * Classe que controla os eventos no sitema.
 * 
 */
public class Evento {

	private double horaOcorrencia;
	private TipoEvento tipo;
	private Fregues fregues;
	private Servidor servidor;

	/**
	 * Construtor da classe Evento, utilizado apenas quando o evento for de chegada.
	 * 
	 * @param horaOcorrencia Instânte que ocorrerá o evento.
	 * @param tipo Tipo do evento, que pode ser de chegada ou de saída.
	 * @param fregues Freguês do evendo.
	 * @return Retorna o evento que acabou de ser criado.
	 */
	public Evento(double horaOcorrencia, TipoEvento tipo, Fregues fregues) {
		// coloca na variável horaOcorrencia o instante da ocorrencia do evendo que é passado como parametro
		this.horaOcorrencia = horaOcorrencia;
		// preenche o tipo do evento ( CHEGADA ou SAÍDA )
		this.tipo = tipo;
		// o freguês que gerou esse evento
		this.fregues = fregues;
	}

	/**
	 * Construtor da classe Evento, utilizado apenas quando o evento for de saída.
	 * 
	 * @param horaOcorrencia Instante que ocorrerá o evento.
	 * @param tipo Tipo do evento,que pode ser de chegada ou de saída.
	 * @param servidor Servidor no qual o cliente vai se destinar quando chegar o seu
	 *            instante de ocorrência.
	 * @return Retorna o evento que acabou de ser criado.
	 */
	public Evento(double horaOcorrencia, TipoEvento tipo, Servidor servidor) {
		// coloca na variável horaOcorrencia o instante que ocorrerá o evento
		this.horaOcorrencia = horaOcorrencia;
		// o tipo do evento ( CHEGADA ou SAÍDA )
		this.tipo = tipo;
		// colocar em server o servidor que irá tratar desse evento de saída.
		this.servidor = servidor;
	}

	/**
	 * @return Retorna true se o evento é de chegada ou false se for de saída.
	 * 
	 */
	public boolean isEventoChegada() {
		// verifica se o tipo de evento é de chegada
		return this.tipo == TipoEvento.CHEGADA;
	}

	/**
	 * @return Retorna false se o evento é de chegada ou true se for de saída.
	 * 
	 */
	public boolean isEventoSaida() {
		// verifica se o tipo de evento é de saída
		return this.tipo == TipoEvento.SAIDA;
	}

	/**
	 * @return Retorna o Freguês que originou o evento.
	 * 
	 */
	public Fregues getFregues() {
		return this.fregues;
	}

	/**
	 * @return Retorna o tipo de evento, "SAIDA" ou "CHEGADA".
	 * 
	 */
	public TipoEvento getTipo() {
		return this.tipo;
	}

	/**
	 * @return Retorna o instante de ocorrência do evento.
	 * 
	 */
	public double getHoraOcorrencia() {
		return this.horaOcorrencia;
	}

	/**
	 * @return Retorna o servidor em que um evento de saída será tratado.
	 * 
	 */
	public Servidor getServidor() {
		return servidor;
	}
}
