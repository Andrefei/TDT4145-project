package trening;

import java.sql.Connection;

public class Category implements ActiveDomainObject{
	
	private int id;
	private String name;
	
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Category(String name) {
		this.id = -1;
		this.name = name;
	}

	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		
	}

}
