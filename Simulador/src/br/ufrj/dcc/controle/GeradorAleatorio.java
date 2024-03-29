package br.ufrj.dcc.controle;

import java.util.Random;

/**
 * Classe que gera as amostra do sistema. Para fazer isso ela chama o metódo
 * random da classe Math para gerar um número aleatório, após isso multiplicamos por
 * um número muito grande, para assim poder gerar a semente. Em seguida cria-se
 * um objeto da classe Random passando a semente gerada como parâmetro.
 */
public class GeradorAleatorio {

	private static GeradorAleatorio INSTANCE;
	private Random random;

	private GeradorAleatorio() {
		// gera a semente chamando a Math.random e multiplicando por um número muito grande
		long semente = (long) (Math.random() * 2000000000);
		// cria um objeto da classe random passando a semente criada
		random = new Random(semente);
	}

	/**
	 * Retorna a instância do meu gerador e na primeria vez que se chama esse
	 * método cria o objeto.
	 * 
	 * @return Retorna a única instância do gerado.
	 */
	public static GeradorAleatorio getInstance() {
		// caso a instância não tenha sido criada ainda, criamos uma nova.
		if (INSTANCE == null) {
			INSTANCE = new GeradorAleatorio();
		}
		// retorna a única instância possível do objeto
		return INSTANCE;
	}

	/**
	 * Método que serve para gerar o instante de uma chegada no sistema ou 
	 * o tempo de serviço, quando a distribuicao é exponencial 
	 * 
	 * @param taxa Usado para gerar o instante.
	 * @return Retorna um instante
	 */
	public double getGeraAmostra(double taxa) {
		// retorna um amostra gerada pela fórmula da exponencial.
		return -(Math.log(random.nextDouble()) / taxa);
	}
	
	
	/**
	 * Método que serve para gerar o instante de uma chegada no sistema ou 
	 * o tempo de serviço, quando a distribuicao é exponencial ou deterministica
	 * 
	 * @param taxa Usado para gerar o instante.
	 * @param tipo de distribuicao desejada
	 * @return Retorna um instante
	 */
	public double getGeraAmostra(double taxa, String distribuicao) {
		// retorna um amostra gerada pela fórmula da exponencial.
		double ret = 0;
		switch (distribuicao) {
		case "exponencial":
			ret = getGeraAmostra(taxa);
			break;
		case "deterministica":
			ret = 1/taxa;
			break;
		}
		return ret;
	}
	/**
	 * Método que serve para gerar o instante de uma chegada no sistema ou 
	 * o tempo de serviço, quando a distribuicao é normal
	 * 
	 * @param media da distribuicao normal
	 * @param desvio padrao da distribuicao normal
	 * @return Retorna um instante
	 */
	public double getGeraAmostra(double media, double dp)
	{
		double result = 0.0;
		do
		{
			result = random.nextGaussian()*dp + 1/media;	
		}while(result < 0.0);
		
		return result;
	}

	
}
