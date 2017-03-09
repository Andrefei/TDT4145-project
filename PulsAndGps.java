package trening;

import java.sql.Connection;
import java.time.LocalTime;

public class PulsAndGps implements ActiveDomainObject{
	
	private Workout workout;
	private LocalTime time;
	private int pulse, longitude, latitude, altitude;
	
	public PulsAndGps(Workout w, String data) {
		workout = w;
		String[] info = data.split(",");
		time = LocalTime.parse(info[0]);
		pulse = Integer.valueOf(info[1]);
		longitude = Integer.valueOf(info[2]);
		latitude = Integer.valueOf(info[3]);
		altitude = Integer.valueOf(info[4]);
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
