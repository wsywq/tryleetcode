package com.ywq.codedesign.bridge;

import java.util.List;

public class TelephoneSender implements MsgSender {

    private List<String> telephone;

    public TelephoneSender(List<String> telephone) {
        this.telephone = telephone;
    }

    @Override
    public void send(String message) {
        System.out.println("TelephoneSender send message: " + message);
    }
}
