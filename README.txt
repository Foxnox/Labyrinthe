Modif 10/10/2013 (T)
	- correction de la déclaration LinkedList dans la classe Labyrinthe
	- correction dela fonction readFiles() dans la classe Labyrinthe (pour le décalage)
	- fonctions d'affichage du labyrinthe dans la classe LabyrintheApp
	- debut classe Personnage
	- ajout booleen adjacente dans la classe Salle (avec accesseurs)

	notes :
	j'ai pas r�ussi corriger le bug de l'exception
	
Modif 11/10/2013 22:35 (A)
	-halo de lumiere ok !! le probl�me venai de la taille de l'image. les pixels etaient bien identifier mais le resize lors de l'affichage cr�ait un d�calage.
	J'ai donc resize lors de l'instanciation avec la m�thode resize ;). �a semble etre OK !!
	
	- 01:00 : Mouvements implement�s !! SO EASY !! Il ne reste plus que la sortie du labyrinthe � g�rer !!

Modif 12/10/2013 19:05 (A)
	-correction de l'halo de lumiere.
	-correction d'un bug de d�placement.
	
Probleme (bug?) 16/10/2013
	j'arrive pas afficher un deuxieme labyrinthe laby.txt et test.txt marchent mais level[X].txt ne fonctinne pas

Modif 16/10/2013 (T)
	- modif dans LabyrintheApp : ajout d'un tableau de files

Modif 24/10/2013 (A)
	- ajout de pi�ges
	
Modif 24/10/2013 (T)
	- modif dans LabyrintheApp : ajout menu avec selecteur de niveau
	
Modif 27/10/2013 (A)
	- ajout d'items
	
Modif 28/10/2013 (T)
	- hud : vie du joueur et ses items
	- affichage de la sortie lorsque le joueur poss�de la boussole
	- affichage des salles pi�g�es lorsque le joueur poss�de l'�il (les salles apparaissent en orange)
	- un c�ur ne redonne qu'un point de vie
	- la lanterne double le champ de vision
	- ajout d'une liste d'items dans la classe Personne (pour faciliter l'affichage)
	- le champ de vision est reset � chaque level
	- d�calage de l'affichage du labyrinthe afin que le hud ne masque pas le laby
	- ajout game over quand le joueur n'a plus de point de vie cqfd ^^
	- ajout toString() dans Item
	
	notes :
	- il faudrait que tu compl�tes le readme de temps en temps quand tu fais des modif ^^
	- � quoi �a sert de compter le nb d'items ? // OK LAISSE TOMBER J'AI TROUV� FUCK
	
	bug :
	- bug ? d�s qu'un items apparait une fois il ne r�apparait plus m�me en repassant dans le menu ou en changeant de level // OK LAISSE TOMBER J'AI TROUV� FUCK� -- BUG R�SOLU
	- des items peuvent pop sur la salle enter et exit
	
	
	
	- ajout reset() dans Item pour r�initialiser le nombre d'item // CONNERIE DE MERDE
