package com.spacegame.game;

import com.badlogic.gdx.math.Vector2;
import com.spacegame.Const;
import com.spacegame.common.SolMath;
import com.spacegame.game.planet.Planet;
import com.spacegame.game.planet.SolSystem;

public interface CamRotStrategy {
  public float getRotation(Vector2 pos, SolGame game);

  public static class Static implements CamRotStrategy {
    public float getRotation(Vector2 pos, SolGame game) {
      return 0;
    }
  }

  public static class ToPlanet implements CamRotStrategy {

    public float getRotation(Vector2 pos, SolGame game) {
      Planet np = game.getPlanetMan().getNearestPlanet();
      float fh = np.getFullHeight();
      Vector2 npPos = np.getPos();
      if (npPos.dst(pos) < fh) {
        return SolMath.angle(pos, npPos, true) - 90;
      }
      SolSystem sys = game.getPlanetMan().getNearestSystem(pos);
      Vector2 sysPos = sys.getPos();
      if (sysPos.dst(pos) < Const.SUN_RADIUS) {
        return SolMath.angle(pos, sysPos, true) - 90;
      }
      return 0;
    }
  }
}
