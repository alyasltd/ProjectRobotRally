# ProjectRobotRally

## _Présentation :_ 
Nous avons créé sur Java un jeu roborally qui de base était un jeu de société. Ce dernier se joue de 2 à 4 joueurs, Le but du jeu est d’une part de garder son robot en état de marche, et d’autre part d’être le premier à rallier dans l’ordre 5 checkpoints. 

## _Règles du jeu :_
Pour se déplacer dans le jeu, chaque joueur recevra 9 cartes de direction et devra en sélectionner 5 qui définiront ainsi son parcours sur le plateau, et ce pour chaque tour. 
Une fois ces 5 cartes choisies, le premier joueur joue sa première carte, le deuxième joueur sa première carte et ce jusqu’au dernier joueur. L’ordre de passage des joueurs est défini aléatoirement.

Au cours de la partie, des interactions avec les éléments de l’usine vont influer sur les joueurs, leur direction, position sur le plateau, ou encore sur leur point de vie et point de dégât.
Les éléments de la fabrique qu’un robot peut rencontrer sont :
-	*Case Spirale* : change la direction du joueur.
-	*Case Mur* : empêche le joueur d’avancer.
-	*Case Boost* :  fait avancer le joueur de 1 en plus de l’avancement de sa carte.
-	*Case DoubleBoost* : fait avancer le joueur de 2 en plus de l’avancement de sa carte.
-	*Case Laser* : augmente les points de dégâts du joueur de 1, 2 ou 3 selon la clé associée à la case.
-	*Case BlackHole* : détruit le joueur et le renvoie à son dernier chekcpoint.
-	*Case Outils* : case de réparation, baisse les points de dégât du joueur de 1, 2 ou 3, selon la clé associée à la case.

L’importance des points de dégât et des points de vie est primordiale. 
Si un joueur perd 1 point de vie, il meurt temporairement et est renvoyé au dernier checkpoint traversé. Au bout de 3 points de vie perdus, le robot est définitivement mort et le joueur éliminé.
Si un jour possède 10 points de dégâts, alors il perd 1 point de vie. De plus, à partir de 5 points de dégâts, on ne distribue plus que 5 cartes au joueur, donc on ne lui permet plus de choisir les cartes qu’il veut parmi 9 proposées. Et de 6 à 10 points de dégâts, le nombre de carte distribuées au joueur diminue de 1 à 1.

La joueur qui est passé par le plus de checkpoints gagne.	


## _Liste des technologies :_
-	Java FX
-	Modèle MVC
-	Scène Builder
-	Sérialisation
-	Paint.net

## _Les Bugs :_
       - Parfois, le message 'Server is ready ne peut pas s'afficher sur la console, cela signifie surement que le port 
       du serveur est déjà utilisé ou a trop était utilisé. Changer le port dans le fichier Main du serveur et aussi du Client !
       -

## _Source :_
-	YouTube
-	Stackoverflow


## _Comment lancer le jeu :_

Pour lancer le jeu, il vous suffit de Run le fichier Main du coté "RBR Server", quand le message "Server is ready" s'affiche dans la console. 
Vous pourriez lancer le fichier Main coté "RBR Clien", vous lancer ce fichier tant de fois que vous désirez de joueur (Max 4). 

## _Droit d’auteur :_
-> Alya ZOUZOU
-> Audric GIRONDIN
-> Noa THEBAUT
-> Jonathan DUCKES
-> Lucas MARTINEZ
