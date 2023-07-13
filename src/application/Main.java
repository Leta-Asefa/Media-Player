package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
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
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setMinHeight(400);
			primaryStage.setMinWidth(600);
			primaryStage.setTitle("L Player");
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
