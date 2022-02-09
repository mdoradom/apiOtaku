CREATE TABLE messages(
    messageid uuid NOT NULL DEFAULT gen_random_uuid(),
    sender uuid REFERENCES usser(userid) ON DELETE CASCADE,
    receiver uuid REFERENCES usser(userid) ON DELETE CASCADE,
    message text,
    PRIMARY KEY(messageid,sender,receiver)
);