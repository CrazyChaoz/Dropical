package com.dropical.client.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Fish extends Sprite {
    private World world;
    private Body body;

//    private Sprite fish;
    private float x = 0;
    private float y = 0;
    private int directionDegrees = 0;

//    public Fish() {
//        fish = new Sprite(new Texture(Gdx.files.internal("Fish/fish.png")));
//        fish.setSize(28, 28);
//        fish.setPosition(0, 0);
//        fish.setOrigin(28, 28);
//        fish.setRotation(0);
//    }

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

//    public void draw(Batch batch) {
//        fish.draw(batch);
//    }

    public Fish(World world, String name, float x, float y) {
        super(new Texture(Gdx.files.internal(name)));
        this.world = world;
        setPosition(x-getWidth()/2, y-getHeight()/2);
        createBody();
    }

    private void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth()/2, getHeight()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void update() {
        this.setPosition(body.getPosition().x, body.getPosition().y);
    }

}
