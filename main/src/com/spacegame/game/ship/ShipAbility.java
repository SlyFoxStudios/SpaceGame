package com.spacegame.game.ship;

import com.spacegame.game.AbilityCommonConfig;
import com.spacegame.game.SolGame;

public interface ShipAbility {
  boolean update(SolGame game, SolShip owner, boolean tryToUse);
  public AbilityConfig getConfig();
  AbilityCommonConfig getCommonConfig();
  float getRadius();
}
