package com.dropical.client.managers;

public class SettingsManager {
    private boolean ghostActive = false;

    private static SettingsManager ourInstance = new SettingsManager();
    public static SettingsManager getInstance() {
        return ourInstance;
    }

    private SettingsManager() {

    }

    public boolean isGhostActive() {
        return ghostActive;
    }
    public void setGhostActive(boolean ghostActive) {
        this.ghostActive = ghostActive;
    }

}
