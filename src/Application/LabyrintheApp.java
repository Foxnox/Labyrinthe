package Application;

import java.io.File;
import java.io.IOException;
import processing.core.*;
import Items.Item;
import Labyrinthe.Labyrinthe;
import Labyrinthe.Salle;
import Personnage.Personnage;

@SuppressWarnings("serial")
public class LabyrintheApp extends PApplet {
	/**
	 * Image de fond des menu et du jeu
	 */
	private PImage imgBackGround;
	
	/**
	 * Image des coeurs symbolisants la vie.
	 */
	private PImage imgHeart;
	
	/**
	 * Nom du fichier contenant les "éléments" du labyrinthe.
	 */
	private String filename;
	
	/**
	 * Labyrinthe
	 */
	private Labyrinthe laby;
	
	/**
	 * Le personnage (joueur)
	 */
	private Personnage player = new Personnage(this);
	
	/**
	 * Taille de la fenetre
	 * largeur : W_width.
	 * hauteur : W_height.
	 */
	private int W_width = 600;
	private int W_height = 600;
	
	/**
	 * Niveau actuel.
	 */
	private int stage = 0;
	
	/**
	 * Gère l'état du jeu et de la victoire.
	 */
	private static boolean victoire = false;
	
	
	/**
	 * Gère l'état du jeu et du game over.
	 */
	private static boolean gameOver = false;
	
	/**
	 * Gère l'état du jeu. Vaut vrai si l'on est dans le menu.
	 */
	private boolean menu = true;
	
	/**
	 * Gère l'état du jeu. Vaut vrai si l'on est dans le menu de selection des labyrinthes.
	 */
	private boolean selectLevel = false;
	
	/**
	 * Tableau contenant les differents option du menu.
	 */
	private String[] tabMenu = { "New Game", "Select Level", "Quit" };
	
	/**
	 * Position du curseur dans le menu.
	 */
	private int cursorMenu = 0;

	/**
	 * Répertoire contenant tout les fichiers de labyrinthe.
	 */
	File monRepertoire = new File("../labs/");
	
	/**
	 * Tableau contenant tout les noms des fichiers de labyrinthes.
	 */
	File[] f = monRepertoire.listFiles();

	/**
	 * Getter de laby
	 */
	public Labyrinthe getLaby() {
		return laby;
	}

	/**
	 * Getter de player
	 */
	public Personnage getPlayer() {
		return player;
	}
	
	/**
	 * Accesseurs en lecture puis ecriture de gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean go) {
		gameOver = go;
	}

	/**
	 * Gère la creation du LabyrintheApp courant.
	 * @see processing.core.PApplet#setup()
	 */
	public void setup() {
		if (menu || selectLevel) {
			cursorMenu = 0;

		} else {
			if (stage < f.length && !gameOver) {

				Item.reset(player);

				laby = new Labyrinthe();
				filename = f[stage].getPath();
				try {
					readFiles(laby);
					player.setSalleCourante(laby.getEntry());
				} catch (IOException e) {

					System.out.println(e);
					System.out.println("erreur chargement fichier");
					// e.printStackTrace();
				}
				laby.addSalles(laby.getExit());
				laby.addSalles(laby.getEntry());
			} else {
				W_width = 600;
				W_height = 600;
			}
		}
		size(W_width, W_height);
		frameRate(60);
		imgBackGround = loadImage("../img/background1.jpg");
		imgBackGround.resize(W_width, W_height);
		imgHeart = loadImage("../img/Coeur.png");
		imgHeart.resize(20, 20);
	}

	/**
	 * Gère la saisie des touches durant les menus (mouvement dans les menus, choix et retour) et le jeu (mouvement du personnage, retour au menu)
	 * @see processing.core.PApplet#keyPressed()
	 */
	public void keyPressed() {
		size(W_width, W_height);
		// System.out.println(keyCode);
		if (menu && !selectLevel) {

			// NEW GAME
			if (cursorMenu == 0) {
				if (key == ENTER || keyCode == RIGHT || key == 'd') {
					player = new Personnage(this);
					menu = false;
					setup();
				}
			}

			// SELECT LEVEL
			if (cursorMenu == 1) {
				if (key == ENTER || keyCode == RIGHT || key == 'd') {
					selectLevel = true;
					setup();
				}
			}

			// QUIT
			if (cursorMenu == tabMenu.length - 1) {
				if (key == ENTER || keyCode == RIGHT || key == 'd') {
					exit();
				}
			}

			if (keyCode == UP || key == 'z') {
				if (cursorMenu == 0)
					cursorMenu = tabMenu.length - 1;
				else {
					cursorMenu--;
				}
			}

			if (keyCode == DOWN || key == 's') {
				cursorMenu++;
				cursorMenu = cursorMenu % tabMenu.length;
			}

		} else if (menu && selectLevel) {

			if (keyCode == UP || key == 'z') {
				if (cursorMenu == 0)
					cursorMenu = f.length - 1;
				else {
					cursorMenu--;
				}
			}

			if (keyCode == DOWN || key == 's') {
				cursorMenu++;
				cursorMenu = cursorMenu % f.length;
			}

			if (key == ENTER || keyCode == RIGHT || key == 'd') {
				player = new Personnage(this);
				stage = cursorMenu;
				menu = false;
				selectLevel = false;
				setup();
			}

			if (keyCode == 8 /* RETURN */|| keyCode == LEFT || key == 'q') {
				menu = true;
				selectLevel = false;
				setup();
			}

		} else {
			/*
			 * 0 1 2 3 sallesAdjacentes = || droite | haut | gauche | bas || si
			 * pas de salle = booleen adjacente = false
			 */

			if ((key == 'z' || key == 'q' || key == 's' || key == 'd')
					&& millis() > player.getStunTime() + Personnage.STUN_TIME
					&& stage < f.length && !gameOver) {
				player.resetSallesAdjacentes();
				player.majSallesAdjacentes(laby.getSalles());
				if (key == 'z') {
					player.move(1);
				}
				if (key == 'q') {
					player.move(2);
				}
				if (key == 's') {
					player.move(3);
				}
				if (key == 'd') {
					player.move(0);
				}

				player.dessiner(this);
				if (player.getSalleCourante().isTraped()) {
					player.takeTrap();
					player.getSalleCourante().setTraped(false);
					player.majSallesAdjacentes(laby.getSalles());
					laby.majLabyrinthe(player);
					player.setStunTime(millis());
				}
				if (player.getSalleCourante().isContainItem()) {
					player.getSalleCourante().setContainItem(false);
					player.takeItem();
					laby.majLabyrinthe(player);
				}
			}

			if (key == ENTER) {
				menu = true;
				victoire = false;
				gameOver = false;
				setup();
			}

			/* KEY DEV */
			 if (keyCode == 107 && player.getDistanceBrightness() < 1000)
				 player.setDistanceBrightness(player.getDistanceBrightness() * 2);
			 if (keyCode == 109 && player.getDistanceBrightness() > 80)
				 player.setDistanceBrightness(player.getDistanceBrightness() / 2);
		}

	}

	/**
	 * Gére la relache des touches. Utiliser pour le passage sur la sortie (en jeu).
	 * @see processing.core.PApplet#keyReleased()
	 */
	public void keyReleased() {
		if (menu || selectLevel) {
		} else {
			if (stage < f.length && !gameOver) {
				if (player.getSalleCourante().isExit()) {
					passNextLaby();
				}
			}
		}
	}

	/**
	 * Gèrer l'affichage lors des menus et du jeu.
	 * Gère le halo de lumiere sur l'image de fond pendant le jeu.
	 * @see processing.core.PApplet#draw()
	 */
	public void draw() {
		if (menu && !selectLevel) {
			image(imgBackGround, 0, 0);
			textSize(32);
			fill(0, 102, 153);
			text("Labyrinthe", W_width / 2, 40);
			textSize(24);
			for (int i = 0; i < tabMenu.length; ++i)
				text(tabMenu[i], W_width / 2, W_height / 2 - 100 + 50 * i);
			star(W_width / 2 - 100, W_height / 2 - 100 + 50 * cursorMenu, 5,
					15, 5);
		} else if (menu && selectLevel) {
			image(imgBackGround, 0, 0);
			textSize(32);
			fill(0, 102, 153);
			text("Select Level", W_width / 2, 40);
			textSize(18);
			for (int i = 0; i < f.length; ++i)
				text(f[i].getName(), W_width / 2, 100 + 30 * i);
			star(W_width / 2 - 100, 100 + 30 * cursorMenu, 5, 12, 5);
		} else {
			if (stage < f.length && !gameOver) {
				loadPixels();
				int posX = this.player.getSalleCourante().getX();
				int posY = this.player.getSalleCourante().getY();
				// We must also call loadPixels() on the PImage since we are
				// going to
				// read its pixels. img.loadPixels();
				for (int x = 0; x < imgBackGround.width; x++) {
					for (int y = 0; y < imgBackGround.height; y++) {

						// Calculate the 1D pixel location
						int loc = x + y * imgBackGround.width;

						// Get the R,G,B values from image
						float r = red(imgBackGround.pixels[loc]);
						float g = green(imgBackGround.pixels[loc]);
						float b = blue(imgBackGround.pixels[loc]);

						// Calculate an amount to change brightness
						// based on proximity to the mouse
						float distance = dist(x, y, posX, posY);

						// The closer the pixel is to the mouse, the lower the
						// value of "distance"
						// We want closer pixels to be brighter, however, so we
						// invert the value with the formula: adjustBrightness =
						// (80-distance)/80
						// Pixels with a distance of 80 (or greater) have a
						// brightness of 0.0 (or negative which is equivalent to
						// 0 here)
						// Pixels with a distance of 0 have a brightness of 1.0.
						float adjustBrightness = (player
								.getDistanceBrightness() - distance)
								/ player.getDistanceBrightness();
						r *= adjustBrightness;
						g *= adjustBrightness;
						b *= adjustBrightness;

						// Constrain RGB to between 0-255
						r = constrain(r, 0, 255);
						g = constrain(g, 0, 255);
						b = constrain(b, 0, 255);

						// Make a new color and set pixel in the window
						// color c = color(r,g,b);
						pixels[loc] = color(r, g, b);
					}
				}

				updatePixels();

				laby.dessiner(this);
				player.dessiner(this);

				textSize(18);
				fill(0, 102, 153, 150);
				text(f[stage].getName(), W_width / 2, 10);

				for (int i = 0; i < player.getLife(); ++i)
					image(imgHeart, 10 + 10 * i, 10);
				int k = 130;
				for (Item i : player.getListItems()) {
					fill(255, 255, 255, 150);
					ellipse((float) (k + 7.5), (float) (10 + 7.5), 20, 20);
					image(i.getImg(), k, 10);
					k += 20;
				}
				k = 120;
			}

			if (victoire) {
				image(imgBackGround, 0, 0);
				textSize(32);
				fill(0, 102, 153);
				text("Victoire", W_width / 2, W_height / 2);
			}

			if (gameOver) {
				image(imgBackGround, 0, 0);
				textSize(32);
				fill(0, 102, 153);
				text("Game Over", W_width / 2, W_height / 2);
			}
		}
		textAlign(CENTER, CENTER);
	}

	/**
	 * Lit le fichier filename et remplie le Labyrinthe lab avec les élément du fichier.
	 * @param lab Labyrinthe a remplir.
	 * @throws IOException Exception a retourner si la lecture du fichier est impossible.
	 */
	public void readFiles(Labyrinthe lab) throws IOException {
		String[] lines;
		lines = loadStrings(filename);
		for (int i = 0; i < lines.length; ++i) {
			String[] temp = lines[i].split(" ");
			int premier, second;
			if (temp.length == 2) {
				if (Integer.parseInt(temp[0]) > 50) {
					temp[0] = "50";
				}
				if (Integer.parseInt(temp[1]) > 50) {
					temp[1] = "50";
				}
				premier = Integer.parseInt(temp[0]) * Salle.getSize() + 10;
				second = Integer.parseInt(temp[1]) * Salle.getSize() + 30;
			} else {
				premier = -20;
				second = -20;
			}
			Salle salle_temp = new Salle(premier, second, false, false, this);
			if (i == 0) {
				lab.setHeight(premier);
				lab.setWidth(second);
				W_width = premier + 2 * Salle.getSize();
				W_height = second + 2 * Salle.getSize();
			} else if (i == 1) {
				salle_temp.setEntry(true);
				lab.setEntry(salle_temp);
			} else if (i == 2) {
				salle_temp.setExit(true);
				lab.setExit(salle_temp);
			} else {
				lab.addSalles(salle_temp);
			}
		}
	}

	/**
	 * Permet de passer a un labyrinthe suivant. Gère la victoire si tout les labyrinthes ont été terminé.
	 */
	private void passNextLaby() {
		this.clear();
		stage++;
		if (stage >= f.length) {
			victoire = true;
		}

		setup();
	}

	/**
	 * Permet de dessiner une étoile (dans le menu).
	 * @param x Position en abscisse
	 * @param y Position en ordonné
	 * @param radius1 Diametre extérieur.
	 * @param radius2 Diametre intérieur.
	 * @param npoints Nombre de sommet.
	 */
	public void star(float x, float y, float radius1, float radius2, int npoints) {
		float angle = TWO_PI / npoints;
		float halfAngle = (float) (angle / 2.0);
		beginShape();
		for (float a = 0; a < TWO_PI; a += angle) {
			float sx = x + cos(a) * radius2;
			float sy = y + sin(a) * radius2;
			vertex(sx, sy);
			sx = x + cos(a + halfAngle) * radius1;
			sy = y + sin(a + halfAngle) * radius1;
			vertex(sx, sy);
		}
		endShape(CLOSE);
	}

	public static void main(String[] args) {
		PApplet.main("LabyrintheApp");
	}
}
