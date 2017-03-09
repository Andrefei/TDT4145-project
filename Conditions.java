package trening;

import java.sql.Connection;

public class Conditions implements ActiveDomainObject{
	
	private Exercise exercise;
	private int temperature;
	private String weather;
	
	public Conditions(Exercise exercise, int temp, String weather) {
		this.exercise = exercise;
		this.temperature = temp;
		this.weather = weather;
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
