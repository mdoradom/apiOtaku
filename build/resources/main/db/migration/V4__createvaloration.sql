CREATE TABLE valoration (
    userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
    animeid uuid REFERENCES animes(animeid) ON DELETE CASCADE,
    rating numeric,
    PRIMARY KEY (userid, animeid));

INSERT INTO valoration VALUES
    ((SELECT userid FROM usser WHERE username = 'user'),(SELECT animeid FROM animes WHERE name='Anime One'), 5.5);