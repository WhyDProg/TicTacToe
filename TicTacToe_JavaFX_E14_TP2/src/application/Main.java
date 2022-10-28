package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

//git push -u origin main (command pour COMMIT)
public class Main extends Application {
	//ici on vien créer une variable InfoCenter issue de la class infoCenter et Grille issue de la class grille
	private infoCenter InfoCenter;
	private grille Grille;
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Ici je vien créer une méthode ou j'appel d'autres méthode ou je vais instancier un objet issue des class que j'ai créer (infoCenter et TileBoard)
	private void initialiserLayout(BorderPane root) {
		initialiserInfoCenter(root);
		initialiserGrille(root);
		
	}
	private void initialiserGrille(BorderPane root) {
		Grille = new grille(InfoCenter);
		root.getChildren().add(Grille.getStackPane());
		
	}
	private void initialiserInfoCenter(BorderPane root) {
		// On vien instancier notre InfoCenter dans la méthode initialiserInfoCenter 
		InfoCenter = new infoCenter();
		//Maintenant lorsqu'on utilise le bouton cela apelle la method startNewGame au lieu de la main class
		InfoCenter.setBtnStartGameOnAction(startNewGame());	
		
		//En général, une application JavaFX aura trois composants principaux, à savoir Stage, Scene et Nodes,et la premiere Nodes est root
		//On peu créer une scène en instanciant la classe de scène. On peu opter pour la taille de la scène en transmettant ses dimensions (hauteur et largeur), qui est notre infoCenter, avec le nœud racine à son constructeur root
		// ces ca que je fait ici avec la méthode dans infoCenter (getStackPane) qui renvoit la valeur de la variable
		root.getChildren().add(InfoCenter.getStackPane());
		
	}
	//Un événement représentant un certain type d'action. Ce type d'événement est largement utilisé pour représenter une variété de choses, 
	//comme lorsqu'un bouton a été déclenché comme ici.)
	//EvenHandler et ActionEvent doit etre importer avec JavaFX (import javafx.event.EventHandler; && import javafx.event.ActionEvent;)
	private EventHandler<ActionEvent> startNewGame() {
		return new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				InfoCenter.hidebtnStartGame();
				InfoCenter.updateMessage("Tour au joueur X");
				// on vien afficher dans la console la string suivante ...
				System.out.println("Partie commencer!");
			}
		};
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
