/*
 * Copyright 2015 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.destinationsol.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import org.destinationsol.GameOptions;
import org.destinationsol.SolApplication;
import org.destinationsol.TextureManager;
import org.destinationsol.common.SolColor;
import org.destinationsol.game.DebugOptions;
import org.destinationsol.ui.SolInputManager;
import org.destinationsol.ui.SolUiControl;
import org.destinationsol.ui.SolUiScreen;
import org.destinationsol.ui.UiDrawer;

import java.util.ArrayList;
import java.util.List;

public class ExtrasScreen implements SolUiScreen {
  public static final float CREDITS_BTN_W = .15f;
  public static final float CREDITS_BTN_H = .07f;

  private final ArrayList<SolUiControl> myControls;
  private final SolUiControl mySkinsCtrl;
  private final SolUiControl myBackCtrl;

  //Textures here.
  private final TextureAtlas.AtlasRegion myTitleTex;
  //Mobile component
  private final boolean isMobile;

  public ExtrasScreen(MenuLayout menuLayout, TextureManager textureManager, boolean mobile, float r, GameOptions gameOptions) {
    isMobile = mobile;
    myControls = new ArrayList<SolUiControl>();

    mySkinsCtrl = new SolUiControl(menuLayout.buttonRect(-1, 0), true, Input.Keys.C);
    mySkinsCtrl.setDisplayName("COMMING SOON")
    myControls.add(mySkinsCtrl);

    myBackCtrl = new SolUiControl(menuLayout.buttonRect(-1, 1), true, gameOptions.getKeyEscape());
    myBackCtrl.setDisplayName("Back");
    myControls.add(myBackCtrl);

//New image being loaded here.
    FileHandle imageFile = FileManager.getInstance().getImagesDirectory().child("christmas.png");
    exampleImg = textureManager.getTexture(imageFile);
//End of loading new image

    myTitleTex = textureManager.getTex("ui/title", null);
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


    //What to do when Extras button is pressed.
    if(myBackCtrl.isJustOff){
      im.setScreen(cmp, screens.main);
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
    //Draws background in here.
  }

  @Override
  public void drawImgs(UiDrawer uiDrawer, SolApplication cmp) {
    
    //Drawing the Normal logo
    float sz = .55f;
    if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(myTitleTex, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);

    //Loading the new image
      float sz = .55f;
    if (!DebugOptions.PRINT_BALANCE) uiDrawer.draw(exampleImg, sz, sz, sz/2, sz/2, uiDrawer.r/2, sz/2, 0, SolColor.W);

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
