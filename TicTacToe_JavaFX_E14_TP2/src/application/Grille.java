package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;



public class Grille {
	//création de champ privé 
		//Quelques imports ici
		private Info info;
		private StackPane pane;
		//array 2D 3x3
		private Case[][] cases = new Case[3][3];

		private char tourDuJoueur = 'X';
		//boolean
		private boolean isEndOfGame = false;
		//Constructeur Grille	
		//Je veu faire une référence à notre class info,car cette grille va contenir la logique(ex: regarder si il y a un gagnant,gérer le changement de joueurs etc..) 
		//alors on va avoir de besoin d'une facon de relier l'information a la class info a partir de cette class grille, alor quand on instancie notre new grille class 
		//on va lui passer notre info en meme temp
		public Grille(Info info) {
			//Le mot clé this fait référence à l'objet actuel dans une méthode ou un constructeur
			this.info = info;
			pane = new StackPane();
			pane.setMinSize(GUI.WIDTH, GUI.GRILLE_HEIGHT);
			//setTranslateX = axe Horizontale
			pane.setTranslateX(GUI.WIDTH / 2);
			//setTranslateY = axe Verticale
			pane.setTranslateY((GUI.GRILLE_HEIGHT / 2) + GUI.INFO_HEIGHT);
			
			addCases();
		}
		
		private void addCases() {
			for (int row = 0; row <3; row++) {
				for (int col = 0; col <3; col++) {
					Case Case = new Case();
					Case.getStackPane().setTranslateX((col * 100) - 100);
					Case.getStackPane().setTranslateY((row * 100) - 100);
					pane.getChildren().add(Case.getStackPane());
					cases[row][col] = Case;
				}
			}
		}
			
		//La grille sera concue de différente case alors on va créer une class privée qui va contenir l'instance d'une case..
		private class Case {
				
				//Cette case va contenir un autre StackPane(enfant) et un Label qu'on import encore avec JavaFX
				private StackPane pane;
				private Label label;
					
				//constructeur Case
				public Case() {
				pane = new StackPane();
				pane. setMinSize(100,100);
					
				//Ici on vien utiliser la class Rectangle de JavaFx (import javafx.scene.shape.Rectangle;)
				Rectangle border = new Rectangle();
				border.setHeight(100);
				border.setWidth(100);
				border.setFill(Color.ORANGE);
				border.setStroke(Color.PURPLE);
				border.setStrokeWidth(5);
				//on vien ajouter notre rectangle a la case avec StackPane
				pane.getChildren().add(border);
						
				//Il faut un X ou un O au milieu de chaque case lorsqu'on on click 
				//On utilise la class Label de JavaFX (import javafx.scene.control.Label;)
				label = new Label("");
				label.setAlignment(Pos.CENTER);
				label.setTextFill(Color.PURPLE);
				label.setFont(Font.font(64));
				pane.getChildren().add(label);
						
				//lors du click sur la case tout dependant le tour du joueur ces si un X ou un O
				pane.setOnMouseClicked(event -> {
					if (label.getText().isEmpty() && !isEndOfGame) {
						//change le caractere dans la case
						label.setText(getPlayerTurn());
						//texte au haut
						changerTourJoueur();
						checkForWinner();
						}
					});
				}
					
			// getStackPane de la class Case	
			//La méthode get renvoie la valeur de la variable (getters)
			public StackPane getStackPane() {
					return pane;
				}
				public String getValue() {
					return label.getText();
				}
			// setValue de la class Case
			//la méthode set définit la valeur. (setters)
			public void setValue(String value) {
				label.setText(value);
			}
		  }
		
		public void changerTourJoueur() {
			if(tourDuJoueur == 'X') {
				tourDuJoueur = 'O';		
			}else {
				tourDuJoueur = 'X';
			}
			info.updateMessage("Tour du joueur " + tourDuJoueur);
		}
		//La méthode get renvoie la valeur de la variable (getters)
			public String getPlayerTurn() {
				return String.valueOf(tourDuJoueur);
			}
		

		// getStackPane de la class grille	
		//On vien créer un getters pour le StackPane
		public StackPane getStackPane() {
			return pane;
		}
		
		public void checkForWinner() {
			checkRows();
			checkCols();
			checkTopLeftToBottomRight();
			checkTopRightToBottomLeft();
			checkForTie();	
		}
		
		// ici je vien reset la grille case par case si il apuit sur le boutton (on l'appel a partir du main)
		public void nouvellePartie() {
			isEndOfGame = false;
			tourDuJoueur = 'X';
			for (int row = 0; row <3; row++) {
				for (int col = 0; col <3; col++) {
					cases[row][col].setValue("");
					}
			}
		}
		private void checkRows() {	
				for (int row = 0; row < 3; row++) {
					if(cases[row][0].getValue().equals(cases[row][1].getValue()) && cases[row][0].getValue().equals(cases[row][2].getValue()) && !cases[row][0].getValue().isEmpty()){
						//ici je prend la valeur du caractere dans la case pour passer le message gagnant avec le bon joueur
						String winner = cases[row][0].getValue();
						endGame(winner);
						return;
					}					
				}	
		}
		private void checkCols() {
			if (!isEndOfGame) {
				for (int col = 0; col < 3; col++) {
					if(cases[0][col].getValue().equals(cases[1][col].getValue()) && cases[0][col].getValue().equals(cases[2][col].getValue()) && !cases[0][col].getValue().isEmpty()){
						String winner = cases[0][col].getValue();
						endGame(winner);
						return;
					}					
				}	
			}
		}
		private void checkTopLeftToBottomRight() {
			if (!isEndOfGame) {
				if(cases[0][0].getValue().equals(cases[1][1].getValue()) && cases[0][0].getValue().equals(cases[2][2].getValue()) && !cases[0][0].getValue().isEmpty()){
					String winner = cases[0][0].getValue();
					endGame(winner);
					return;
				}				
			}
		}
		private void checkTopRightToBottomLeft() {
			if (!isEndOfGame) {
				if(cases[0][2].getValue().equals(cases[1][1].getValue()) && cases[0][2].getValue().equals(cases[2][0].getValue()) && !cases[0][2].getValue().isEmpty()){
					String winner = cases[0][2].getValue();
					endGame(winner);
					return;
				}				
			}
		}
		private void checkForTie() {
			if (!isEndOfGame) {
				for (int row = 0; row < 3 ; row++) {
					for(int col = 0; col <3; col++) {
						if (cases[row][col].getValue().isEmpty()) {
							return;
						}
					}
				}
				isEndOfGame = true;
				info.updateMessage("Partie Égale!");
				info.showbtnNouvellePartie();
			}		
		}	
		private void endGame(String winner) {
			isEndOfGame = true;
			info.updateMessage("Joueur " + winner + " à gagner!");
			info.showbtnNouvellePartie();
		}		
	
}
