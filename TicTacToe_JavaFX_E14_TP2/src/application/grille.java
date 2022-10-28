package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class grille {
	
	//création du champ privé infoCenter et StackPane
	private infoCenter InfoCenter;
	private StackPane pane;
	private Case[][] cases = new Case[3][3];
	
	private char playerTurn = 'X';
	private boolean isEndOfGame = false;

	//Constructeur
	//Nous voulous une référence à notre class infoCenter,car cette grille va contenir la logique(ex:gérer le changement de joueurs etc..) alors on va avoir de besoin d'une facon de
	// relier l'information a la class infocenter a partir de cette class grille, alor quand on instancie notre new grille class on va lui passer notre infoCenter en meme temp
	public grille(infoCenter InfoCenter) {
		this.InfoCenter = InfoCenter;
		pane = new StackPane();
		pane.setMinSize(GUI.WIDTH, GUI.TILE_BOARD_HEIGHT);
		pane.setTranslateX(GUI.WIDTH / 2);
		pane.setTranslateY((GUI.TILE_BOARD_HEIGHT / 2) + GUI.INFO_CENTER_HEIGHT);
		
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
	public void changePlayerTurn() {
		if(playerTurn == 'X') {
			playerTurn = 'O';
		}else {
			playerTurn = 'X';
		}
		InfoCenter.updateMessage("Tour du joueur" + playerTurn);
	}
	
	public String getPlayerTurn() {
		return String.valueOf(playerTurn);
	}

	//On vien créer un getters pour le StackPane
	public StackPane getStackPane() {
		return pane;
	}
	
	public void checkForWinner() {
		checkRowsForWinner();
		checkColsForWinner();
		checkTopLeftToBottomRightForWinner();
		checkTopRightToBottomLeftForWinner();
		checkForTie();
		
	}
	
	private void checkRowsForWinner() {
		for (int row = 0; row < 3; row++) {
			if(cases[row][0].getValue().equals(cases[row][1].getValue()) && cases[row][0].getValue().equals(cases[row][2].getValue()) && !cases[row][0].getValue().isEmpty()){
				String winner = cases[row][0].getValue();
				endGame(winner);
				return;
			}					
		}	
}
	
	private void checkColsForWinner() {
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

	private void checkTopLeftToBottomRightForWinner() {
		if (!isEndOfGame) {
			if(cases[0][0].getValue().equals(cases[1][1].getValue()) && cases[0][0].getValue().equals(cases[2][2].getValue()) && !cases[0][0].getValue().isEmpty()){
				String winner = cases[0][0].getValue();
				endGame(winner);
				return;
			}				
		}
	}
	private void checkTopRightToBottomLeftForWinner() {
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
			InfoCenter.updateMessage("Partie Égale!");
			InfoCenter.showbtnStartGame();
		}
		
	}

	private void endGame(String winner) {
		isEndOfGame = true;
		InfoCenter.updateMessage("Joueur " + winner + " à gagner!");
		InfoCenter.showbtnStartGame();
	}

	//La grille sera concue de différente case alors on va créer une class privée qui va contenir l'instance d'une case..
	private class Case {
		
		private StackPane pane;
		private Label label;
		
		//constructeur
		public Case() {
			pane = new StackPane();
			pane. setMinSize(100,100);
			
			//Ici on vien utiliser la class Rectangle de JavaFx (import javafx.scene.shape.Rectangle;)
			Rectangle border = new Rectangle();
			border.setHeight(100);
			border.setWidth(100);
			border.setFill(Color.TRANSPARENT);
			border.setStroke(Color.BLACK);
			pane.getChildren().add(border);
			
			//On utilise la class Label de JavaFX (import javafx.scene.control.Label;)
		    label = new Label("");
			label.setAlignment(Pos.CENTER);
			label.setFont(Font.font(24));
			pane.getChildren().add(label);
			
			pane.setOnMouseClicked(event -> {
				if (label.getText().isEmpty() && !isEndOfGame) {
					label.setText(getPlayerTurn());
					changePlayerTurn();
					checkForWinner();
				}
			});
		}
		
		public StackPane getStackPane() {
			return pane;
		}
		public String getValue() {
			return label.getText();
		}
		public void setValue(String value) {
			label.setText(value);
		}
	}
	
}


