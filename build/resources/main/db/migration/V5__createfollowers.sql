CREATE TABLE followers(
    userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
    followed uuid REFERENCES usser(userid) ON DELETE CASCADE,
    PRIMARY KEY (userid)
);