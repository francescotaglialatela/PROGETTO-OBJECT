package Model;

public class Test {
	public String nomeTest;
	public float punteggioMax;
	public boolean visionaPunteggio;
	public Insegnante ins;
	String usernameInsegnante;
	
	public Test(String n, Insegnante i) {
		nomeTest= n;
		ins = i;
	}
	
	public Test(String n) {
		nomeTest = n;
		}
	
	/**
	 * Gets NomeTest.
	 * 
	 * @return nomeTest
	 */
	
	public String getNomeTest() {
		return nomeTest;
	}
	
	/**
	 * Gets punteggioMax.
	 * 
	 * @return punteggioMax
	 */
	
	public float getPunteggioMax() {
		return punteggioMax;
	}
	
	/**
	 * Gets visionaPunteggio.
	 * 
	 * @return visionaPunteggio
	 */
	
	public boolean getVisionaPunteggio() {
		return visionaPunteggio;
	}
}
