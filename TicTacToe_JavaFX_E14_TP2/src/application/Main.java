package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

//git push -u origin main (command pour COMMIT)
public class Main extends Application {
	//ici on vien créer une variable InfoCenter issue de la class infoCenter et Grille issue de la class grille
	private Info info;
	private Grille grille;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			//On vien ajouter nos constante pour le GUI dans le code par defaut de JavaFX dans la class Main..
			Scene scene = new Scene(root,GUI.WIDTH,GUI.HEIGHT);
			//Ici j'appel ma méthode initialiserLayout()
			initialiserLayout(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			//Ici je vien désactiver le boutton pour agrandir la fenetre du GUI
			primaryStage.resizableProperty().setValue(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Ici je vien créer une méthode ou j'appel d'autres méthode ou je vais instancier un objet issue des class que j'ai créer (infoCenter et Grille)
	private void initialiserLayout(BorderPane root) {
		initialiserInfo(root);
		initialiserGrille(root);	
	}
	private void initialiserGrille(BorderPane root) {
		grille = new Grille(info);
		root.getChildren().add(grille.getStackPane());		
	}
	private void initialiserInfo(BorderPane root) {
		// On vien instancier notre Info dans la méthode initialiserInfo 
		info = new Info();
		//Maintenant lorsqu'on utilise le bouton cela apelle la method startNewGame au lieu de la main class
		info.setNouvellePartieOnAction(nouvellePartie());	
		//On peu créer une scène en instanciant la classe de scène. On peu opter pour la taille de la scène en transmettant ses dimensions (hauteur et largeur), qui est notre info, avec le noeud racine à son constructeur root
		// ces ca que je fait ici avec la méthode dans info(getStackPane) qui renvoit la valeur de la variable
		root.getChildren().add(info.getStackPane());	
	}
	//Un événement représentant un certain type d'action. Ce type d'événement est largement utilisé pour représenter une variété de choses, 
	//comme lorsqu'un bouton a été déclenché comme ici.)
	//EvenHandler et ActionEvent doit etre importer avec JavaFX (import javafx.event.EventHandler; && import javafx.event.ActionEvent;)
	private EventHandler<ActionEvent> nouvellePartie() {
		return new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				info.hidebtnNouvellePartie();
				info.updateMessage("Tour au joueur X");
				grille.nouvellePartie();
			}
		};
	}
	public static void main(String[] args) {
		launch(args);	
	}
}
