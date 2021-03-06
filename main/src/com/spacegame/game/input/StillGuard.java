package com.spacegame.game.input;

import com.badlogic.gdx.math.Vector2;
import com.spacegame.Const;
import com.spacegame.common.SolMath;
import com.spacegame.game.ShipConfig;
import com.spacegame.game.SolGame;
import com.spacegame.game.planet.PlanetBind;
import com.spacegame.game.ship.hulls.HullConfig;
import com.spacegame.game.ship.SolShip;

public class StillGuard implements MoveDestProvider {

  private final PlanetBind myPlanetBind;
  private final float myDesiredSpdLen;
  private Vector2 myDest;
  private Vector2 myDestSpd;

  public StillGuard(Vector2 target, SolGame game, ShipConfig sc) {
    myDest = new Vector2(target);
    myPlanetBind = PlanetBind.tryBind(game, myDest, 0);
    myDesiredSpdLen = sc.hull.getType() == HullConfig.Type.BIG ? Const.BIG_AI_SPD : Const.DEFAULT_AI_SPD;
    myDestSpd = new Vector2();
  }

  @Override
  public Vector2 getDest() {
    return myDest;
  }

  @Override
  public boolean shouldAvoidBigObjs() {
    return myPlanetBind != null;
  }

  @Override
  public float getDesiredSpdLen() {
    return myDesiredSpdLen;
  }

  @Override
  public boolean shouldStopNearDest() {
    return true;
  }

  @Override
  public void update(SolGame game, Vector2 shipPos, float maxIdleDist, HullConfig hullConfig, SolShip nearestEnemy) {
    if (myPlanetBind != null) {
      Vector2 diff = SolMath.getVec();
      myPlanetBind.setDiff(diff, myDest, false);
      myDest.add(diff);
      SolMath.free(diff);
      myPlanetBind.getPlanet().calcSpdAtPos(myDestSpd, myDest);
    }
  }

  @Override
  public Boolean shouldManeuver(boolean canShoot, SolShip nearestEnemy, boolean nearGround) {
    return true;
  }

  @Override
  public Vector2 getDestSpd() {
    return myDestSpd;
  }
}
