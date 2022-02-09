package com.example.demo.domain.model.compositekeys;

import java.io.Serializable;
import java.util.UUID;

public class SenderReceiverKey implements Serializable {
    public UUID messageid;
    public UUID sender;
    public UUID receiver;
}
