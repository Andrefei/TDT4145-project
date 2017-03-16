import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import com.sun.javafx.tk.Toolkit;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.xml.ws.Service;


public class logController {

    private Connection conn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<logRow> workoutTable;

    @FXML
    private TableColumn<logRow, LocalDate> dateCol;

    @FXML
    private TableColumn<logRow, String> noteCol;

    @FXML
    private TableColumn<logRow, LocalTime> time;

    @FXML
    private TableColumn<logRow, Integer> form;

    @FXML
    private TableColumn<logRow, Integer> performance;


    @FXML
    private Button close;

    @FXML
    void initialize() {

        workoutTable.setEditable(true);
        workoutTable.getColumns().clear();

        ObservableList<logRow> row = rowPopulator();

        dateCol.setCellValueFactory(new PropertyValueFactory<logRow, LocalDate>("date"));
        noteCol.setCellValueFactory(new PropertyValueFactory<logRow, String>("note"));
        time.setCellValueFactory(new PropertyValueFactory<logRow, LocalTime>("duration"));
        form.setCellValueFactory(new PropertyValueFactory<logRow, Integer>("form"));
        performance.setCellValueFactory(new PropertyValueFactory<logRow, Integer>("performance"));

        workoutTable.setItems(row);
        workoutTable.getColumns().addAll(dateCol,noteCol,time,form,performance);


        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) close.getScene().getWindow();
                stage.close();
            }
        });
    }

    public ObservableList<logRow> rowPopulator(){
        ObservableList<logRow> row = FXCollections.observableArrayList();

        try {
            Statement getWorkouts = conn.createStatement();
            ResultSet workouts = getWorkouts.executeQuery("select * from workout;");

            while (workouts.next()){
                LocalDate date = workouts.getDate("date").toLocalDate();
                String note = workouts.getString("notes");
                LocalTime duration = workouts.getTime("duration").toLocalTime();
                Integer form = workouts.getInt("personalPerf");
                Integer performance = workouts.getInt("performance");

                row.add(new logRow(date,note,duration,form,performance));
            }

        }catch (Exception e){
            System.out.println(e);
        }

        return row;
    }


    public logController(Connection conn){
        this.conn = conn;
    }
}
