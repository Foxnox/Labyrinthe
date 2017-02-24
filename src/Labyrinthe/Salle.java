package Labyrinthe;

import Application.LabyrintheApp;
import Items.Item;

import processing.core.PApplet;

public class Salle {
	
	/**
	 * Coordon�s en abscisse (x) et en ordonn� (y)
	 */
	private int x;
	private int y;
	
	/**
	 * Taille de la salle. Identique pour toute les salles.
	 */
	private static int size = 15;
	
	/**
	 * Bool�ens permetant de savoir si la salle est une entr�e (isEntry) ou une sortie (isExit).
	 */
	private boolean isEntry;
	private boolean isExit;
	
	/**
	 * Permet de savoir si la salle est pi�g� ou non.
	 */
	private boolean isTraped;
	
	/**
	 * Permet de savoir si la salle est acc�ssible ou non.
	 */
	private boolean accessible;
	
	/**
	 * Permet de savoir si la salle contien un Item
	 */
	private boolean containItem;
	
	/**
	 * Diff�rentes probabilit�s conditionnant les chance d'apparition :
	 * Des pi�ges : probIsTraped
	 * Des items : probContainItem
	 * des Coeurs : probContainHeart
	 */
	static double probIsTraped = 2;
	static double probContainHeart = 3;
	static double probContainItem = 1;
	
	/**
	 * Item pr�sent dans la salle
	 */
	private Item item = null;

	/**
	 * Enumeration d�finissant toutes les natures possible des objets.
	 */
	public enum ItemIs {
		heart, lantern,  armor, oracle, compass, key
	}

	/**
	 * Constructeur de la classe Salle. Qui la remplie avec un piege ou un Item selon le r�sultat des random.
	 * @param x Position en abscisse de la Salle
	 * @param y Position en ordonn� de la Salle
	 * @param entry Permet de savoir si c'est une entr�e.
	 * @param exit Permet de savoir si c'est une sortie.
	 * @param labyrintheApp Permet de r�cuperer le Personnage et d'instancier des Items.
	 */
	public Salle(int x, int y, boolean entry, boolean exit,
			LabyrintheApp labyrintheApp) {
		setX(x);
		setY(y);
		isEntry = entry;
		isExit = exit;
		accessible = false;
		containItem = false;

		if (probIsTraped > (Math.random() * 300) && !isEntry && !isExit) {
			isTraped = true;
		} else {
			isTraped = false;
		}
		if (probContainHeart > (Math.random() * 300) && !isEntry && !isExit) {
			ItemIs wi = ItemIs.heart;
			item = new Item(wi, labyrintheApp);
			containItem = true;
		} else if (probContainItem > (Math.random() * 300) && !containItem
				&& !labyrintheApp.getPlayer().isHasLantern()
				&& Item.getNblantern() < 1
				&& !isEntry && !isExit) {
			ItemIs wi = ItemIs.lantern;
			item = new Item(wi, labyrintheApp);
			containItem = true;
			Item.setNblantern(Item.getNblantern() + 1);
		} else if (probContainItem > (Math.random() * 300) && !containItem
				&& !labyrintheApp.getPlayer().isHasArmor()
				&& Item.getNbarmor() < 1
				&& !isEntry && !isExit) {
			ItemIs wi = ItemIs.armor;
			item = new Item(wi, labyrintheApp);
			containItem = true;
			Item.setNbarmor(Item.getNbarmor() + 1);
		} else if (probContainItem > (Math.random() * 300) && !containItem
				&& !labyrintheApp.getPlayer().isHasOracle()
				&& Item.getNboracle() < 1
				&& !isEntry && !isExit) {
			ItemIs wi = ItemIs.oracle;
			item = new Item(wi, labyrintheApp);
			containItem = true;
			Item.setNboracle(Item.getNboracle() + 1);
		} else if (probContainItem > (Math.random() * 300) && !containItem
				&& !labyrintheApp.getPlayer().isHasCompass()
				&& Item.getNbcompass() < 1
				&& !isEntry && !isExit) {
			ItemIs wi = ItemIs.compass;
			item = new Item(wi, labyrintheApp);
			containItem = true;
			Item.setNbcompass(Item.getNbcompass() + 1);
		}
	}

	/**
	 * Constructeur par copie de la classe Salle
	 * @param s Salle � copier.
	 */
	public Salle(Salle s) {
		this.x = s.x;
		this.y = s.y;
		this.accessible = s.accessible;
		this.isEntry = s.isEntry;
		this.isExit = s.isExit;
		this.isTraped = s.isTraped;
		this.containItem = s.containItem;
		this.item = s.item;
	}

	/**
	 * Accesseurs en lecture puis ecriture de X
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	/////////////////////////////////////////////////////////////////
	/**
	 * Accesseurs en lecture puis ecriture de Y
	 */
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	/////////////////////////////////////////////////////////////////
	/**
	 * Getter sur size
	 */
	public static int getSize() {
		return size;
	}
	/////////////////////////////////////////////////////////////////
	/**
	 * Accesseurs en lecture puis ecriture de isEntry
	 */
	public boolean isEntry() {
		return isEntry;
	}

	public void setEntry(boolean isEntry) {
		this.isEntry = isEntry;
	}
	/////////////////////////////////////////////////////////////////
	/**
	 * Accesseurs en lecture puis ecriture de isExit
	 */
	public boolean isExit() {
		return isExit;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}
	/////////////////////////////////////////////////////////////////
	/**
	 * Accesseurs en lecture puis ecriture de isTraped
	 */
	public boolean isTraped() {
		return isTraped;
	}

	public void setTraped(boolean isTraped) {
		this.isTraped = isTraped;
	}
	/////////////////////////////////////////////////////////////////
	/**
	 * Accesseurs en lecture puis ecriture de isAccessible
	 */
	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	/////////////////////////////////////////////////////////////////
	/**
	 * Getter de item
	 */
	public Item getItem() {
		return item;
	}
	/////////////////////////////////////////////////////////////////
	/**
	 * Accesseurs en lecture puis ecriture de isContainItem
	 */
	public boolean isContainItem() {
		return containItem;
	}

	public void setContainItem(boolean containItem) {
		this.containItem = containItem;
	}

	/**
	 * Permet de dessiner chaque salle dans le LabyrintheApp la.
	 * L'entr� est dessin�e en bleu (0,0,255).
	 * La sortie est dessin�e en rouge (255,0,0).
	 * Si le joueur a trouv� l'oracle, alors les salles pi�g�s sont dessin�es en oranges (255,127,0).
	 * Un salle normale (qui ne remplie aucune des condition pr�cedente) sont dessin�es en blanc (255,255,255).
	 * Enfin, la couleur est ajust� par rapport a la distance de la salle par rapport au joueur afin de vr�er un "halo" de lumiere.
	 * @param la LabyriintheApp dans lequel dessiner la salle.
	 */
	public void dessiner(LabyrintheApp la) {
		int r, v, b;
		if (isEntry) {
			r = 0;
			v = 0;
			b = 255;
		} else if (isExit) {
			r = 255;
			v = 0;
			b = 0;
		} else if (la.getPlayer().isHasOracle() && isTraped) {
			r = 255;
			v = 127;
			b = 0;
		}

		else {
			r = 255;
			v = 255;
			b = 255;
		}

		float distance = PApplet.dist(x, y, la.getPlayer().getSalleCourante()
				.getX(), la.getPlayer().getSalleCourante().getY());
		if (distance <= la.getPlayer().getDistanceBrightness()) {
			float adjustBrightness = (la.getPlayer().getDistanceBrightness() - distance)
					/ la.getPlayer().getDistanceBrightness();
			r *= adjustBrightness;
			v *= adjustBrightness;
			b *= adjustBrightness;
			// Constrain RGB to between 0-255
			r = PApplet.constrain(r, 0, 255);
			v = PApplet.constrain(v, 0, 255);
			b = PApplet.constrain(b, 0, 255);
			// Make a new color and set pixel in the window
			la.fill(r, v, b);
			la.rect(x, y, size, size);
			if (item != null && containItem) {
				item.dessiner(x, y, la);
			}

		}
		if (la.getPlayer().isHasCompass() && isExit) {
			la.fill(255, 0, 0);
			la.rect(x, y, size, size);
		}
	}

	/**
	 * Permet de savoir si deux salles (this et s) sont �gales. C'est a dire si leurs coordonn�s sont �gales.
	 * @param s  seconde Salle a tester
	 * @return Vrai si les coordon�s de la salle s et de la salle courante (this) sont �gales.
	 */
	public boolean isEqual(Salle s) {
		return (this.x == s.x && this.y == s.y);
	}

}
