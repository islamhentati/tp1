# TP1-DAR2026 – Sockets TCP en mode connecté

*islemhenteti* – LSI3 – Développement d'applications réparties

# Activité 1-1 : Première connexion Client/Serveur

## Étape 1 : Vérification de Git
![git version](image\cap3.png)

Git est installé : la commande git --version affiche la version installée
(git version 2.55.0.windows.5).

## Étape 2 : Vérification de Java
![java version](image\cap1.png)

La commande java --version échoue (« Unrecognized option: --version ») car cette
option n'existe qu'à partir de Java 9. Avec le JDK 8 (Temurin 1.8.0_482), la commande
java -version affiche bien la version, et javac -version confirme la présence
du compilateur. Le projet est exécuté dans IntelliJ avec le JDK 25.

![dossier](image\cap20.png)

Création du répertoire de travail islemhenteti_LSI3_DevAppReparties sur le Bureau.
Il contient le projet TP1-DAR2026.

## Étape 3 : Création du serveur
![projet](image\cap8.png)

Création du projet TP1-DAR2026 avec dépôt Git, du package serverPackage
et de la classe Server (ServerSocket sur le port 5000, accept(), fermeture des sockets).

![console serveur](image\cap23.jpg)

*Interprétation* : le ServerSocket s'ouvre sur le port 5000, puis accept()
est une méthode bloquante : le serveur attend sans rien faire qu'un client se
connecte. Le message « un client est connecté » n'apparaît pas encore, ce qui
prouve qu'aucun client n'est connecté.

## Étape 4 : Création du client
![code client](image\cap9.png)
![console client](image\cap22.jpg)

Client lancé avec localhost et le port 5000.

![console serveur](image\cap21.jpg)

*Interprétation* : le client crée un Socket vers localhost:5000. Le serveur
débloque son accept() et affiche « un client est connecté ». Le client affiche
« je suis un client connecté » : la connexion TCP est établie. Les deux programmes
se terminent après la fermeture des sockets (Process finished with exit code 0).



---

# Activité 1-2 : Service de calculatrice

## Étape 1 : Préparation de l'espace de travail
Le projet TP1-DAR2026 de l'Activité 1-1 est réutilisé et étendu : le client envoie
un entier au serveur, qui le multiplie par 5 et renvoie le résultat.
Les échanges utilisent DataInputStream et DataOutputStream.

## Étape 2 : Service de multiplication

### Code du serveur
![code serveur](image\cap19.png)
![code serveur](image\cap18.png)
Le serveur attend un client, lit un entier avec readInt(), calcule x * 5,
puis renvoie le résultat avec writeInt().

### Code du client
![code client](image\cap16.png)

Le client lit un entier au clavier (Scanner), l'envoie avec writeInt(),
puis reçoit et affiche le résultat avec readInt().

### Tests avec différentes valeurs
![test ](image\cap15.png)



---

# Activité 1-3 : Amélioration du service de calculatrice

## Étape 1 : Connexion Client/Serveur avec l'adresse IP réelle

![ipconfig du machine A](image\cap25.jpg)

La commande ipconfig donne l'adresse IPv4 de la machine : *10.248.38.120*. de l'autre machine A

![code serveur](image\CAP.jpg)


Le serveur utilise 'InetAddress et InetSocketAddress pour configurer son adresse réseau et le port 1234.

![code client](image\cap13.png)

Le client utilise l'adresse IPv4 de la machine A au lieu de "localhost. Les deux machines doivent être connectées au 
même réseau et le port 1234 doit être accessible.

![serveur en attente](image\cap26.jpg)

Le serveur affiche qu'il attend sur 10.248.38.120.

![client connecté](image\cap11.png)

Le client se connecte au serveur en utilisant l'adresse IP réelle.

![serveur connecté](image\cap27.jpg)

Le serveur affiche l'adresse IP du client connecté.

*Interprétation :* la machine A joue le rôle du serveur et la machine B celui du client. Le client établit une 
connexion TCP vers l'adresse IP de A sur le port 1234.

## Étape 2 : Service de calculatrice amélioré

![code client menu](image\cap29.png)

Le client demande deux entiers et un opérateur, envoie les données au serveur, puis reçoit et affiche le résultat.

![code serveur menu](image\cap30.png)

Le serveur reçoit les opérandes et l'opérateur envoyés par le client. Il utilise une structure switch pour effectuer
l'addition, la soustraction, la multiplication ou la division.

### Tests

![addition client](image\img.png)
![addition serveur](image\cap31.jpg)

*Addition* : 8 + 6 = 14.0.

Ce TP m'a permis de comprendre la communication client-serveur en Java avec les sockets TCP, d'abord sur une connexion simple, puis avec un service de multiplication et une calculatrice comportant quatre opérations.

L'utilisation de deux machines permet également de mettre en pratique la communication sur un réseau local : le serveur écoute sur la machine A et le client se connecte à son adresse IP depuis la machine B.