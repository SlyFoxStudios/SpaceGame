package com.miloshpetrov.sol2.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.miloshpetrov.sol2.GameOptions;
import com.miloshpetrov.sol2.SolApplication;
import com.miloshpetrov.sol2.TextureManager;
import com.miloshpetrov.sol2.common.SolColor;
import com.miloshpetrov.sol2.common.SolMath;
import com.miloshpetrov.sol2.game.sound.MusicManager;
import com.miloshpetrov.sol2.files.FileManager;
import com.miloshpetrov.sol2.game.DebugOptions;
import com.miloshpetrov.sol2.ui.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.FileHandler;

public class MainScreen implements SolUiScreen {
  public static final float CREDITS_BTN_W = .15f;
  public static final float CREDITS_BTN_H = .07f;

  private final ArrayList<SolUiControl> myControls;
    private final SolUiControl myPlayCtrl;
  private final SolUiControl myOptionsCtrl;
  private final SolUiControl myExitCtrl;
  private final SolUiControl myCreditsCtrl;
  private final TextureAtlas.AtlasRegion myTitleTex;
    private final TextureAtlas.AtlasRegion myNebTex;
    //Loading all the logos
    private final TextureAtlas.AtlasRegion logo;
    private final TextureAtlas.AtlasRegion christmas;
    private final TextureAtlas.AtlasRegion easter;
    private final TextureAtlas.AtlasRegion newyears;
    private final TextureAtlas.AtlasRegion aprilfools;
    private final TextureAtlas.AtlasRegion halloween;
    private final TextureAtlas.AtlasRegion australiaDay;
    private final TextureAtlas.AtlasRegion bdayGame;
    private final TextureAtlas.AtlasRegion bdayMe;
    private final TextureAtlas.AtlasRegion christmasBackground;

    //Setting them up for the date checker.
    private boolean isChristmas;
    private boolean isEaster;
    private boolean isNewYears;
    private boolean isAprilFools;
    private boolean isHalloween;
    private boolean isAustraliaDay;
    private boolean isBdayGame;
    private boolean isBdayMe;


  private final boolean isMobile;

  public MainScreen(MenuLayout menuLayout, TextureManager textureManager, boolean mobile, float r, GameOptions gameOptions) {
    isMobile = mobile;
    myControls = new ArrayList<SolUiControl>();



      myPlayCtrl = new SolUiControl(menuLayout.buttonRect(-1, 1), true, Input.Keys.P);
      myPlayCtrl.setDisplayName("Play");
      myControls.add(myPlayCtrl);

      myOptionsCtrl = new SolUiControl(mobile ? null : menuLayout.buttonRect(-1, 2), true, Input.Keys.O);
      myOptionsCtrl.setDisplayName("Options");
      myControls.add(myOptionsCtrl);

      myCreditsCtrl = new SolUiControl(menuLayout.buttonRect(-1, 3), true, Input.Keys.C);
      myCreditsCtrl.setDisplayName("Credits");
      myControls.add(myCreditsCtrl);

      myExitCtrl = new SolUiControl(menuLayout.buttonRect(-1, 4), true, gameOptions.getKeyEscape());
      myExitCtrl.setDisplayName("Exit");
      myControls.add(myExitCtrl);

//Loading all logos
      //Normal
      FileHandle imageFile = FileManager.getInstance().getImagesDirectory().child("logo.png");
      logo = textureManager.getTexture(imageFile);
      //Christmas
      FileHandle imageFile1 = FileManager.getInstance().getImagesDirectory().child("christmas.png");
      christmas = textureManager.getTexture(imageFile1);
      //Easter
      FileHandle imageFile2 = FileManager.getInstance().getImagesDirectory().child("easter.png");
      easter = textureManager.getTexture(imageFile2);
      //NewYears
      FileHandle imageFile3 = FileManager.getInstance().getImagesDirectory().child("newyear.png");
      newyears = textureManager.getTexture(imageFile3);
      //AprilFools
      FileHandle imageFile4 = FileManager.getInstance().getImagesDirectory().child("aprilfools.png");
      aprilfools = textureManager.getTexture(imageFile4);
      //AustraliaDay
      FileHandle imageFile5 = FileManager.getInstance().getImagesDirectory().child("australiaday.png");
      australiaDay = textureManager.getTexture(imageFile5);
      //Halloween
      FileHandle imageFile6 = FileManager.getInstance().getImagesDirectory().child("halloween.png");
      halloween = textureManager.getTexture(imageFile6);
      //Birthday Game
      FileHandle imageFile7 = FileManager.getInstance().getImagesDirectory().child("bdaygame.png");
      bdayGame = textureManager.getTexture(imageFile7);
      //Birthday Developer (crazywolf)
      FileHandle imageFile8 = FileManager.getInstance().getImagesDirectory().child("bdaydev.png");
      bdayMe = textureManager.getTexture(imageFile8);

      myNebTex = textureManager.getTex("farBgBig/nebulae2", SolMath.test(.5f), null);
      myTitleTex = textureManager.getTex("ui/title", null);

      /** One day i hope i can work out how to add this epic background **/
      FileHandle imageFile9 = FileManager.getInstance().getImagesDirectory().child("background.gif");
      christmasBackground = textureManager.getTexture(imageFile9);

      /**Here we are changing the logo for different dates...**/
      Calendar var1 = Calendar.getInstance();
      if (var1.get(2) + 1 == 12 && var1.get(3) >= 20 && var1.get(5) <= 30)
      {
          this.isChristmas = true;
      }
      else if (var1.get(2) + 1 == 11 && var1.get(5) >=  10 && var1.get(5) <= 12)
      {
          this.isBdayGame = true;
      }
      else if (var1.get(2) + 1 == 05 && var1.get(5) >= 29 && var1.get(5) <= 31)
      {
          this.isBdayMe = true;
      }
      else if (var1.get(2) + 1 == 10 && var1.get(5) >= 25 && var1.get(5) <= 31)
      {
          this.isHalloween = true;
      }
      else if (var1.get(2) + 1 == 11 && var1.get(5) >= 01 && var1.get(5) <= 02)
      {
          this.isHalloween = true;
      }
      else if (var1.get(2) + 1 == 01 && var1.get(5) >= 01 && var1.get(5) <= 07)
      {
          this.isNewYears = true;
      }
      else if (var1.get(2) + 1 == 04 && var1.get(5) >= 01 && var1.get(5) <= 03)
      {
          this.isAprilFools = true;
      }
      else if (var1.get(2) + 1 == 03 && var1.get(5) >= 26 && var1.get(5) <= 28)
      {
          this.isEaster = true;
      }
      else if (var1.get(2) + 1 == 01 && var1.get(5) >= 26 && var1.get(5) <= 28)
      {
          this.isAustraliaDay = true;
      }



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
    if (myPlayCtrl.isJustOff()) {
        im.setScreen(cmp, screens.playGameScreen);
        return;
    }
    if (myOptionsCtrl.isJustOff()) {
      im.setScreen(cmp, screens.options);
      return;
    }
    if (myExitCtrl.isJustOff()) {
      // Save the settings on exit, but not on mobile as settings don't exist there.
      if (isMobile == false) {
        cmp.getOptions().save();
      }
      Gdx.app.exit();
      return;
    }
    if (myCreditsCtrl.isJustOff()) {
      im.setScreen(cmp, screens.credits);
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
      float sz = 10f;
      uiDrawer.draw(myNebTex, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.Background_Grey);
      //MusicManager.getInstance().PlayMenuMusic();
  }

  @Override
  public void drawImgs(UiDrawer uiDrawer, SolApplication cmp) {

      /**We are now loading the correct image for the date**/
      if (this.isChristmas)
      {
          float sz = .55f;
          if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(christmas, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
      }
      else if (this.isNewYears)
      {
          float sz = .55f;
          if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(newyears, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
      }
      else if (this.isAustraliaDay)
      {
          float sz = .55f;
          if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(australiaDay, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
      }
      else if (this.isEaster)
      {
          float sz = .55f;
          if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(easter, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
      }
      else if (this.isAprilFools)
      {
          float sz = .55f;
          if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(aprilfools, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
      }
      else if (this.isHalloween)
      {
          float sz = .55f;
          if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(halloween, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
      }
      else if (this.isBdayGame)
      {
          float sz = .55f;
          if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(bdayGame, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
      }
      else if (this.isBdayMe)
      {
          float sz = .55f;
          if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(bdayMe, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
      }
      else
      {
          float sz = .55f;
          if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(logo, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
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
