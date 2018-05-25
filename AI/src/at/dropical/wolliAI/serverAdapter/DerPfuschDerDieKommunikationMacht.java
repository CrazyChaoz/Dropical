package at.dropical.wolliAI.serverAdapter;
// Created by julian on 25.05.18.

import at.dropical.client.DropicalProxy;
import at.dropical.client.impl.BestJavaListener;
import at.dropical.shared.net.requests.HandleInputRequest;
import at.dropical.shared.net.requests.JoinRequest;

import java.io.IOException;
import java.util.Scanner;

/**
 * TODO Description
 */
public class DerPfuschDerDieKommunikationMacht {

    private DropicalProxy proxy;
    private String playername;


    public DerPfuschDerDieKommunikationMacht() throws IOException {
        this.proxy = getStandardProxy();
        this.playername = "Wolli AI";
    }

    public void start() {

    }

    public static DropicalProxy getStandardProxy() throws IOException {
        return new DropicalProxy("localhost", 45000, new BestJavaListener());
    }
}
