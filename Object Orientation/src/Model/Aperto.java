package Model;

public class Aperto extends Quiz{
	public double puntMin;
	public double puntMax;
	public int maxLeng;

	public Aperto(String d, double pmin, double pmax, int mleng, char rispostaC) {
		super(d, rispostaC);
		puntMin=pmin;
		puntMax=pmax;
		maxLeng=mleng;
	}
	
	/**
	 * Gets Punteggio Minimo
	 * 
	 * @return puntMin
	 */
	
	public double getPuntMin() {
		return puntMin;
	}
	
	/**
	 * Gets Punteggio Massimo
	 * 
	 * @return puntMax
	 */
	
	public double getPuntMax() {
		return puntMax;
	}
	
	/**
	 * Gets Lunghezza massima del testo prevista per una risposta.
	 * 
	 * @return maxLeng
	 */
	
	public int getMaxLeng() {
		return maxLeng;
	}
 }
	
