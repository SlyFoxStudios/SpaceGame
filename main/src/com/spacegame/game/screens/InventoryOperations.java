package com.spacegame.game.screens;

import com.spacegame.game.SolGame;
import com.spacegame.game.item.ItemContainer;
import com.spacegame.game.item.SolItem;
import com.spacegame.ui.SolUiScreen;

public interface InventoryOperations extends SolUiScreen {
  ItemContainer getItems(SolGame game);
  boolean isUsing(SolGame game, SolItem item);
  float getPriceMul();
  String getHeader();
}
