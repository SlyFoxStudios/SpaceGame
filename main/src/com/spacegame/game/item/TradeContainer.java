package com.spacegame.game.item;

import com.spacegame.game.SolGame;

import java.util.List;

public class TradeContainer {
  private static final float MAX_AWAIT = 180f;

  private final TradeConfig myConfig;
  private final ItemContainer myItems;

  private float myAwait;

  public TradeContainer(TradeConfig config) {
    myConfig = config;
    myItems = new ItemContainer();
  }

  public void update(SolGame game) {
    if (0 < myAwait) {
      myAwait -= game.getTimeStep();
      return;
    }

    myAwait = MAX_AWAIT;
    myItems.clear();
    List<ItemConfig> items = myConfig.items;
    for (int i1 = 0, sz = items.size(); i1 < sz; i1++) {
      ItemConfig i = items.get(i1);
      SolItem ex = i.examples.get(0);
      int amt = ex.isSame(ex) ? 16 : 1;
      for (int j = 0; j < amt; j++) {
        if (myItems.canAdd(ex)) myItems.add(ex.copy());
      }
    }
  }

  public ItemContainer getItems() {
    return myItems;
  }

  public ItemContainer getShips() {
    return myConfig.hulls;
  }

  public ItemContainer getMercs() {
    return myConfig.mercs;
  }
}
