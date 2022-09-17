# UniEasy

Progetto sviluppo software per il corso di "Ingegneria del Software avanzata" dell'Università degli Studi di Ferrara. 
Il progetto consiste in un sistema di gestione degli esami universitari utilizzato dagli studenti e dai docenti che offre, 
alle due categorie di utenti, servizi diversi in base alle loro specifiche esigenze.
Il docente avrà la possibilità di gestire gli appelli di esame delle materie di cui è titolare, mentre lo studente 
utilizzerà il sistema per consultare il proprio piano di studi e gestire le prenotazioni agli appelli di esame a cui vuole partecipare.

OPERAZIONI NECESSARIE PRIMA DI ESEGUIRE L'APPLICAZIONE:
Creare un database utilizzando il file unieasy.sql fornito.
Modificare le credenziali per l'accesso al DB nella classe "MyConnection":
- url = "jdbc:postgresql://localhost/nomeDB"
- user = "UsernameScelto"
- password = "passwordScelta"

Nel progetto è stato utilizzato il PostgreSQL con PgAdmin 4 per una gestione semplificata del DB.

DATABASE
Per lo store dei dati che riguardano gli studenti, i docenti, gli appelli fissati, il corso a cui lo studente è iscritto, 
gli esami sostenuti dallo studente, le prenotazioni ad appelli e le materie che comprende il corso,
viene utilizzato un database SQL con sette tabelle:
- appello

![appello](https://user-images.githubusercontent.com/110738908/190869528-f77971b3-8e11-4909-a2b7-83fdcd5a5ece.png)

- corso

![corso](https://user-images.githubusercontent.com/110738908/190869554-f3652510-65db-4c37-8383-a6f97e6478cd.png)

- docente

![docente](https://user-images.githubusercontent.com/110738908/190869565-a6e537de-e472-43f3-bcef-18f3550f03ff.png)

- studente

![studente](https://user-images.githubusercontent.com/110738908/190869577-61df594c-c53e-49a1-8f46-f6cf1c3b7409.png)

- esame

![esame](https://user-images.githubusercontent.com/110738908/190869587-bd909b83-f8a1-406e-a2cf-1b3e5a351fd3.png)

- materia

![materia](https://user-images.githubusercontent.com/110738908/190869598-ce33a8ae-8cb8-4dc8-a095-213f4c6951e1.png)

- prenotazione

![prenotazione](https://user-images.githubusercontent.com/110738908/190869602-e6939121-a4f9-441a-8b53-0fc5eb48a306.png)


