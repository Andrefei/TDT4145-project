package trening;

import java.sql.*;
import java.time.LocalTime;

public class PulsAndGps implements ActiveDomainObject{

	private Workout workout;
	private LocalTime time;
	private int id, pulse, longitude, latitude, altitude;


	public PulsAndGps(int id,Workout w, String data) {
		this.id = id;
		workout = w;
		String[] info = data.split(",");
		time = LocalTime.parse(info[0]);
		pulse = Integer.valueOf(info[1]);
		longitude = Integer.valueOf(info[2]);
		latitude = Integer.valueOf(info[3]);
		altitude = Integer.valueOf(info[4]);
	}
	public PulsAndGps(Workout w, String data) {
		this(-1, w, data);
	}

	private String dataAsString(){
		String data = "";
		data += (java.sql.Time.valueOf(time)+","+pulse+","+longitude+","+latitude+","+altitude);
		return data;
	}

	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Connection conn) {
		initialize(conn);

	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			String data = dataAsString();
			if (id != -1){
				stmt.executeUpdate("UPDATE pulsandgps SET data="+data+", workout="+workout.getId()+", WHERE id="+id);
			} else {
				stmt.executeUpdate("INSERT INTO pulsandgps VALUES(NULL,"+data+","+workout.getId()+")");
				ResultSet rs = stmt.executeQuery("SELECT last_insert_id() FROM pulsandgps");
				while (rs.next()){
					id = rs.getInt(1);
				}
			}
		} catch (Exception e){
			System.out.println("db error during saving of goal");
			return;
		}

	}

}
