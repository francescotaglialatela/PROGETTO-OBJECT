package Model;

public class Svolge {
	public String testScelto;
	public String risposta;
	public Studente studente;
	
	public Svolge(String t, String r, Studente stu) {
		testScelto = t;
		risposta = r;
		studente = stu;
	}
	
	/**
	 * Gets Test scelto dall'utente.
	 * 
	 * @return testScelto
	 */
	
	public String getTestScelto() {
		return testScelto;
	}
	
	/**
	 * Gets Risposta data da uno studente
	 * 
	 * @return risposta
	 */
	
	public String getRisposta() {
		return risposta;
	}
}
