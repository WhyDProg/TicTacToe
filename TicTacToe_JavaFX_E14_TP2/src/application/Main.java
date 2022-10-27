package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private infoCenter InfoCenter;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,GUI.APP_WIDTH,GUI.APP_HEIGHT);
			initLayout(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void initLayout(BorderPane root) {
		initInfoCenter(root);
		initTileBoard(root);
		
	}
	private void initTileBoard(BorderPane root) {
		// TODO Auto-generated method stub
		
	}
	private void initInfoCenter(BorderPane root) {
		InfoCenter = new infoCenter();
		
		root.getChildren().add(InfoCenter.getStackPane());
		
	}
	//git remote add origin https://github.com/WhyDProg/TicTacToe.git (command pour COMMIT)
	public static void main(String[] args) {
		launch(args);
	}
}
