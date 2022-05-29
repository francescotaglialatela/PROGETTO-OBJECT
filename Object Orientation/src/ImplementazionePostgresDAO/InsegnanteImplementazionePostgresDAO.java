package ImplementazionePostgresDAO;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Controller;
import DAO.InsegnanteDAO;
import DAO.StudenteDAO;
import DAO.TestDAO;
import Model.Utente;
import Model.Aperto;
import Model.Insegnante;
import Model.Multiplo;
import Model.Test;
import Database.ConnessioneDatabase;

public class InsegnanteImplementazionePostgresDAO implements InsegnanteDAO{
	
private Connection connection;
int code;
int num;

	/**Connessione col database**/
	
	public InsegnanteImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	};
	
	/** Metodo registrazione di uno insegnante nel database **/
	@Override
	public void RegistrazioneInsegnanteDB(Insegnante ins) {
		try{PreparedStatement RegistrazioneInsegnantePS = connection.prepareStatement (
				"INSERT INTO INSEGNANTE(idInsegnante,nome,cognome,username,password, email,sesso) VALUES ((nextval('idIns')), '"+ins.getNomeInsegnante()+"'," +
		"'" + ins.getCognomeInsegnante() + "','" +ins.getUsername() +"','" +ins.getPassword() +"','" + ins.getEmail()
						+"','" + ins.getSessoInsegnante() + "');"
						);
		RegistrazioneInsegnantePS.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		};
	};

	

	@Override
	public boolean LoginInsegnanteDB(String username, String password) {
		try {PreparedStatement LoginInsegnantePS = connection.prepareStatement( 
				"SELECT * FROM INSEGNANTE WHERE USERNAME =" +"'"+username +"'"+
				"AND PASSWORD="+"'"+password+"'");
					ResultSet rs = LoginInsegnantePS.executeQuery();
						if(!rs.next()) {
							
								return false;
							} 
						rs.close();
						}catch(SQLException e) {
						e.printStackTrace();
					}
		
					return true;
	}

	@Override
	public int TrovaIdInsegnanteDB(String username) {
		try {PreparedStatement TrovaInsPS = connection.prepareStatement(
				"SELECT IDINSEGNANTE FROM INSEGNANTE WHERE USERNAME=" + "'"+username+"'");
		ResultSet rs =TrovaInsPS.executeQuery();
		if(rs.next()) {
			
			code=rs.getInt("idInsegnante");
		}
		rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return code;
	};
	
	@Override
	public void CreazioneTestDB(String nomeTest, String username) {
		
		int idInsegnante;
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		idInsegnante = ins.TrovaIdInsegnanteDB(username);
		
		try {PreparedStatement CreazioneTestPS = connection.prepareStatement( 
				"INSERT INTO TEST VALUES((nextval('idTest')),"+ idInsegnante+",'"+nomeTest+"'"+");"
			);
		CreazioneTestPS.executeUpdate();
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	//Da rimuovere questo metodo
	public void CancellaTestDB(Test t) {
		try{PreparedStatement CancellaTestPS =  connection.prepareStatement(
				"DELETE FROM TEST WHERE NOMETEST='"+t.getNomeTest() + "'"
				);
				CancellaTestPS.executeUpdate();
				
				
				}catch(SQLException e) {
					e.printStackTrace();
				}
	}

	@Override
	public int addQuizMultiploDB(Multiplo m, String test) {
		int idTest = 0;
		int idQuizM = 0;
		
		
		/**Queery per creare un quiz**/
		try {
			PreparedStatement createQuizMultiploPS = connection.prepareStatement(
				"INSERT INTO QUIZ_M VALUES ((nextval('idQuizm')),'"+m.getDomanda()+"','"+m.getRispostaC()+"',"+m.getPuntC()+","+m.getPuntS()+");"
				);
			createQuizMultiploPS.executeUpdate();
			
			createQuizMultiploPS = connection.prepareStatement(
					"select max(idquizm)"+
					"from quiz_m"
					);
			ResultSet rs = createQuizMultiploPS.executeQuery();
			
			if(rs.next()) 
			{
				idQuizM = rs.getInt("max");
			}
			rs.close();
			
			
			
			createQuizMultiploPS = connection.prepareStatement(
					"select idtest from test where nometest = '"+test+"'"
					);
			 rs = createQuizMultiploPS.executeQuery();
			
			if(rs.next()) {
				idTest = rs.getInt("idtest");
			}
			
			rs.close();
			
			createQuizMultiploPS = connection.prepareStatement(
					"INSERT INTO COMPOSTO_DA_QM VALUES ("+idQuizM + ","+idTest+");"
					);
			createQuizMultiploPS.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return idQuizM;
	}	

	@Override
	public void addOpzione(String text, int idquiz,  char op) {
		
		try {
			PreparedStatement addOpzionePS = connection.prepareStatement(
				"INSERT INTO ELENCO_OPZIONI VALUES("+idquiz+","+"'"+op+"',"+"'"+text+"');"
				);
		addOpzionePS.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}

	}	
	
	@Override
	public int addQuizApertoDB(Aperto a,String nometest) {
		int idQuizA=0;
		int idTest =0;
		
		TestDAO t = new TestImplementazionePostgresDAO();
		idTest = t.getIdTestDB(nometest);
		
		try {PreparedStatement addQuizApertoPS = connection.prepareStatement(
				"INSERT INTO QUIZ_A VALUES ((nextval('idQuiza')),'"+a.getDomanda()+"',"+a.getPuntMax()+","+a.getPuntMin()+");"
				);
		addQuizApertoPS.executeUpdate();
		
		addQuizApertoPS = connection.prepareStatement(
				"select max(idquiza)"+
				"from quiz_a"
				);
		ResultSet rs = addQuizApertoPS.executeQuery();
		if(rs.next()) {
			idQuizA = rs.getInt("max");
		}
		rs.close();
		
		addQuizApertoPS = connection.prepareStatement(
				"INSERT INTO COMPOSTO_DA_QA VALUES ("+idQuizA + ","+idTest+");"
				);
		addQuizApertoPS.executeUpdate();
		
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		
		return idQuizA;
}

	@Override
	public ArrayList<String> VisualizzaTestCreati(String username) {
		ArrayList<String> testCreati = new ArrayList();
		int IdInsegnante =0;
		
		InsegnanteDAO ins = new  InsegnanteImplementazionePostgresDAO();
		IdInsegnante = ins.TrovaIdInsegnanteDB(username);
		
		try {PreparedStatement visualizzaTestCreatiPS = connection.prepareStatement(
				"SELECT NOMETEST FROM TEST WHERE INSEGNANTE="+IdInsegnante
				);
		ResultSet rs = visualizzaTestCreatiPS.executeQuery();
		while(rs.next()) {
			testCreati.add(rs.getString("nometest"));
		}
		rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return testCreati;
	}

	@Override
	
	public void CorreggiTest(int idStudente, int idQuizA, double punteggio) {
		
		try {PreparedStatement CorreggiTestPS = connection.prepareStatement(
				"update risposte set punteggioassegnato="+punteggio+" where studente="+idStudente+" and idquiza="+idQuizA
				);
		CorreggiTestPS.executeUpdate();
	}catch(SQLException e) {
		e.printStackTrace();
	}
}		
	

	//@Override
	public ArrayList VisualizzaTestConsegnati(String username_i) {
		ArrayList testConsegnati = new ArrayList();
		int idInsegnante=0;
		InsegnanteDAO i = new InsegnanteImplementazionePostgresDAO();
		idInsegnante=i.TrovaIdInsegnanteDB(username_i);
		StudenteDAO s = new StudenteImplementazionePostgresDAO();
		
		
		try {PreparedStatement visualizzaTestConsegnatiPS = connection.prepareStatement(
				"SELECT IDSTUDENTE,IDTEST"
				+" FROM TEST_CORRETTI "
						+" WHERE IDINSEGNANTE="+idInsegnante
				);
			
			ResultSet rs = visualizzaTestConsegnatiPS.executeQuery();
			while(rs.next())
			{
				testConsegnati.add(rs.getInt("idstudente"));
				testConsegnati.add(rs.getInt("idtest"));
			}
			rs.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return testConsegnati;
	}
	
	@Override
	public void SpuntaTest(int idStudente, int idTest) {
		
		try {PreparedStatement spuntaTestPS = connection.prepareStatement(
				"UPDATE TEST_CORRETTI SET VERIFICACORREZIONE=TRUE WHERE IDSTUDENTE="+idStudente+" AND IDTEST="+idTest
				);
		spuntaTestPS.executeUpdate();
	}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean verificaTestCorretto(int idStudente, int idTest) {
		
		try {PreparedStatement verificaTestCorrettoPS = connection.prepareStatement(
				"SELECT VERIFICACORREZIONE FROM TEST_CORRETTI WHERE IDTEST="+idTest+" AND IDSTUDENTE="+idStudente+" AND VERIFICACORREZIONE=TRUE"
				);
		ResultSet rs = verificaTestCorrettoPS.executeQuery();
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

	@Override
	public ArrayList<String> datiInsegnanteDB(String username) {
		
		ArrayList<String> datiInsegnante = new ArrayList();
		
		try {PreparedStatement datiInsegnantePS = connection.prepareStatement(
				"select nome,cognome,email,username,password from insegnante where username='"+username+"'"
				);
		ResultSet rs = datiInsegnantePS.executeQuery();
		
		if(rs.next()) {
			datiInsegnante.add(rs.getString("nome"));
			datiInsegnante.add(rs.getString("cognome"));
			datiInsegnante.add(rs.getString("email"));
			datiInsegnante.add(rs.getString("username"));
			datiInsegnante.add(rs.getString("password"));
		}
		rs.close();
		
	}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return datiInsegnante;
}

	@Override
	public boolean checkPasswordInsegnanteDB(String password, String username) {
		
		try {PreparedStatement checkPasswordInsegnantePS = connection.prepareStatement(
				"select password from insegnante where username='"+username+"'"
				);
		ResultSet rs = checkPasswordInsegnantePS.executeQuery();
		if(rs.next())
		{
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
	public void modificaPasswordInsegnanteDB(String username, String nuovaPassword) {
		
		int idInsegnante =0;
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		idInsegnante = ins.TrovaIdInsegnanteDB(username);
		
		try {PreparedStatement modificaPasswordInsegnantePS = connection.prepareStatement(
				"update insegnante set password='"+nuovaPassword+"' where idinsegnante="+idInsegnante
				);
		modificaPasswordInsegnantePS.executeUpdate();
		
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		
	}
	
	public static void main(String[] args) {
		InsegnanteDAO s = new InsegnanteImplementazionePostgresDAO();
		
		s.modificaPasswordInsegnanteDB("cristiana", "cristiana");
		
	}
	
}

