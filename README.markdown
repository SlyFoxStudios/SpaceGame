Space Game
==========

<!--<p align="center"><img src="/main/imgSrcs/ui/title.png" alt="Destination Sol"/></p>-->


This repo contains the official source code to Space Game. This game is a re-code of another game with improvements and game-play changes. The old repo with the old game is located at [Github](https://github.com/MovingBlocks/DestinationSol)

This project plans to be different and move away from the old game. We are here to bring more of the commubnity into it... but keep the game style. This is SPACE GAME. (very creative name...)

Got a cool feature you have added your self? Well... we are here to help. Create a pull request and we will over view your code and even fix a few things if need be. Then within a week or 2 the game will have your little creation.

Space Game is now officially licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html) and available in source code form at [GitHub](https://github.com/crazywolf132/SpaceGame).

We plan to release the game on steam after we work out where we would like to take this project and after we fix some of the major bugs. This game will also be released on android and maybe iphone.
 
Feel free to look at the old project and Join that community, but be warned it is the old project and alot of our code will be remade from the ground-up.

Gameplay
--------

You start at the edge of a solar system as a pilot in a small ship. You are free to explore space, land on planets, fight with enemies, upgrade your ship and equipment, hire mercenaries, mine asteroids, and so on.

Enemy ships are orange icons, allies are blue. Enemies can be marked with a skull icon - beware! They are likely stronger than you. Improve your ship and equipment and fight them later!
 
Your ship has a certain number of hit points (your armor), which will recover if you have consumable repair kits in your inventory and stay idle for a short while. You may also have a shield that takes damage first. Each is vulnerable to different weapons, both on your ship and others.

Weapons and special abilities often need consumables to function (like Bullets or Slo Mo Charges) and take time to rearm.

You can destroy asteroids for easy money, even with the starting ship's ammo-less but weak gun.

Warnings get posted if you get close to dangerous ships or may soon collide with something on your current course. Blue dots along the edge of the screen indicate a planet is nearby.

Controls
--------

Note: You can select either pure keyboard, keyboard + mouse, or controller (in the settings). Exact details may change over time. Below are the default key mappings (no mouse). You can change these in-game.

*Main screen*

* [Space] - Fire main gun
* [Ctrl] - Fire secondary gun (if equipped)
* [Shift] - Use ship ability
* [Left,Right] - Turn the ship
* [Up] - Thrust. There are no brakes! You have to turn and burn to change direction or slow down 
* [Tab] - Show the map
* [I] - Show inventory
* [T] - Talk (interact with a station)
* [ESC] - Menu / close screens

*With map up*

* [Up, Down] - Zoom in and out on the map

*With inventory up*

* [Left, Right] - change page
* [Up, Down] - scroll up and down
* [Space] - equip / unequip item *OR* buy / sell if talking to a station
* [D] - discard selected item


Building and running from source
--------

You only need Java installed to run Destination Sol from source. Use Java 7 or 8, the newer the better.

Run any commands in the project root directory

* Download / clone the [source from GitHub](https://github.com/MovingBlocks/DestinationSol)
* To run from the command line: `gradlew run`
* To prepare for IntelliJ run: `gradlew idea` then load the generated project via `DestinationSol.ipr`
* To create a game package for distribution (Windows, Linux, Mac): `gradlew distZip`

For Android a little extra setup is needed

* Add in the Android code: `gradlew fetchAndroid` and then rerun Gradle once - for instance `gradlew idea` again
* Install the official Android SDK from Google - exact version info is still TODO
* Make a local.properties in the project root with the path to your SDK. Example: sdk.dir=C\:/Dev/Android/SDK
* To prepare in IntelliJ go to Project Structure and under Modules click the android "folder" icon and set the SDK
* In the same window go to Artifacts and add a new Android Application, created from the android module
* Supply a code signing keystore or supply other debug configuration
* To run in IntelliJ make sure you have an Android emulator (or USB connection) working then create a run configuration
* We use `gradlew assembleRelease` to build the APK for Google Play (with the right keystore etc)

You can also run the Android version via Gradle: `gradlew android` - but need your device setup. Need instructions.

GWT / HTML

LibGDX supports an HTML based facade based on the Google Web Toolkit. Its use isn't all the way implemented yet.

* Add in the code like with Android: `gradlew fetchGwt` then rerun Gradle

Contributors
------------
[GitHub contribution stats](https://github.com/MovingBlocks/DestinationSol/graphs/contributors)

*Original creators:*

* [Milosh Petrov](https://github.com/miloshpetrov)
* [Nika Burimenko](https://github.com/NoiseDoll)
* Kent C. Jensen
* Julia Nikolaeva 

*Contributors on GitHub:* 

* [Cervator](https://github.com/Cervator)
* [PrivateAlpha](https://github.com/PrivateAlpha)
* [theotherjay](https://github.com/theotherjay)
* [LinusVanElswijk](https://github.com/LinusVanElswijk)
* [SimonC4](https://github.com/SimonC4)
* [grauerkoala](https://github.com/grauerkoala)

... and your name here? :-) More coming!

Apologies in advance for any omissions, contact [Cervator](http://forum.terasology.org/members/cervator.2/) if you believe you've been missed :-)
