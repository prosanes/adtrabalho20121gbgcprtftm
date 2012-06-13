package br.ufrj.dcc.controle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
		// retorna o valor da tag fasetransiente
		int fasetransiente = Integer.parseInt(getChildTagValue(tagConfig, "fasetransiente"));
		
		// retorna o valor da tag numerorodadas
		int numerorodadas = Integer.parseInt(getChildTagValue(tagConfig, "numerorodadas"));
		
		// retorna o valor da tag tamamnhorodadas
		int tamanhorodadas = Integer.parseInt(getChildTagValue(tagConfig, "tamanhorodadas"));
		
		// retorna o valor da tag taxaservico
		double taxaservico = Double.parseDouble(getChildTagValue(tagConfig, "taxaservico"));
		double media1 = Double.parseDouble(getChildTagValue(tagConfig, "media1"));
		double dp1 = Double.parseDouble(getChildTagValue(tagConfig, "dp1"));
		//retorna a distribuicao do servidor
		String distribuicaoServidor1 = getChildTagValue(tagConfig, "distribuicaoServidor1");
		
		// retorna o valor da tag taxaservico od servidor 2
		double taxaservico2 = Double.parseDouble(getChildTagValue(tagConfig, "taxaservico2"));
		double media2 = Double.parseDouble(getChildTagValue(tagConfig, "media2"));
		double dp2 = Double.parseDouble(getChildTagValue(tagConfig, "dp2"));
		//retorna a distribuicao do servidor
		String distribuicaoServidor2 = getChildTagValue(tagConfig, "distribuicaoServidor2");
				
		
		// retorna o valor da tag utilizacao
		double utilizacao = Double.parseDouble(getChildTagValue(tagConfig, "utilizacao"));
	
		// retorna a disciplina de atendimento da fila1
		String fila1 = getChildTagValue(tagConfig, "fila1");
		
		// retorna a disciplina de atendimento da fila2
		String fila2 = getChildTagValue(tagConfig, "fila2");
		// retorna o tipo de comportamento pós interrupção pela fila1
		int tipoInterrupcaoFila2 = Integer.parseInt(getChildTagValue(tagConfig, "tipoInterrupcaoFila2"));

		// cria um objeto da classe Configuração com os valores supra obtidos.
		Configuracao config = new Configuracao(fasetransiente, numerorodadas,
				tamanhorodadas, taxaservico,media1,dp1,distribuicaoServidor1,taxaservico2,media2, dp2, distribuicaoServidor2, 
				utilizacao, fila1, fila2, tipoInterrupcaoFila2);
		return config;
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
