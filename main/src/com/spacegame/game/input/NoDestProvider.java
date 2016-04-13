package com.spacegame.game.input;

import com.badlogic.gdx.math.Vector2;
import com.spacegame.Const;
import com.spacegame.game.SolGame;
import com.spacegame.game.ship.hulls.HullConfig;
import com.spacegame.game.ship.SolShip;

/**
 * Just stays wherever it is, but maneuvers
 */
public class NoDestProvider implements MoveDestProvider {

  public NoDestProvider() {
  }

  @Override
  public Vector2 getDest() {
    return null;
  }

  @Override
  public boolean shouldAvoidBigObjs() {
    return false;
  }

  @Override
  public float getDesiredSpdLen() {
    return Const.DEFAULT_AI_SPD;
  }

  @Override
  public boolean shouldStopNearDest() {
    return false;
  }

  @Override
  public void update(SolGame game, Vector2 shipPos, float maxIdleDist, HullConfig hullConfig, SolShip nearestEnemy) {
  }

  @Override
  public Boolean shouldManeuver(boolean canShoot, SolShip nearestEnemy, boolean nearGround) {
    return null;
  }

  @Override
  public Vector2 getDestSpd() {
    return Vector2.Zero;
  }
}
