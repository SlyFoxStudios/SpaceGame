package com.spacegame.menu;

import com.badlogic.gdx.Input;
import com.spacegame.GameOptions;
import com.spacegame.SolApplication;
import com.spacegame.common.SolColor;
import com.spacegame.game.DebugOptions;
import com.spacegame.game.SolGame;
import com.spacegame.ui.SolInputManager;
import com.spacegame.ui.SolUiControl;
import com.spacegame.ui.SolUiScreen;
import com.spacegame.ui.UiDrawer;
import com.spacegame.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brayden on 19/08/2015.
 */

public class ControlOptions implements SolUiScreen {
    private final ArrayList<SolUiControl> myControls;
    private final SolUiControl myBackCtrl;
    private final SolUiControl myControlTypeCtrl;
    private final SolUiControl inputMapCtrl;

    public ControlOptions(MenuLayout menuLayout, GameOptions gameOptions) {

        myControls = new ArrayList<SolUiControl>();

        myControlTypeCtrl = new SolUiControl(menuLayout.buttonRect(-1, 1), true, Input.Keys.C);
        myControlTypeCtrl.setDisplayName("Control Type");
        myControls.add(myControlTypeCtrl);

        inputMapCtrl = new SolUiControl(menuLayout.buttonRect(-1, 2), true, Input.Keys.M);
        inputMapCtrl.setDisplayName("Input");
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

        int ct = cmp.getOptions().controlType;
        String ctName = "Keyboard";
        if (ct == GameOptions.CONTROL_MIXED) ctName = "KB + Mouse";
        if (ct == GameOptions.CONTROL_MOUSE) ctName = "Mouse";
        if (ct == GameOptions.CONTROL_CONTROLLER) ctName = "Controller";
        myControlTypeCtrl.setDisplayName("Input: " + ctName);
        if (myControlTypeCtrl.isJustOff()) {
            cmp.getOptions().advanceControlType(false);
        }

        if (myBackCtrl.isJustOff()) {
            im.setScreen(cmp, screens.gameoptionsScreen);
        }



        if (inputMapCtrl.isJustOff()) {
            if (ct == GameOptions.CONTROL_MIXED) {
                screens.inputMapScreen.setOperations(screens.inputMapScreen.inputMapMixedScreen);
            } else if (ct == GameOptions.CONTROL_KB) {
                screens.inputMapScreen.setOperations(screens.inputMapScreen.inputMapKeyboardScreen);
            } else if (ct == GameOptions.CONTROL_CONTROLLER) {
                screens.inputMapScreen.setOperations(screens.inputMapScreen.inputMapControllerScreen);
            }
            im.setScreen(cmp, screens.inputMapScreen);
        }
    }

    @Override
    public void drawBg(UiDrawer uiDrawer, SolApplication cmp) {

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
