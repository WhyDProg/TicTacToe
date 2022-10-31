package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Info {
	// Class de JavaFX
		// La classe StackPane présente ses enfants sous la forme d'une pile. Le nouveau nœud est placé au-dessus du nœud précédent dans un StackPane. La classe StackPane hérite de la classe Pane.
		private StackPane pane;
		// Ici avec la classe Label on créer une variable de ce type
		private Label message;
		// Ici avec la classe Button on créer une variable de ce type
		private Button btnNouvellePartie;
		//Ici je vien crée le constructeur 
		public Info() {
			//Ici on vien instancier un StackPane..		
			pane = new StackPane();
			//la méthode set définit la valeur. (setters)
			pane.setMinSize(GUI.WIDTH,GUI.INFO_HEIGHT);
			//setTranslateX = axe Horizontal
			pane.setTranslateX(GUI.WIDTH / 2);
			// setTranslateY = axe Verticale
			pane.setTranslateY(GUI.INFO_HEIGHT / 2);
			
			message = new Label("Tic-Tac-Toe");
			message.setTextFill(Color.PURPLE);
			message.setMinSize(GUI.WIDTH, GUI.INFO_HEIGHT);
			message.setFont(Font.font(34));
			//Position du message dans le GUI
			message.setAlignment(Pos.CENTER);
			message.setTranslateY(-40);
			// On ajoute ici l'enfant message dans StackPane de info
			pane.getChildren().add(message);
			
			btnNouvellePartie = new Button("Commencer une nouvelle partie");
			btnNouvellePartie.setMinSize(200, 40);
			btnNouvellePartie.setTranslateY(20);
			btnNouvellePartie.setTextFill(Color.PURPLE);
			// On ajoute ici l'enfant btnStartGame dans StackPane pane
			pane.getChildren().add(btnNouvellePartie);
		}
		
		//La méthode get renvoie la valeur de la variable (getters)
		public StackPane getStackPane() {
			return pane;
		}
		
		//Ici on crée une méthode pour update le message (Tour du joueur X /// Tour du joueur O)
		public void updateMessage(String message) {
			//Le mot clé this fait référence à l'objet actuel dans une méthode ou un constructeur
			this.message.setText(message);
		}
		// On vien créer une méthode pour afficher le bouton de démarrage de partie à la fin d'une partie... 
		public void showbtnNouvellePartie() {
			btnNouvellePartie.setVisible(true);
		}
		// On vien créer une méthode pour cacher le bouton de démarrage de partie lors d'une partie... 
		public void hidebtnNouvellePartie() {
			btnNouvellePartie.setVisible(false);
		}
		// Ici on vien ajouter un événement au btnStartGame... (Un événement représentant un certain type d'action. Ce type d'événement est largement utilisé pour représenter une variété de choses, 
		//comme lorsqu'un bouton a été déclenché comme ici.)
		//EvenHandler et ActionEvent doit etre importer avec JavaFX (import javafx.event.EventHandler; && import javafx.event.ActionEvent;)
		public void setNouvellePartieOnAction(EventHandler<ActionEvent> onAction) {
			btnNouvellePartie.setOnAction(onAction);
		}
}
