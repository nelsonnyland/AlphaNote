package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Settings;

public interface SettingsDAO extends JpaRepository<Settings,Integer>{

}
