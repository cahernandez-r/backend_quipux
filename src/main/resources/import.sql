INSERT INTO tbl_playlist (name, description, active) VALUES ('Lista de reproduccion 1', 'Esta es la primera lista', true);
INSERT INTO tbl_playlist (name, description, active) VALUES ('Lista de reproduccion 2', 'Esta es la segunda lista', true);

INSERT INTO tbl_album (code, description, active) VALUES ('001','Toxicity', true);
INSERT INTO tbl_album (code, description, active) VALUES ('002','Hypnotize', true);
INSERT INTO tbl_album (code, description, active) VALUES ('003','Mezmerize', true);
INSERT INTO tbl_album (code, description, active) VALUES ('004','Recuerdos', true);

INSERT INTO tbl_genre (code, description, active) VALUES ('001','Rock', true);
INSERT INTO tbl_genre (code, description, active) VALUES ('002','Metal', true);
INSERT INTO tbl_genre (code, description, active) VALUES ('003','Balada', true);
INSERT INTO tbl_genre (code, description, active) VALUES ('004','Country', true);

INSERT INTO tbl_song (title, artist, id_genre, id_album, release_year, active) VALUES ('Chop Suey','Serj Tankian', 2, 1, 1996, true);
INSERT INTO tbl_song (title, artist, id_genre, id_album, release_year, active) VALUES ('La Nave del olvido','Jose Jose', 3, 2, 1997, true);
INSERT INTO tbl_song (title, artist, id_genre, id_album, release_year, active) VALUES ('Bounce','System of a Down', 2, 1, 2001, true);
INSERT INTO tbl_song (title, artist, id_genre, id_album, release_year, active) VALUES ('Toxicity','System of a Down', 2, 1, 2001, true);
INSERT INTO tbl_song (title, artist, id_genre, id_album, release_year, active) VALUES ('Aerials','System of a Down', 2, 1, 2001, true);
INSERT INTO tbl_song (title, artist, id_genre, id_album, release_year, active) VALUES ('Soldier of Love','Sade', 3, 4, 2010, true);
INSERT INTO tbl_song (title, artist, id_genre, id_album, release_year, active) VALUES ('I Will Always Love You','Whitney Houston', 3, 4, 1992, true);

INSERT INTO tbl_playlist_song (id_playlist, id_song, active) VALUES (1, 1, true);
INSERT INTO tbl_playlist_song (id_playlist, id_song, active) VALUES (2, 1, true);