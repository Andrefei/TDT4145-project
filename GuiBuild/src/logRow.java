import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by matsj on 16.03.2017.
 */
public class logRow {

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public Integer getForm() {
        return form;
    }

    public void setForm(Integer form) {
        this.form = form;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    private LocalDate date;
    private String note;
    private LocalTime duration;
    private Integer form;
    private Integer performance;

    public logRow(LocalDate date, String note, LocalTime duration, Integer form, Integer performance) {
        this.date = date;
        this.note = note;
        this.duration = duration;
        this.form = form;
        this.performance = performance;
    }
}
