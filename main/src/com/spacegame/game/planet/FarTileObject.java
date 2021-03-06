package com.spacegame.game.planet;

import com.badlogic.gdx.math.Vector2;
import com.spacegame.common.SolMath;
import com.spacegame.game.*;

public class FarTileObject implements FarObj {
  private final Planet myPlanet;
  private final float myToPlanetAngle;
  private final float myDist;
  private final float mySize;
  private final Tile myTile;
  private final Vector2 myPos;
  private final float myRadius;

  public FarTileObject(Planet planet, float toPlanetAngle, float dist, float size, Tile tile) {
    myPlanet = planet;
    myToPlanetAngle = toPlanetAngle;
    myDist = dist;
    mySize = size;
    myRadius = SolMath.sqrt(2) * mySize;
    myTile = tile;
    myPos = new Vector2();
  }

  @Override
  public boolean shouldBeRemoved(SolGame game) {
    return false;
  }

  @Override
  public SolObject toObj(SolGame game) {
    return new TileObjBuilder().build(game, mySize, myToPlanetAngle, myDist, myTile, myPlanet);
  }

  @Override
  public void update(SolGame game) {
    if (game.getPlanetMan().getNearestPlanet() == myPlanet) {
      SolMath.fromAl(myPos, myPlanet.getAngle() + myToPlanetAngle, myDist);
      myPos.add(myPlanet.getPos());
    }
  }

  @Override
  public float getRadius() {
    return myRadius;
  }

  @Override
  public Vector2 getPos() {
    return myPos;
  }

  @Override
  public String toDebugString() {
    return null;
  }

  @Override
  public boolean hasBody() {
    return true;
  }

  public float getAngle() {
    return myPlanet.getAngle() + myToPlanetAngle + 90;
  }

  public Planet getPlanet() {
    return myPlanet;
  }


  public float getSz() {
    return mySize;
  }

  public Tile getTile() {
    return myTile;
  }
}
