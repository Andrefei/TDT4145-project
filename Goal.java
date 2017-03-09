package trening;

import java.time.LocalDate;

public class Goal {
	
	private Exercise exercise;
	private String description;
	private double weight, distance, duration;
	private int repetitions, sets;
	private LocalDate date;
	
	public Goal(Exercise e, String desc, double weight, double dist, double dur, int reps, int sets, LocalDate date) {
		this.exercise = e;
		this.description = desc;
		this.weight = weight;
		this.distance = dist;
		this.duration = dur;
		this.repetitions = reps;
		this.sets = sets;
		this.date = date;
	}

}
