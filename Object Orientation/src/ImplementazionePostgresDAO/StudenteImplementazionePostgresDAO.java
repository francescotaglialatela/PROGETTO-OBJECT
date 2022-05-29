package ImplementazionePostgresDAO;
import java.io.Reader;import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.StudenteDAO;
import DAO.TestDAO;
import Model.Utente;
import Model.Insegnante;
import Model.Studente;
import Model.Svolge;
import Database.ConnessioneDatabase;

public class StudenteImplementazionePostgresDAO implements StudenteDAO{
	
	private Connection connection;
	private int Id;
	
	public StudenteImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	};
	
	@Override
	public void RegistrazioneStudenteDB(Studente s) {
		try{PreparedStatement RegistrazioneStudentePS = connection.prepareStatement (
				"INSERT INTO STUDENTE(idStudente,nome,cognome,username,password,email,sesso) VALUES ((nextval('idStu')), '"+s.getNomeStudente()+"'," +
		"'" + s.getCognomeStudente() + "','" +s.getUsername() +"','" +s.getPassword() +"','" + s.getEmail()
						+"','" + s.getSessoStudente() +"');"
						);
		RegistrazioneStudentePS.executeUpdate();
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	};
	
	@Override
	public boolean LoginStudenteDB(String username, String password) {
		try {PreparedStatement LoginStudentePS = connection.prepareStatement( 
				"SELECT * FROM STUDENTE WHERE USERNAME =" +"'"+username +"'"+
				"AND PASSWORD="+"'"+password+"'");
					ResultSet rs = LoginStudentePS.executeQuery();
						if(!rs.next()) {
								return false;
							} 
						rs.close();
						}catch(SQLException e) {
						e.printStackTrace();
					}
					return true;
	};
	
	@Override
	//Cambiare in return arraylist
public ArrayList<String> VisualizzaElencoTestDB() {
		
		ArrayList<String> elencotest = new ArrayList();
		
		int i=0;
		try {PreparedStatement ElencoTestPS = connection.prepareStatement(
				"SELECT nometest,Cognome FROM test,insegnante where idinsegnante = insegnante"
				  
				);
		ResultSet rs = ElencoTestPS.executeQuery();
		while(rs.next()) {
			elencotest.add(rs.getString("nometest"));
			elencotest.add(rs.getString("cognome"));
			
		}
			rs.close();
		
	}catch(SQLException e) {
		e.printStackTrace();
		}
		
		return elencotest;
	}
	
	public int TrovaIdStudenteDB(String username) {
		try {PreparedStatement TrovaInsPS = connection.prepareStatement(
				"SELECT idstudente FROM studente WHERE USERNAME=" + "'"+ username+"'");
		ResultSet rs =TrovaInsPS.executeQuery();
		if(rs.next()) {
			
			Id=rs.getInt("idstudente");
		}
		rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return Id;
	};
	
	@Override
	public void aggiungiRispostaDB(Character risposta,String nometest, int idquizm, String username, String rispostaAperta,  int idquiza) {
		int idInsegnante=0;
		int idStudente =0;
		int idTest =0;
		int idQuizM = 0;
		int idQuizA = 0;
		
		
		idQuizA = idquiza;
		TestDAO idtest = new TestImplementazionePostgresDAO();
		StudenteDAO idstudente = new StudenteImplementazionePostgresDAO();
		idStudente = idstudente.TrovaIdStudenteDB(username);
		idTest = idtest.getIdTestDB(nometest);
		idQuizM = idquizm;
		
		try {PreparedStatement trovaIdInsegnantePS = connection.prepareStatement(
				"SELECT INSEGNANTE FROM TEST WHERE NOMETEST='"+nometest+"'"
				);
		ResultSet rs = trovaIdInsegnantePS.executeQuery();
		if(rs.next()) {
			idInsegnante = rs.getInt("insegnante");
		}
		rs.close();
		
		
		trovaIdInsegnantePS = connection.prepareStatement(
				"SELECT IDQUIZM FROM RISPOSTE WHERE IDQUIZM="+idQuizM+" AND STUDENTE="+idStudente
				);
		 rs = trovaIdInsegnantePS.executeQuery();
		
		 if(rs.next()) {
			 
		 }
		 else {
		 
		trovaIdInsegnantePS = connection.prepareStatement(
				"INSERT INTO RISPOSTE (Studente,Insegnante,idQuizM,idTest,rispostam,verificacorrezione) values "+
				"("+idStudente+","+idInsegnante+","+idQuizM+","+idTest+",'"+risposta+"',FALSE);"
				);
		trovaIdInsegnantePS.executeUpdate();
		 }
		 
		rs.close();
		
		
		trovaIdInsegnantePS = connection.prepareStatement(
				"SELECT IDQUIZA,STUDENTE FROM RISPOSTE WHERE IDQUIZA="+idQuizA+" AND STUDENTE="+idStudente
				);
		rs = trovaIdInsegnantePS.executeQuery();
		
		if(rs.next()) {
			
		}
		else {
		
		trovaIdInsegnantePS = connection.prepareStatement(
				"INSERT INTO RISPOSTE (Studente,Insegnante,idQuizA,idTest,rispostaa,verificacorrezione) values "+
				"("+idStudente+","+idInsegnante+","+idQuizA+","+idTest+",'"+rispostaAperta+"',FALSE);"
				);
		trovaIdInsegnantePS.executeUpdate();
		}
		rs.close();
		
		trovaIdInsegnantePS = connection.prepareStatement(
				"SELECT * FROM TEST_SVOLTI WHERE idTest="+idTest+" AND STUDENTE="+idStudente
				);
		rs = trovaIdInsegnantePS.executeQuery();
		if(rs.next())
		{
			
		}
		else
		{
		trovaIdInsegnantePS = connection.prepareStatement(
				"INSERT INTO TEST_SVOLTI VALUES ("+idTest+","+idStudente+");"
				);
		trovaIdInsegnantePS.executeUpdate();
		}
		rs.close();
		
		trovaIdInsegnantePS = connection.prepareStatement(
				"SELECT * FROM TEST_CORRETTI WHERE idTest="+idTest+" AND IDSTUDENTE="+idStudente
				);
		rs = trovaIdInsegnantePS.executeQuery();
		
		if(rs.next())
		{
			
		}
		else
		{
		trovaIdInsegnantePS = connection.prepareStatement(
				"INSERT INTO TEST_CORRETTI VALUES("+idTest+","+idStudente+","+idInsegnante+",FALSE);"
				);
		
		trovaIdInsegnantePS.executeUpdate();
		}
		rs.close();
		
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}


	
	
	public ArrayList VisualizzaTestSvoltiDB(String username) {

		ArrayList testSvolti = new ArrayList();
		int idStudente;
		StudenteDAO s = new StudenteImplementazionePostgresDAO();
		idStudente = s.TrovaIdStudenteDB(username);
		
		try {PreparedStatement getTestSvoltiPS = connection.prepareStatement(
				"SELECT idtest, studente FROM TEST_SVOLTI WHERE STUDENTE="+idStudente
				);
		ResultSet rs = getTestSvoltiPS.executeQuery();
		while(rs.next()) {
			testSvolti.add(rs.getInt("idtest"));
			testSvolti.add(rs.getInt("studente"));
		}
		rs.close();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		
		return testSvolti;
}

	@Override
	public String TrovaCognomeStudenteDB(int id) {
		String cognome="";
		
		try {PreparedStatement TrovaCognomeStudentePS = connection.prepareStatement(
				"SELECT COGNOME FROM STUDENTE WHERE IDSTUDENTE ="+id
				);
		ResultSet rs = TrovaCognomeStudentePS.executeQuery();
		if(rs.next()) {
			cognome = rs.getString("cognome");
		}
		rs.close();
	}catch(SQLException e ) {
		e.printStackTrace();
	}
		return cognome;
}

	@Override
	public boolean valutazioneDB(int idTest, int idStudente) {
		
		try {PreparedStatement valutazionePS = connection.prepareStatement(
				"SELECT VERIFICACORREZIONE FROM TEST_CORRETTI WHERE IDTEST="+idTest+"AND IDSTUDENTE="+idStudente
				);
		ResultSet rs = valutazionePS.executeQuery();
		if(rs.next()) {
			if(rs.getBoolean("verificacorrezione")==false)
				return false;
		}
		rs.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return true;
	}

	@Override
	public int visionaPunteggioDB(int idTest, int idStudente) {
		int punteggioqm=0;
		int punteggioa =0;
		int punteggioTOT=0;
		
		try {PreparedStatement visionaPunteggioPS = connection.prepareStatement(
				"select sum(puntiqm)"
				+ " from risposte"
				+ " where idtest = "+idTest+" and studente="+idStudente+" and idquizm is not null"
				);
		ResultSet rs = visionaPunteggioPS.executeQuery();
		if(rs.next()) {
			punteggioqm = rs.getInt("sum");
		}
		
		
		visionaPunteggioPS = connection.prepareStatement(
				"select sum(punteggioassegnato)"
				+" from risposte"
				+" where idtest = "+idTest+" and studente= "+idStudente
				);
		
		 rs = visionaPunteggioPS.executeQuery();
		if(rs.next()) {
			punteggioa = rs.getInt("sum");
		}
		rs.close();
		
		punteggioTOT = punteggioqm + punteggioa;
		
	}catch(SQLException e ) {
		e.printStackTrace();
	}
		return punteggioTOT;
	}
	
	

	@Override
	public void modificaPasswordDB(String username, String nuovaPassword) {
		
		int idStudente=0;
		
		StudenteDAO s = new StudenteImplementazionePostgresDAO();
		idStudente = s.TrovaIdStudenteDB(username);
		
		try {PreparedStatement modificaPasswordPS = connection.prepareStatement(
				"update studente set password='"+nuovaPassword+"' where idstudente="+idStudente
				);
		modificaPasswordPS.executeUpdate();
		
		connection.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	}

	@Override
	public boolean checkPasswordDB(String password, String username) {

		try {PreparedStatement checkPasswordPS = connection.prepareStatement(
				"select password from studente where username ='"+username+"'"
				);
		ResultSet rs = checkPasswordPS.executeQuery();
		if(rs.next()) {
			if(password.equals(rs.getString("password"))==true)
			{
			return false;
			}
		}
		rs.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return true;
}

	@Override
	public ArrayList<String> datiStudenteDB(String username) {
		
		ArrayList<String> datiStudente = new ArrayList();
		
		try {PreparedStatement datiStudentePS = connection.prepareStatement(
				"select nome,cognome,email,username,password from studente where username='"+username+"'"
				);
		ResultSet rs = datiStudentePS.executeQuery();
		
		if(rs.next()) {
			datiStudente.add(rs.getString("nome"));
			datiStudente.add(rs.getString("cognome"));
			datiStudente.add(rs.getString("email"));
			datiStudente.add(rs.getString("username"));
			datiStudente.add(rs.getString("password"));
		}
		rs.close();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		
		return datiStudente;
	
	}

	@Override
	public ArrayList<String> trovaDatiDB(int idStudente) {
		ArrayList<String> aux = new ArrayList();
		
		try {PreparedStatement trovaDatiPS = connection.prepareStatement(
				"SELECT NOME,COGNOME "
				+"FROM STUDENTE "
						+"WHERE IDSTUDENTE="+idStudente
				);
		ResultSet rs = trovaDatiPS.executeQuery();
		if(rs.next())
		{
			aux.add(rs.getString("nome"));
			aux.add(rs.getString("cognome"));
		}
		
		rs.close();
	}catch(SQLException e)
		{
		e.printStackTrace();
		}
		return aux;
	}

	@Override
	public ArrayList<String> trovaDomandaQuizApertoDB(int idStudente,int idTest) {
		ArrayList<String> Domande = new ArrayList();
		
		try {PreparedStatement trovaRispostaApertePS = connection.prepareStatement(
				"SELECT DOMANDA FROM QUIZ_A WHERE IDQUIZA IN "+
						"(SELECT IDQUIZA FROM RISPOSTE WHERE IDTEST="+idTest+" AND STUDENTE="+idStudente+" AND IDQUIZA IS NOT NULL)"
				);
				ResultSet rs = trovaRispostaApertePS.executeQuery(); 
				while(rs.next())
				{
					Domande.add(rs.getString("domanda"));
				}
				rs.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return Domande;
	}

	@Override
	public ArrayList<String> trovaRisposteAperteDB(int idStudente, int idTest) {
		ArrayList<String> Risposte = new ArrayList();
		
		try {PreparedStatement trovaRispostaApertePS = connection.prepareStatement(
				"SELECT RISPOSTAA"+
		" FROM RISPOSTE"+
						" WHERE IDTEST="+idTest+" and Studente="+idStudente+" and idquiza is not null"
				);
				ResultSet rs = trovaRispostaApertePS.executeQuery();
				while(rs.next())
				{
					Risposte.add(rs.getString("rispostaa"));
				}
				rs.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return Risposte;
		
	}

	@Override
	public ArrayList trovaPunteggiDaAssegnareDB(int idStudente, int idTest) {
		ArrayList<Double> punteggi = new ArrayList();
		
		try {PreparedStatement trovaPunteggiDaAssegnarePS = connection.prepareStatement(
				"select puntmin,puntmax from quiz_a where idquiza in "+
						"(select idquiza from risposte where idtest="+idTest+" AND STUDENTE="+idStudente+")"
				);
		ResultSet rs = trovaPunteggiDaAssegnarePS.executeQuery();
		
		while(rs.next())
		{
			punteggi.add(rs.getDouble("puntmin"));
			punteggi.add(rs.getDouble("puntmax"));
		}
			rs.close();
			
		}catch(SQLException e)
			{
				e.printStackTrace();
			}
		return punteggi;
	}

	@Override
	public boolean verificaTestSvoltoDB(int idStudente, int idTest) {
		
		try {PreparedStatement verificaTestSvoltoPS = connection.prepareStatement(
				"SELECT IDSTUDENTE FROM TEST_CORRETTI WHERE IDTEST="+idTest+" and idStudente="+idStudente
				);
		ResultSet rs = verificaTestSvoltoPS.executeQuery();
		if(rs.next())
		{
			return true;
		}
		rs.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return false;
	}
	
	public static void main(String[] args) {
		StudenteDAO ins = new StudenteImplementazionePostgresDAO();
		
		System.out.println(ins.visionaPunteggioDB(500002, 200013));
	}
	
}
