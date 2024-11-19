package com.ywq.codedesign.bridge;

public class ServerNotification extends Notification{
    public ServerNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {
        msgSender.send(message);
    }

}
