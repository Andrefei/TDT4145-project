package GuiBuild.src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Main extends Application {

    Connection conn;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Sett opp connection, sett inn egne properties her om du vil prøve appen
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties p = new Properties();
            p.put("user", "root");
            p.put("password", "root");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/workoutapp?useSSL=false",p);

        }catch (Exception e){
        }


        //Sett opp øvelsespanelet
        FXMLLoader ovelseLoader = new FXMLLoader(getClass().getResource("ovelse.fxml"));
        ovelseLoader.setController(new OvController(conn));
        Parent ovUI = ovelseLoader.load();

        //Stter opp og åpner vindu
        Scene scene = new Scene(ovUI);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Øvelse");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
