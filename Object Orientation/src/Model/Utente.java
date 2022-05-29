package Model;

public class Utente {
	/**Username di un utente**/
	public String Username;
	
	/**Password di un utente**/
	public String Password;
	
	/**Email di un utente**/
	protected String email;
	
	/**Costruttore di Utente**/
		public Utente(String u, String p, String e) {
			Username = u;
			Password=p;
			email = e;
		};
		
	/**
	 * Gets Username.
	 * 
	 * @return Username
	 */
		
	public String getUsername() {
		return Username;
	}
	
	/**
	 * Gets Password.
	 * 
	 * @return Password
	 */
	
	public String getPassword() {
		return Password;
	}
	
	/**
	 * Gets Email.
	 * 
	 * @return email
	 */
	
	public String getEmail() {
		return email;
	}
}
