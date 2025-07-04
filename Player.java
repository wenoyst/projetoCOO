
import java.awt.Color;
import GameEngine.GameStates;
import Interfaces.*;
//dx é a velocidade no plano x, dy é a velocidade no plano y

public class Player implements Entity,Drawable {

private static final long respawnTime = 2000;
private static final long firingCooldown = 200;


private double X;
private double Y;
private int health;
private double dy;
private double dx;
private double radius;
private int damage;
private long cooldown;
private double ExplosionStart;
private double ExplosionEnd;
private int state;
private Color color;
private double weaponPower = 0;

public Color getColor() {
  return color;
}

public void setColor(Color color) {
  this.color = color;
}

public void setState(int state) {
  this.state = state;
}

public int getState() {
  return state;
}

public long getCooldown() {
  return cooldown;
}

public void setCooldown(long cooldown) {
  this.cooldown = cooldown;
}

public int getDamage() {
  return damage;
}

public void setDamage(int damage) {
  this.damage = damage;
}

public double getRadius() {
  return radius;
}

public void setRadius(double radius) {
  this.radius = radius;
}

public double getDy() {
  return dy;
}

public double getDx() {
  return dx;
}

public void setDx(double dx) {
  this.dx = dx;
}

public void setDy(double dy) {
  this.dy = dy;
}

public void setHealth(int h){
    health = h;}

public int getHealth(){
    return health;}


public double getX(){
  return X;}

public double getY(){
  return Y;}

public void setX(double x){
    X = x;}

public void setY(double y) {
  Y = y;
}

public void setExplosionEnd(double explosionEnd) {
  ExplosionEnd = explosionEnd;
}

public void setExplosionStart(double explosionStart) {
  ExplosionStart = explosionStart;
}

public double getExplosionEnd() {
  return ExplosionEnd;
}

public double getExplosionStart() {
  return ExplosionStart;
}

public Player(int health, double posX, double posY, double dy, double dx, long cooldown, double radius)  {
    setX(posX);
    setY(posY);
    setHealth(health);
    setDy(dy);
    setDx(dx);
    setCooldown(cooldown);
    setRadius(radius);
    setState(GameStates.ACTIVE);

  }

void getPowerUP1(){
        setDx(getDx() + 0.2);
      setDy(getDy() + 0.2);
  }

void explode(long currentTime){
	setHealth(getHealth() - 1);
	setState(GameStates.EXPLODING);
	setExplosionStart(currentTime);
	setExplosionEnd(currentTime + respawnTime);
  }

void endPlayerE(long currentTime){
if(getState() == GameStates.EXPLODING){
				if(currentTime > getExplosionEnd()){
				setState(GameStates.ACTIVE);
			}
		}
	}

	void checkPlayerCoord(){
			if(getX() < 0.0) setX(0.0);
			if(getX() >= GameLib.WIDTH) setX(GameLib.WIDTH - 1);
			if(getY() < 25.0) setY(25.0);
			if(getY() >= GameLib.HEIGHT) setY(GameLib.HEIGHT - 1);
	}


public void move(double dx, double dy, long delta) {
    this.X += dx * delta;
    this.Y += dy * delta;
}

public void chargeWeapon() {
    this.weaponPower++;
}

public playerProjectiles fireWeapon(long currentTime) {

    playerProjectiles newProjectile;
    double projectileSpeed;
    Color projectileColor;

   if (this.weaponPower >= 1000) {
        projectileColor = Color.GREEN;
        projectileSpeed = -1.0;
 if (PowerUp2.getActive()) {
        projectileColor = Color.YELLOW;
        projectileSpeed = -2.0;
    }
    }
 else {
        projectileColor = Color.WHITE;
        projectileSpeed = -0.5;
    }

    newProjectile = new playerProjectiles(projectileColor, 0.0, projectileSpeed,this.getX(), this.getY() - (2 * this.getRadius()), 10);

    this.setCooldown(currentTime + firingCooldown);
    this.weaponPower = 0;

    return newProjectile;
  }

public boolean update(long delta, long currentTime){

if(InputController.requestingUp() == true && getState() == GameStates.ACTIVE && (Boss2.getIsTimeStopped() == false || PowerUp2.getActive() == true)) move(0, -getDy(), delta);

if(InputController.requestingDown() == true && getState() == GameStates.ACTIVE && (Boss2.getIsTimeStopped() == false || PowerUp2.getActive() == true)) move(0, getDy(), delta);

if(InputController.requestingRight() == true && getState() == GameStates.ACTIVE && (Boss2.getIsTimeStopped() == false || PowerUp2.getActive() == true)) move(getDx(), 0, delta);

if(InputController.requestingLeft() == true && getState() == GameStates.ACTIVE &&(Boss2.getIsTimeStopped() == false || PowerUp2.getActive() == true)) move(-getDx(), 0, delta);

checkPlayerCoord();

if(InputController.requestingCtrl() == true && getState() == GameStates.ACTIVE) chargeWeapon();
else if (this.getCooldown() < currentTime && this.weaponPower > 0 && (Boss2.getIsTimeStopped() == false || PowerUp2.getActive() == true)) {return true;}

endPlayerE(currentTime);

if(InputController.requestingEsc())	System.exit(0);

return false;
    }


public void draw(long currentTime){

			if(getState() == GameStates.EXPLODING){

				double alpha = (currentTime - getExplosionStart()) / (getExplosionEnd() - getExplosionStart());
				GameLib.drawExplosion(getX(), getY(), alpha);
			}
			else{

				GameLib.setColor(Color.BLUE);

		   if(Boss2.getIsTimeStopped() == true && weaponPower == 0 && PowerUp2.getActive() == false) GameLib.setColor(Color.GRAY);
			 GameLib.drawPlayer(getX(), getY(), getRadius());
			 if(weaponPower > 1000){
		  GameLib.setColor(Color.GREEN);
		  	if(Boss2.getIsTimeStopped() == true) GameLib.setColor(Color.GRAY);
		  	if(PowerUp2.getActive() == true) GameLib.setColor(Color.YELLOW);
		  	GameLib.drawCircle(getX(), getY(), getRadius() + 5);

		}
		}

  }
}





