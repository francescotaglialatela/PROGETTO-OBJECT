package Controller;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.*;
import ImplementazionePostgresDAO.*;
import Model.*;

import Database.ConnessioneDatabase;


public class Controller {
	
	Insegnante i;
	Studente s;
	Test t;
	Quiz q;
	Utente u;
	ConnessioneDatabase connessione;
	
	
	public Controller() {};
	
	
	/**
	 * Elenco domande aperte di un determinato test.
	 * 
	 * @param nometest
	 * @return
	 */
	public ArrayList<String> getDomandaApertaTest(String nometest) {
		ArrayList<String> elencoDomandeAperte = new ArrayList();
		TestDAO t = new TestImplementazionePostgresDAO();
		elencoDomandeAperte=t.getElencoQuizApertoDB(nometest);
		
		return elencoDomandeAperte;
	}
	
	/**
	 * Elenco Domande e opzioni di un quiz multiplo di un determinato test.
	 * 
	 * @param nometest
	 * @return
	 */
	public ArrayList<String> getDomandeMultipleTest(String nometest) {
		ArrayList<String> elencoDomandeMultiple = new ArrayList();
		TestDAO t = new TestImplementazionePostgresDAO();
		elencoDomandeMultiple = t.getElencoQuizMultiploDB(nometest);
		
		return elencoDomandeMultiple;
	}
	
	/**
	 * Registrazione Studente
	 * 
	 * @param u
	 * @param p
	 * @param e
	 * @param n
	 * @param c
	 * @param se
	 * @param t
	 */
	
	public void registrazioneStudente(String u, String p, String e, String n, String c, char se, String t) {
		s= new Studente(u,p,e,n,c,se,t);
		StudenteDAO nuovoStu = new StudenteImplementazionePostgresDAO();
		nuovoStu.RegistrazioneStudenteDB(s);
		
	}
	
	/**
	 * Login di uno studente.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean loginStudente(String username, String password) {
		boolean res;
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		res = stu.LoginStudenteDB(username, password);
		
		return res;
	}
	
	/**
	 * Registrazione di un insegnante.
	 * 
	 * @param u
	 * @param p
	 * @param e
	 * @param n
	 * @param c
	 * @param s
	 * @param t
	 * @param in
	 */
	
	public void registrazioneInsegnante(String u, String p, String e, String n, String c, char s, String t, String in) {
		i = new Insegnante(u,p,e,n,c,s,t,in);
		InsegnanteDAO nuovoIns = new InsegnanteImplementazionePostgresDAO();
		nuovoIns.RegistrazioneInsegnanteDB(i);
	}
	
	/**
	 * Login di un insegnante.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	
	public boolean loginInsegnante(String username, String password) {
		boolean res;
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		res = ins.LoginInsegnanteDB(username, password);
		
		return res;
	}
	
	
	/**
	 * Creare le opzioni di un quiz multiplo
	 * 
	 * @param text
	 * @param idquizm
	 * @param nm
	 * @param op
	 */
	public void aggiungiOpzione(String text, int idquizm, char op) {
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		ins.addOpzione(text, idquizm, op);
	}
	
	public void correggiTest(int idStudente, int idQuizA, double punteggio)
	{
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		ins.CorreggiTest(idStudente, idQuizA, punteggio);
	}
	
	public void spuntaTest(int idStudente, int idTest)
	{
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		ins.SpuntaTest(idStudente, idTest);
	}
	
	public int getIdTest(String nomeTest)
	{
		int idTest=0;
		
		TestDAO test = new TestImplementazionePostgresDAO();
		
		idTest = test.getIdTestDB(nomeTest);
		
		return idTest;
	}
	
	public ArrayList<String> trovaDomandaQuizApertoDB(int idStudente, int idTest)
	{
		ArrayList<String> domande = new ArrayList();
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		domande = stu.trovaDomandaQuizApertoDB(idStudente, idTest);
		
		return domande;
	}
	
	public ArrayList<String> trovaRisposteAperteDB(int idStudente,int idTest)
	{
		ArrayList<String> risposte = new ArrayList();
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		risposte = stu.trovaRisposteAperteDB(idStudente, idTest);
		
		return risposte;
	}
	
	public ArrayList trovaPunteggiDaAssegnareDB(int idStudente, int idTest)
	{
		ArrayList punteggi = new ArrayList();
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		punteggi = stu.trovaPunteggiDaAssegnareDB(idStudente, idTest);
		
		return punteggi;
	}
	
	public ArrayList getElencoQuizApertoDB(String nomeTest)
	{
		ArrayList elencoQuizAperto = new ArrayList();
		
		TestDAO test = new TestImplementazionePostgresDAO();
		
		elencoQuizAperto = test.getElencoQuizApertoDB(nomeTest);
		
		return elencoQuizAperto;
				
	}
	
	public ArrayList<String> trovaDatiDB(int idStudente)
	{
		ArrayList<String> datiStudente = new ArrayList();
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		datiStudente = stu.trovaDatiDB(idStudente);
		
		return datiStudente;
	}
	
	public void CreazioneTestDB(String nomeTest, String username)
	{
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		ins.CreazioneTestDB(nomeTest, username);
	}
	
	public boolean checkPasswordDB(String password, String username)
	{
		boolean checkPassword=false;
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		checkPassword = stu.checkPasswordDB(password, username);
		
		return checkPassword;
	}
	
	public void modificaPasswordDB(String username, String nuovaPassword)
	{
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		stu.modificaPasswordDB(username, nuovaPassword);
	}
	
	public int addQuizApertoDB(String domanda,double punteggioMin, double punteggioMax, String nometest)
	{
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		Aperto quizAperto = new Aperto(domanda,punteggioMin,punteggioMax,0,'a');
		
		ins.addQuizApertoDB(quizAperto, nometest);
		
		return 0;
	}
	
	public int  aggiungiQuizMultiplo(String domanda, double puntC, double puntS, String nomeTest, char rispostaC) {
		
		int idQuizM;
		
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		Multiplo quizMultiplo = new Multiplo(domanda, puntC, puntS, "", rispostaC);
		
		idQuizM = ins.addQuizMultiploDB(quizMultiplo, nomeTest);
		
		return idQuizM;
	}
	
	public void addOpzione(String text, int idquiz, char op)
	{
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		ins.addOpzione(text, idquiz, op);
		
	}
	
	public boolean verificaTestSvoltoDB(int idStudente, int idTest)
	{
		boolean checkTestSvolto;
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		checkTestSvolto = stu.verificaTestSvoltoDB(idStudente, idTest);
		
		return checkTestSvolto;
	}
	
	public int TrovaIdStudenteDB(String username)
	{
		int idStudente;
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		idStudente = stu.TrovaIdStudenteDB(username);
		
		return idStudente;
	}
	
	public int getIdTestDB(String nometest)
	{
		int idTest;
		
		TestDAO test = new TestImplementazionePostgresDAO();
		
		idTest = test.getIdTestDB(nometest);
		
		return idTest;
	}
	
	public ArrayList<String> VisualizzaElencoTestDB()
	{
		ArrayList<String> elencoTest = new ArrayList();
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		elencoTest = stu.VisualizzaElencoTestDB();
		
		return elencoTest;
	}
	
	public ArrayList<String> datiStudenteDB(String username)
	{
		ArrayList<String> datiStudente = new ArrayList();
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		datiStudente = stu.datiStudenteDB(username);
		
		return datiStudente;
	}
	
	public void aggiungiRispostaDB(Character risposta,String nometest, int idquizm, String username, String rispostaAperta, int idquiza)
	{
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		stu.aggiungiRispostaDB(risposta, nometest, idquizm, username, rispostaAperta, idquiza);
	}
	
	public ArrayList<String> VisualizzaQuizApertoDB(String nometest, int idquiza)
	{
		ArrayList<String> ElencoQuizAperto = new ArrayList();
		
		TestDAO test = new TestImplementazionePostgresDAO();
		
		ElencoQuizAperto = test.VisualizzaQuizApertoDB(nometest, idquiza);
		
		return ElencoQuizAperto;
	}
	
	public ArrayList<String> VisualizzaTestCreati(String username)
	{
		ArrayList<String> testCreati = new ArrayList();
		
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		testCreati = ins.VisualizzaTestCreati(username);
		
		return testCreati;
	}
	
	public ArrayList VisualizzaTestSvoltiDB(String username)
	{
		ArrayList testSvolti = new ArrayList();
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		testSvolti = stu.VisualizzaTestSvoltiDB(username);
		
		return testSvolti;
		
	}
	
	public boolean valutazioneDB(int idTest, int idStudente)
	{
		boolean valutazione;
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		valutazione = stu.valutazioneDB(idTest, idStudente);
		
		return valutazione;
	}
	
	public String TrovaCognomeStudenteDB(int id)
	{
		String cognome;
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		cognome = stu.TrovaCognomeStudenteDB(id);
		
		return cognome;
		
	}
	
	public String getNomeTestDB(int id)
	{
		String nomeTest;
		
		TestDAO test = new TestImplementazionePostgresDAO();
		
		nomeTest = test.getNomeTestDB(id);
		
		return nomeTest;
	}
	
	public int visionaPunteggioDB(int idTest, int idStudente)
	{
		int punteggio;
		
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		
		punteggio = stu.visionaPunteggioDB(idTest, idStudente);
		
		return punteggio;
	}
	
	public ArrayList getElencoQuizMultiploDB(String nometest)
	{
		ArrayList elenco = new ArrayList();
		
		TestDAO test = new TestImplementazionePostgresDAO();
		
		elenco = test.getElencoQuizMultiploDB(nometest);
		
		return elenco;
	}
	
	public ArrayList<String> VisualizzaQuizMultiploDB(String nometest, int idquizm)
	{
		ArrayList<String> quizMultiplo = new ArrayList();
		
		TestDAO test = new TestImplementazionePostgresDAO();
		
		quizMultiplo = test.VisualizzaQuizMultiploDB(nometest, idquizm);
		
		return quizMultiplo;
	}
	
	public boolean verificaTestCorretto(int idStudente, int idTest)
	{
		boolean verificaTest;
		
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		verificaTest = ins.verificaTestCorretto(idStudente, idTest);
		
		return verificaTest;
	}
	
	public ArrayList VisualizzaTestConsegnati(String username_i)
	{
		ArrayList testConsegnati;
		
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		testConsegnati = ins.VisualizzaTestConsegnati(username_i);
		
		return testConsegnati;
	}
	
	public ArrayList<String> datiInsegnanteDB(String username)
	{
		ArrayList<String> datiInsegnante = new ArrayList();
		
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		datiInsegnante = ins.datiInsegnanteDB(username);
		
		return datiInsegnante;
	}
	
	public boolean checkPasswordInsegnanteDB(String password, String username)
	{
		boolean checkPassword=false;
		
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		checkPassword = ins.checkPasswordInsegnanteDB(password, username);
		
		return checkPassword;
	}
	
	public void modificaPasswordInsegnanteDB(String username, String nuovaPassword)
	{
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		
		ins.modificaPasswordInsegnanteDB(username, nuovaPassword);
	}
	
	
	
}
