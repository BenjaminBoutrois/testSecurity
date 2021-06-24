# testSecurity

Projet utilisant Spring Security (Authentification, Rôles)

## Développeurs 

- Benjamin Boutrois
- Lyes Koriche 
- Cédric Nozerand

## PREREQUIS: 

Installer [maven](https://maven.apache.org/download.cgi)

Avoir un serveur de Base de données MYSQL ou MARIADB
Avoir un serveur Apache

__Nous recommandons l'utilisation de XAMPP.__

## INSTALLATION:

### ETAPE 1 : 

  Cloner le projet à partir du dépôt (https://github.com/BenjaminBoutrois/testSecurity.git)
  
### ETAPE 2 : 

* Ouvrir le fichier *application.properties* (security/src/main/resources) 

* Verifier et mettre à jour les informations de connexions à la base de données (username et password)

* Vérifier et mettre à jour le port de connexion avec votre serveur de base de données (datasource.url) 

* Créer une base de données vide sous le nom *testsecurity*
    
 ### ETAPE 3 :
 
 #### Exécuter le projet
  
   - Ouvrir une fenêtre de commande à partir de la racine du projet
    
   - Lancer la commande suivante pour démarrer le Serveur :
    
      > mvn spring-boot:run 
      
   - Vérifiez bien que le serveur tourne
   
   - Vérifier vos paramètres en cas d'erreurs (n'hésitez pas à nous contacter pour plus de support)
    
   - Lancer votre navigateur et aller à l'url pour vérifier : [security](http://localhost:8080/)
     
   - Pour pouvoir s'authentifier, il faut d'abord s'inscrire sur le site



