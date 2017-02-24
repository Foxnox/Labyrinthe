package Labyrinthe;

import java.util.LinkedList;

import Application.LabyrintheApp;
import Personnage.Personnage;

public class Labyrinthe {
	
	/**
	 * Taille du Labyrinthe.
	 * width pour la largeur.
	 * heught pour la hauteur.
	 */
	private int width;
	private int height;
	
	/**
	 * LinkedList qui contient toutes les salles du jeu.
	 */
	private LinkedList<Salle> salles;
	
	/**
	 * Salles particulieres.
	 * L'entré : entry
	 * La sortie : exit
	 */
	private Salle entry;
	private Salle exit;
	
	/**
	 * Constructeur de la Classe Labyrinthe
	 */
	public Labyrinthe(){
		 salles = new LinkedList<Salle>();
	}
	
	/**
	 * Accesseurs en lecture puis ecriture de width
	 */
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Accesseurs en lecture puis ecriture de height
	 */
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Accesseurs en lecture puis ecriture de entry
	 */
	public Salle getEntry() {
		return entry;
	}

	public void setEntry(Salle entry) {
		this.entry = entry;
	}

	/**
	 * Accesseurs en lecture puis ecriture de exit
	 */
	public Salle getExit() {
		return exit;
	}

	public void setExit(Salle exit) {
		this.exit = exit;
	}

	/**
	 * @param s Salle a ajouter dans la liste de Salle salles.
	 */
	public void addSalles(Salle s) {
		salles.add(s);
	}

	/**
	 * Getter de la liste de Salle salles
	 */
	public LinkedList<Salle> getSalles() {
		return salles;
	}

	/**
	 * Cette methode permet de dessiner le labyrinthe en appelant la méthode dessiner de la classe Salle pour chaque Salle de salles.
	 * @param la LabyrintheApp dans lequel dessiner le labyrinthe.
	 */
	public void dessiner(LabyrintheApp la) {
		
		for (Salle s : salles) {
			s.dessiner(la);
		}
	}
	
	/**
	 * Permet de mettre a jour la liste de Salle salles avec les éléments de la salle courante du Personnage p.
	 * Et ainsi gerer le ramassage des Item et les pieges.
	 * @param p Personnage a partir du quel il faut mettre a jour la liste de Salle salles.
	 */
	public void majLabyrinthe(Personnage p)
	{
		for (Salle s : salles)
		{
			if (p.getSalleCourante().isEqual(s))
			{
				s.setTraped(p.getSalleCourante().isTraped());
				s.setContainItem(p.getSalleCourante().isContainItem());
			}
		}

	}
}
