package org.taskmates.notification_service.model;

import org.taskmates.notification_service.model.enums.ChannelsType;

public class Notification {
    private String message;
    private ChannelsType channel;

    public Notification(String message, ChannelsType channel) {
        this.message = message;
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChannelsType getChannel() {
        return channel;
    }

    public void setChannel(ChannelsType channel) {
        this.channel = channel;
    }
}
