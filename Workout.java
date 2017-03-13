import java.sql.*;
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

	public int getId() {
		return this.id;
	}


	public Workout(LocalDate date, LocalTime start, int duration, String note, int form, int performance) {
		this(-1, date, start, duration, note, form, performance);
	}

	public static void main(String[] args) {
		LocalDate d = LocalDate.now();
		LocalTime t = LocalTime.now();
		Workout w = new Workout(1,d, t, 50, "Bra", 2,  2);
		DBConn db = new DBConn();
		db.connect();
		w.save(db.getConnection());
		db.close();
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
				String str = "UPDATE workout SET date=\'"+java.sql.Date.valueOf(date).toString()+"\', time=\'"+java.sql.Time.valueOf(startTime).toString()+"\', duration="+(double)duration
						+", notes=\'"+note+"\', performance="+(byte)performance+" WHERE id="+id;
				stmt.executeUpdate(str);
			} else {
				String SQLString = "INSERT INTO workout (date, time, duration, notes, performance) VALUES (\'"+java.sql.Date.valueOf(date).toString()+"\',\'"+java.sql.Time.valueOf(startTime).toString()+"\',"+(double)duration+",\'"+note+"\',"+ (byte)performance+")";
				stmt.executeUpdate(SQLString);
				ResultSet rs = stmt.executeQuery("SELECT last_insert_id() FROM workout");
				while (rs.next()){
					id = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			System.out.println("DB error during saving of workout \n");
			System.out.println(e.getMessage());
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

	}


}
