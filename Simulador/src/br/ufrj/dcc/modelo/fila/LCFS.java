package br.ufrj.dcc.modelo.fila;

import java.util.Comparator;
import java.util.Stack;
import br.ufrj.dcc.modelo.Fregues;
import br.ufrj.dcc.modelo.Servidor;
import br.ufrj.dcc.modelo.enumarator.FilaOrigem;

public class LCFS extends Fila {

	/**
	 * Construtor utilizado para a fila2 porque nela não há chegadas.
	 * 
	 * @param servidor servido em que  o freguês irá ser tratado.
	 */
	public LCFS(Servidor servidor) {
		super(servidor);
		fregueses = new Stack<Fregues>();
	}

	/**
	 * Construtor utilizado para a fila1 
	 * 
	 * @param lambda Taxa de chegada dos fregueses ao sistema.
	 * @param servidor Servidor que atende esta fila.
	 * @param fila Fila na qual tenho maior prioridade e posso tirar o cliente do serviço
	 */
	public LCFS(double lambda, Servidor servidor, Fila fila) {
		// chamo o construtor da classe Fila que é super-classe desta
		super(lambda, servidor, fila);
		// cria uma lista encadeada para armazenar os clientes este construtor é
		// apenas utilizado pela fila 2
		fregueses = new Stack<Fregues>();
	}
	/**
	 * Método que retorna o primeiro elemento da lista encadeada com ordenação em relação ao instante de chegada transformando-a em pilha.
	 * 
	 * @return Primeiro elemento da fila1.
	 */
	public Fregues removerParaAtendimento() {
		// chamo o construtor da classe Fila que é super-classe desta
		this.setNumeroFreguesesNaFila(fregueses.size() - 1);
		// removo um freguês da fila para atendimento
		return ((Stack<Fregues>) fregueses).pop();
	}

	/**
	 * Método que adiciona um freguês a sua fila, porém antes verificamos se o
	 * servidor não está vazio, porque se estiver, o enviamos direto para o
	 * servidor. <br>
	 * Caso o cliente que está em serviço seja da fila2 tiro ele do serviço.
	 * Caso quem esteja em serviço seje da fila1 entro na fila1 de espera. <br>
	 * Como a disciplina de atendimento é LCFS este cliente será inserido no
	 * ínicio da fila.
	 * 
	 * @param fregues O cliente que acbou de chegar ao sistema
	 */
	public void addFregues(Fregues fregues) {
		// quando eu for adicionar um freguês mas o servido está vazio o coloco direto no servidor
		if (servidor.isOcioso()) {
			// verifico se é da fila1 para poder pegar o instante de chegada a sua fila.
			if (fregues.getFilaAtual().equals(FilaOrigem.FILA1)) {
				servidor.receive(fregues, fregues.getInstanteChegadaFila1());
			} else {
				// verifico se é da fila2 para poder pegar o instante de chegada a sua fila.
				servidor.receive(fregues, fregues.getInstanteChegadaFila2());
			}
			// ocorre quand vou adicionar um freguês a fila 1 mas tem um freguês
			// da fila 2 em atendimento ou seja tiro do serviço
		} else if (servidor.getFilaOrigem() == FilaOrigem.FILA2
				&& fregues.getFilaAtual() == FilaOrigem.FILA1) {
			// chamo o método que trata interrupção
			servidor.trataInterrupcao(fregues, preemptiva);
		} else {
			// senão adiciono o freguês a fila e atualizo a quantidade de fregueses na fila
			if(tipoDeInterrupcao == 1)
			{
				Fregues frequesAntigo = null;
				if(((Stack<Fregues>) fregueses).size() > 0) frequesAntigo = ((Stack<Fregues>) fregueses).peek();
				if(frequesAntigo != null && frequesAntigo.getServicoResidual() != 0.0)
				{
					((Stack<Fregues>) fregueses).pop();
					((Stack<Fregues>) fregueses).push(fregues);
					((Stack<Fregues>) fregueses).push(frequesAntigo);
				}
				else
				{
					((Stack<Fregues>) fregueses).push(fregues);
				}
				
					
			}
			else
			{
				((Stack<Fregues>) fregueses).push(fregues);
			}
			
			
			this.setNumeroFreguesesNaFila(fregueses.size());
		}
	}

	/**
	 * Método que realiza a comparação entre dois Fregueses.
	 * 
	 */
	static final Comparator<Fregues> TIME_ORDER = new Comparator<Fregues>() {
		public int compare(Fregues f1, Fregues f2) {
			return f2.compareTo(f1);
		}
	};

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
			((Stack<Fregues>) fregueses).push(fregues);
		}
		// se for do tipo 2, insiro no fundo da pilha
		else
		{
			Stack<Fregues> tempStack = new Stack<Fregues>();
			while(fregueses.size() > 0)
			{
				tempStack.push(((Stack<Fregues>) fregueses).pop());
			}
			((Stack<Fregues>) fregueses).push(fregues);
			while(tempStack.size() > 0)
			{
				((Stack<Fregues>) fregueses).push(tempStack.pop());
			}
		}

		// atualizo o número de fregueses na fila
		this.setNumeroFreguesesNaFila(fregueses.size());
	}

}
