package com.spacegame.game.screens;

import com.badlogic.gdx.math.Vector2;
import com.spacegame.Const;
import com.spacegame.game.SolGame;
import com.spacegame.game.ship.SolShip;

public class SunWarnDrawer extends WarnDrawer {

  public SunWarnDrawer(float r) {
    super(r, "Sun Near");
  }

  public boolean shouldWarn(SolGame game) {
    SolShip hero = game.getHero();
    if (hero == null) return false;
    Vector2 pos = hero.getPos();
    float toCenter = game.getPlanetMan().getNearestSystem(pos).getPos().dst(pos);
    return toCenter < Const.SUN_RADIUS;
  }
}
