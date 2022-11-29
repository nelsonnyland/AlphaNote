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
	
	public Settings(String email, String first_name) {
		this.email = email;
		this.first_name = first_name;
	}
	
	
	public Settings() {
		super();
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public static void exportSettings()
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
	      fout = new FileOutputStream("c:\\temp\\settings.ser", true);
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
	
	@Override
	public String toString() {
		return "Settings [id=" + id + ", email=" + email + ", first_name=" + first_name + "]";
	}


	public static void importSettings() {
		Object result = null;
        try {
        	FileInputStream fis = new FileInputStream("c:\\temp\\settings.ser");
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
