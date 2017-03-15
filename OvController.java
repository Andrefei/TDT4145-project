/**
 * Created by matsj on 10.03.2017.
 */
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OvController {

    private Connection conn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button trenGjennomfort;

    @FXML
    private Button ovGjennomfort;

    @FXML
    private Button nyOv;

    @FXML
    private ComboBox<String> velgOv;

    @FXML
    private Spinner<Integer> set;

    @FXML
    private Spinner<Integer> repetisjoner;

    @FXML
    private Button visBeskrivelse;

    @FXML
    private Spinner<Double> vekt;

    @FXML
    private Spinner<Double> avstandKm;

    @FXML
    private Spinner<Double> varighetTimer;

    @FXML
    private ComboBox<String> vaerforhold;

    @FXML
    private TextField temperatur;

    @FXML
    void initialize() {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties p = new Properties();
            p.put("user", "root");
            p.put("password", "groot");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/workoutapp?useSSL=false",p);

        }catch (Exception e){
        }


        //Velg Øvelse setup
        try{
            Statement getOvs = conn.createStatement();
            ResultSet ovsGot = getOvs.executeQuery("SELECT name FROM exercise");
            ObservableList<String> ovsList = FXCollections.observableArrayList();

            while (ovsGot.next()){
                ovsList.add(ovsGot.getString(1));
            }
            velgOv.setItems(ovsList);

        }catch (Exception e){

        }


        //Beskriv Øvelse setup TODO
        visBeskrivelse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (velgOv.getValue() != null){
                    try{
                        AnchorPane beskPane = FXMLLoader.load(getClass().getResource("beskrivelsePopup.fxml"));
                        Scene scene = new Scene(beskPane);
                        Stage ovBeskrivelse = new Stage();
                        ovBeskrivelse.setScene(scene);
                        ovBeskrivelse.setTitle("Øvelse " + velgOv.getValue());
                        ovBeskrivelse.show();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });

        //Ny Øvelse setup
        nyOv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    AnchorPane nyOv = FXMLLoader.load(getClass().getResource("ny_ovelse.fxml"));
                    Scene scene = new Scene(nyOv);
                    Stage nyOvStage = new Stage();
                    nyOvStage.setScene(scene);
                    nyOvStage.setTitle("Ny Øvelse");
                    nyOvStage.show();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        //Set setup
        set.setEditable(false);

        //Repetisjoner setup
        repetisjoner.setEditable(false);
        //Vekt setup
        vekt.setEditable(true);

        //Km. Avstand setup
        avstandKm.setEditable(true);

        //Timer varighet setup
        varighetTimer.setEditable(true);

        //Værforhold setup
        ObservableList<String > vaerListe = FXCollections.observableArrayList("Sol","Regn","Snø","Kuling");
        vaerforhold.setItems(vaerListe);

        //Temperatursetup

        //Øvelse gjennomført setup
        ovGjennomfort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        //Treningsøkt gjennomført setup
        trenGjennomfort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

    }

    public static void main(String[] args) {
        //FXMLLoader fxmlLoad = new FXMLLoader(.getClass().getResource(ovelse.fxml));
        OvController ovc = new OvController();
        ovc.initialize();
    }
}
