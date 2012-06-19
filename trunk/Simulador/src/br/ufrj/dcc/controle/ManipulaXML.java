package br.ufrj.dcc.controle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufrj.dcc.modelo.AttrFila;
import br.ufrj.dcc.modelo.Configuracao;

public class ManipulaXML {
	
	private String xmlPathname;

	/**
	 * Construtor da classe, recebe como parametro a o caminho de onde está o
	 * arquivo.
	 * 
	 * @param path Caminho do arquivo
	 */
	public ManipulaXML(String path) {
		xmlPathname = path;
	}

	/**
	 * Lê o arquivo config.xml para setar os parâmetros do sistema.
	 * 
	 * @return Retorna um objeto da classe configuração com os parâmetros lidos
	 *         do xml.
	 */
	public Configuracao leConfiguracao() throws Exception {
		
		AttrFila attrFila1 = new AttrFila();
		AttrFila attrFila2 = new AttrFila();
		
		// crio um objeto da classe DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// crio um objeto da classe DocumentBuilder
		DocumentBuilder db = dbf.newDocumentBuilder();
		// crio um objeto da classe Document e passo como parâmetro o caminho do meu config.xml
		Document doc = db.parse(xmlPathname);
		// pego o primeiro elemento do xml
		Element elem = doc.getDocumentElement();
		// retorno tags internas a tag cujo nome é configuracao
		NodeList nl = elem.getElementsByTagName("configuracao");
		// pega a primeira tag configuração
		Element tagConfig = (Element) nl.item(0);

		
		// retorna o valor da tag numerorodadas
//		int numerorodadas = Integer.parseInt(getChildTagValue(tagConfig, "numerorodadas"));		
		// retorna o valor da tag fasetransiente
//		int fasetransiente = Integer.parseInt(getChildTagValue(tagConfig, "fasetransiente"))
		// retorna o valor da tag tamamnhorodadas
//		int tamanhorodadas = Integer.parseInt(getChildTagValue(tagConfig, "tamanhorodadas"));
		
		int numerorodadas = 8;
		int fasetransiente = 2600;	
		int tamanhorodadas = 26000;
		
		
		
		// retorna o valor da tag utilizacao 
		///TODO TROCAR POR LAMBDA
		double txChegada = Double.parseDouble(getChildTagValue(tagConfig, "txChegada"));

		// retorna o tipo do comportamento pós interrupção por um cliente vindo da fila 1 - CASO 1 : Vai para o primeiro da fila 2 - CASO 2: vai para o final da fila 2
		int casoInterrupcao = Integer.parseInt(getChildTagValue(tagConfig, "casoInterrupcao"));
		

		// retorno tags internas da tag fila 1
		NodeList filaConfig = elem.getElementsByTagName("fila1");
		Element tagFila1 = (Element) filaConfig.item(0);
		// retorna a disciplina de atendimento da fila1
		attrFila1.tipo = getChildTagValue(tagFila1, "tipo");
		// retorna o tipo de distribuicao do servidor da fila 1
		attrFila1.distribuicaoServidor = getChildTagValue(tagFila1, "distribuicaoServidor");
		attrFila1.txServico = getChildTagValue(tagFila1, "txServico").isEmpty() ? 0.0 : Double.parseDouble(getChildTagValue(tagFila1, "txServico"));
		attrFila1.media = getChildTagValue(tagFila1, "media").isEmpty()? 0.0 : Double.parseDouble(getChildTagValue(tagFila1, "media"));
		attrFila1.desvioPadrao = getChildTagValue(tagFila1, "desvioPadrao").isEmpty() ? 0.0 : Double.parseDouble(getChildTagValue(tagFila1, "desvioPadrao"));
		
		// retorno tags internas da tag fila 1
		filaConfig = elem.getElementsByTagName("fila2");
		Element tagFila2 = (Element) filaConfig.item(0);
		// retorna a disciplina de atendimento da fila1
		attrFila2.tipo = getChildTagValue(tagFila2, "tipo");
		// retorna o tipo de distribuicao do servidor da fila 1
		attrFila2.distribuicaoServidor = getChildTagValue(tagFila2, "distribuicaoServidor");
		attrFila2.txServico = getChildTagValue(tagFila2, "txServico").isEmpty() ? 0.0 : Double.parseDouble(getChildTagValue(tagFila2, "txServico"));
		attrFila2.media = getChildTagValue(tagFila2, "media").isEmpty()? 0.0 : Double.parseDouble(getChildTagValue(tagFila2, "media"));
		attrFila2.desvioPadrao = getChildTagValue(tagFila2, "desvioPadrao").isEmpty() ? 0.0 : Double.parseDouble(getChildTagValue(tagFila2, "desvioPadrao"));
				
		
		// cria um objeto da classe Configuração com os valores supra obtidos.
		return new Configuracao(fasetransiente, numerorodadas, tamanhorodadas, txChegada, casoInterrupcao, attrFila1, attrFila2);
		
	}

	/**
	 * Pega o valor da tag cujo nome é passado como parâmetro.
	 * 
	 * @param elem Elemento do qual a tag faz parte
	 * @param tagName Nome da tag
	 * @return Retorna o valor que está dentro da tag.
	 */
	private String getChildTagValue(Element elem, String tagName) throws Exception {
		// pega o conteúdo da tag cujo nome é fornecido pelo parâmetro tagName
		NodeList children = elem.getElementsByTagName(tagName);
		// se for nulo retorno null
		if (children == null) {
			return null; 
		}
		// pego os elementos
		Element child = (Element) children.item(0);
		// se for nulo retorno null
		if (child == null) {
			return null;
		}
		// senão retorno o valor da tag.
		return child.getFirstChild().getNodeValue(); 
	}
}
