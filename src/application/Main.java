package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static Stage currentStage;
	public static Stage getCurrentStage() {
		return currentStage;
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			currentStage=primaryStage;
			BorderPane root =FXMLLoader.load(getClass().getResource("design.fxml"));
			Scene scene = new Scene(root,800,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Image icon=new Image (getClass().getResource("logo.PNG").toURI().toString());
			primaryStage.getIcons().add(icon);

			
			primaryStage.setMinHeight(500);
			primaryStage.setMinWidth(800);
			primaryStage.setTitle("My Player");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
