package com.miloshpetrov.sol2.game.screens;

import com.miloshpetrov.sol2.SolApplication;
import com.miloshpetrov.sol2.common.SolColor;
import com.miloshpetrov.sol2.game.SolGame;
import com.miloshpetrov.sol2.GameOptions;
import com.miloshpetrov.sol2.menu.MenuLayout;
import com.miloshpetrov.sol2.menu.MenuScreens;
import com.miloshpetrov.sol2.ui.*;

import java.util.ArrayList;
import java.util.List;

public class MenuScreen implements SolUiScreen {
  private final List<SolUiControl> myControls;
  private final SolUiControl myCloseCtrl;
  private final SolUiControl myExitCtrl;
  private final SolUiControl myRespawnCtrl;

  private final SolUiControl myOptionsCtrl;

  public MenuScreen(MenuLayout menuLayout, GameOptions gameOptions) {
    myControls = new ArrayList<SolUiControl>();

    myCloseCtrl = new SolUiControl(menuLayout.buttonRect(-1, 1), true, gameOptions.getKeyClose());
    myCloseCtrl.setDisplayName("Resume");
    myControls.add(myCloseCtrl);

    myRespawnCtrl = new SolUiControl(menuLayout.buttonRect(-1, 2), true);
    myRespawnCtrl.setDisplayName("Respawn");
    myControls.add(myRespawnCtrl);

    myOptionsCtrl = new SolUiControl(menuLayout.buttonRect(-1, 3), true);
    myOptionsCtrl.setDisplayName("Options");
    myControls.add(myOptionsCtrl);

    myExitCtrl = new SolUiControl(menuLayout.buttonRect(-1, 4), true);
    myExitCtrl.setDisplayName("Exit");
    myControls.add(myExitCtrl);


  }

  @Override
  public List<SolUiControl> getControls() {
    return myControls;
  }

  @Override
  public void updateCustom(SolApplication cmp, SolInputManager.Ptr[] ptrs, boolean clickedOutside) {
    SolGame g = cmp.getGame();
    g.setPaused(true);
    MenuScreens screens = cmp.getMenuScreens();
    SolInputManager im = cmp.getInputMan();
    GameOptions options = cmp.getOptions();

    if (myRespawnCtrl.isJustOff()) {
      g.respawn();
      g.setPaused(false);
    }
    if (myExitCtrl.isJustOff()) {
      cmp.finishGame();
    }
    if (myOptionsCtrl.isJustOff()) {
      im.setScreen(cmp, screens.gameoptionsScreen);
    }
    if (myCloseCtrl.isJustOff()) {
      g.setPaused(false);
      im.setScreen(cmp, g.getScreens().mainScreen);
    }
  }



  @Override
  public void drawBg(UiDrawer uiDrawer, SolApplication cmp) {
    uiDrawer.draw(uiDrawer.filler, SolColor.UI_BG);
  }

  @Override
  public void drawImgs(UiDrawer uiDrawer, SolApplication cmp) {

  }

  @Override
  public void drawText(UiDrawer uiDrawer, SolApplication cmp) {
  }

  @Override
  public boolean reactsToClickOutside() {
    return false;
  }

  @Override
  public boolean isCursorOnBg(SolInputManager.Ptr ptr) {
    return true;
  }

  @Override
  public void onAdd(SolApplication cmp) {

  }

  @Override
  public void blurCustom(SolApplication cmp) {

  }
}
