package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.utilities.Settings;

public interface SettingsDAO extends JpaRepository<Settings,Integer>{

}
