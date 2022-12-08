package application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Team is an object for storing team membership.
 *
 * @author Mario Vidal
 */
public class Team {

	private List<String> members = new ArrayList<>();
	
	public void addMember(String name){
		members.add(name);
	}
	
	public List<String> getMembers() {
		return members;
	}
	
}
