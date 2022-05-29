package Model;

import java.util.ArrayList;

public class Multiplo extends Quiz{

	
	public double puntC;
	public double puntS;
	public ArrayList<String> textOpzione;
	public String txt;
	
	public Multiplo(String d, double pc, double ps, String txtop,char rc) {
		super(d,rc);
		puntC= pc;
		puntS= ps;
		txt=txtop;
	}
	
	public ArrayList<String> addElement(String txt) {
		textOpzione.add(txt);
		return textOpzione;
	}
	
	/**
	 * Gets Punteggio Corretto.
	 * 
	 * @return puntC
	 */
	
	public double getPuntC() {
		return puntC;
	}
	
	/**
	 * Gets Punteggio per una risposta sbagliata.
	 * 
	 * @return puntS
	 */
	
	public double getPuntS() {
		return puntS;
	}
	
	/**
	 * Gets Testo dell'opzione del quiz
	 * 
	 * @return textOpzione
	 */
	
	public ArrayList<String> getTextOpzione() {
		return textOpzione;
	}
	
	public String getText() {
		return txt;
	}
}
