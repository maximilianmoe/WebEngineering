-- Sollte es gewünscht sein, dass bei Serverstart keine Events initialisiert werden sollen, kann dieser Code auskommentiert werden.
  INSERT INTO EVENT(event_id , name, date, event_type, location, interest, disinterest, description, is_liked, is_disliked) VALUES
    (1,'Frequency 2020', '2020-08-13', 'Festival', 'St. Pölten', 2000, 221, 'Das Frequency Festival (auch Frequency oder FM4-Frequency-Festival genannt) ist ein jährlich stattfindendes, österreichisches Musikfestival.', false, false),
    (2,'Rock am Ring 2020', '2020-08-16', 'Festival', 'Nürnberg', 1000, 54, 'Rock am Ring ist eine jährliche Parallelveranstaltung zu Rock im Park mit einer fast identischen Bandbesetzung.', false, false),
    (3,'Harry Potter in Concert - Stein der Weisen', '2022-08-16', 'Konzert', 'München', 2, 24, 'Ein muss für alle Harry Potter Fans', false, false),
    (4,'Nova Rock 2020', '2020-08-21', 'Festival', 'Nickelsdorf', 967, 35, 'Nova Rock ist ein österreichisches Rockmusik-Festival, das seit 2005 jedes Jahr im Juni in Nickelsdorf (Burgenland) stattfindet', false, false),
    (5, 'Rock im Park 2021', '2021-07-30', 'Festival','Nürnberg', 200, 102, 'Rock im Park ist ein jährlich im Mai oder Juni im Volkspark Dutzendteich in Nürnberg stattfindendes Musikfestival und eine Parallelveranstaltung zu Rock am Ring mit einer fast identischen Bandbesetzung.', false, false),
    (6,'Nova Rock 2019', '2019-06-12', 'Festival', 'Nickelsdorf', 4201, 824, 'Nova Rock ist ein österreichisches Rockmusik-Festival, das seit 2005 jedes Jahr im Juni in Nickelsdorf (Burgenland) stattfindet', false, false);

