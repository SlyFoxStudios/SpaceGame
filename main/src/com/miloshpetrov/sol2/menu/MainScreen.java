package com.miloshpetrov.sol2.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.miloshpetrov.sol2.GameOptions;
import com.miloshpetrov.sol2.SolApplication;
import com.miloshpetrov.sol2.TextureManager;
import com.miloshpetrov.sol2.common.SolColor;
import com.miloshpetrov.sol2.common.SolMath;
import com.miloshpetrov.sol2.files.FileManager;
import com.miloshpetrov.sol2.game.DebugOptions;

import com.miloshpetrov.sol2.ui.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainScreen implements SolUiScreen {
  public static final float CREDITS_BTN_W = .15f;
  public static final float CREDITS_BTN_H = .07f;

  private final ArrayList<SolUiControl> myControls;
  //private final SolUiControl myTutCtrl;
  private final SolUiControl myOptionsCtrl;
  private final SolUiControl myExitCtrl;
 // private final SolUiControl myNewGameCtrl;
  private final SolUiControl myCreditsCtrl;
  private final SolUiControl myPlayGameCtrl;
  private final SolUiControl myCommunityCtrl;

  private boolean isChristmas;
  private boolean isNewYears;
  private boolean isEaster;
  public final TextureAtlas.AtlasRegion myNebTex;
    //Logos
    public final TextureAtlas.AtlasRegion normalLogo;
  public final TextureAtlas.AtlasRegion christmasLogo;
 // private final TextureAtlas.AtlasRegion myTitleTex;


  private final boolean isMobile;

  public MainScreen(MenuLayout menuLayout, TextureManager textureManager, boolean mobile, float r, GameOptions gameOptions) {
    isMobile = mobile;
    myControls = new ArrayList<SolUiControl>();

/**START DATE VARIABLES**/
    Calendar var1 = Calendar.getInstance();

    if (var1.get(2) + 1 == 12 && var1.get(3) >= 28 && var1.get(5) <= 28)
    {
      this.isChristmas = true;
    }


      /**END DATE VARIABLES**/
      /**START MENU BUTTONS**/
    myPlayGameCtrl = new SolUiControl(menuLayout.buttonRect(-1, 0), true, Input.Keys.P);
    myPlayGameCtrl.setDisplayName("Play");
    myControls.add(myPlayGameCtrl);

    myOptionsCtrl = new SolUiControl(mobile ? null : menuLayout.buttonRect(-1, 1), true, Input.Keys.O);
    myOptionsCtrl.setDisplayName("Options");
    myControls.add(myOptionsCtrl);

    myCommunityCtrl = new SolUiControl(menuLayout.buttonRect(-1, 2), true, Input.Keys.M);
    myCommunityCtrl.setDisplayName("Extras");
    myControls.add(myCommunityCtrl);

    myCreditsCtrl = new SolUiControl(menuLayout.buttonRect(-1, 3), true, Input.Keys.C);
    myCreditsCtrl.setDisplayName("Credits");
    myControls.add(myCreditsCtrl);


    myExitCtrl = new SolUiControl(menuLayout.buttonRect(-1, 4), true, gameOptions.getKeyEscape());
    myExitCtrl.setDisplayName("Exit");
    myControls.add(myExitCtrl);
      /**END MENU BUTTONS**/

      /**START LOGO IMPORT FOR DATE VARIABLES**/
      //Normal Logo
        FileHandle imageFile = FileManager.getInstance().getImagesDirectory().child("normal.png");
        normalLogo = textureManager.getTexture(imageFile);
      //Christmas Logo
          FileHandle imageFile1 = FileManager.getInstance().getImagesDirectory().child("christmas.png");
          christmasLogo = textureManager.getTexture(imageFile1);
      /**END LOGO IMPORT**/


     // myTitleTex = textureManager.getTex("ui/title", SolMath.test(.5f), null);
      myNebTex = textureManager.getTex("farBgBig/nebulae2", SolMath.test(.5f), null);


  }

  public static Rectangle creditsBtnRect(float r) {
    return new Rectangle(r - CREDITS_BTN_W, 1 - CREDITS_BTN_H, CREDITS_BTN_W, CREDITS_BTN_H);
  }

  public List<SolUiControl> getControls() {
    return myControls;
  }

  @Override
  public void updateCustom(SolApplication cmp, SolInputManager.Ptr[] ptrs, boolean clickedOutside) {
    SolInputManager im = cmp.getInputMan();
    MenuScreens screens = cmp.getMenuScreens();


    //What to do when NewGame is pressed
    if(myCommunityCtrl.isJustOff()) {
      im.setScreen(cmp, screens.extrasScreen);
      return;
    }


      //What to do when Play Button is pressed
    if (myPlayGameCtrl.isJustOff()) {
      im.setScreen(cmp, screens.newGame);
      return;
    }


    //What to do when Options is pressed
    if (myOptionsCtrl.isJustOff()) {
      im.setScreen(cmp, screens.options);
      return;
    }


     //What to do when credits is pressed
     if (myCreditsCtrl.isJustOff()) {
         im.setScreen(cmp, screens.credits);
     }

    //what to do when Exit is pressed
    if (myExitCtrl.isJustOff()) {
      // Save the settings on exit, but not on mobile as settings don't exist there.
      if (isMobile == false) {
        cmp.getOptions().save();
      }
      Gdx.app.exit();
      return;
    }

  }

  @Override
  public boolean isCursorOnBg(SolInputManager.Ptr ptr) {
    return false;
  }

  @Override
  public void onAdd(SolApplication cmp) {

  }

  @Override
  public void drawBg(UiDrawer uiDrawer, SolApplication cmp) {

    //uiDrawer.draw(uiDrawer.filler, SolColor.Background_Grey);
      float sz = 10f;
      uiDrawer.draw(myNebTex, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.Background_Grey);

  }

  @Override
  public void drawImgs(UiDrawer uiDrawer, SolApplication cmp) {

/**START DATE VARIABLES DECLARING WHAT IMAGE SHOULD BE LOADED FOR THE LOGO**/
    if (this.isChristmas)
    {
        float sz = .55f;
        if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(christmasLogo, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
    }else if (this.isEaster) {

    }else if(this.isNewYears) {

    }else {
        float sz = .55f;
        if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(normalLogo, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
    }

  }

  @Override
  public void drawText(UiDrawer uiDrawer, SolApplication cmp) {
  }

  @Override
  public boolean reactsToClickOutside() {
    return false;
  }

  @Override
  public void blurCustom(SolApplication cmp) {

  }
}
