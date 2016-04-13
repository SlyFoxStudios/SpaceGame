package com.spacegame.game.sound;

/**
 * Created by Brayden on 03-Nov-15.
 */
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



        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.audio.Music;
        import com.spacegame.files.FileManager;

/**
 * Singleton class that is responsible for playing all music throughout the game.
 */
public final class MusicManager {
    private static MusicManager instance = null;
    private static final String DIR = "res/sounds/";
    private final Music menuMusic;

    /**
     * Returns the singleton instance of this class.
     * @return The instance.
     */
    public static MusicManager getInstance() {
        if(instance == null) {
            instance = new MusicManager();
        }

        return instance;
    }

    /**
     * Initalise the MusicManager class.
     */
    private MusicManager() {
        menuMusic = Gdx.audio.newMusic(FileManager.getInstance().getStaticFile("res/sounds/music/main.ogg"));
        menuMusic.setLooping(true);
    }

    /**
     * Start playing the music menu from the beginning of the track. The menu music loops continuously.
     */
    public void PlayMenuMusic() {
        menuMusic.play();
    }

    /**
     * Stop playing all music.
     */
    public void StopMusic() {
        menuMusic.stop();
    }

}
