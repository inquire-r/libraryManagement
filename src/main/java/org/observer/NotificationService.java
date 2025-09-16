package org.observer;

import org.utils.LoggerUtil;

public class NotificationService implements Observer {

    private String name;

    public NotificationService(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        LoggerUtil.logInfo("Notification for " + name + ": " + message);
    }
}