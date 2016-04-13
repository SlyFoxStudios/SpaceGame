package com.spacegame.game.maze;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.spacegame.*;
import com.spacegame.files.FileManager;
import com.spacegame.files.HullConfigManager;
import com.spacegame.game.item.ItemManager;

import java.util.ArrayList;
import java.util.List;

public class MazeConfigs {
  public final List<MazeConfig> configs;

  public MazeConfigs(TextureManager textureManager, HullConfigManager hullConfigs, ItemManager itemManager) {
    configs = new ArrayList<MazeConfig>();

    JsonReader r = new JsonReader();
    FileHandle configFile = FileManager.getInstance().getConfigDirectory().child("mazes.json");
    JsonValue mazesNode = r.parse(configFile);
    for (JsonValue mazeNode : mazesNode) {
      MazeConfig c = MazeConfig.load(textureManager, hullConfigs, mazeNode, configFile, itemManager);
      configs.add(c);
    }
  }
}
