package com.spacegame.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.spacegame.*;
import com.spacegame.common.DebugCol;
import com.spacegame.common.SolMath;
import com.spacegame.files.FileManager;
import com.spacegame.files.HullConfigManager;
import com.spacegame.game.asteroid.AsteroidBuilder;
import com.spacegame.game.chunk.ChunkManager;
import com.spacegame.game.dra.DraDebugger;
import com.spacegame.game.dra.DraMan;
import com.spacegame.game.farBg.FarBgMan;
import com.spacegame.game.farBg.FarBackgroundManagerOld;
import com.spacegame.game.gun.GunItem;
import com.spacegame.game.input.*;
import com.spacegame.game.item.*;
import com.spacegame.game.particle.*;
import com.spacegame.game.planet.*;
import com.spacegame.game.screens.GameScreens;
import com.spacegame.game.ship.*;
import com.spacegame.game.ship.hulls.HullConfig;
import com.spacegame.game.sound.SoundManager;
import com.spacegame.game.sound.SpecialSounds;
import com.spacegame.GameOptions;
import com.spacegame.ui.*;

import java.util.ArrayList;
import java.util.List;

public class SolGame {

  private final GameScreens myScreens;
  private final SolCam myCam;
  private final ObjectManager myObjectManager;
  private final SolApplication myCmp;
  private final DraMan myDraMan;
  private final PlanetManager myPlanetManager;
  private final TextureManager myTextureManager;
  private final ChunkManager myChunkManager;
  private final PartMan myPartMan;
  private final AsteroidBuilder myAsteroidBuilder;
  private final LootBuilder myLootBuilder;
  private final ShipBuilder myShipBuilder;
  private final HullConfigManager hullConfigManager;
  private final GridDrawer myGridDrawer;
  private final FarBgMan myFarBgMan;
  private final FarBackgroundManagerOld myFarBackgroundManagerOld;
  private final FactionMan myFactionMan;
  private final MapDrawer myMapDrawer;
  private final ShardBuilder myShardBuilder;
  private final ItemManager myItemManager;
  private final StarPort.Builder myStarPortBuilder;
  private final SoundManager mySoundManager;
  private final PlayerSpawnConfig myPlayerSpawnConfig;
  private final DraDebugger myDraDebugger;
  private final SpecialSounds mySpecialSounds;
  private final EffectTypes myEffectTypes;
  private final SpecialEffects mySpecialEffects;
  private final GameColors gameColors;
  private final AbilityCommonConfigs myAbilityCommonConfigs;
  private final SolNames myNames;
  private final BeaconHandler myBeaconHandler;
  private final MountDetectDrawer myMountDetectDrawer;
  private final TutorialManager myTutorialManager;

  private SolShip myHero;
  private float myTimeStep;
  private float myTime;
  private boolean myPaused;
  private final GalaxyFiller myGalaxyFiller;
  private StarPort.Transcendent myTranscendentHero;
  private float myTimeFactor;
  private float myRespawnMoney;
  private HullConfig myRespawnHull;
  private final ArrayList<SolItem> myRespawnItems;

  public SolGame(SolApplication cmp, boolean usePrevShip, TextureManager textureManager, boolean tut, CommonDrawer commonDrawer) {
    myCmp = cmp;
    GameDrawer drawer = new GameDrawer(textureManager, commonDrawer);
    gameColors = new GameColors();
    mySoundManager = new SoundManager();
    mySpecialSounds = new SpecialSounds(mySoundManager);
    myDraMan = new DraMan(drawer);
    myCam = new SolCam(drawer.r);
    myScreens = new GameScreens(drawer.r, cmp);
    myTutorialManager = tut ? new TutorialManager(commonDrawer.r, myScreens, cmp.isMobile(), cmp.getOptions()) : null;
    myTextureManager = textureManager;
    myFarBackgroundManagerOld = new FarBackgroundManagerOld(myTextureManager);
    myShipBuilder = new ShipBuilder();
    myEffectTypes = new EffectTypes();
    mySpecialEffects = new SpecialEffects(myEffectTypes, myTextureManager, gameColors);
    myItemManager = new ItemManager(myTextureManager, mySoundManager, myEffectTypes, gameColors);
    myAbilityCommonConfigs = new AbilityCommonConfigs(myEffectTypes, myTextureManager, gameColors, mySoundManager);
    hullConfigManager = new HullConfigManager(myShipBuilder, FileManager.getInstance(), textureManager, myItemManager, myAbilityCommonConfigs, mySoundManager);
    myNames = new SolNames();
    myPlanetManager = new PlanetManager(myTextureManager, hullConfigManager, gameColors, myItemManager);
    SolContactListener contactListener = new SolContactListener(this);
    myFactionMan = new FactionMan(myTextureManager);
    myObjectManager = new ObjectManager(contactListener, myFactionMan);
    myGridDrawer = new GridDrawer(textureManager);
    myChunkManager = new ChunkManager(myTextureManager);
    myPartMan = new PartMan();
    myAsteroidBuilder = new AsteroidBuilder(myTextureManager);
    myLootBuilder = new LootBuilder();
    myFarBgMan = new FarBgMan();
    myMapDrawer = new MapDrawer(myTextureManager, commonDrawer.h);
    myShardBuilder = new ShardBuilder(myTextureManager);
    myGalaxyFiller = new GalaxyFiller();
    myStarPortBuilder = new StarPort.Builder();
    myPlayerSpawnConfig = PlayerSpawnConfig.load(hullConfigManager, myItemManager);
    myDraDebugger = new DraDebugger();
    myBeaconHandler = new BeaconHandler(textureManager);
    myMountDetectDrawer = new MountDetectDrawer(textureManager);
    myRespawnItems = new ArrayList<SolItem>();
    myTimeFactor = 1;

    // from this point we're ready!
    myPlanetManager.fill(myNames);
    myGalaxyFiller.fill(this);
    ShipConfig startingShip = usePrevShip ? SaveManager.readShip(hullConfigManager, myItemManager) : null;
    createPlayer(startingShip);
    SolMath.checkVectorsTaken(null);
  }

  // uh, this needs refactoring
  private void createPlayer(ShipConfig prevShip) {
    Vector2 pos = myGalaxyFiller.getPlayerSpawnPos(this);
    myCam.setPos(pos);

    Pilot pilot;
    if (myCmp.getOptions().controlType == GameOptions.CONTROL_MOUSE) {
      myBeaconHandler.init(this, pos);
      pilot = new AiPilot(new BeaconDestProvider(), true, Faction.LAANI, false, "you", Const.AI_DET_DIST);
    } else {
      pilot = new UiControlledPilot(myScreens.mainScreen);
    }

    ShipConfig shipConfig;
    if (DebugOptions.GOD_MODE) {
      shipConfig = myPlayerSpawnConfig.godShipConfig;
    } else if (prevShip != null) {
      shipConfig = prevShip;
    } else {
      shipConfig = myPlayerSpawnConfig.shipConfig;
    }

    float money = myRespawnMoney != 0 ? myRespawnMoney : myTutorialManager != null ? 200 : shipConfig.money;

    HullConfig hull = myRespawnHull != null ? myRespawnHull : shipConfig.hull;

    String itemsStr = !myRespawnItems.isEmpty() ? "" : shipConfig.items;

    boolean giveAmmo = prevShip == null && myRespawnItems.isEmpty();
    myHero = myShipBuilder.buildNewFar(this, new Vector2(pos), null, 0, 0, pilot, itemsStr, hull, null, true, money, null, giveAmmo).toObj(this);

    ItemContainer ic = myHero.getItemContainer();
    if (!myRespawnItems.isEmpty()) {
      for (int i1 = 0, sz = myRespawnItems.size(); i1 < sz; i1++) {
        SolItem item = myRespawnItems.get(i1);
        ic.add(item);
      }
    } else if (DebugOptions.GOD_MODE) {
      myItemManager.addAllGuns(ic);
    } else if (myTutorialManager != null) {
      for (int i = 0; i < 50; i++) {
        if (ic.groupCount() > 1.5f * Const.ITEM_GROUPS_PER_PAGE) break;
        SolItem it = myItemManager.random();
        if (!(it instanceof GunItem) && it.getIcon(this) != null && ic.canAdd(it)) {
          ic.add(it.copy());
        }
      }
    }
    ic.seenAll();
    AiPilot.reEquip(this, myHero);

    myObjectManager.addObjDelayed(myHero);
    myObjectManager.resetDelays();
  }

  public void onGameEnd() {
    saveShip();
    myObjectManager.dispose();
    mySoundManager.dispose();
  }

  public void saveShip() {
    if (myTutorialManager != null) return;
    HullConfig hull;
    float money;
    ArrayList<SolItem> items;
    if (myHero != null) {
      hull = myHero.getHull().config;
      money = myHero.getMoney();
      items = new ArrayList<SolItem>();
      for (List<SolItem> group : myHero.getItemContainer()) {
        for (SolItem i : group) {
          items.add(0, i);
        }
      }
    } else if (myTranscendentHero != null) {
      FarShip farH = myTranscendentHero.getShip();
      hull = farH.getHullConfig();
      money = farH.getMoney();
      items = new ArrayList<SolItem>();
      for (List<SolItem> group : farH.getIc()) {
        for (SolItem i : group) {
          items.add(0, i);
        }
      }
    } else {
      hull = myRespawnHull;
      money = myRespawnMoney;
      items = myRespawnItems;
    }
    SaveManager.writeShip(hull, money, items, this);
  }

  public GameScreens getScreens() {
    return myScreens;
  }

  public void update() {
    myDraDebugger.update(this);

    if (myPaused) return;

    myTimeFactor = DebugOptions.GAME_SPEED_MULTIPLIER;
    if (myHero != null) {
      ShipAbility ability = myHero.getAbility();
      if (ability instanceof SloMo) {
        float factor = ((SloMo) ability).getFactor();
        myTimeFactor *= factor;
      }
    }
    myTimeStep = Const.REAL_TIME_STEP * myTimeFactor;
    myTime += myTimeStep;

    myPlanetManager.update(this);
    myCam.update(this);
    myChunkManager.update(this);
    myMountDetectDrawer.update(this);
    myObjectManager.update(this);
    myDraMan.update(this);
    myMapDrawer.update(this);
    mySoundManager.update(this);
    myBeaconHandler.update(this);

    myHero = null;
    myTranscendentHero = null;
    List<SolObject> objs = myObjectManager.getObjs();
    for (int i = 0, objsSize = objs.size(); i < objsSize; i++) {
      SolObject obj = objs.get(i);
      if ((obj instanceof SolShip)) {
        SolShip ship = (SolShip) obj;
        Pilot prov = ship.getPilot();
        if (prov.isPlayer()) {
          myHero = ship;
          break;
        }
      }
      if (obj instanceof StarPort.Transcendent) {
        StarPort.Transcendent trans = (StarPort.Transcendent) obj;
        FarShip ship = trans.getShip();
        if (ship.getPilot().isPlayer()) {
          myTranscendentHero = trans;
          break;
        }
      }
    }

    if (myTutorialManager != null) myTutorialManager.update();
  }

  public void draw() {
    myDraMan.draw(this);
  }

  public void drawDebug(GameDrawer drawer) {
    if (DebugOptions.GRID_SZ > 0) myGridDrawer.draw(drawer, this, DebugOptions.GRID_SZ, drawer.debugWhiteTex);
    myPlanetManager.drawDebug(drawer, this);
    myObjectManager.drawDebug(drawer, this);
    if (DebugOptions.ZOOM_OVERRIDE != 0) myCam.drawDebug(drawer);
    drawDebugPoint(drawer, DebugOptions.DEBUG_POINT, DebugCol.POINT);
    drawDebugPoint(drawer, DebugOptions.DEBUG_POINT2, DebugCol.POINT2);
    drawDebugPoint(drawer, DebugOptions.DEBUG_POINT3, DebugCol.POINT3);
  }

  private void drawDebugPoint(GameDrawer drawer, Vector2 dp, Color col) {
    if (dp.x != 0 || dp.y != 0) {
      float sz = myCam.getRealLineWidth() * 5;
      drawer.draw(drawer.debugWhiteTex, sz, sz, sz / 2, sz / 2, dp.x, dp.y, 0, col);
    }
  }

  public float getTimeStep() {
    return myTimeStep;
  }

  public SolCam getCam() {
    return myCam;
  }

  public SolApplication getCmp() {
    return myCmp;
  }

  public DraMan getDraMan() {
    return myDraMan;
  }

  public ObjectManager getObjMan() {
    return myObjectManager;
  }

  public TextureManager getTexMan() {
    return myTextureManager;
  }

  public PlanetManager getPlanetMan() {
    return myPlanetManager;
  }

  public PartMan getPartMan() {
    return myPartMan;
  }

  public AsteroidBuilder getAsteroidBuilder() {
    return myAsteroidBuilder;
  }

  public LootBuilder getLootBuilder() {
    return myLootBuilder;
  }

  public SolShip getHero() {
    return myHero;
  }

  public ShipBuilder getShipBuilder() {
    return myShipBuilder;
  }

  public ItemManager getItemMan() {
    return myItemManager;
  }

  public HullConfigManager getHullConfigs() {
    return hullConfigManager;
  }

  public boolean isPaused() {
    return myPaused;
  }

  public void setPaused(boolean paused) {
    myPaused = paused;
    DebugCollector.warn(myPaused ? "game paused" : "game resumed");
  }

  public void respawn() {
    if (myHero != null) {
      beforeHeroDeath();
      myObjectManager.removeObjDelayed(myHero);
    } else if (myTranscendentHero != null) {
      FarShip farH = myTranscendentHero.getShip();
      setRespawnState(farH.getMoney(), farH.getIc(), farH.getHullConfig());
      myObjectManager.removeObjDelayed(myTranscendentHero);
    }
    createPlayer(null);
  }

  public FactionMan getFactionMan() {
    return myFactionMan;
  }

  public boolean isPlaceEmpty(Vector2 pos, boolean considerPlanets) {
    Planet np = myPlanetManager.getNearestPlanet(pos);
    if (considerPlanets) {
      boolean inPlanet = np.getPos().dst(pos) < np.getFullHeight();
      if (inPlanet) return false;
    }
    SolSystem ns = myPlanetManager.getNearestSystem(pos);
    if (ns.getPos().dst(pos) < SunSingleton.SUN_HOT_RAD) return false;
    List<SolObject> objs = myObjectManager.getObjs();
    for (int i = 0, objsSize = objs.size(); i < objsSize; i++) {
      SolObject o = objs.get(i);
      if (!o.hasBody()) continue;
      if (pos.dst(o.getPos()) < myObjectManager.getRadius(o)) {
        return false;
      }
    }
    List<FarObjData> farObjs = myObjectManager.getFarObjs();
    for (int i = 0, farObjsSize = farObjs.size(); i < farObjsSize; i++) {
      FarObjData fod = farObjs.get(i);
      FarObj o = fod.fo;
      if (!o.hasBody()) continue;
      if (pos.dst(o.getPos()) < o.getRadius()) {
        return false;
      }
    }
    return true;
  }

  public MapDrawer getMapDrawer() {
    return myMapDrawer;
  }

  public ShardBuilder getShardBuilder() {
    return myShardBuilder;
  }

  public FarBackgroundManagerOld getFarBgManOld() {
    return myFarBackgroundManagerOld;
  }

  public GalaxyFiller getGalaxyFiller() {
    return myGalaxyFiller;
  }

  public StarPort.Builder getStarPortBuilder() {
    return myStarPortBuilder;
  }

  public StarPort.Transcendent getTranscendentHero() {
    return myTranscendentHero;
  }

  public GridDrawer getGridDrawer() {
    return myGridDrawer;
  }

  public SoundManager getSoundMan() {
    return mySoundManager;
  }

  public float getTime() {
    return myTime;
  }

  public void drawDebugUi(UiDrawer uiDrawer) {
    myDraDebugger.draw(uiDrawer, this);
  }

  public PlayerSpawnConfig getPlayerSpawnConfig() {
    return myPlayerSpawnConfig;
  }

  public SpecialSounds getSpecialSounds() {
    return mySpecialSounds;
  }

  public SpecialEffects getSpecialEffects() {
    return mySpecialEffects;
  }

  public GameColors getCols() {
    return gameColors;
  }

  public float getTimeFactor() {
    return myTimeFactor;
  }

  public BeaconHandler getBeaconHandler() {
    return myBeaconHandler;
  }

  public MountDetectDrawer getMountDetectDrawer() {
    return myMountDetectDrawer;
  }

  public TutorialManager getTutMan() {
    return myTutorialManager;
  }

  public void beforeHeroDeath() {
    if (myHero == null) return;

    float money = myHero.getMoney();
    ItemContainer ic = myHero.getItemContainer();

    setRespawnState(money, ic, myHero.getHull().config);

    myHero.setMoney(money - myRespawnMoney);
    for (SolItem item : myRespawnItems) {
      ic.remove(item);
    }
  }

  private void setRespawnState(float money, ItemContainer ic, HullConfig hullConfig) {
    myRespawnMoney = .75f * money;
    myRespawnHull = hullConfig;
    myRespawnItems.clear();
    for (List<SolItem> group : ic) {
      for (SolItem item : group) {
        boolean equipped = myHero == null || myHero.maybeUnequip(this, item, false);
        if (equipped || SolMath.test(.75f)) {
          myRespawnItems.add(0, item);
        }
      }
    }
  }
}
