package com.spacegame.game.projectile;

import com.badlogic.gdx.math.Vector2;
import com.spacegame.game.SolGame;
import com.spacegame.game.ship.SolShip;

public interface ProjectileBody {
  void update(SolGame game);
  Vector2 getPos();
  Vector2 getSpd();
  void receiveForce(Vector2 force, SolGame game, boolean acc);
  void onRemove(SolGame game);
  float getAngle();
  void changeAngle(float diff);
  float getDesiredAngle(SolShip ne);
}
