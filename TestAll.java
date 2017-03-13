import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Choffa on 13-Mar-17.
 */
public class TestAll implements Runnable {

    private DBConn db;

    public TestAll(){
        this.db = new DBConn();
        db.connect();
    }

    @Override
    public void run() {

        Workout w = new Workout(LocalDate.parse("2017-03-17"), LocalTime.parse("10:00"), 45, "Dette gikk kjempebra", 5, 6);
        Exercise e = new Exercise("Benkpress", "Repetisjoner med vekt");
        Category c = new Category("Løft");
        c.
    }
}
