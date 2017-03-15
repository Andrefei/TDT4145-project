package GuiBuild.src;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class workoutController {

    private List<String> exercises;
    private Connection conn;
    private Stage ovWindow;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button save;

    @FXML
    private Button cancel;

    @FXML
    private ComboBox<Integer> performance;

    @FXML
    private ChoiceBox<String> fromH;

    @FXML
    private ChoiceBox<String> fromM;

    @FXML
    private ChoiceBox<String> toH;

    @FXML
    private ChoiceBox<String> toM;

    @FXML
    private ComboBox<Integer> personalPerf;

    @FXML
    private DatePicker dateNow;

    @FXML
    private TextArea notes;

    @FXML
    void initialize() {
        //Lists for time:
        ObservableList<String> hourList = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        ObservableList<String> minuteList = FXCollections.observableArrayList("00","15","30","45");

        //Setting up timepicker
        fromH.setItems(hourList);
        toH.setItems(hourList);

        fromM.setItems(minuteList);
        toM.setItems(minuteList);


        //Setting up datepicker, default today
        dateNow.setValue(LocalDate.now());

        //Setting up form and performance
        ObservableList<Integer> scale = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10);
        personalPerf.setItems(scale);
        performance.setItems(scale);

        //Cancelbutton
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage)cancel.getScene().getWindow();
                stage.close();
            }
        });

        //Savebutton
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Checks if user has entered all fields and if SQL statements went through. Excuse the mess.
                if (fromH.getValue() != null && toH.getValue() != null && fromM.getValue() != null && toM.getValue() != null && notes.getText() != null && personalPerf.getValue() != null && performance.getValue() !=null && addWorkout()){
                    Stage stage = (Stage)save.getScene().getWindow();
                    ovWindow.close();
                    stage.close();
                }else {
                    errPopUp();
                }


            }
        });


    }

    public workoutController(informationHolder info, Connection conn, Stage ovWindow){
        exercises = info.getExercises();
        this.conn = conn;
        this.ovWindow = ovWindow;
    }
    private void errPopUp(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feilmelding");
        alert.setHeaderText(null);
        alert.setContentText("En eller fler felter er feil innført.");

        alert.showAndWait();
    }

    private boolean addWorkout(){
        //Returns true if successful

        LocalTime fromTime = LocalTime.of(Integer.parseInt(fromH.getValue()), (Integer.parseInt(fromM.getValue())));
        LocalTime toTime = LocalTime.of(Integer.parseInt(toH.getValue()), (Integer.parseInt(toM.getValue())));

        try {
            //Add data
            Statement addDate = conn.createStatement();
            addDate.executeUpdate("INSERT INTO `workoutapp`.`workout` (`date`,`time`,`duration`,`notes`,`performance`,`personalPerf`)"+
                    "VALUES('" + dateNow.getValue() + "','00:" + fromTime + "',timediff('"+ toTime + "','" + fromTime + "'),'" + notes.getText() + "','" + performance.getValue() + "','" + personalPerf.getValue() + "')");

        }catch (Exception e){
            System.out.println(e);
            //errPopUp();
        }

        //Now add exercises to this workout
        try {
            //Finding which workout this is:
            Statement lastWorkoutGet = conn.createStatement();
            ResultSet currWorkout = lastWorkoutGet.executeQuery("select max(id) from workout;");
            currWorkout.next();

            //Add exercises to this workout
            Statement addExercises = conn.createStatement();
            for (String exercise: exercises) {
                addExercises.executeUpdate("INSERT INTO `workoutapp`.`exercise_workout` (`exercise`, `workout`) VALUES ('" + exercise + "','" + currWorkout.getInt(1) +  "');");
            }

            return true;

        }catch (Exception e){
            System.out.println(e);
            return false;

        }
    }
}
