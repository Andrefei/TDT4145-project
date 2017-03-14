import java.sql.Connection;
import java.util.List;

/**
 * Created by matsj on 13.03.2017.
 */
public class informationHolder {
    private String tittel;
    private String beskrivelse;
    private String goalData;
    private List<String> exercises;



    private Connection conn;

    public String getTittel() {
        return tittel;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public String getGoalData(){ return goalData; }

    public List<String> getExercises() { return exercises; }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public void setGoalData(String goalData) { this.goalData = goalData; }

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }
}
