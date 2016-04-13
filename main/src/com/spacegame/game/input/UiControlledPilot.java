package com.spacegame.game.input;


import com.spacegame.Const;
import com.spacegame.game.Faction;
import com.spacegame.game.SolGame;
import com.spacegame.game.screens.MainScreen;
import com.spacegame.game.ship.FarShip;
import com.spacegame.game.ship.SolShip;

public class UiControlledPilot implements Pilot {

  private final MainScreen myScreen;

  public UiControlledPilot(MainScreen screen) {
    myScreen = screen;
  }

  @Override
  public void update(SolGame game, SolShip ship, SolShip nearestEnemy) {
  }

  @Override
  public boolean isUp() {
    return myScreen.isUp();
  }

  @Override
  public boolean isLeft() {
    return myScreen.isLeft();
  }

  @Override
  public boolean isRight() {
    return myScreen.isRight();
  }

  @Override
  public boolean isShoot() {
    return myScreen.isShoot();
  }

  @Override
  public boolean isShoot2() {
    return myScreen.isShoot2();
  }

  @Override
  public boolean collectsItems() {
    return true;
  }

  @Override
  public boolean isAbility() {
    return myScreen.isAbility();
  }

  @Override
  public Faction getFaction() {
    return Faction.LAANI;
  }

  @Override
  public boolean shootsAtObstacles() {
    return false;
  }

  @Override
  public float getDetectionDist() {
    return Const.AUTO_SHOOT_SPACE; // just for unfixed mounts
  }

  @Override
  public String getMapHint() {
    return "You";
  }

  @Override
  public void updateFar(SolGame game, FarShip farShip) {
  }

  @Override
  public String toDebugString() {
    return "";
  }

  @Override
  public boolean isPlayer() {
    return true;
  }
}
