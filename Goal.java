
import java.sql.Connection;
import java.time.LocalDate;

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
