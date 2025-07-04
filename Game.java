
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class Game {

private Player player = new Player(3, GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25, 0.25, 0, 12.0);
private List<playerProjectiles> pP = new ArrayList<>();
private List<Enemy1> enemy1 = new ArrayList<>();
private List<Enemy2> enemy2 = new ArrayList<>();
private List<Boss1> funny = new ArrayList<>();
private List<Boss2> timeLords = new ArrayList<>();
private List<Projectiles> enemProjectiles = new ArrayList<>();
private List<background1> bg1 = new ArrayList<>();
private List<background2> bg2 = new ArrayList<>();
private List<PowerUp1> pu1 = new ArrayList<>();
private List<PowerUp2> pu2 = new ArrayList<>();

private RenderManager Render = new RenderManager();
private CollisionManager Colider = new CollisionManager(player, pP, enemProjectiles, funny, timeLords, enemy1, enemy2, pu1, pu2, Render);
private UpdateManager Updater = new UpdateManager(player, pP, enemProjectiles, funny, timeLords, enemy1, enemy2, pu1, pu2, bg1, bg2, Render);
private throwManager thrower;

private boolean win = false;

private long delta;
private boolean running = true;
private long currentTime = System.currentTimeMillis();


public static void busyWait(long time){

   while(System.currentTimeMillis() < time) Thread.yield();
}

public boolean getWin(){
    return win;}

void checkPlayerHealth(){
     if(player.getHealth() <= 0) running = false;
	}

 Game(String level, int health){
player.setHealth(health);

File levelFile = new File(level);
thrower = new throwManager(enemProjectiles, funny, timeLords, enemy1, enemy2, pu1, pu2, Render, levelFile);


for(int i = 0; i < 50; i++){
  background1 newB1 = new background1 (Math.random() * GameLib.WIDTH,	Math.random() * GameLib.HEIGHT);
  Render.register(newB1);
  bg1.add(newB1);
		}
for(int i = 0; i < 20; i++){
     background2 newB2 = new background2 (Math.random() * GameLib.WIDTH,	Math.random() * GameLib.HEIGHT);
     	Render.register(newB2);
     bg2.add(newB2);
		}
    background1.setSpeed(0.14);
    background2.setSpeed(0.09);
		GameLib.initGraphics();
    thrower.getThings(currentTime);
    Render.register(player);
 	while(running == true){
     delta = System.currentTimeMillis() - currentTime;
     currentTime = System.currentTimeMillis();
     Colider.checkCollisions(currentTime);
     if(thrower.getNextThing() <= currentTime && thrower.getNextThing() != -1) thrower.throwThings(currentTime);
     if(Updater.checkUpdates(delta, currentTime) == true){win = true; running = false;} ;
     Render.drawAll(currentTime);
     busyWait(currentTime + 3);
     checkPlayerHealth();
  	}
  }
}
