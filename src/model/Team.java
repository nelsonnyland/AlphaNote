package model;

import java.util.ArrayList;
import java.util.List;

public class Team {

	private List<String> members = new ArrayList<>();
	
	public void addMember(String name){
		members.add(name);
	}
	
	public List<String> getMembers() {
		return members;
	}
	
}
