package com.spacegame.save;

import com.spacegame.game.FarObj;
import com.spacegame.game.planet.Planet;
import com.spacegame.game.planet.SolSystem;

import java.util.ArrayList;
import java.util.List;

public class SaveData {
  public final List<FarObj> farObjs;
  public final List<SolSystem> systems;
  public final List<Planet> planets;

  public SaveData() {
    farObjs = new ArrayList<FarObj>();
    planets = new ArrayList<Planet>();
    systems = new ArrayList<SolSystem>();
  }
}
