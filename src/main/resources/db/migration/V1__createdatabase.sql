CREATE TABLE animes (
    animeid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    yearr integer,
    image text --,
    --image2 text,
    --image3 text,
    --image4 text
    );

CREATE TABLE authors (
    authorid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    image text
    );

CREATE TABLE genres (
    genreid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    label text,
    image text
    );

CREATE TABLE anime_author (
    animeid uuid REFERENCES animes(animeid) ON DELETE CASCADE,
    authorid uuid REFERENCES authors(authorid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, authorid));


CREATE TABLE anime_genre (
    animeid uuid REFERENCES animes(animeid) ON DELETE CASCADE,
    genreid uuid REFERENCES genres(genreid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, genreid));


INSERT INTO animes(name, description, type, yearr, image) VALUES
    ('Anime One','This is the One Anime', 'film', 2020,'Anime1.jpg'),
    ('Anime Two','The Two Anime is the next', 'film', 2020, 'Anime2.jpg'),
    ('Anime Three','The Trilogy', 'film', 2020, 'Anime3.jpg'),
    ('Anime Four','Four Animes is too much', 'film', 2020, 'Anime4.jpg');

--INSERT INTO animes(name, description, type, yearr, image, image2, image3, image4) VALUE
--    ('Anime Five','Five Animes XD', 'film', 2020, 'Anime5_1.jpg', 'Anime5_1.jpg', 'Anime5_1.jpg', 'Anime5_1.jpg');

INSERT INTO authors(name, image) VALUES
    ('Author One','Author1.jpg'),
    ('Author Two','Author2.jpg'),
    ('Author Three','Author3.jpg'),
    ('Author Four','Author4.jpg');

INSERT INTO genres(label, image) VALUES
      ('Genre One','Genre1.jpg'),
      ('Genre Two','Genre2.jpg'),
      ('Genre Three','Genre3.jpg'),
      ('Genre Four','Genre4.jpg');

INSERT INTO anime_author VALUES
    ((SELECT animeid FROM animes WHERE name='Anime One'),(SELECT authorid FROM authors WHERE name='Author One')),
    ((SELECT animeid FROM animes WHERE name='Anime One'),(SELECT authorid FROM authors WHERE name='Author Two')),
    ((SELECT animeid FROM animes WHERE name='Anime Two'),(SELECT authorid FROM authors WHERE name='Author Three')),
    ((SELECT animeid FROM animes WHERE name='Anime Two'),(SELECT authorid FROM authors WHERE name='Author Four')),
    ((SELECT animeid FROM animes WHERE name='Anime Three'),(SELECT authorid FROM authors WHERE name='Author Four')),
    ((SELECT animeid FROM animes WHERE name='Anime Four'),(SELECT authorid FROM authors WHERE name='Author One')),
    ((SELECT animeid FROM animes WHERE name='Anime Four'),(SELECT authorid FROM authors WHERE name='Author Four'));

INSERT INTO anime_genre VALUES
    ((SELECT animeid FROM animes WHERE name='Anime One'),(SELECT genreid FROM genres WHERE label='Genre One')),
    ((SELECT animeid FROM animes WHERE name='Anime One'),(SELECT genreid FROM genres WHERE label='Genre Two')),
    ((SELECT animeid FROM animes WHERE name='Anime Two'),(SELECT genreid FROM genres WHERE label='Genre One')),
    ((SELECT animeid FROM animes WHERE name='Anime Three'),(SELECT genreid FROM genres WHERE label='Genre One')),
    ((SELECT animeid FROM animes WHERE name='Anime Three'),(SELECT genreid FROM genres WHERE label='Genre Two')),
    ((SELECT animeid FROM animes WHERE name='Anime Three'),(SELECT genreid FROM genres WHERE label='Genre Three'));

