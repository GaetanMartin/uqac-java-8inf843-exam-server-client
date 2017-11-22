# 8INF843 Systèmes Répartis - Examen

Mettre en place une architecture avec stub et skeleton

+ Première méthode : communication par chaîne de caractères
+ Deuxième méthode : communication par objet (Message) 

## Lancement application

Pour tester l’application, il suffit de lancer tout d’abord le module serveur : (server/src/AppServer.java) puis de lancer le client (client/src/AppClient.java). 

## Paramétrage

La méthode par envoi d'objet est activée par défaut, pour tester la méthode par envoi de chaîne de caractères, il suffit de décommenter les lignes des fichiers Stub et Skeleton.

Dans la classe AppClient, il est possible de modifier les opérations appellées.  

Tous les paramètres sont accessibles dans le fichier client/src/config/Config.java