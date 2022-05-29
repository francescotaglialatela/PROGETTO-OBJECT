package Model;

public class Studente extends Utente{
	
	public String nome;
	public String cognome;
	public char sesso;
	public String telefono;
	
	/**Costruttore per istanziare un nuovo studente**/
	public Studente(String u, String p, String e, String n, String c, char s, String t) {
		super(u, p, e);
		nome = n;
		cognome= c;
		sesso = s;
		telefono = t;
	}
	
	/**
	 * Gets Nome.
	 * 
	 * @Return nome
	 */
	
		public String getNomeStudente() {
			return nome;
		};
		
	/**
	 * Gets Cognome. 
	 * 
	 * @return cognome.
	 */
		
		public String getCognomeStudente() {
			return cognome;
		};
		
		/**
		 * Gets Sesso
		 * 
		 * @return sesso
		 */
		
		public char getSessoStudente() {
			return sesso;
		};
	
		/**
		 * Gets Telefono
		 * 
		 * @return telefono
		 */
		
		public String getTelefonoStudente() {
			return telefono;
		}
		
	
}
