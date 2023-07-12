package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class Controller implements Initializable{

    @FXML
    private Button chooseFileButton;

    @FXML
    private Button defaultSpeedButton;

    @FXML
    private Button fastButton;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button nextButton;

    @FXML
    private Button paly_pauseButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button slowButton;

    @FXML
    private Slider statusBar;

    @FXML
    private Slider volumeSlider;
    private String filePath;
    private File file;
    private Media media;
    private MediaPlayer player;

	@FXML StackPane stackPane;

	@FXML BorderPane borderPane;

	@FXML Button screenRationButton;

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	file = new File("test.mp4");
    	setUpMediaView();
    	Binding();
	}
    
    
    
    private void Binding() {
    	mediaView.setManaged(false);
    	stackPane.setAlignment(Pos.CENTER_RIGHT);
    mediaView.fitWidthProperty().bind(stackPane.widthProperty());
    mediaView.fitHeightProperty().bind(stackPane.heightProperty());
    
    mediaView.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
        mediaView.setTranslateX((stackPane.getWidth() - newBounds.getWidth()) / 2);
        mediaView.setTranslateY((stackPane.getHeight() - newBounds.getHeight()) / 2);
    });
    
    
   }



	@FXML
    void chooseFile(ActionEvent event) {
    	FileChooser chooser= new FileChooser();  
    	FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("select (*.mp4)", "*.mp4");  
    	FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("select (*.mp3)", "*.mp3");  
    	FileChooser.ExtensionFilter filter3 = new FileChooser.ExtensionFilter("select (*.avi)", "*.avi");  
    	FileChooser.ExtensionFilter filter4 = new FileChooser.ExtensionFilter("select (*.mkv)", "*.mkv");   
    	chooser.getExtensionFilters().add(filter1);    chooser.getExtensionFilters().add(filter2);   
    	chooser.getExtensionFilters().add(filter3);    chooser.getExtensionFilters().add(filter4);     
    	file=chooser.showOpenDialog(null);   
    	filePath=file.toURI().toString();        
    }

    @FXML
    void defaultSpeed(ActionEvent event) {

    }

    @FXML
    void next(ActionEvent event) {

    }

    @FXML
    void play_pause(ActionEvent event) {
    	if(MediaPlayer.Status.PLAYING==player.getStatus()) {
    		player.pause();
    		
    	}
    	else if(MediaPlayer.Status.PAUSED==player.getStatus() ||MediaPlayer.Status.READY==player.getStatus() ) {
    		
    		player.play();
    		player.volumeProperty().add(2);
    	}
    }

    @FXML
    void previous(ActionEvent event) {

    }

    @FXML
    void slowDown(ActionEvent event) {

    }

    @FXML
    void speedUp(ActionEvent event) {

    }

    void setUpMediaView() {
    	media=new Media(file.toURI().toString());
    	player = new MediaPlayer(media);
    	mediaView.setMediaPlayer(player);
    }



	@FXML public void changeRatio() {
		 
		 if(mediaView.isPreserveRatio()) {
			 mediaView.setPreserveRatio(false);//to stretch and fill the screen
		 }else {
			 mediaView.setPreserveRatio(true);
		 }
		
	}
	

}