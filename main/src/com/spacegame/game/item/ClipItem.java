package com.spacegame.game.item;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.spacegame.game.SolGame;

public class ClipItem implements SolItem {
  private final ClipConfig myConfig;

  public ClipItem(ClipConfig config) {
    myConfig = config;
  }

  @Override
  public String getDisplayName() {
    return myConfig.displayName;
  }

  @Override
  public float getPrice() {
    return myConfig.price;
  }

  @Override
  public String getDesc() {
    return myConfig.desc;
  }

  public ClipConfig getConfig() {
    return myConfig;
  }

  @Override
  public SolItem copy() {
    return new com.spacegame.game.item.ClipItem(myConfig);
  }

  @Override
  public boolean isSame(SolItem item) {
    return item instanceof com.spacegame.game.item.ClipItem && ((com.spacegame.game.item.ClipItem) item).myConfig == myConfig;
  }

  @Override
  public TextureAtlas.AtlasRegion getIcon(SolGame game) {
    return myConfig.icon;
  }

  @Override
  public SolItemType getItemType() {
    return myConfig.itemType;
  }

  @Override
  public String getCode() {
    return myConfig.code;
  }
}
