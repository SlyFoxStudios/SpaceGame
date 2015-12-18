package com.miloshpetrov.sol2.menu;

import com.miloshpetrov.sol2.GameOptions;
import com.miloshpetrov.sol2.TextureManager;
import com.miloshpetrov.sol2.ui.SolLayouts;

public class MenuScreens {
  public final MainScreen main;
  public final OptionsScreen options;
  public final InputMapScreen inputMapScreen;
  public final ResolutionScreen resolutionScreen;
  public final CreditsScreen credits;
  public final LoadingScreen loading;
  public final NewGameScreen newGame;
  public final NewShipScreen newShip;
    public final GameOptionsScreen gameoptionsScreen;
    public final ControlOptions controloptionsScreen;
    public final ContinueScreen continueScreen;
    public final PlayGameScreen playGameScreen;

  public MenuScreens(SolLayouts layouts, TextureManager textureManager, boolean mobile, float r, GameOptions gameOptions) {
    MenuLayout menuLayout = layouts.menuLayout;
    main = new MainScreen(menuLayout, textureManager, mobile, r, gameOptions);
    options = new OptionsScreen(menuLayout, textureManager, gameOptions);
    inputMapScreen = new InputMapScreen(r, gameOptions);
    resolutionScreen = new ResolutionScreen(menuLayout, gameOptions);
    credits = new CreditsScreen(menuLayout, r, gameOptions);
    loading = new LoadingScreen();
    newGame = new NewGameScreen(menuLayout, textureManager, gameOptions);
    newShip = new NewShipScreen(menuLayout, gameOptions);
      gameoptionsScreen = new GameOptionsScreen(menuLayout, mobile, textureManager, r, gameOptions);
      controloptionsScreen = new ControlOptions(menuLayout, gameOptions);
      continueScreen = new ContinueScreen(menuLayout, textureManager, gameOptions);
      playGameScreen = new PlayGameScreen(menuLayout, textureManager, gameOptions);
  }

}
