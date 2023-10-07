Compile mais n arrive pas a executer les requetes listeinstrument correctement
!!!

pour ne pas avoir a compiler deux main: celui actuel est dans la classe QuickTestJdbc.java) !!!! 

!!! PLAN A PARTIR DU 27/6/23
Reecrire les Controller d'une vue SignUp a partir de ce que j ai appris dans
les videos Udemy (juste avant hibernate): l'objet session, l'url mapping.

! a partir du 10/8/23
ne pas integrer directement dans le code ni avec la methode
import(requete.sql) la super requete affichage (partition, enregistrement,
musician, instrument) mais preparer le terrain une jsp affichage/consultation
partition avec une etape de transformation objet List<Map<>>;

Note: List<Map<>> est bien generalise maintenant

! a partir du 04/09/23
UpdatAll_Controller.java a 2 controllers
dao/InstrAllPrepService.java fait de l'import sql pour des requetes update
partitions

Il faudra gerer (temporairement) une deuxieme branche "HSQLDB" pour tester
simplement le pont jdbc sur une base hsqldb sur le petit pc portable.

