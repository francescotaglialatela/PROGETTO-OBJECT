package DAO;
import Model.Studente;
import Model.Test;
import Model.Insegnante;
import Model.Multiplo;

import java.util.ArrayList;

import Model.Aperto;


public interface InsegnanteDAO {
	/**Metodo per la registrazione di un insegnante nel database**/
	public void RegistrazioneInsegnanteDB(Insegnante s);
	
	/**Metodo per il login di un insegnante nell'applicativo tramite database**/
	public boolean LoginInsegnanteDB(String username, String password);
	
	/**Metodo per creare un test **/
	public void CreazioneTestDB(String nomeTest, String username);
	
	/**Metodo per cancellare un test**/
	public void CancellaTestDB(Test t);
	
	/**Metodo per trovare l'id di un insegnante**/
	public int  TrovaIdInsegnanteDB(String username);
	
	/**Aggiungi un quiz multiplo**/
	public int addQuizMultiploDB(Multiplo m, String t);
	
	/**Aggiungi opzione ad un quiz multiplo **/
	public void addOpzione(String text, int idquiz, char op);
	
	public int addQuizApertoDB(Aperto a, String nometest);
	
	public ArrayList<String> VisualizzaTestCreati(String username);
	
	public void CorreggiTest( int idStudente, int idQuizA, double punteggio);
	
	public ArrayList VisualizzaTestConsegnati(String username_i);
	
	public void SpuntaTest(int idStudente,int idTest);
	
	public boolean verificaTestCorretto(int idStudente, int idTest);
	
	public ArrayList<String> datiInsegnanteDB(String username);
	
	public boolean checkPasswordInsegnanteDB(String password, String username);
	
	public void modificaPasswordInsegnanteDB(String username, String nuovaPassword);
	
}
