package com.spacegame.game.ship;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.spacegame.common.SolMath;
import com.spacegame.game.AbilityCommonConfig;
import com.spacegame.game.SolGame;
import com.spacegame.game.dra.DraLevel;
import com.spacegame.game.item.ItemManager;
import com.spacegame.game.item.SolItem;
import com.spacegame.game.particle.ParticleSrc;

public class SloMo implements ShipAbility {
  private static final float SLO_MO_CHG_SPD = .03f;
  private final Config myConfig;

  private float myFactor;

  public SloMo(Config config) {
    myConfig = config;
    myFactor = 1;
  }

  @Override
  public AbilityConfig getConfig() {
    return myConfig;
  }

  @Override
  public AbilityCommonConfig getCommonConfig() {
    return myConfig.cc;
  }

  @Override
  public float getRadius() {
    return Float.MAX_VALUE;
  }

  @Override
  public boolean update(SolGame game, SolShip owner, boolean tryToUse) {
    if (tryToUse) {
      myFactor = myConfig.factor;
      Vector2 pos = owner.getPos();
      ParticleSrc src = new ParticleSrc(myConfig.cc.effect, -1, DraLevel.PART_BG_0, new Vector2(), true, game, pos, owner.getSpd(), 0);
      game.getPartMan().finish(game, src, pos);
      return true;
    }
    float ts = game.getTimeStep();
    myFactor = SolMath.approach(myFactor, 1, SLO_MO_CHG_SPD * ts);
    return false;
  }

  public float getFactor() {
    return myFactor;
  }


  public static class Config implements AbilityConfig {
    public final float factor;
    public final float rechargeTime;
    private final SolItem chargeExample;
    private final AbilityCommonConfig cc;

    public Config(float factor, float rechargeTime, SolItem chargeExample, AbilityCommonConfig cc)
    {
      this.factor = factor;
      this.rechargeTime = rechargeTime;
      this.chargeExample = chargeExample;
      this.cc = cc;
    }

    @Override
    public ShipAbility build() {
      return new SloMo(this);
    }

    @Override
    public SolItem getChargeExample() {
      return chargeExample;
    }

    @Override
    public float getRechargeTime() {
      return rechargeTime;
    }

    @Override
    public void appendDesc(StringBuilder sb) {
      sb.append("Time slow down to ").append((int) (factor * 100)).append("%\n");
    }

    public static AbilityConfig load(JsonValue abNode, ItemManager itemManager, AbilityCommonConfig cc) {
      float factor = abNode.getFloat("factor");
      float rechargeTime = abNode.getFloat("rechargeTime");
      SolItem chargeExample = itemManager.getExample("sloMoCharge");
      return new Config(factor, rechargeTime, chargeExample, cc);
    }
  }
}
