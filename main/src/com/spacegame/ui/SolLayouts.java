package com.spacegame.ui;

import com.spacegame.game.screens.RightPaneLayout;
import com.spacegame.menu.MenuLayout;

public class SolLayouts {
  public final RightPaneLayout rightPaneLayout;
  public final MenuLayout menuLayout;

  public SolLayouts(float r) {
    rightPaneLayout = new RightPaneLayout(r);
    menuLayout = new MenuLayout(r);
  }
}
