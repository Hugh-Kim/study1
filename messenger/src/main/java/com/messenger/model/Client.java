package com.messenger.model;

/**
 * Created by Administrator on 2014-06-02.
 */
public class Client {
    private String clientId;
    private Message message;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
