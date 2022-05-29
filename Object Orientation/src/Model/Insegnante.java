package Model;

public class Insegnante extends Utente{
	public String nome;
	public String cognome;
	public char sesso;
	public String telefono;
	public String Insegnamento;
	
	/**Costruttore di insegnante**/
	public Insegnante(String u, String p, String e, String n, String c, char s, String t, String i) {
		super(u, p, e);
		nome = n;
		cognome = c;
		sesso = s;
		telefono = t;
		Insegnamento = i;
	}
	
	
	
	/**
	 * Gets Nome.
	 * 
	 * @Return nome
	 */
		
		public String getNomeInsegnante() {
			return nome;
		};
		
	/**
	 * Gets Cognome. 
	 * 
	 * @return cognome.
	 */
		
		public String getCognomeInsegnante() {
			return cognome;
		};
		
		/**
		 * Gets Sesso.
		 * 
		 * @return
		 */
		public char getSessoInsegnante() {
			return sesso;
		};
		
		/**
		 * Gets Telefono.
		 * 
		 * @return telefono
		 */
		
		public String getTelefonoInsegnante() {
			return telefono;
		}
		
		/**
		 * Gets Insegnamento.
		 * 
		 * @return Insegnamento
		 */
		
		public String getInsegnamento() {
			return Insegnamento;
		}
		
		public static void main(String[] args) {
			Insegnante nuovo = new Insegnante("kekkot", "Francy07","kdas@gmail.com","Francesco","Taglialatela",'M',"12","Matematica");
			System.out.println(nuovo.getPassword());
		}
	
}
