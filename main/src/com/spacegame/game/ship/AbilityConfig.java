package com.spacegame.game.ship;

import com.spacegame.game.item.SolItem;

public interface AbilityConfig {
  public ShipAbility build();
  public SolItem getChargeExample();
  public float getRechargeTime();
  void appendDesc(StringBuilder sb);
}
