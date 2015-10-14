package com.miloshpetrov.sol2.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.miloshpetrov.sol2.GameOptions;
import com.miloshpetrov.sol2.SolApplication;
import com.miloshpetrov.sol2.TextureManager;
import com.miloshpetrov.sol2.common.SolColor;
import com.miloshpetrov.sol2.files.FileManager;
import com.miloshpetrov.sol2.game.DebugOptions;
import com.miloshpetrov.sol2.game.SolGame;
import com.miloshpetrov.sol2.ui.SolInputManager;
import com.miloshpetrov.sol2.ui.SolUiControl;
import com.miloshpetrov.sol2.ui.SolUiScreen;
import com.miloshpetrov.sol2.ui.UiDrawer;
import com.miloshpetrov.sol2.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brayden on 19/08/2015.
 */

public class GameOptionsScreen implements SolUiScreen {
    private final ArrayList<SolUiControl> myControls;
    private final SolUiControl myBackCtrl;
    private final SolUiControl myResoCtrl;

    private final SolUiControl inputMapCtrl;
    private final SolUiControl myVolCtrl;
    private final boolean isMobile;

    public GameOptionsScreen(MenuLayout menuLayout, boolean mobile, TextureManager textureManager, float r, GameOptions gameOptions) {
        isMobile = mobile;
        myControls = new ArrayList<SolUiControl>();

        myResoCtrl = new SolUiControl(menuLayout.buttonRect(-1, 1), true);
        myResoCtrl.setDisplayName("Resolution");
        myControls.add(myResoCtrl);

        myVolCtrl = new SolUiControl(menuLayout.buttonRect(-1, 2), true);
        myVolCtrl.setDisplayName("Vol");
        myControls.add(myVolCtrl);

        inputMapCtrl = new SolUiControl(mobile ? null : menuLayout.buttonRect(-1, 3), true, Input.Keys.M);
        inputMapCtrl.setDisplayName("Controls");
        myControls.add(inputMapCtrl);

        myBackCtrl = new SolUiControl(menuLayout.buttonRect(-1, 4), true, gameOptions.getKeyEscape());
        myBackCtrl.setDisplayName("Back");
        myControls.add(myBackCtrl);

    }

    @Override
    public List<SolUiControl> getControls() {
        return myControls;
    }

    @Override
    public void updateCustom(SolApplication cmp, SolInputManager.Ptr[] ptrs, boolean clickedOutside) {
        SolInputManager im = cmp.getInputMan();
        MenuScreens screens = cmp.getMenuScreens();
        SolGame g = cmp.getGame();
        g.setPaused(true);
        GameOptions options = cmp.getOptions();
        myVolCtrl.setDisplayName("Volume: " + getVolName(options));
        if (myVolCtrl.isJustOff()) {
            options.advanceVolMul();
        }

        if (myResoCtrl.isJustOff()) {
            im.setScreen(cmp, screens.resolutionScreen);
        }



        if (myBackCtrl.isJustOff()) {
            im.setScreen(cmp, g.getScreens().menuScreen);
        }



        if (inputMapCtrl.isJustOff()) {

            im.setScreen(cmp, screens.controloptionsScreen);
        }
    }

    private String getVolName(GameOptions options) {
        float volMul = options.volMul;
        if (volMul == 0) return "Off";
        if (volMul < .4f) return "Low";
        if (volMul < .7f) return "High";
        return "Max";
    }

    @Override
    public void drawBg(UiDrawer uiDrawer, SolApplication cmp) {
        uiDrawer.draw(uiDrawer.filler, SolColor.Background_Grey);

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
        return false;
    }

    @Override
    public void onAdd(SolApplication cmp) {

    }

    @Override
    public void blurCustom(SolApplication cmp) {

    }
}
