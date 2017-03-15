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

        System.out.println("Creating Variables");
        Workout w = new Workout(LocalDate.parse("2017-03-17"), LocalTime.parse("10:00"), 45, "Dette gikk kjempebra", 5, 6);
        Exercise e = new Exercise("Benkpress", "Repetisjoner med vekt");
        Category c = new Category("Løft");
        e.addCategory(c);
        Conditions cond = new Conditions(e, 19, "Regn");
        Goal g = new Goal(e, "Løfte 80kg på benk", 80, 0, 0, 10, 1, LocalDate.parse("2017-04-18"));
        Result r = new Result(e, 70, 0, 0, 8, 1, LocalDate.parse("2017-03-15"));

        System.out.println("Saving");

        System.out.println("Saving workout");
        w.save(db.getConnection());

        System.out.println("Saving exercise");
        e.save(db.getConnection());

        System.out.println("Saving category");
        c.save(db.getConnection());

        System.out.println("Saving conditions");
        cond.save(db.getConnection());

        System.out.println("saving goal");
        g.save(db.getConnection());

        System.out.println("Saving result");
        r.save(db.getConnection());

        System.out.println("Closing");
        db.close();

    }

    public static void main(String[] args) {
        TestAll test = new TestAll();
        test.run();
    }
}
