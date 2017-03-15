package GuiBuild.src;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NyOvController {

    Connection conn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button lagre;

    @FXML
    private Button avbryt;

    @FXML
    private Button nyKategori;

    @FXML
    private ComboBox<String> kategorier;

    @FXML
    private TextArea ovNavn;

    @FXML
    private TextArea nyOvBeskrivelse;

    @FXML
    private DatePicker malsetningsFrist;

    @FXML
    private TextArea malsetningsBeskrivelse;

    @FXML
    void initialize() {

        //Kategorifane
        updateCat();


        //Ny Kateogriknapp
        nyKategori.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    //Setter opp vinduet for beskrivelse av øvelse
                    FXMLLoader nyOvLoader = new FXMLLoader(getClass().getResource("NyKategori.fxml"));
                    nyOvLoader.setController(new NyKategoriController(conn));
                    Parent beskUI = nyOvLoader.load();

                    Scene scene = new Scene(beskUI);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Ny Kategori");
                    stage.showAndWait();
                } catch (Exception e){
                    e.printStackTrace();
                }
                updateCat();
            }
        });

        //avbrytknapp
        avbryt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) avbryt.getScene().getWindow();
                stage.close();
            }
        });

        //Lagre øvelse
        lagre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(malsetningsFrist.getValue());
                Boolean validExercise = true;
                if ((ovNavn.getText() == null) || (nyOvBeskrivelse.getText() == null) ||(kategorier.getValue() == null)
                        || ((malsetningsFrist.getValue() == null) || (malsetningsFrist.getValue().compareTo(LocalDate.now()) < 1))){
                    errPopUp();
                }
                else{
                    createExercise();
                }
            }
        });


    }

    public NyOvController(Connection conn){
        this.conn = conn;
    }

    private void errPopUp(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feilmelding");
        alert.setHeaderText(null);
        alert.setContentText("En eller fler felter er feil innført.");

        alert.showAndWait();
    }

    private void createExercise(){
        try {
            //Setter øvelse inn i exercise
            Statement updateTable = conn.createStatement();
            updateTable.executeUpdate("INSERT INTO `workoutapp`.`exercise` (`name`, `description`) VALUES ('" + ovNavn.getText() + "','" + nyOvBeskrivelse.getText() +  "');");

            //Så setter vi inn category_exercise på en tungvint måte
            Statement catNumGetter = conn.createStatement();
            ResultSet catNum = catNumGetter.executeQuery("SELECT id FROM category WHERE name ='"+kategorier.getValue()+"'");
            catNum.next();

            Statement setCat = conn.createStatement();
            setCat.executeUpdate("INSERT INTO `workoutapp`.`category_exercise` (`category`, `exercise`) VALUES ('"+catNum.getString(1) + "','" + ovNavn.getText() +"')");


            //Sjekker om målsetning er fylt inn, hvis ja så lagrer vi mål
            if (malsetningsBeskrivelse != null){
                Statement setGoalDesc = conn.createStatement();
                setGoalDesc.executeUpdate("INSERT INTO `workoutapp`.`goal` (`exercise`,`goalDesc`,`goalDate`) VALUES ('"+ovNavn.getText() + "','" + malsetningsBeskrivelse.getText() + "','" + malsetningsFrist.getValue() +"')");
            }


            Stage stage = (Stage) lagre.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            System.out.println(e);

        }
    }

    private void updateCat(){
        try{
            Statement getOvCategory = conn.createStatement();
            ResultSet categoriesGot = getOvCategory.executeQuery("SELECT name FROM category");
            ObservableList<String> categoryList = FXCollections.observableArrayList();

            while (categoriesGot.next()){
                categoryList.add(categoriesGot.getString(1));
            }
            kategorier.setItems(categoryList);

        }catch (Exception e){

        }

    }


}
