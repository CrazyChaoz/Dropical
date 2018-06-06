package com.dropical.client.managers;

import at.dropical.client.DropicalListener;
import at.dropical.client.DropicalProxy;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.container.GameOverContainer;
import at.dropical.shared.net.container.ListDataContainer;

import java.io.IOException;

public class DataManager implements DropicalListener {
    private DropicalProxy proxy;

    private CountDownContainer countDownContainer;
    private GameDataContainer gameData;
    private ListDataContainer listData;
    private GameOverContainer gameOverContainer;

    private static DataManager ourInstance = new DataManager();
    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() { }

    public void createProxy() {
        try {
            proxy = new DropicalProxy("localhost", 45000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void countDown(CountDownContainer container) {
        countDownContainer = container;
    }

    @Override
    public void updateUI(GameDataContainer container) {
        gameData = container;
    }

    @Override
    public void somebodyJoinedTheLobby(ListDataContainer container) {
        listData = container;
    }

    @Override
    public void onGameOver(GameOverContainer container) {
        gameOverContainer = container;
    }

    public DropicalProxy getProxy() {
        return proxy;
    }
    public void setProxy(DropicalProxy proxy) {
        this.proxy = proxy;
    }
    public CountDownContainer getCountDownContainer() {
        return countDownContainer;
    }
    public void setCountDownContainer(CountDownContainer countDownContainer) {
        this.countDownContainer = countDownContainer;
    }
    public GameDataContainer getGameData() {
        return gameData;
    }
    public void setGameData(GameDataContainer gameData) {
        this.gameData = gameData;
    }
    public ListDataContainer getListData() {
        return listData;
    }
    public void setListData(ListDataContainer listData) {
        this.listData = listData;
    }
    public GameOverContainer getGameOverContainer() {
        return gameOverContainer;
    }
    public void setGameOverContainer(GameOverContainer gameOverContainer) {
        this.gameOverContainer = gameOverContainer;
    }
}