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
	/**
	 * addMember Adds a member to the members list
	 *
	 * @author Mario Vidal
	 */
	public void addMember(String name){
		members.add(name);
	}
	/**
	 * getMembers returns the list of members
	 *
	 * @author Mario Vidal
	 */
	public List<String> getMembers() {
		return members;
	}
	
}
