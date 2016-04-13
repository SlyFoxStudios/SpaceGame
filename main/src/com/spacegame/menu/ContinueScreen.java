package com.spacegame.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.spacegame.GameOptions;
import com.spacegame.SolApplication;
import com.spacegame.TextureManager;
import com.spacegame.common.SolColor;
import com.spacegame.common.SolMath;
import com.spacegame.files.FileManager;
import com.spacegame.game.DebugOptions;
import com.spacegame.game.SaveManager;
import com.spacegame.ui.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContinueScreen implements SolUiScreen {
    private final ArrayList<SolUiControl> myControls;
    private final SolUiControl myBackCtrl;
    private final SolUiControl myPrevCtrl;
    private final SolUiControl myNewCtrl;
    public final TextureAtlas.AtlasRegion myNebTex;


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

    //Setting them up for the date checker.
    private boolean isChristmas;
    private boolean isEaster;
    private boolean isNewYears;
    private boolean isAprilFools;
    private boolean isHalloween;
    private boolean isAustraliaDay;
    private boolean isBdayGame;
    private boolean isBdayMe;



    public ContinueScreen(MenuLayout menuLayout, TextureManager textureManager, GameOptions gameOptions) {
        myControls = new ArrayList<SolUiControl>();

        myPrevCtrl = new SolUiControl(menuLayout.buttonRect(-1, 2), true, gameOptions.getKeyShoot());
        myPrevCtrl.setDisplayName("Previous Game");
        myControls.add(myPrevCtrl);

        myNewCtrl = new SolUiControl(menuLayout.buttonRect(-1, 3), true);
        myNewCtrl.setDisplayName("New Game");
        myControls.add(myNewCtrl);

        myBackCtrl = new SolUiControl(menuLayout.buttonRect(-1, 4), true, gameOptions.getKeyEscape());
        myBackCtrl.setDisplayName("Cancel");
        myControls.add(myBackCtrl);

        myNebTex = textureManager.getTex("farBgBig/nebulae2", SolMath.test(.5f), null);

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


        /**Here we are changing the logo for different dates...**/
        Calendar var1 = Calendar.getInstance();
        if (var1.get(2) + 1 == 9 && var1.get(3) >= 20 && var1.get(5) <= 30)
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

    @Override
    public List<SolUiControl> getControls() {
        return myControls;
    }

    @Override
    public void onAdd(SolApplication cmp) {
        myPrevCtrl.setEnabled(SaveManager.hasPrevShip());
    }

    @Override
    public void updateCustom(SolApplication cmp, SolInputManager.Ptr[] ptrs, boolean clickedOutside) {
        MenuScreens screens = cmp.getMenuScreens();
        SolInputManager im = cmp.getInputMan();


        if (myBackCtrl.isJustOff()) {
            im.setScreen(cmp, screens.newGame);
            return;
        }
        if (myPrevCtrl.isJustOff()) {
            cmp.loadNewGame(false, true);
            return;
        }
        if (myNewCtrl.isJustOff()) {
            if (!myPrevCtrl.isEnabled()) {
                cmp.loadNewGame(false, false);
            } else {
                im.setScreen(cmp, screens.newShip);
            }
        }
    }

    @Override
    public boolean isCursorOnBg(SolInputManager.Ptr ptr) {
        return true;
    }

    @Override
    public void blurCustom(SolApplication cmp) {
    }

    @Override
    public void drawBg(UiDrawer uiDrawer, SolApplication cmp) {
        float sz = 10f;
        uiDrawer.draw(myNebTex, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.Background_Grey);

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

}
