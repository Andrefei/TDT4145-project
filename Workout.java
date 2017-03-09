package trening;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Workout implements ActiveDomainObject{
	
	private LocalDate date;
	private LocalTime startTime;
	private int duration; //varighet i minutter
	private String note;
	private int id, form, performance;
	private ArrayList<Exercise> exercises;
	
	
	
	public Workout(LocalDate date, LocalTime start, int duration, String note, int form, int performance) {
		this.id = -1;
		this.date = date;
		this.startTime = start;
		this.duration = duration;
		this.note = note;
		this.form = form;
		this.performance = performance;
		this.exercises = new ArrayList<>();
	}
	
	public Workout(int id, LocalDate date, LocalTime start, int duration, String note, int form, int performance) {
		this.id = id;
		this.date = date;
		this.startTime = start;
		this.duration = duration;
		this.note = note;
		this.form = form;
		this.performance = performance;
		this.exercises = new ArrayList<>();
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
		// TODO Auto-generated method stub
		
	}
	
	

}
