package trening;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conditions implements ActiveDomainObject{

	private Exercise exercise;
	private int id, temperature;
	private String weather;


	public Conditions(int id, Exercise exercise, int temp, String weather) {
		this.id = id;
		this.exercise = exercise;
		this.temperature = temp;
		this.weather = weather;
	}
	public Conditions(Exercise exercise, int temp, String weather) {
		this(-1, exercise, temp, weather);
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
		try {
			Statement stmt = conn.createStatement();
			if (id != -1){
				stmt.executeUpdate("UPDATE conditions SET exercise="+exercise.getId()+", temperature="+temperature+", weather="+weather+" WHERE id="+id);
			} else {
				stmt.executeUpdate("INSERT INTO conditions VALUES (NULL,"+exercise.getId()+", "+temperature+", "+weather+")");
				ResultSet rs = stmt.executeQuery("SELECT last_insert_id() FROM conditions");
				while (rs.next()){
					id = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			System.out.println("db error during saving of the condition");
			return;
		}

	}

}
