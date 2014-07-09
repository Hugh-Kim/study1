package com.messenger.model;

/**
 * Created by Administrator on 2014-06-02.
 */
public class Client {
    private String clientId;
    private int chatRoomPort;
    private Message message;

    public Client(String userName, int chatRoomPort) {
        this.clientId = userName;
        this.chatRoomPort = chatRoomPort;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getChatRoomPort() {
        return chatRoomPort;
    }

    public void setChatRoomPort(int chatRoomPort) {
        this.chatRoomPort = chatRoomPort;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
