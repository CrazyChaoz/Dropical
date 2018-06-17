package com.dropical.client.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Fish {
    private Sprite fish;
    private int x = 0;
    private int y = 0;
    private int directionDegrees = 0;
    Actor actor = new Actor();

    public Fish() {
        fish = new Sprite(new Texture(Gdx.files.internal("Fish/fish.png")));
        fish.setSize(28, 28);
        fish.setPosition(0, 0);
        fish.setOrigin(28, 28);
        fish.setRotation(0);
    }

    public void followCursor() {
        float proportionX = 1;
        float proportionY = 1;
        float cursorX = Gdx.input.getX();
        float cursorY = Gdx.input.getY();
        cursorY = 720-cursorY;

        if(Math.abs(cursorX-x) != 0 && Math.abs(cursorY-y) != 0) {
            proportionX = Math.abs(cursorX-x)/Math.abs(cursorY-y);
            proportionY = Math.abs(cursorY-y)/Math.abs(cursorX-x);
            if(proportionX > 3) {
                proportionX = 3;
            }
            if(proportionY > 3) {
                proportionY = 3;
            }
        }

        if(cursorX > x) {
            x += proportionX;
        }
        else if(cursorX < x){
            x -= proportionX;
        }

        if(cursorY < y) {
            y -= proportionY;
        }
        else if(cursorY > y){
            y += proportionY;
        }
    }

    public void update(int time) {


        fish.setPosition(x, y);
    }



    public void draw(Batch batch) {
        fish.draw(batch);
    }

}
