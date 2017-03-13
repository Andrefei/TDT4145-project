
import java.sql.Connection;
import java.time.LocalDate;
import java.sql.Date;

public class Goal implements ActiveDomainObject{

	private Exercise exercise;
	private String description;
	private double weight, distance, duration;
	private int id, repetitions, sets;
	private LocalDate date;


	public Goal(int id, Exercise e, String desc, double weight, double dist, double dur, int reps, int sets, LocalDate date) {
		this.id = id;
		this.exercise = e;
		this.description = desc;
		this.weight = weight;
		this.distance = dist;
		this.duration = dur;
		this.repetitions = reps;
		this.sets = sets;
		this.date = date;
	}
	public Goal(Exercise e, String desc, double weight, double dist, double dur, int reps, int sets, LocalDate date) {
		this(-1, e, desc, weight, dist, dur, reps, sets, date);
	}

	@Override
	public void initialize(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery();
		} catch (Exception e){
			System.out.println("db error during select of Goal " + e);
			return;
		}

	}

	@Override
	public void refresh(Connection conn) {
		initialize(conn);

	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			if (id != -1){
				stmt.executeUpdate("UPDATE goal SET exercise="+exercise.id+", description="+description+", weight="weight+", distance="
				+distance+", duration="+duration+", repetitions="+repetitions+", sets="+sets+", date="+java.sql.Date.valueOf(date)
				+", WHERE id="+id);
			} else {
				stmt.executeUpdate("INSERT INTO goal VALUES(NULL,"+exercise.id+","+description+","+weight+","+distance+","+duration+","
				+repetitions+","+sets+","+java.sql.date.valueOf(date)+")");
				id = last_insert_id();
			}
		} catch (Exception e){
			System.out.println("db error during saving of goal");
			return;
		}

	}

}
