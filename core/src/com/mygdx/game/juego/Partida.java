package com.mygdx.game.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MainMenuScreen;
import com.mygdx.game.Puntuaciones;
import com.mygdx.game.utils.*;
import com.mygdx.game.Assets;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens;


public class Partida extends Screens {

    //Sistema de puntuación (lo uso en el método update)
    Puntuaciones puntuaciones = new Puntuaciones();



    //Indica si aún no he perdido.
    Boolean jugando = true;



    //Sonidos.
    static Music sonido4;
    Music derrota;



    //Variables de puntuación actual y mensajes.
    static int puntos = 0, cont = 0;
    private final static int MAX = 6;
    private int res;
    private String mensaje;
    Box2DDebugRenderer renderer;
    World oWorld;



    //Arrays de objetos en juego.
    Array<Body> arrBodies;
    Array<GameObject> arrGameObjects;



    // Imágenes de los objetos.
    TextureRegion background, ball, java, zombi, android, juan, chris;
    Animation<TextureRegion> explosion;



    public Partida(MyGdxGame game) {
        super(game);

        //Indico con que objeto se comienza.
        this.res = MathUtils.random(4);
        mostrarSiguiente();


        //Declaro el sonido de explosión y le asigno un volumen.
        sonido4 = Gdx.audio.newMusic(Gdx.files.internal("sounds/explosion_sound.wav"));
        sonido4.setVolume(0.06f);
        //Declaro el sonido que aparece cuando pierdes.
        derrota = Gdx.audio.newMusic(Gdx.files.internal("sounds/derrota_sound.wav"));
        //Asigno el fondo de escenario.
        background = new TextureRegion(new Texture(Gdx.files.internal("fondo.png")));



        //Asigno características a mi mundo.
        Vector2 gravity = new Vector2(0, -9.8f);
        oWorld = new World(gravity, true);
        oWorld.setContactListener(new CollisionListener());

        renderer = new Box2DDebugRenderer();
        arrBodies = new Array<>();
        arrGameObjects = new Array<>();



        //Cargo las imágenes.
        ball = Utils.getRegion("ball.png");
        java = Utils.getRegion("java.png");
        zombi = Utils.getRegion("zombi.png");
        android = Utils.getRegion("android.png");
        juan = Utils.getRegion("juan.png");
        chris = Utils.getRegion("chris.png");

        TextureRegion exp1 = Utils.getRegion("explosion/explosion1.png");
        TextureRegion exp2 = Utils.getRegion("explosion/explosion2.png");
        TextureRegion exp3 = Utils.getRegion("explosion/explosion3.png");
        TextureRegion exp4 = Utils.getRegion("explosion/explosion4.png");
        TextureRegion exp5 = Utils.getRegion("explosion/explosion5.png");
        TextureRegion exp6 = Utils.getRegion("explosion/explosion6.png");
        TextureRegion exp7 = Utils.getRegion("explosion/explosion7.png");
        TextureRegion exp8 = Utils.getRegion("explosion/explosion8.png");
        TextureRegion exp9 = Utils.getRegion("explosion/explosion9.png");
        TextureRegion exp10 = Utils.getRegion("explosion/explosion10.png");
        TextureRegion exp11 = Utils.getRegion("explosion/explosion11.png");
        TextureRegion exp12 = Utils.getRegion("explosion/explosion12.png");
        TextureRegion exp13 = Utils.getRegion("explosion/explosion13.png");
        TextureRegion exp14 = Utils.getRegion("explosion/explosion14.png");
        TextureRegion exp15 = Utils.getRegion("explosion/explosion15.png");
        TextureRegion exp16 = Utils.getRegion("explosion/explosion16.png");
        TextureRegion exp17 = Utils.getRegion("explosion/explosion17.png");
        TextureRegion exp18 = Utils.getRegion("explosion/explosion18.png");
        TextureRegion exp19 = Utils.getRegion("explosion/explosion19.png");

        //Creo la animación de la explosión.
        explosion = new Animation<>(0.05f, exp1, exp2, exp3,
                exp4, exp5, exp6, exp7, exp8, exp9,
                exp10, exp11, exp12, exp13, exp14,
                exp15, exp16, exp17, exp18, exp19);



        //Creo el suelo y los muros.
        createFloor();
        createLeftWall();
        createRightWall();
    }



    //CREACIÓN DE LOS MUROS
    private void createFloor() {
        BodyDef bd = new BodyDef();
        bd.position.set(0, .6f);
        bd.type = BodyType.StaticBody;

        EdgeShape shape = new EdgeShape();
        shape.set(0, 0, WORLD_WIDTH + 20, 0);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.friction = .7f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        shape.dispose();
    }

    private void createLeftWall() {
        BodyDef bd = new BodyDef();
        bd.position.set(0, .6f);
        bd.type = BodyType.StaticBody;

        EdgeShape shape = new EdgeShape();
        shape.set(0, 0, 0, WORLD_HEIGHT + 20);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.friction = .7f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        shape.dispose();
    }

    private void createRightWall() {
        BodyDef bd = new BodyDef();
        bd.position.set(WORLD_WIDTH, 0);
        bd.type = BodyType.StaticBody;

        EdgeShape shape = new EdgeShape();
        shape.set(0, 0, 0, WORLD_HEIGHT);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.friction = .7f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        shape.dispose();
    }



    //CREACIÓN DE LOS OBJETOS
    private void createBall(float x, float y) {
        GameObject obj = new GameObject(x, 12.8f, GameObject.BALL);

        BodyDef bd = new BodyDef();
        bd.position.x = obj.position.x;
        bd.position.y = obj.position.y;
        bd.type = BodyType.DynamicBody;

        CircleShape shape = new CircleShape();
        shape.setRadius(.15f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 15;
        fixDef.friction = .5f;
        fixDef.restitution = .5f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        oBody.setUserData(obj);
        arrGameObjects.add(obj);

        shape.dispose();
        cont++;
        this.res = MathUtils.random(5);
    }

    private void createChris(float x, float y) {
        GameObject obj = new GameObject(x, 12.8f, GameObject.CHRIS);

        BodyDef bd = new BodyDef();
        bd.position.x = obj.position.x;
        bd.position.y = obj.position.y;
        bd.type = BodyType.DynamicBody;

        CircleShape shape = new CircleShape();
        shape.setRadius(.15f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 8;
        fixDef.friction = .3f;
        fixDef.restitution = .5f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        oBody.setUserData(obj);
        arrGameObjects.add(obj);

        shape.dispose();
        cont++;
        this.res = MathUtils.random(5);
    }

    private void createJava(float x, float y) {
        GameObject obj = new GameObject(x, 12.8f, GameObject.JAVA);

        BodyDef bd = new BodyDef();
        bd.position.x = obj.position.x;
        bd.position.y = obj.position.y;
        bd.type = BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();
        // Ajustar el tamaño del objeto de colisión
        shape.setAsBox(.3f, .3f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 15;
        fixDef.friction = .5f;
        fixDef.restitution = .1f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        oBody.setUserData(obj);
        arrGameObjects.add(obj);

        shape.dispose();
        cont++;

        this.res = MathUtils.random(5);
    }

    private void createZombi(float x, float y) {
        GameObject obj = new GameObject(x, 12.8f, GameObject.ZOMBI);

        BodyDef bd = new BodyDef();
        bd.position.x = obj.position.x;
        bd.position.y = obj.position.y;
        bd.type = BodyType.DynamicBody;

        CircleShape shape = new CircleShape();
        shape.setRadius(.1f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 15;
        fixDef.friction = .5f;
        fixDef.restitution = .5f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        oBody.setUserData(obj);
        arrGameObjects.add(obj);

        shape.dispose();
        cont++;

        this.res = MathUtils.random(5);
    }

    private void createAndroid(float x, float y) {
        GameObject obj = new GameObject(x, 12.8f, GameObject.ANDROID);

        BodyDef bd = new BodyDef();
        bd.position.x = obj.position.x;
        bd.position.y = obj.position.y;
        bd.type = BodyType.DynamicBody;

        CircleShape shape = new CircleShape();
        // Aumentar el radio del círculo para hacerlo más grande
        shape.setRadius(.3f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 15;
        fixDef.friction = .5f;
        fixDef.restitution = .5f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        oBody.setUserData(obj);
        arrGameObjects.add(obj);

        shape.dispose();
        cont++;

        this.res = MathUtils.random(5);
    }

    private void createJuan(float x, float y) {
        GameObject obj = new GameObject(x, 12.8f, GameObject.JUAN);

        BodyDef bd = new BodyDef();
        bd.position.x = obj.position.x;
        bd.position.y = obj.position.y;
        bd.type = BodyType.DynamicBody;

        CircleShape shape = new CircleShape();
        shape.setRadius(.6f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 30;
        fixDef.friction = .7f;
        fixDef.restitution = .5f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        oBody.setUserData(obj);
        arrGameObjects.add(obj);

        shape.dispose();
        cont++;

        this.res = MathUtils.random(5);
    }



    //Lo uso para mostrar un mensaje del siguiente objeto.
    private void mostrarSiguiente(){
        if (res==0) mensaje = "Pelota";
        else if (res==1) mensaje = "Zombi";
        else if (res ==2) mensaje = "Java";
        else if (res ==3) mensaje = "Android";
        else if (res ==4) mensaje = "J.Carlos";
        else mensaje = "Chris";
    }




    @Override
    public void update(float delta) {

        if (cont <= MAX){
            if (Gdx.input.justTouched()) {

                Vector3 touchPos = new Vector3(Gdx.input.getX(), 5, 0);
                oCamBox2D.unproject(touchPos);

                if (res==0) createBall(touchPos.x, touchPos.y);
                else if (res==1) createZombi(touchPos.x,touchPos.y);
                else if (res ==2) createJava(touchPos.x, touchPos.y);
                else if (res ==3) createAndroid(touchPos.x, touchPos.y);
                else if (res == 4) createJuan(touchPos.x, touchPos.y);
                else createChris(touchPos.x, touchPos.y);

                puntuaciones.saveHighScore(puntos);

            }

            mostrarSiguiente();

            oWorld.step(delta, 8, 6);
            oWorld.getBodies(arrBodies);

            // Eliminate objects that have been hit
            for (Body body : arrBodies) {

                // If the world is locked do not do anything to this body
                if (oWorld.isLocked()) continue;

                if (body.getUserData() instanceof GameObject) {
                    GameObject obj = (GameObject) body.getUserData();
                    if (obj.state == GameObject.STATE_REMOVE) {
                        arrGameObjects.removeValue(obj, true);
                        oWorld.destroyBody(body);
                    }
                }
            }

            oWorld.getBodies(arrBodies);
            for (Body body : arrBodies) {
                if (body.getUserData() instanceof GameObject) {
                    GameObject obj = (GameObject) body.getUserData();
                    obj.update(body, delta);
                }
            }
        }

    }

    @Override
    public void draw(float delta) throws InterruptedException {

        oCamUI.update();
        spriteBatch.setProjectionMatrix(oCamUI.combined);

        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        if (cont>MAX && jugando==true){
            derrota.play();
            jugando=false;
            spriteBatch.begin();
            Assets.font.draw(spriteBatch, "HAS PERDIDO, Puntos: " + puntos, 100, SCREEN_HEIGHT/2);
            Assets.font.setColor(Color.BROWN);
            float scaleFactor = 5f;
            Assets.font.getData().setScale(scaleFactor);
            spriteBatch.end();
        }
        else if (jugando==false){
            Thread.sleep(5000);
            jugando = true;
            puntos = 0;
            cont = 0;
            MainMenuScreen mainMenuScreen = new MainMenuScreen(game);
            game.setScreen(mainMenuScreen);
        }
        else{
            spriteBatch.begin();
            Assets.font.draw(spriteBatch, "Puntos: " + puntos, SCREEN_WIDTH - 500, SCREEN_HEIGHT - 50);
            Assets.font.setColor(Color.BROWN);
            float scaleFactor = 5f;
            Assets.font.getData().setScale(scaleFactor);
            spriteBatch.end();

            spriteBatch.begin();
            Assets.font.draw(spriteBatch, cont + "/" + MAX, SCREEN_WIDTH - 500, SCREEN_HEIGHT - 150);
            Assets.font.setColor(Color.BROWN);
            Assets.font.getData().setScale(scaleFactor);
            spriteBatch.end();

            spriteBatch.begin();
            Assets.font.draw(spriteBatch, "Next: " + mensaje, SCREEN_WIDTH - 500, SCREEN_HEIGHT - 250);
            Assets.font.setColor(Color.BROWN);
            Assets.font.getData().setScale(scaleFactor);
            spriteBatch.end();
        }

        oCamBox2D.update();

        spriteBatch.setProjectionMatrix(oCamBox2D.combined);
        spriteBatch.begin();

        drawGameObjects();

        spriteBatch.end();
        renderer.render(oWorld, oCamBox2D.combined);

    }

    private void drawGameObjects() {
        for (GameObject obj : arrGameObjects) {
            TextureRegion keyframe;

            if (obj.state == GameObject.STATE_NORMAL) {
                if (obj.type == GameObject.ZOMBI) {
                    // Ajustar el tamaño del zombi para que sea más grande
                    keyframe = zombi;
                    spriteBatch.draw(keyframe, obj.position.x - .1f, obj.position.y - .1f, .1f, .1f,
                            .2f, .2f, 1, 1, obj.angleDeg);
                }
                else if (obj.type == GameObject.JAVA){
                    keyframe = java;
                    spriteBatch.draw(keyframe, obj.position.x - .3f, obj.position.y - .3f, .3f, .3f,
                            .6f, .6f, 1, 1, obj.angleDeg);
                }
                else if (obj.type == GameObject.ANDROID){
                    keyframe = android;
                    spriteBatch.draw(keyframe, obj.position.x - .3f, obj.position.y - .3f, .3f, .3f,
                            .6f, .6f, 1, 1, obj.angleDeg);
                }
                else if (obj.type == GameObject.JUAN){
                    keyframe = juan;
                    spriteBatch.draw(keyframe, obj.position.x - .6f, obj.position.y - .6f, .6f, .6f,
                            1.2f, 1.2f, 1, 1, obj.angleDeg);
                }
                else if (obj.type == GameObject.CHRIS){
                    keyframe = chris;
                    spriteBatch.draw(keyframe, obj.position.x - .15f, obj.position.y - .15f, .15f, .15f,
                            .3f, .3f, 1, 1, obj.angleDeg);
                }
                else {
                    keyframe = ball;
                    spriteBatch.draw(keyframe, obj.position.x - .15f, obj.position.y - .15f, .15f, .15f,
                            .3f, .3f, 1, 1, obj.angleDeg);
                }

            }



            if (obj.state == GameObject.STATE_EXPLODE) {
                spriteBatch.draw(explosion.getKeyFrame(obj.stateTime), obj.position.x - .15f, obj.position.y - .15f,
                        .15f, .15f, .3f, .3f, 1, 1, obj.angleDeg);
            }
        }
    }

    @Override
    public void dispose() {
        oWorld.dispose();
        super.dispose();
    }



}
