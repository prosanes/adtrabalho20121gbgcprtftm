package br.ufrj.dcc.controle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import br.ufrj.dcc.modelo.Configuracao;
import br.ufrj.dcc.modelo.Resultado;
import br.ufrj.dcc.modelo.VAList;


public class Simulador { 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// cria o objeto que fará a leitura do xml
		ManipulaXML xml = new ManipulaXML("./XML/config.xml");
		// cria o objeto que armazena os valores informados no xml
		Configuracao config = null;
		// cria o objeto que irá armazenar os valores das rodadas
		Resultado resultado = new Resultado();
		
		VAList<Integer> listaRodadas = new VAList<Integer>();
		
		try {
			// le o xml
			config = xml.leConfiguracao();
		} catch (Exception e) {
			// caso dê errado a leitura do xml, cria o objeto config com parâmetros padrão.
			System.out.println("Erro na leitura do XML. Por favor, verifique se está correto");
			System.exit(0);
		}
		
		// "loop" que gera as n rodadas que está no objeto config.
		for (int i = 0; i < config.getNumerorodadas(); i++) {
				
			// cria um objeto rodada
			Rodada rodada = new Rodada(config, resultado);

			// gera um rodada de simulação
			try {
				rodada.simulacao(i);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//adiciono na lista de rodadas a lista gerada na rodada
			listaRodadas.Add(rodada.listaTempos, i);
			
		}

		// esperança do tempo de espera médio da fila 1
		System.out.println("E[W1]:" + calculaMedia(resultado.getW1()));		

		// o intervalo de confiança de W1 com precisão de 95%
		calculaIntervaloDeConfianca(resultado.getW1());

		// cálcula a variância de W1
		System.out.println("\nV(W1):" + calculaMedia(resultado.getVarianciaW1()));
		
		// o intervalo de confiança de V(W1) com precisão de 95%
		calculaIntervaloDeConfianca(resultado.getVarianciaW1());
		
		// tempo médio total desde a chegada na fila 1 e término do serviço.
		System.out.println("\nE[T1]:" + calculaMedia(resultado.getT1()));
		
		// o intervalo de confiança de T1 com precisão de 95%
		calculaIntervaloDeConfianca(resultado.getT1());
		
		// cálcula o número médio de fregueses na fila 1
		System.out.println("\nE[Nq1]:" + calculaMedia(resultado.getNq1()));
		
		// o intervalo de confiança de Nq1 com precisão de 95%
		calculaIntervaloDeConfianca(resultado.getNq1());
		
		//número médio de pessoas no sistema 1 (fila 1 e serviço 1)
		System.out.println("\nE[N1]:" + calculaMedia(resultado.getN1()));
		
		// o intervalo de confiança de N1 com precisão de 95%
		calculaIntervaloDeConfianca(resultado.getN1());

		System.out.println("\nResultado Da Fila 2:");

		// esperança do tempo de espera médio da fila 2
		System.out.println("E[W2]:" + calculaMedia(resultado.getW2()));

		// o intervalo de confiança de W2 com precisão de 95%
		calculaIntervaloDeConfianca(resultado.getW2());

		// cálcula a variância de W2
		System.out.println("\nV(W2):" + calculaMedia(resultado.getVarianciaW2()));
		
		// o intervalo de confiança de V(W2) com precisão de 95%
		calculaIntervaloDeConfianca(resultado.getVarianciaW2());
		
		// tempo médio total desde a chegada na fila 2 e termino do serviço.
		System.out.println("\nE[T2]:" + calculaMedia(resultado.getT2()));
		
		// o intervalo de confiança de T2 com precisão de 95%
		calculaIntervaloDeConfianca(resultado.getT2());
		
		// cálcula o número médio de fregueses na fila 2
		System.out.println("\nE[Nq2]:" + calculaMedia(resultado.getNq2()));
		
		// o intervalo de confiança de Nq2 com precisão de 95%
		calculaIntervaloDeConfianca(resultado.getNq2());
		
		//número médio de pessoas no sistema 2 (fila 2 e serviço 2)
		System.out.println("\nE[N2]:" + calculaMedia(resultado.getN2()));
		
		// o intervalo de confiança de N2 com precisão de 95%
		calculaIntervaloDeConfianca(resultado.getN2());
		
		
		
		geraGrafico(listaRodadas, config);
	}

	/**
	 * Cálcula o intervalo de confiança à 95%. Imprimirá após ser cálculado o
	 * valor máximo, mínimo e o intervalo de confiança.
	 * 
	 * @param medias Coleção de valores que serão usados para fazer o cálculo do
	 *            intervalo de cofiança
	 */
	private static void calculaIntervaloDeConfianca(Collection<Double> medias) {
		// armazena a médias das médias achadas a cada rodada
		double mediaDasMedias = 0.0d;
		// armazena o somatório da variância
		double somatorioVariancia = 0.0d;
		// armazeno o valor do desvio padrão
		double desvioPadrao = 0.0d;

		// faz o cálculoda média
		for (Double media : medias) {
			mediaDasMedias += media / medias.size();
			somatorioVariancia += media * media;
		}
		// cálcula o desvio padrão
		desvioPadrao = Math.sqrt((somatorioVariancia - mediaDasMedias * mediaDasMedias * medias.size()) / (medias.size() - 1));
		// cálcula o IC fazendo uso de 1.96 como o valor da T-student
		double tamanhoIntervalo = 1.96 * desvioPadrao / Math.sqrt(medias.size());

		// Imprimo o IC
		System.out.println("Tamanho IC : " + (tamanhoIntervalo * 100 / mediaDasMedias) + "%");
		System.out.println("Mínimo : " + (mediaDasMedias - tamanhoIntervalo));
		System.out.println("Máximo : " + (mediaDasMedias + tamanhoIntervalo));
	}

	/**
	 * Cálcula média
	 * 
	 * @param dados Coleção de valores que serão usados para fazer o cálculo da esperança.
	 * @return retorna a esperança.
	 */
	private static Double calculaMedia(Collection<Double> dados) {
		// Utilizado para calcular a média (estimada)
		Double resultado = 0.0D;
		for (Double dado : dados) {
			resultado += (dado / dados.size());
		}
		return resultado;
	}
	
	
	public static void geraGrafico(VAList<Integer> listaRodadas, Configuracao config)
	{	
		XYSeriesCollection dataset = new XYSeriesCollection();

		for (int i = 0; i < listaRodadas.getNumRows(); i++) {
			XYSeries serie = new XYSeries("Rodada "+ i);			
			ArrayList<Integer> list = listaRodadas.getArrayListRow(i);
			Double maxData = (double)list.get(list.size()-1);
			for(int indice = 0; indice < list.size(); indice ++)
			{
				Double axisY = list.get(indice)/maxData;
				Double axisX = (double)indice/10;
				
				serie.add(axisX, axisY);
			}
			
	        dataset.addSeries(serie);
			
		} 
		
        JFreeChart chart = ChartFactory.createXYLineChart(
            "CDF",          // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,
            false
        );
        
        File file = new File("./chart/chart.png");
        try {
			ChartUtilities.saveChartAsPNG(file,chart,1024,768);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
