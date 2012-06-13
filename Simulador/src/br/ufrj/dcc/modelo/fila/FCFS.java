package br.ufrj.dcc.modelo.fila;

import java.util.LinkedList;
import br.ufrj.dcc.modelo.Fregues;
import br.ufrj.dcc.modelo.Servidor;
import br.ufrj.dcc.modelo.enumarator.FilaOrigem;

public class FCFS extends Fila{

	/**
	 * Construtor utilizado para a fila2 porque nela não há chegadas.
	 * 
	 * @param servidor Servido em que  o freguês irá ser tratado.
	 */
	public FCFS(Servidor servidor) {
		// chamo o construtor da classe Fila que é super-classe desta
		super(servidor);
		// cria uma lista encadeada para armazenar os clientes este construtor é
		// apenas utilizado pela fila 2
		fregueses = new LinkedList<Fregues>();
	}
	/**
	 * Construtor utilizado para a fila1 
	 * 
	 * @param lambda Taxa de chegada dos fregueses ao sistema.
	 * @param server Servidor que atende está fila.
	 * @param preemptiva Fila na qual tenho maior prioridade e posso tirar o cliente dessa do serviço
	 */
	public FCFS(double lambda, Servidor server, Fila preemptiva) {
		// chamo o construtor da classe Fila que é super-classe desta
		super(lambda, server, preemptiva);
		// cria uma lista encadeada para armazenar os clientes este construtor é
		// apenas utilizado pela fila1
		fregueses = new LinkedList<Fregues>();
	}
	/**
	 * Método que retorna o primeiro elemento da lista encadeada.
	 * 
	 * @return Primeiro elemento da fila1.
	 */
	public Fregues removerParaAtendimento() {
		// dimunuo a quantidade de fregueses no sistema
		this.setNumeroFreguesesNaFila(fregueses.size() - 1);
		// removo um freguês da fila para atendimento
		return ((LinkedList<Fregues>) fregueses).removeFirst();
	}

	/**
	 * Método que adiciona um freguês a sua fila porém antes verificamos se o servidor não está vazio porque se estiver o enviamos direto para o servidor. <br>
	 * Caso o cliente que está em serviço seja da fila2 tiro ele do serviço. <br>
	 * Caso quem esteja em serviço seja da fila1 entro na fila de espera. <br>
	 * Como a disciplina de atendimento é FCFS o cliente será inserido ao final da fila.
	 * 
	 * @param fregues  O cliente que acabou de chegar ao sistema.
	 */
	public void addFregues(Fregues fregues) {
		// quando eu for adicionar um freguês mas o servido está vazio o coloco  direto no servidor
		if (servidor.isOcioso()) {
			// verifico se é da fila1 para pegar o instante de chegada a sua fila.
			if (fregues.getFilaAtual().equals(FilaOrigem.FILA1)) {
				servidor.receive(fregues, fregues.getInstanteChegadaFila1());
			} else {
				// verifico se é da fila2 para pegar o instante de chegada a sua fila.
				servidor.receive(fregues, fregues.getInstanteChegadaFila2());
			}

			// ocorre quando vou adicionar um freguês a fila1 mas tem um freguês
			// da fila2 em atendimento ou seja tiro ele do serviço
		} else if (servidor.getFilaOrigem() == FilaOrigem.FILA2
				&& fregues.getFilaAtual() == FilaOrigem.FILA1) {
			// chamo o método que trata interrupção
			servidor.trataInterrupcao(fregues, preemptiva);
		} else {
			// senão adiciono o freguês a fila e atualizo a quantidade de fregueses na fila
			((LinkedList<Fregues>) fregueses).addLast(fregues);
			this.setNumeroFreguesesNaFila(fregueses.size());
		}
	}

	/**
	 * Adicona um freguês que sofreu interrupção na fila e tem serviço pendente. É feita a decisão de onde o cliente 
	 * vai ser inserido de acordo com o tipo do caso passado
	 *
	 * @param fregues Freguês que sofreu uma interrupção.
	 * 
	 */
	public void addFreguesResidual(Fregues fregues) {
		// se for do tipo 1 , insiro normalmente
		if(tipoDeInterrupcao == 1)
		{
			// devolvo um freguês com serviço pedente a fila.
			((LinkedList<Fregues>) fregueses).addFirst(fregues);
		}
		// se for do tipo 2, insiro no fundo da pilha
		else
		{
			((LinkedList<Fregues>) fregueses).addLast(fregues);
		}
		// atualizo o número de fregueses na fila
		this.setNumeroFreguesesNaFila(fregueses.size());
	}
}
