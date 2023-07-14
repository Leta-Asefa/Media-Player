package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    private Button play_pauseButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button slowButton;

    @FXML
    private Slider statusSlider;

    @FXML
    private Slider volumeSlider;

    private String filePath;
    
    private File file;
    
    private Media media;
    
    private MediaPlayer player;

	@FXML StackPane stackPane;

	@FXML BorderPane borderPane;

	@FXML Button screenRationButton;
	
	ArrayList<File> mediaList;
	
    private double rate=1;
    
    int currentMedia=0;
    
    double unmutedVolume;

	@FXML Button muteButton;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	file = new File("test.mp4");
    	mediaList=new ArrayList<File>();
    	setUpMediaView();
    	Binding();
	}
    
    
    
    private void Binding() {
    	mediaView.setManaged(false);
    	mediaView.fitWidthProperty().bind(stackPane.widthProperty());
    	mediaView.fitHeightProperty().bind(stackPane.heightProperty());
    	
    	mediaView.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
        mediaView.setTranslateX((stackPane.getWidth() - newBounds.getWidth()) / 2);
        mediaView.setTranslateY((stackPane.getHeight() - newBounds.getHeight()) / 2);
    });
     
    
    volumeSlider.valueProperty().addListener((observable,oldValue,newValue)->{
    		player.setVolume(volumeSlider.getValue()/100);
    				});
    
   
    
    
    player.currentTimeProperty().addListener(new ChangeListener<Duration>() {

		@Override
		public void changed(ObservableValue<? extends Duration> arg0, Duration oldValue, Duration newValue) {
			statusSlider.setValue(newValue.toSeconds());
			
		}
    	
    }

		);
    
   
    																				
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
    	
    	File directory= new File( file.getParent());
    	
    	File[] filesList=directory.listFiles();
    	
    	for(File f:filesList) {
    		mediaList.add(f);
    	}
    	
    	filePath=file.toURI().toString(); 
    	currentMedia=mediaList.indexOf(file);
    	player.pause();
    	setUpMediaView();
    	
    }

    @FXML
    void defaultSpeed(ActionEvent event) {
    		player.setRate(1);
    		player.play();
    }

    @FXML
    void next(ActionEvent event) {
    	currentMedia++;
    	try {
    		file=mediaList.get(currentMedia);
		} catch (Exception e) {
			System.out.println("no more medias to play");
		}
    	setUpMediaView();
    	
    }

    @FXML
    void play_pause(ActionEvent event) {
    	if(MediaPlayer.Status.PLAYING==player.getStatus()) {
    		player.pause();
    		play_pauseButton.setText("play");
    	}
    	else if(MediaPlayer.Status.PAUSED==player.getStatus() ||MediaPlayer.Status.READY==player.getStatus() ) {
    		
    		player.play();
    		play_pauseButton.setText("pause");
    	}
    }

    @FXML
    void previous(ActionEvent event) {
    	currentMedia--;
    	try {
    		file=mediaList.get(currentMedia);
		} catch (Exception e) {
			System.out.println("no more medias to play");
		}
    	setUpMediaView();
    }

    @FXML
    void slowDown(ActionEvent event) {
    	if(rate==0.2) rate=0.4;
    		rate=rate-0.20;
    		player.setRate(rate);
    	
    }

    @FXML
    void speedUp(ActionEvent event) {
    	
    		rate=rate+0.20;
    		player.setRate(rate);
    	
    }

    void setUpMediaView() {
    	media=new Media(file.toURI().toString());
    	Main.getCurrentStage().setTitle(file.getName());
    	player = new MediaPlayer(media);
    	statusSlider.setMax(player.getTotalDuration().toSeconds());
    	statusSlider.setValue(0);
    	mediaView.setMediaPlayer(player);
    	player.play();
    }

    

		@FXML public void changeRatio() {
		 
		 if(mediaView.isPreserveRatio()) {
			 mediaView.setPreserveRatio(false);//to stretch and fill the screen
		 }else {
			 mediaView.setPreserveRatio(true);
		 }
		
	}

		@FXML public void seekDuration(MouseEvent event) {
		player.seek(Duration.seconds(statusSlider.getValue()));
		
	}

		@FXML public void mute() {
		if(player.getVolume()!=0) {
		unmutedVolume=player.getVolume();
		player.setVolume(0);
		muteButton.setText("unmute");
		}else {
			player.setVolume(unmutedVolume);
			muteButton.setText("mute");
		}
		
	}
	
	 	@FXML public void OnDragOverMedia(DragEvent event) {
			if(event.getDragboard().hasFiles()) {
				event.acceptTransferModes(TransferMode.ANY);
			}
		}
	    
	    @FXML  public void OnDragDropped(DragEvent event) {

	    		List<File> files= event.getDragboard().getFiles();
				file=files.get(0);
				setUpMediaView();
	    		
	    		
	    
	    }

}
