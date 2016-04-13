package com.spacegame.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.spacegame.GameOptions;
import com.spacegame.SolApplication;
import com.spacegame.TextureManager;
import com.spacegame.common.SolColor;
import com.spacegame.common.SolMath;
import com.spacegame.files.FileManager;
import com.spacegame.game.DebugOptions;
import com.spacegame.ui.*;

import java.util.ArrayList;
import java.util.List;

public class Extras implements SolUiScreen {
    public static final float CREDITS_BTN_W = .15f;
    public static final float CREDITS_BTN_H = .07f;

    private final ArrayList<SolUiControl> myControls;
    private final SolUiControl myExitCtrl;
    private final SolUiControl myCreditsCtrl;
    private final SolUiControl myCommunityCtrl;
    public final TextureAtlas.AtlasRegion myNebTex;



    private final boolean isMobile;

    public Extras(MenuLayout menuLayout, TextureManager textureManager, boolean mobile, float r, GameOptions gameOptions) {
        isMobile = mobile;
        myControls = new ArrayList<SolUiControl>();


        myCommunityCtrl = new SolUiControl(menuLayout.buttonRect(-1, 2), true, Input.Keys.M);
        myCommunityCtrl.setDisplayName("Community Hub");
        myControls.add(myCommunityCtrl);

        myCreditsCtrl = new SolUiControl(menuLayout.buttonRect(-1, 3), true, Input.Keys.C);
        myCreditsCtrl.setDisplayName("(COMING SOON)");
        myControls.add(myCreditsCtrl);


        myExitCtrl = new SolUiControl(menuLayout.buttonRect(-1, 4), true, gameOptions.getKeyEscape());
        myExitCtrl.setDisplayName("Back");
        myControls.add(myExitCtrl);



        //myTitleTex = textureManager.getTex("ui/title", null);
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

        //What to do when Community Hub is pressed
        if(myExitCtrl.isJustOff()) {
            im.setScreen(cmp, screens.main);
            return;
        }


        //What to do when Community Button is pressed
        if(myCommunityCtrl.isJustOff()) {
            //tell it what to do, once i create the website.
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

        float sz = 10f;
        uiDrawer.draw(myNebTex, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.Background_Grey);

    }

    @Override
    public void drawImgs(UiDrawer uiDrawer, SolApplication cmp) {
        //float sz = .55f;
        // if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(myTitleTex, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);
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
