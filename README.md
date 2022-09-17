# UniEasy

Progetto sviluppo software per il corso di "Ingegneria del Software avanzata" dell'Università degli Studi di Ferrara. 
Il progetto consiste in un sistema di gestione degli esami universitari utilizzato dagli studenti e dai docenti che offre, 
alle due categorie di utenti, servizi diversi in base alle loro specifiche esigenze.
Il docente avrà la possibilità di gestire gli appelli di esame delle materie di cui è titolare, mentre lo studente 
utilizzerà il sistema per consultare il proprio piano di studi e gestire le prenotazioni agli appelli di esame a cui vuole partecipare.

OPERAZIONI NECESSARIE PRIMA DI ESEGUIRE L'APPLICAZIONE:
Creare un database utilizzando il file unieasy.sql fornito.
Modificare le credenziali per l'accesso al DB nella classe "MyConnection":
- url = "jdbc:mysql://localhost/nomeDB"
- user = "UsernameScelto"
- password = "passwordScelta"

Nel progetto è stato utilizzato il PostgreSQL con PgAdmin 4 per una gestione semplificata del DB.

DATABASE
Per lo store dei dati che riguardano gli studenti, i docenti, gli appelli fissati, il corso a cui lo studente è iscritto, 
gli esami sostenuti dallo studente, le prenotazioni ad appelli e le materie che comprende il corso,
viene utilizzato un database SQL con sette tabelle:
-appello
-corso
-docente
-studente
-esame
-materia
-prenotazione

