package Personnage;

import java.util.LinkedList;

import Application.LabyrintheApp;
import Labyrinthe.Salle;
import Items.Item;

/**
 * @author Arnaud
 *
 */
public class Personnage {
	/**
	 * Salle occupé par le Personnage courant.
	 */
	private Salle salleCourante;
	
	/**
	 * Vie du Personnage courant.
	 */
	private int life;
	
	/**
	 * Taille du "halo" de lumiere. Dépend selon le Personnage courant
	 */
	private float distanceBrightness;
	
	/**
	 * Définie pendant combien de temps le personnage est assomé par un stun (impossible de bouger).
	 */
	public static final float STUN_TIME = 500;
	
	/**
	 * Temps de la derniere fois ou le Personnage a été stun.
	 */
	private float stunTime;

	/**
	 * Tableau de Salle, qui contien les salles adjacentes au joueur.
	 * sallesAdjacentes = || droite | haut | gauche | bas ||
	 */
	private Salle[] sallesAdjacentes;

	/**
	 * Booléens permettant de savoir quels Item a le Personnage courant.
	 * Lanterne : hasLantern.
	 * Armure : hasArmor.
	 * Oeil : hasOracle.
	 * Boussole : hasCompass.
	 */
	private boolean hasLantern;
	private boolean hasArmor;
	private boolean hasOracle;
	private boolean hasCompass;

	/**
	 * Liste contenant les Item ramassés par le Personnage courant.
	 */
	private LinkedList<Item> listItems = new LinkedList<Item>();

	
	/**
	 * Constructeur de la classe Personnage
	 * @param la Permet de faire passer un LabyrintheApp a la méthode Salle(...).
	 */
	public Personnage(LabyrintheApp la) {
		life = 10;
		distanceBrightness = 80;
		sallesAdjacentes = new Salle[4];
		
		for (int i = 0; i < sallesAdjacentes.length; ++i) {
			sallesAdjacentes[i] = new Salle(-1, -1, false, false, la);
		}
		
		hasLantern = false;
		hasArmor = false;
		hasOracle = false;
		hasCompass = false;
	}

	/**
	 * Accesseurs en lecture puis ecriture de salleCourante
	 */
	public Salle getSalleCourante() {
		return salleCourante;

	}

	public void setSalleCourante(Salle salleCourante) {
		this.salleCourante = salleCourante;
	}

	/**
	 * Accesseurs en lecture puis ecriture de life
	 */
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * Accesseurs en lecture puis ecriture de stunTime
	 */
	public float getStunTime() {
		return stunTime;
	}

	public void setStunTime(float stunTime) {
		this.stunTime = stunTime;
	}

	/**
	 * Accesseurs en lecture puis ecriture de hasLantern
	 */
	public boolean isHasLantern() {
		return hasLantern;
	}

	public void setHasLantern(boolean hasLantern) {
		this.hasLantern = hasLantern;
	}

	/**
	 * Accesseurs en lecture puis ecriture de hasArmor
	 */
	public boolean isHasArmor() {
		return hasArmor;
	}

	public void setHasArmor(boolean hasArmor) {
		this.hasArmor = hasArmor;
	}

	/**
	 * Accesseurs en lecture puis ecriture de hasOracle
	 */
	public boolean isHasOracle() {
		return hasOracle;
	}

	public void setHasOracle(boolean hasOracle) {
		this.hasOracle = hasOracle;
	}

	/**
	 * Accesseurs en lecture puis ecriture de hasCompass
	 */
	public boolean isHasCompass() {
		return hasCompass;
	}

	public void setHasCompass(boolean hasCompass) {
		this.hasCompass = hasCompass;
	}

	
	/**
	 * Permet de reinitialiser le booléen accessible de toutes les salles du tableau sallesAdjacentes.
	 */
	public void resetSallesAdjacentes() {
		for (Salle s : sallesAdjacentes) {
			s.setAccessible(false);
		}
	}

	/**
	 * Met a jour le tableau sallesAdjacentes avec la liste de Salle salles
	 * @param salles Liste de Salle a partir de laquel mettre a jour sallesAdjacentes
	 */
	public void majSallesAdjacentes(LinkedList<Salle> salles) {
		for (Salle s : salles) {
			if (s.getX() == salleCourante.getX() + Salle.getSize()
					&& s.getY() == salleCourante.getY()) // Salle de droite =
															// sallesAdjacentes[0]
			{
				sallesAdjacentes[0] = new Salle(s);
				sallesAdjacentes[0].setAccessible(true);
			}

			if (s.getX() == salleCourante.getX() - Salle.getSize()
					&& s.getY() == salleCourante.getY()) // Salle de gauche =
															// sallesAdjacentes[2]
			{
				sallesAdjacentes[2] = new Salle(s);
				sallesAdjacentes[2].setAccessible(true);
			}

			if (s.getY() == salleCourante.getY() + Salle.getSize()
					&& s.getX() == salleCourante.getX()) // Salle du bas =
															// sallesAdjacentes[3]
			{
				sallesAdjacentes[3] = new Salle(s);
				sallesAdjacentes[3].setAccessible(true);
			}

			if (s.getY() == salleCourante.getY() - Salle.getSize()
					&& s.getX() == salleCourante.getX()) // Salle du haut =
															// sallesAdjacentes[1]
			{
				sallesAdjacentes[1] = new Salle(s);
				sallesAdjacentes[1].setAccessible(true);
			}
		}
	}

	
	/**
	 * Permet de changer la salle courante du Personnage par la salle cible.
	 * @param salleCible Permet de définir vers quelle Salle bouger( 0:droite, 1:haut, 2:gauche, 3:bas)
	 */
	public void move(int salleCible) {

		if (sallesAdjacentes[salleCible] != null
				&& sallesAdjacentes[salleCible].isAccessible()) {
			salleCourante = sallesAdjacentes[salleCible];
			sallesAdjacentes[salleCible].setAccessible(false);
		}
	}

	/**
	 * Gère la gestion de la prise de domage avec un piège.
	 */
	public void takeTrap() {
		if (hasArmor) {
			life -= 1;
		} else {
			life -= 2;
		}
		if (life <= 0) {
			life = 0;
			LabyrintheApp.setGameOver(true);
		}
	}

	
	/**
	 * Permet de dessiner le personnage (un petit cercle) dans le labyrinthe.
	 * Le joueur est dessiné en vert (0,255,0).
	 * Le joueur est dessiné en rouge quand il est assomé.
	 * @param pa LabyrintheApp dans lequel dessiner le Personnage.
	 */
	public void dessiner(LabyrintheApp pa) {
		int x = ((salleCourante.getX() - (Salle.getSize() / 2)) + Salle
				.getSize());
		int y = ((salleCourante.getY() - (Salle.getSize() / 2)) + Salle
				.getSize());
		int size = (int) (Salle.getSize() / 1.8);
		if (pa.millis() > this.getStunTime() + Personnage.STUN_TIME) {
			pa.fill(0, 255, 0);
		} else {
			pa.fill(255, 0, 0);
		}
		pa.ellipse(x, y, size, size);
	}

	/**
	 * Gère le ramassage des Item par le Personnage et gère les effets de ceux ci si possible.
	 */
	public void takeItem() {
		switch (salleCourante.getItem().getIi()) {
		case heart:
			life += 1;
			if (life >= 10) {
				life = 10;
			}
			break;
		case lantern:
			if (!hasLantern) {
				distanceBrightness *= 1.5;
				hasLantern = true;
				listItems.add(salleCourante.getItem());
			}
			break;
		case armor:
			if (!hasArmor) {
				hasArmor = true;
				listItems.add(salleCourante.getItem());
			}
			break;
		case oracle:
			if (!hasOracle) {
				hasOracle = true;
				listItems.add(salleCourante.getItem());
			}
			break;
		case compass:
			if (!hasCompass) {
				hasCompass = true;
				listItems.add(salleCourante.getItem());
			}
			break;
		case key:

			listItems.add(salleCourante.getItem());
			break;
		default:
			break;
		}
		
	}

	/**
	 * Getter sur listItems
	 */
	public LinkedList<Item> getListItems() {
		return listItems;
	}

	/**
	 * Accesseurs en lecture puis ecriture de distanceBrigthness
	 */
	public float getDistanceBrightness() {
		return distanceBrightness;
	}

	public void setDistanceBrightness(float distanceBrightness) {
		this.distanceBrightness = distanceBrightness;
	}
}