package Items;

import processing.core.*;
import Application.LabyrintheApp;
import Labyrinthe.Salle;
import Labyrinthe.Salle.ItemIs;
import Personnage.Personnage;

public class Item {
	
	/**
	 * PImage de l'objet (affichage)
	 */
	private PImage img;
	
	/**
	 * Définis la nature de l'objet (lantern, armor, oracle, compass ...)
	 */
	ItemIs ii;

	/**
	 * Entiers permettant de gérer le nombre d'Item qui ont été créé.
	 * nbLantern pour les lanternes.
	 * nbarmor pour les amures.
	 * nboracle pour l'oeil.
	 * nbCompass pour les boussoles
	 */
	private static int nblantern = 0;
	private static int nbarmor = 0;
	private static int nboracle = 0;
	private static int nbcompass = 0;

	/**
	 * Construteur de l'objet Item qui charge la bonne image selon la nature de l'Item que l'on veut créer
	 * @param wi ItemIs qui permet de défenir la nature de l'Item
	 * @param la LabyrintheApp qui permet ici de charger les images
	 */
	public Item(ItemIs wi, LabyrintheApp la) {
		ii = wi;
		switch (ii) {
		case heart:
			img = la.loadImage("../img/Coeur.png");
			img.resize(Salle.getSize(), Salle.getSize());
			break;
		case lantern:
			img = la.loadImage("../img/Lantern.png");
			img.resize(Salle.getSize(), Salle.getSize());
			break;
		case armor:
			img = la.loadImage("../img/Armor.gif");
			img.resize(Salle.getSize(), Salle.getSize());
			break;
		case oracle:
			img = la.loadImage("../img/Eye.gif");
			img.resize(Salle.getSize(), Salle.getSize());
			break;
		case compass:
			img = la.loadImage("../img/Compass.png");
			img.resize(Salle.getSize(), Salle.getSize());
			break;
		case key:
			img = la.loadImage("../img/Key.gif");
			img.resize(Salle.getSize(), Salle.getSize());
			break;
		default:
			break;
		}
	}

	/**
	 * Getter de l'ItemIs ii
	 */
	public ItemIs getIi() {
		return ii;
	}

	/**
	 * Getter de la PImage img
	 */
	public PImage getImg() {
		return img;
	}

	/**
	 * Accesseurs en lecture puis ecriture de nblantern
	 */
	public static int getNblantern() {
		return nblantern;
	}

	public static void setNblantern(int nblantern) {
		Item.nblantern = nblantern;
	}

	/**
	 * Accesseurs en lecture puis ecriture de nbarmor
	 */
	public static int getNbarmor() {
		return nbarmor;
	}

	
	public static void setNbarmor(int nbarmor) {
		Item.nbarmor = nbarmor;
	}

	/**
	 * Accesseurs en lecture puis ecriture de nboracle
	 */
	public static int getNboracle() {
		return nboracle;
	}

	public static void setNboracle(int nboracle) {
		Item.nboracle = nboracle;
	}

	
	/**
	 * Accesseurs en lecture puis ecriture de nbcompass
	 */
	public static int getNbcompass() {
		return nbcompass;
	}

	public static void setNbcompass(int nbcompass) {
		Item.nbcompass = nbcompass;
	}

	/**
	 * Permet de dessiner l'Item dans le LabyrintheApp la, aux coordonnés x et y.
	 * @param x coordonné en absicce
	 * @param y coordonné en ordonné
	 * @param la LabyrintheApp dans lequel dessiner.
	 */
	public void dessiner(int x, int y, LabyrintheApp la) {
		la.image(img, x, y);
	}

	/**
	 * Cette methode permet de remetre a zero le nombre d'Item de chaque Item a 0. Afin de gerer la creation d'Item dans les niveaux successifs.
	 * @param player Corespond au Personnage sur lequel il fait remettre a 0 (si necessaire) le nombre d'Item (selon l'Item).
	 */
	public static void reset(Personnage player) {
		if (!player.isHasLantern()) {
			nblantern = 0;
		}
		if (!player.isHasArmor()) {
			nbarmor = 0;
		}

		if (!player.isHasOracle()) {
			nboracle = 0;
		}
		if (!player.isHasCompass()) {
			nbcompass = 0;
		}
	}
}
