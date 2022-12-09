package application.utilities;

import java.io.Serializable;
import java.util.List;

import application.repository.SettingsDAO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.*;

/**
 * Settings persists user settings for the application.
 *
 * @author Mario Vidal
 */
@Entity
public class Settings implements Serializable{
	
	private static final long serialVersionUID = 6529685098267757690L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String first_name;

	/**
	 * Settings instantiates the Settings class
	 *
	 * @author Mario Vidal
	 * @param email the email of the user
	 * @param first_name the first name of the user
	 */
	public Settings(String email, String first_name) {
		this.email = email;
		this.first_name = first_name;
	}

	/**
	 * A no arg constructor for Settings
	 *
	 * @author Mario Vidal
	 */
	public Settings() {
		super();
	}

	/**
	 * getEmail gets the email for the application user
	 *
	 * @author Mario Vidal
	 * @return the email for the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setEmail sets the email for the application user
	 *
	 * @author Mario Vidal
	 * @param email the email of the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * getFirst_name gets the first name of the application user
	 *
	 * @author Mario Vidal
	 * @return the first name of the user
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * setFirst_name sets the first name of the application user
	 *
	 * @author Mario Vidal
	 * @param first_name the first name of the user
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * exportSettings exports the settings for the application
	 *
	 * @author Mario Vidal
	 */

	public static void exportSettings(String path)
	{		
		SettingsDAO settingsDAO = SpringContext.getBean(SettingsDAO.class);
		
		List<Settings> settingsList = settingsDAO.findAll();    	
    	if(settingsList.size() == 0) {
    		return;    		
    	}
		    	   	
    	Settings settings = settingsList.get(0);
    	
    	System.out.println(settings);
    	
		ObjectOutputStream oos = null;
	    FileOutputStream fout = null;
	    try{ 
	      fout = new FileOutputStream(path, true);
	      oos = new ObjectOutputStream(fout);
	      oos.writeObject(settings);
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	    }
	    finally {
	       if(oos != null){
	         try {
	        	 oos.close();
	         } catch (Exception e) {
	        	 System.out.println(e.getMessage());
	         }
	       }
	    }
	}

	/**
	 * toString override for printing the settings
	 *
	 * @author Mario Vidal
	 * @return the settings
	 */
	@Override
	public String toString() {
		return "Settings [id=" + id + ", email=" + email + ", first_name=" + first_name + "]";
	}


	public static void importSettings(String path) {
		Object result = null;
        try {
        	FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = ois.readObject();
        }catch(Exception e) {
        	 System.out.println(e.getMessage());
        }
        
        if(result != null) {
        	Settings sIn = (Settings)result;
        	System.out.println(sIn);        	
        	saveSettings(sIn.first_name, sIn.email);        	
        }        
	}
	
	public static void saveSettings(String first_name, String email) {
		
		SettingsDAO settingsDAO = SpringContext.getBean(SettingsDAO.class);		
		
		List<Settings> settingsList = settingsDAO.findAll();
		
    	if(settingsList.size() == 0) {
    		Settings settings = new Settings(email, first_name);
        	settingsDAO.save(settings);
    	}else {    		
    		Settings settings = settingsList.get(0);
    		settings.setEmail(email);
    		settings.setFirst_name(first_name);
    		settingsDAO.save(settings);    		
    	}
	}
	
}
