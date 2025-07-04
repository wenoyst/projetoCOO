import java.awt.Color;
import GameEngine.GameStates;
import Interfaces.*;
//classe inigimo generico, dx é a velocidade, dy é o angulo, e  dydx é a velocidade de rotacao do angulo.

public class Enemy1 implements Entity,Drawable {


private double X;
private double Y;
private int health;
private double angle;
private double speed;
private double radius;
private long cooldown;
private double ExplosionStart;
private double ExplosionEnd;
private int state;
private double angleSpeed;
private Color color;

public void setColor(Color color) {
  this.color = color;
}

public Color getColor() {
  return color;
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

public double getRadius() {
  return radius;
}

public void setRadius(double radius) {
  this.radius = radius;
}

public double getAngle() {
  return angle;
}

public double getSpeed() {
  return speed;
}

public void setSpeed(double speed) {
  this.speed = speed;
}

public void setAngle(double angle) {
  this.angle = angle;
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

public double getAngleSpeed() {
  return angleSpeed;
}

public void setAngleSpeed(double angleSpeed) {
  this.angleSpeed = angleSpeed;
}

Enemy1(int health, double posX, double posY, double angle, double speed, double radius, double angleSpeed, Color color){
  setHealth(health);
  setX(posX);
  setY(posY);
  setSpeed(speed);
  setAngle(angle);
  setState(GameStates.ACTIVE);
  setRadius(radius);
  setAngleSpeed(angleSpeed);
  setColor(color);
  }


public void checkExplosionE(long currentTime){
    if(getState() == GameStates.EXPLODING){
					if(currentTime > getExplosionEnd()){setState(GameStates.INACTIVE);}
				}}

public void explode(long currentTime){
setState(GameStates.EXPLODING);
setExplosionStart(currentTime);
setExplosionEnd(currentTime + 250);
	}


public void takeDamage(Color pColor){
	if(pColor == Color.WHITE)setHealth(getHealth() - playerProjectiles.shoot);
	if(pColor == Color.GREEN)setHealth(getHealth() - playerProjectiles.chargedShoot);
	if(pColor == Color.YELLOW)setHealth(getHealth() - playerProjectiles.goldenChargedShoot);


  };


Projectiles Shoot(){

return new Projectiles(Color.RED,Math.cos(getAngle()) * 0.45, Math.sin(getAngle()) * 0.45 * (-1.0), getX(), getY(), 2);
  }

public boolean update(long currentTime, long delta, double playerY){

		if(getY() > GameLib.HEIGHT + 10) {setState(GameStates.INACTIVE);}

					else {
						setX(getX() + getSpeed() * Math.cos(getAngle()) * delta);
						setY(getY() + getSpeed() * Math.sin(getAngle()) * delta * (-1.0));
						setAngle(getAngle() + getAngleSpeed() * delta);

						if(currentTime > getCooldown() && getY() < playerY){
								setCooldown((long) (currentTime +  Math.random() * 500));
				        return true;
						}
		}
	return false;
  }

public void collide(long currentTime, Color pColor){

if(getHealth() <= 0) explode(currentTime);

else takeDamage(pColor);

  }

public void draw(long currentTime){

				if(getState() == GameStates.EXPLODING){

					double alpha = (currentTime - getExplosionStart()) / 500;
					GameLib.drawExplosion(getX(), getY(), alpha);
				}

				if(getState() == GameStates.ACTIVE){

					GameLib.setColor(getColor());
					GameLib.drawCircle(getX(), getY(), getRadius());
				}
	}

}


