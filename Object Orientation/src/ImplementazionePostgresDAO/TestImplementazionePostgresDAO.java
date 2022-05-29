package ImplementazionePostgresDAO;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.TestDAO;
import DAO.InsegnanteDAO;
import Model.Utente;
import Model.Insegnante;
import Model.Test;
import Database.ConnessioneDatabase;




public class TestImplementazionePostgresDAO implements TestDAO{

	private Connection connection;
	public int idTest;
	
	//ArrayList int
	ArrayList elencoIdQuizMultiplo = new ArrayList();
	ArrayList elencoIdQuizAperto = new ArrayList();
	
	
	public ArrayList<String> domandeMultiple = new ArrayList();
	public ArrayList<String> domandeAperte = new ArrayList();
	
	public TestImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	

	@Override
	public int getIdTestDB(String nometest) {
		try {PreparedStatement getIdTestPS = connection.prepareStatement(
				"SELECT IDTEST FROM TEST WHERE NOMETEST='"+nometest+"'"
				);
		ResultSet rs = getIdTestPS.executeQuery();
		if(rs.next()) {
			idTest = rs.getInt("idtest");
		}
		
		rs.close();
		
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return idTest;
	}
	
	//Metodo per raccogliere i quiz multipli di un test
	@Override
	public ArrayList getElencoQuizMultiploDB(String nometest) {
		ArrayList elencoQuizMultiplo = new ArrayList();
		
		try { PreparedStatement getElencoQuizPS = connection.prepareStatement(
				"select q.idquizm from composto_da_qm as qm ,test as t,quiz_m as q where q.idquizm = qm.idquizm and qm.idtest=t.idtest and t.idtest in (select idtest from test as t where nometest='"+nometest+"')"
				);
		ResultSet rs = getElencoQuizPS.executeQuery();
		while(rs.next()) {
			elencoQuizMultiplo.add(rs.getInt("idquizm"));
		}
		rs.close();

	}catch(SQLException e) {
		e.printStackTrace();
	}
		return elencoQuizMultiplo;
	}

	@Override
	public ArrayList getElencoQuizApertoDB(String nometest) {
		
		ArrayList elencoQuizAperto = new ArrayList();
		
		try { PreparedStatement getElencoQuizPS = connection.prepareStatement(
				"select q.idquiza from composto_da_qa as qa, test as t,quiz_a as q where q.idquiza = qa.idquiza and qa.idtest=t.idtest and t.idtest in (select idtest from test as t where nometest='"+nometest+"')"
				);
		ResultSet rs = getElencoQuizPS.executeQuery();
		while(rs.next()) {
			elencoQuizAperto.add(rs.getInt("idquiza"));
		}
		rs.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return elencoQuizAperto;
	}
	
	
	//metodo per raccogliere i quiz aperti di un test
	

	/*@Override
		public int getIdTestDB(String nometest) {
		int idTest=0;
		
		try {PreparedStatement getIdTestPS = connection.prepareStatement(
				"SELECT idtest from test where nometest='"+nometest+"'"
				);
		ResultSet rs = getIdTestPS.executeQuery();
		
		if(rs.next()) {
			idTest= rs.getInt("idtest");
		}
		
		rs.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return idTest;
	}
	*/
	
	@Override
	public ArrayList<String> VisualizzaQuizMultiploDB(String nometest, int idquizm) {
	
		ArrayList elencoQuizMultiplo = new ArrayList();
		
		TestDAO t = new TestImplementazionePostgresDAO();
		elencoQuizMultiplo = t.getElencoQuizMultiploDB(nometest);
		ArrayList<String> domande = new ArrayList();
			 
			try {PreparedStatement getDomandeMultipleST = connection.prepareStatement(
					"select domanda,testoopzione,t.idtest "+
					"from composto_da_qm as qq, quiz_m as q, elenco_opzioni as e ,test as t "+
					"where qq.idquizm="+elencoQuizMultiplo.get(idquizm)+ " and q.idquizm="+elencoQuizMultiplo.get(idquizm)+" and e.idquizm="+elencoQuizMultiplo.get(idquizm)+" and t.idtest in(select idtest"
																								   +" from test"
																								   +" where nometest='Fisica')"
					);
				ResultSet rs = getDomandeMultipleST.executeQuery();
				if(rs.next()) {
				domande.add(rs.getString("domanda"));
				domande.add(rs.getString("testoopzione"));
				idTest = rs.getInt("idtest");
				}
				
				while(rs.next()) {
					
						domande.add(rs.getString("testoopzione"));
						
				}
				
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return domande;
		}

	@Override
	public ArrayList<String> VisualizzaQuizApertoDB(String nometest, int idquiza) {
		
		ArrayList elencoQuizAperto = new ArrayList();
		TestDAO test = new TestImplementazionePostgresDAO();
		elencoQuizAperto = test.getElencoQuizApertoDB(nometest);
		
		ArrayList<String> domande = new ArrayList();
		 
			try {PreparedStatement getDomandeAperteST = connection.prepareStatement(
					"select domanda,t.idtest "+
					"from composto_da_qa as qq, quiz_a as q, test as t "+
					"where qq.idquiza="+elencoQuizAperto.get(idquiza)+" and q.idquiza ="+ elencoQuizAperto.get(idquiza)+" and t.idtest in (select idtest"+
					" from test"+
					" where nometest='"+nometest+"' )"
					);
				ResultSet rs = getDomandeAperteST.executeQuery();
				
				
				
				while(rs.next()) {
					domande.add(rs.getString("domanda"));
					idTest= rs.getInt("idTest");
				}
				
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return domande;
		}

	@Override
	public String getNomeTestDB(int id) {
		String nomeTest="";
		
		try {PreparedStatement getNomeTestPS = connection.prepareStatement(
				"SELECT NOMETEST FROM TEST WHERE IDTEST="+id
				);
		ResultSet rs = getNomeTestPS.executeQuery();
		if(rs.next()) {
			nomeTest = rs.getString("nometest");
		}
		rs.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return nomeTest;
 }
	
public static void main(String[] args) {
	TestDAO a = new TestImplementazionePostgresDAO();
	ArrayList b = a.getElencoQuizApertoDB("TEST DI PROVA");
	ArrayList<String> d;
	d = a.VisualizzaQuizApertoDB("TEST DI PROVA", 1);
	System.out.println(d);
}
}

