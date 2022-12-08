package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.utilities.Settings;

/**
 * SettingsDAO is for extending the Settings object to the database.
 *
 * @author Mario Vidal
 */
public interface SettingsDAO extends JpaRepository<Settings,Integer>{

}
