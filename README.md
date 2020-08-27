#README Eventplanungssystem ##Gruppe 17 - Entwickler

Maximilian Lehner 85980

Maximilian Möginger 85463

Matthias Scherzer 77319

##0. Tomcat Deployment mit IntelliJ unter Windows Kompilieren mit mvn package (Alternativ rechts oben Reiter Maven auswählen und Doppelklick auf package)

Das Verzeichnis der nun erstellten G17-20ss-web.data.eng-0.0.1-SNAPSHOT.war ist nun im Verzeichnis, dass im Log angezeigt wird. Beispielsweis: G:\development\intelliJ\G17-20ss-web.data.eng-0.0.1-SNAPSHOT.war

Nun muss der Tomcat Server heruntergeladen und installiert werden. https://tomcat.apache.org/

Nach dem Entpacken in /conf die server.xml öffnen und das Java Home directory angeben.

Anschließend den .war file G17-20ss-web.data.eng-0.0.1-SNAPSHOT.war in den Ordner /webapps kopieren und in ROOT.war umbennen. Ohne diesen Schritt funktioniert das Programm nicht richtig da falsch refenziert wird. (404 unter http://localhost:8080/)

Nach Start des Servers via startup.bat ist das Eventplanungssystem nach einigen Sekunden gestaret. Die explizite Konfiguration einer Datenbank ist nicht nötig, da H2 verwendet wird.

##1. Anleitung und Hinweise

####1.1 Erreichbarkeit und Browser

Sobald alles gestartet ist kann man mit Google Chrome auf http://localhost:8080/ auf die Seite zugreifen. Die Seite ist selbstverständlich auch mit anderen Browsern erreichbar, allerdings können Bugs auftreten, da während der Entwicklung ausschließlich mit Google Chrome getestet wurde.

####1.2 CORS Bypass und Wetter API

Um die verwendete Wetter API nutzen zu können muss CORS (Cross-Origin Resource Sharing) umgangen werden. Dies geschieht am einfachsten mit einem Plugin. Zum Beispiel MOESIF CORS (https://chrome.google.com/webstore/detail/moesif-orign-cors-changer/digfbfaphojjndkpccljibejjbppifbc)

Ohne diesen Beipass können keine Wetterdaten empfangen werden. Außerdem erkennt die verwendete Wetter API viele Orte nicht. Passau wird beispielsweise nicht erkannt. Größere Städte wie München, London etc. werden einwandfrei erkannt.

####1.3 REST API Endpunkt

Unter http://localhost:8080/events?n=X bzw. GET /events?n=X kann für X eine beliebige natürliche Zahl eingegeben werden. Die entsprechende Anzahl an Events wird anschließend als JSON Objekt zurückgegeben.

####1.4 Bei Serverstart werden 5 Events initialisiert

Die Initialisierung erfolgt in data.sql. Auf Wunsch kann diese Initialisierung auskommentiert werden.
