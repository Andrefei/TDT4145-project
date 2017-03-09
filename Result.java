package trening;

import java.sql.Connection;
import java.time.LocalDate;

public class Result implements ActiveDomainObject{
	
	private Exercise exercise;
	private double weight, distance, duration;
	private int repetitions, sets;
	private LocalDate date;
	
	public Result(Exercise e, double weight, double dist, double dur, int reps, int sets, LocalDate date) {
		this.exercise = e;
		this.weight = weight;
		this.distance = dist;
		this.duration = dur;
		this.repetitions = reps;
		this.sets = sets;
		this.date = date;
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
