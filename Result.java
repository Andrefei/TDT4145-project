package trening;

import java.time.LocalDate;

public class Result {
	
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

}
