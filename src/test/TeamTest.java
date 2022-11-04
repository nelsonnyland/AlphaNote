package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTest {

	
	private Team team;
	
	@BeforeEach
    public void setResources() {
		team = new Team();	
	}
	
	@Test
	public void testAdd() {
		
		int count = team.getMembers().size();
		assertEquals(count, 0);
		
		team.addMember("member1");
		team.addMember("member2");
		team.addMember("member3");
		team.addMember("member4");
		
		count = team.getMembers().size();
		
		assertEquals(count, 4);
		
	}
	
}
