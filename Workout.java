import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class Workout implements ActiveDomainObject {

	private LocalDate date;
	private LocalTime startTime;
	private int duration; //varighet i minutter
	private String note;
	private int id, form, performance;
	private ArrayList<Exercise> exercises;

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
			if (id != -1){
				stmt.executeUpdate("UPDATE workout SET date="+java.sql.Date.valueOf(date)+", startTime="+startTime+", duration="+duration
									+", note="+note+", form="+form+", performance="+performance+", WHERE id="+id);
			} else {
				stmt.executeUpdate("INSERT INTO workout VALUES (NULL,"+date+","+startTime+","+duration+","+note+","+form+","+performance+")");
				ResultSet rs = stmt.executeQuery("SELECT last_insert_id() FROM workout");
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("db error during saving of workout");
			return;
		}
		try {
			Statement stmt = conn.createStatement();
			for (Exercise ex: exercises) {
				if (ex.getId() != -1) {
					stmt.executeUpdate("INSERT INTO exercise_workout VALUES (" + ex.getId()+","+id+")");
				}
			}
		} catch (Exception e) {
			System.out.println("Error updating workout_exercise table");
		}
		// Iterere gjennom exercices  og oppdatere workout-exercise tabellen, plassere id til workout på den ene siden og id til ex på andre
		// Hvis id til exerciase = -1 somethings fugged

	}



}
