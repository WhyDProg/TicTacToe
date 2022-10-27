package application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class infoCenter {
	
	private StackPane pane;
	private Label message;
	private Button btnStartGame;
	
	public infoCenter() {
		pane = new StackPane();
		pane.setMinSize(GUI.APP_WIDTH,GUI.INFO_CENTER_HEIGHT);
		pane.setTranslateX(GUI.APP_WIDTH / 2);
		pane.setTranslateY(GUI.INFO_CENTER_HEIGHT / 2);
		
		message = new Label("Tic-Tac-Toe");
		message.setMinSize(GUI.APP_WIDTH, GUI.INFO_CENTER_HEIGHT);
		message.setFont(Font.font(24));
		message.setAlignment(Pos.CENTER);
		message.setTranslateY(-20);
		pane.getChildren().add(message);
		
		btnStartGame = new Button("Partir une nouvelle partit");
		btnStartGame.setMinSize(160, 50);
		btnStartGame.setTranslateY(20);
		pane.getChildren().add(btnStartGame);
	}
	public StackPane getStackPane() {
		return pane;
	}
	public void updateMessage(String message) {
		this.message.setText(message);
	}
	public void showbtnStartGame() {
		btnStartGame.setVisible(true);
	}
	public void hidebtnStartGame() {
		btnStartGame.setVisible(false);
	}
	public void setBtnStartGameOnAction(EventHandler<ActionEvent> onAction) {
		btnStartGame.setOnAction(onAction);
	}
}
