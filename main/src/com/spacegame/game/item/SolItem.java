package com.spacegame.game.item;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.spacegame.game.SolGame;

public interface SolItem {
  String getDisplayName();
  float getPrice();
  String getDesc();
  SolItem copy();
  boolean isSame(SolItem item);
  TextureAtlas.AtlasRegion getIcon(SolGame game);
  SolItemType getItemType();
  String getCode();
}
