package Model;

public class Quiz {
	public String domanda;
	public String tipo;
	public char rispostaC;
	
	/**Costruttore per un nuovo Quiz**/
	
	public Quiz(String d,  char rc) {
		domanda= d;
		rispostaC=rc;
	}
	
	/**
	 * Gets Domanda
	 * 
	 * @return domanda
	 */
	
	public String getDomanda() {
		return domanda;
	}
	
	
	/**
	 * Gets Risposta Corretta
	 * 
	 * @return rispostaC
	 */
	
	public char getRispostaC() {
		return rispostaC;
	}
}

