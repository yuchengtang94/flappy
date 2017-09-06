package ground;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.Gameinfo;

/**
 * Created by Tang on 2017/7/29.
 */

public class GroundBody {

    private World world;
    private Body body;

    public  GroundBody(World world, Sprite ground){
        this.world = world;
        createGroundBody(ground);
    }

    void createGroundBody(Sprite ground) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(ground.getWidth() / Gameinfo.PPM,
                (-ground.getHeight() / 2f - 55f) / Gameinfo.PPM);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(ground.getWidth() / Gameinfo.PPM, ground.getHeight() / Gameinfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Gameinfo.GROUND;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Ground");

        shape.dispose();

    }


}// ground body
