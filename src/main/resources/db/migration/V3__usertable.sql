CREATE TABLE usser (
  userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
  username varchar(24) NOT NULL UNIQUE,
  password varchar(255) NOT NULL,
  role varchar(10),
  enabled boolean DEFAULT true
);



-- afegim un usuari de prova
-- CREATE EXTENSION pgcrypto;
CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO usser (username, password) VALUES ('user', crypt('pass', gen_salt('bf')));
INSERT INTO usser (username, password) VALUES ('user2', crypt('pass2', gen_salt('bf')));


CREATE TABLE favorite (
    userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
    animeid uuid REFERENCES animes(animeid) ON DELETE CASCADE,
    PRIMARY KEY (userid, animeid));

INSERT INTO favorite VALUES
    ((SELECT userid FROM usser WHERE username = 'user'),(SELECT animeid FROM animes WHERE name='Anime One'));
