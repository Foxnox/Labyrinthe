Modif 10/10/2013 (T)
	- correction de la dÃ©claration LinkedList dans la classe Labyrinthe
	- correction dela fonction readFiles() dans la classe Labyrinthe (pour le dÃ©calage)
	- fonctions d'affichage du labyrinthe dans la classe LabyrintheApp
	- debut classe Personnage
	- ajout booleen adjacente dans la classe Salle (avec accesseurs)

	notes :
	j'ai pas réussi corriger le bug de l'exception
	
Modif 11/10/2013 22:35 (A)
	-halo de lumiere ok !! le problème venai de la taille de l'image. les pixels etaient bien identifier mais le resize lors de l'affichage créait un décalage.
	J'ai donc resize lors de l'instanciation avec la méthode resize ;). ça semble etre OK !!
	
	- 01:00 : Mouvements implementés !! SO EASY !! Il ne reste plus que la sortie du labyrinthe à gérer !!

Modif 12/10/2013 19:05 (A)
	-correction de l'halo de lumiere.
	-correction d'un bug de déplacement.
	
Probleme (bug?) 16/10/2013
	j'arrive pas afficher un deuxieme labyrinthe laby.txt et test.txt marchent mais level[X].txt ne fonctinne pas

Modif 16/10/2013 (T)
	- modif dans LabyrintheApp : ajout d'un tableau de files

Modif 24/10/2013 (A)
	- ajout de pièges
	
Modif 24/10/2013 (T)
	- modif dans LabyrintheApp : ajout menu avec selecteur de niveau
	
Modif 27/10/2013 (A)
	- ajout d'items
	
Modif 28/10/2013 (T)
	- hud : vie du joueur et ses items
	- affichage de la sortie lorsque le joueur possède la boussole
	- affichage des salles piégées lorsque le joueur possède l'œil (les salles apparaissent en orange)
	- un cœur ne redonne qu'un point de vie
	- la lanterne double le champ de vision
	- ajout d'une liste d'items dans la classe Personne (pour faciliter l'affichage)
	- le champ de vision est reset à chaque level
	- décalage de l'affichage du labyrinthe afin que le hud ne masque pas le laby
	- ajout game over quand le joueur n'a plus de point de vie cqfd ^^
	- ajout toString() dans Item
	
	notes :
	- il faudrait que tu complètes le readme de temps en temps quand tu fais des modif ^^
	- à quoi ça sert de compter le nb d'items ? // OK LAISSE TOMBER J'AI TROUVÉ FUCK
	
	bug :
	- bug ? dès qu'un items apparait une fois il ne réapparait plus même en repassant dans le menu ou en changeant de level // OK LAISSE TOMBER J'AI TROUVÉ FUCK² -- BUG RÉSOLU
	- des items peuvent pop sur la salle enter et exit
	
	
	
	- ajout reset() dans Item pour réinitialiser le nombre d'item // CONNERIE DE MERDE
