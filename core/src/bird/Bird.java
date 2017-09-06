package bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameManager;
import helpers.Gameinfo;

/**
 * Created by Tang on 2017/7/29.
 */

public class Bird extends Sprite{

    private World world;
    private Body body;

    private boolean isAlive;

    private Texture birdDead;

    private TextureAtlas birdAtlas;
    private Animation animation;
    private float elapsedTime;

    public Bird(World world, float x, float y){
        super(new Texture("Birds/" +
                GameManager.getInstance().getBird() + "/Idle.png"));
        birdDead = new Texture("Birds/" +
                GameManager.getInstance().getBird() + "/Dead.png");
        this.world = world;
        setPosition(x, y);
        createBody();
        createAnimation();
    }

    void createBody() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX() / Gameinfo.PPM, getY() / Gameinfo.PPM);

        body = world.createBody(bodyDef);
        body.setFixedRotation(false);

        CircleShape shape = new CircleShape();
        shape.setRadius((getHeight() / 2) / Gameinfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.filter.categoryBits = Gameinfo.BIRD;
        fixtureDef.filter.maskBits = Gameinfo.GROUND | Gameinfo.PIPE | Gameinfo.SCORE;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Bird");

        shape.dispose();

        body.setActive(false);
    }

    public void birdFlap() {
        body.setLinearVelocity(0, 3);
    }

    public void activeBird() {
        isAlive = true;
        body.setActive(true);
    }

    public void drawIdle(SpriteBatch batch){
        if(!isAlive){
            batch.draw(this, getX() - getWidth() / 2f,
                    getY() - getHeight() / 2f );
        }
    }

    public void animateBird(SpriteBatch batch) {
        if(isAlive) {
            elapsedTime += Gdx.graphics.getDeltaTime();

            batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true),
                    getX() - getWidth() / 2f, getY() - getHeight() / 2f);
        }
    }

    public void updateBird(){
        setPosition(body.getPosition().x * Gameinfo.PPM,
                body.getPosition().y * Gameinfo.PPM);
    }

    void createAnimation() {
        birdAtlas = new TextureAtlas("Birds/" + GameManager.getInstance().getBird() +
                "/" + GameManager.getInstance().getBird() + " Bird.atlas");
        animation = new Animation(1f / 7f, birdAtlas.getRegions());
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getAlive() {
        return isAlive;
    }

    public void birdDied() {
        this.setTexture(birdDead);
    }
}




















