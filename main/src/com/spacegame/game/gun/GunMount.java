package com.spacegame.game.gun;

import com.badlogic.gdx.math.Vector2;
import com.spacegame.Const;
import com.spacegame.common.SolMath;
import com.spacegame.game.*;
import com.spacegame.game.dra.Dra;
import com.spacegame.game.input.Shooter;
import com.spacegame.game.item.ItemContainer;
import com.spacegame.game.ship.hulls.GunSlot;
import com.spacegame.game.ship.hulls.HullConfig;
import com.spacegame.game.ship.SolShip;

import java.util.List;

public class GunMount {
  private final Vector2 myRelPos;
  private final boolean myFixed;
  private SolGun myGun;
  private boolean myDetected;
  private float myRelGunAngle;

  public GunMount(GunSlot gunSlot) {
    myRelPos = gunSlot.getPosition();
    myFixed = !gunSlot.allowsRotation();
  }

  public void update(ItemContainer ic, SolGame game, float shipAngle, SolShip creator, boolean shouldShoot, SolShip nearestEnemy, Faction faction) {
    if (myGun == null) return;
    if (!ic.contains(myGun.getItem())) {
      setGun(game, creator, null, false);
      return;
    }

    if (creator.getHull().config.getType() != HullConfig.Type.STATION) myRelGunAngle = 0;
    myDetected = false;
    if (!myFixed && nearestEnemy != null) {
      Vector2 creatorPos = creator.getPos();
      Vector2 nePos = nearestEnemy.getPos();
      float dst = creatorPos.dst(nePos) - creator.getHull().config.getApproxRadius() - nearestEnemy.getHull().config.getApproxRadius();
      float detDst = game.getPlanetMan().getNearestPlanet().isNearGround(creatorPos) ? Const.AUTO_SHOOT_GROUND : Const.AUTO_SHOOT_SPACE;
      if (dst < detDst) {
        Vector2 mountPos = SolMath.toWorld(myRelPos, shipAngle, creatorPos);
        boolean player = creator.getPilot().isPlayer();
        float shootAngle = Shooter.calcShootAngle(mountPos, creator.getSpd(), nePos, nearestEnemy.getSpd(), myGun.getConfig().clipConf.projConfig.spdLen, player);
        if (shootAngle == shootAngle) {
          myRelGunAngle = shootAngle - shipAngle;
          myDetected = true;
          if (player) game.getMountDetectDrawer().setNe(nearestEnemy);
        }
        SolMath.free(mountPos);
      }
    }

    float gunAngle = shipAngle + myRelGunAngle;
    myGun.update(ic, game, gunAngle, creator, shouldShoot, faction);
  }

  public GunItem getGun() {
    return myGun == null ? null : myGun.getItem();
  }

  public void setGun(SolGame game, SolObject o, GunItem gunItem, boolean underShip) {
    List<Dra> dras = o.getDras();
    if (myGun != null) {
      List<Dra> dras1 = myGun.getDras();
      dras.removeAll(dras1);
      game.getDraMan().removeAll(dras1);
      myGun = null;
    }
    if (gunItem != null) {
      if (gunItem.config.fixed != myFixed) throw new AssertionError("tried to set gun to incompatible mount");
      myGun = new SolGun(game, gunItem, myRelPos, underShip);
      List<Dra> dras1 = myGun.getDras();
      dras.addAll(dras1);
      game.getDraMan().addAll(dras1);
    }
  }

  public boolean isFixed() {
    return myFixed;
  }

  public Vector2 getRelPos() {
    return myRelPos;
  }

  public boolean isDetected() {
    return myDetected;
  }
}
