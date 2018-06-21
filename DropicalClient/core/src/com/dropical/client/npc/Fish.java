package com.dropical.client.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Fish extends Sprite {
    private World world;
    private Body body;
    private TextureRegion[][] currentFishTexture;

    private enum State {STAYING, SWIMMING, JUMPING;}
    private State currentState;
    private float time = 0;

    private float x = 0;
    private float y = 0;

    //----------------------------------------

    public Fish(World world, String name, float x, float y) {
        super(new TextureRegion(new Texture(Gdx.files.internal(name))));
        this.world = world;
        setSize(28, 28);

        currentState = State.SWIMMING;
        currentFishTexture = new TextureRegion(getTexture()).split(14, 14);
        setRegion(currentFishTexture[0][0]);

        setPosition(x-getWidth()/2, y-getHeight()/2);
        createBody();
    }
    private void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX()/10, getY()/10);
        body = world.createBody(bodyDef);
        body.setLinearDamping(1f);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2)/10, (getHeight()/2)/10);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.25f;
        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void update() {
        swim();
        detectJump();
        detectFishEscaping();
        setJumpTexture();

        setPosition(body.getPosition().x*10, body.getPosition().y*10);
    }

    //----------------------------------------

    private void swim() {
        //Schwimmrichtung bestimmen (nur zu zufällig generierter Zeit)
        time++;
        int interval = (int) Math.floor(Math.random()*30+30);

        if(time%interval == 0) {
            //returns 0-7 for any swim direction and 8 for staying
            int random = (int) Math.floor(Math.random()*9);
            if(currentState == State.SWIMMING) {
                switch(random) {
                    case 0:
                        swimUp();
                        break;
                    case 1:
                        swimNO();
                        break;
                    case 2:
                        swimRight();
                        break;
                    case 3:
                        swimSO();
                        break;
                    case 4:
                        swimDown();
                        break;
                    case 5:
                        swimSW();
                        break;
                    case 6:
                        swimLeft();
                        break;
                    case 7:
                        swimNW();
                        break;
                } //switch
            } //if swimming
        } //if time

    }
    private void swimUp() {
        body.applyLinearImpulse(0, 10f, body.getPosition().x, body.getPosition().y, true);
        setRegion(currentFishTexture[0][3]);
    }
    private void swimNO() {
        body.applyLinearImpulse(10f, 10f, body.getPosition().x, body.getPosition().y, true);
        setRegion(currentFishTexture[0][4]);
    }
    private void swimRight() {
        body.applyLinearImpulse(10f, 0, body.getPosition().x, body.getPosition().y, true);
        setRegion(currentFishTexture[0][5]);
    }
    private void swimSO() {
        body.applyLinearImpulse(10f, -10f, body.getPosition().x, body.getPosition().y, true);
        setRegion(currentFishTexture[0][6]);
    }
    private void swimDown() {
        body.applyLinearImpulse(0, -10f, body.getPosition().x, body.getPosition().y, true);
        setRegion(currentFishTexture[0][7]);
    }
    private void swimSW() {
        body.applyLinearImpulse(-10f, -10f, body.getPosition().x, body.getPosition().y, true);
        setRegion(currentFishTexture[0][0]);
    }
    private void swimLeft() {
        body.applyLinearImpulse(-10f, 0, body.getPosition().x, body.getPosition().y, true);
        setRegion(currentFishTexture[0][1]);
    }
    private void swimNW() {
        body.applyLinearImpulse(-10f, 10f, body.getPosition().x, body.getPosition().y, true);
        setRegion(currentFishTexture[0][2]);
    }
    private void boostUp() {
        body.applyLinearImpulse(0, 120f, body.getPosition().x, body.getPosition().y, true);
        setRegion(currentFishTexture[0][3]);
    }

    private void detectFishEscaping() {
        if(body.getPosition().x < 0) {
            swimRight();
        }
        if(body.getPosition().x > 128) {
            swimLeft();
        }
        if(body.getPosition().y < 0) {
            boostUp();
        }
    }
    private void detectJump() {
        //wenn Fisch über Wasseroberfläche --> Sprung
        if(body.getPosition().y > 38.4 && currentState == State.SWIMMING) {
            jump();
            currentState = State.JUMPING;
        }
        //solange Fisch springt --> Gravitation aktiv
        if(currentState == State.JUMPING) {
            body.applyForceToCenter(new Vector2(0, -50f), true);
        }
        //wenn Fisch unterwasser --> Schwimmen
        if(body.getPosition().y <= 38.4) {
            currentState = State.SWIMMING;
        }
    }
    private void jump() {
        body.applyLinearImpulse(0, 30f, body.getPosition().x, body.getPosition().y, true);
    }
    private void setJumpTexture() {
        //Sprung-Textur bestimmen
        if(currentState == State.JUMPING) {
            if(body.getLinearVelocity().y > 0 && body.getLinearVelocity().x > 0) {
                setRegion(currentFishTexture[0][4]);
            }
            else if(body.getLinearVelocity().y > 0 && body.getLinearVelocity().x < 0) {
                setRegion(currentFishTexture[0][2]);
            }
            else if(body.getLinearVelocity().y > 0 && body.getLinearVelocity().x == 0) {
                setRegion(currentFishTexture[0][3]);
            }

            else if(body.getLinearVelocity().y < 0 && body.getLinearVelocity().x > 0) {
                setRegion(currentFishTexture[0][6]);
            }
            else if(body.getLinearVelocity().y < 0 && body.getLinearVelocity().x < 0) {
                setRegion(currentFishTexture[0][0]);
            }
            else if(body.getLinearVelocity().y < 0 && body.getLinearVelocity().x == 0) {
                setRegion(currentFishTexture[0][7]);
            }

            else if(body.getLinearVelocity().y == 0 && body.getLinearVelocity().x > 0) {
                setRegion(currentFishTexture[0][5]);
            }
            else if(body.getLinearVelocity().y == 0 && body.getLinearVelocity().x < 0) {
                setRegion(currentFishTexture[0][1]);
            }
            else if(body.getLinearVelocity().y == 0 && body.getLinearVelocity().x == 0) {
                setRegion(currentFishTexture[0][5]);
            }
        }
    }

    //----------------------------------------

    @Override
    public void draw(Batch batch) {
        batch.draw(this, getX()-getWidth()/2, getY()-getHeight()/2, this.getWidth(), this.getHeight());
    }

}
