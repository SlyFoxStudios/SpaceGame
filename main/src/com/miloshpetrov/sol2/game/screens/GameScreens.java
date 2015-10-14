package com.miloshpetrov.sol2.game.screens;

import com.miloshpetrov.sol2.SolApplication;
import com.miloshpetrov.sol2.menu.ControlOptions;
import com.miloshpetrov.sol2.menu.GameOptionsScreen;
import com.miloshpetrov.sol2.ui.SolLayouts;

public class GameScreens {
  public final MainScreen mainScreen;
  public final MapScreen mapScreen;
  public final MenuScreen menuScreen;
  public final InventoryScreen inventoryScreen;
  public final TalkScreen talkScreen;
  public final ControlOptions controloptionsScreen;

  public GameScreens(float r, SolApplication cmp) {
    SolLayouts layouts = cmp.getLayouts();
    RightPaneLayout rightPaneLayout = layouts.rightPaneLayout;
    mainScreen = new MainScreen(r, rightPaneLayout, cmp);
    mapScreen = new MapScreen(rightPaneLayout, cmp.isMobile(), r, cmp.getOptions());
    menuScreen = new MenuScreen(layouts.menuLayout, cmp.getOptions());
    inventoryScreen = new InventoryScreen(r, cmp.getOptions());
    talkScreen = new TalkScreen(layouts.menuLayout, cmp.getOptions());
    controloptionsScreen = new ControlOptions(layouts.menuLayout, cmp.getOptions());
  }


}
