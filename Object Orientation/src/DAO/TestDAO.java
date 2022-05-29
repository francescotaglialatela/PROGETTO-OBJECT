package DAO;
import java.util.ArrayList;

import Model.Test;

public interface TestDAO {
	
	public ArrayList getElencoQuizMultiploDB(String nometest);
	public ArrayList getElencoQuizApertoDB(String nometest);
	public ArrayList<String> VisualizzaQuizMultiploDB(String nometest, int idquizm);
	
	public ArrayList<String> VisualizzaQuizApertoDB(String nometest, int idquiza);
	
	public int getIdTestDB(String nometest);
	
	public String getNomeTestDB(int id);
	
	

}
