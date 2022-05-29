package DAO;
import java.util.ArrayList;

import Model.Studente;
import Model.Svolge;

public interface StudenteDAO {
	
		/**Metodo per la registrazione di uno studente nel database**/
		public void RegistrazioneStudenteDB(Studente s);
		
		/**Metodo per il login di uno studente nell'applicativo tramite database**/
		public boolean LoginStudenteDB(String username, String password); 
		
		/*Metodo per vedere l'elenco dei test disponnibili **/
		public ArrayList<String> VisualizzaElencoTestDB();
		
		/**Metodo per svolgere un test**/
		public void aggiungiRispostaDB(Character risposta,String nometest, int idquizm, String username, String rispostaAperta, int idquiza);
		
		public ArrayList VisualizzaTestSvoltiDB(String username);
		
		/**Metodo per trovare l'id di uno studente dal database**/
		public int TrovaIdStudenteDB(String username);
		
		/**Metodo per trovare il cognome di uno studente dal database **/
		public String TrovaCognomeStudenteDB(int id);
		
		/**Metodo  per vedere se un test è stato corretto**/
		public boolean valutazioneDB(int idTest, int idStudente);
		
		/**Metodo per visionare il punteggio di un test svolto**/
		public int visionaPunteggioDB(int idTest, int idStudente);
		
		/**Metodo per controllare se la password gia esiste**/
		public boolean checkPasswordDB(String password, String username);
		
		/**Metodo per modificare la password di uno studente**/
		public void modificaPasswordDB(String username, String nuovaPassword);
		
		/**Metodo per reperire informazioni di uno studente dal database**/
		public ArrayList<String> datiStudenteDB(String username);
		
		/**Metodo per trovare il nome e cognome di uno studente dall'id**/
		public ArrayList<String> trovaDatiDB(int idStudente);
		
		/**Metodo per trovare le risposte date da uno studente ad un quiz aperto **/
		public ArrayList<String> trovaRisposteAperteDB(int idStudente,int idTest);
		
		/**Metodo per trovare gli id quiz aperti risposti da uno studente ad un determinato test **/
		public ArrayList<String> trovaDomandaQuizApertoDB(int idStudente, int idTest);
		
		/**Metodo per trovare i punteggio di da assegnare ad un quiz aperto**/
		public ArrayList trovaPunteggiDaAssegnareDB(int idStudente, int idTest);
		
		/**Metodo per vedere se uno studente puo svolgere un test**/
		public boolean verificaTestSvoltoDB(int idStudente, int idTest);

	
		
		
		
}
