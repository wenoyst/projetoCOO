import java.util.List;
import Interfaces.*;
import GameEngine.GameStates;
import java.awt.Color;

public class Boss1 implements Entity,Drawable{


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
private boolean isClone;
private int maxHealth;
private boolean ability1State;
private double ability1Ending;
private double ability1Cooldown;
private double ability2Cooldown;


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


public boolean getAbility1State(){return ability1State;}

public void setMaxHealth(int maxHealth) {
  this.maxHealth = maxHealth;
}
public int getMaxHealth() {
  return maxHealth;
}

public void setClone(boolean isClone) {
  this.isClone = isClone;
}

public boolean getClone(){return isClone;}

public void setAbility1State(boolean ability1State) {
  this.ability1State = ability1State;
}

public void setAbility1Ending(double ability1Ending) {
  this.ability1Ending = ability1Ending;
}


public void setAbility1Cooldown(double ability1Cooldown) {
  this.ability1Cooldown = ability1Cooldown;
}

public void setAbility2Cooldown(double ability2Cooldown) {
  this.ability2Cooldown = ability2Cooldown;
}

public double getAbility1Ending() {
  return ability1Ending;
}

public double getAbility1Cooldown() {
  return ability1Cooldown;
}

public double getAbility2Cooldown() {
  return ability2Cooldown;
}




  public Boss1(int health,int maxHealth, double posX, double posY, double angle, double speed, double radius, double angleSpeed, boolean isClone){
  setHealth(health);
  setX(posX);
  setY(posY);
  setSpeed(speed);
  setAngle(angle);
  setState(GameStates.ACTIVE);
  setRadius(radius);
  setAngleSpeed(angleSpeed);
  setColor(color);
  setMaxHealth(maxHealth);
  setClone(isClone);
  setAbility1State(false);
  setAbility1Ending(0);
  setAbility1Cooldown(0);
  setAbility2Cooldown(0);
  }

void explode(long currentTime){
	setState(GameStates.EXPLODING);
	setExplosionStart(currentTime);
	setExplosionEnd(currentTime + 250);
  }

  Projectiles fireProjectile(Color color, double Dx, double Dy, double X, double Y, double radius){
  return new Projectiles(color, Dx, Dy, X, Y, radius);
  }


void takeDamage(long currentTime, playerProjectiles proj){
    		if(getHealth() <= 0){
					explode(currentTime);
					}
			else {
							if(proj.getColor() == Color.WHITE) setHealth(getHealth() - playerProjectiles.shoot);
							if(proj.getColor() == Color.GREEN) setHealth(getHealth() - playerProjectiles.chargedShoot);
							if(proj.getColor() == Color.YELLOW) setHealth(getHealth() - playerProjectiles.goldenChargedShoot);

					}

  }

public void activateAbility1(long currentTime){
 if(getClone() == false && currentTime > getAbility1Cooldown()){
        setAbility1State(true);
        setAbility1Ending(currentTime + 20000);
        setAbility1Cooldown(currentTime + 30000);
      }
  }

public Projectiles Shoot(Color color, double Dx, double Dy, double X, double Y, double radius){
return new Projectiles(color, Dx, Dy, X, Y, radius);
}


public void collide(long currentTime, playerProjectiles proj, List<Projectiles> enemProjectiles, RenderManager Renderer){

if(getAbility1State() == true || proj.getColor() != Color.YELLOW) {
      Projectiles Proj = Shoot(proj.getColor(), proj.getDx(), -proj.getDy(), proj.getX(), proj.getY(), proj.getRadius());
      Renderer.register(Proj);
   enemProjectiles.add(Proj);


    }

else{
		if(getHealth() <= 0) explode(currentTime);
		else takeDamage(currentTime, proj);
		}
}

public Projectiles Shoot(){
return new Projectiles(Color.RED,Math.cos(getAngle()) * 0.45, Math.sin(getAngle()) * 0.45 * (-1.0), getX(), getY(), 2);
  }


public boolean updateAbility(long currentTime){

	if( (double)getHealth()/getMaxHealth() < 0.5 && getAbility2Cooldown() < currentTime && getClone() == false){
    setAbility2Cooldown(currentTime + 50000);
   return true;
    }


return false;
  }


public boolean update(long currentTime, long delta, double playerY){

		setX(getX() + getSpeed() * Math.sin(getAngle()) * delta);
		if(getX() < 0 && getSpeed() > 0){setSpeed(-getSpeed());}
				  	if(getX() > 480 && getSpeed() < 0){setSpeed(-getSpeed());}
						setAngle(getAngle() + getAngleSpeed() * delta);

						if(currentTime > getCooldown() && getY() < playerY){
								setCooldown((long) (currentTime +  Math.random() * 500));
				return true;
		}
				if(getAbility1State() == true && currentTime > getAbility1Ending()){setAbility1State(false);}

return false;
  }

public void draw(long currentTime) {
				if(getState() == GameStates.EXPLODING){

					double alpha = ((currentTime - getExplosionStart()) / 500);
					GameLib.drawExplosion(getX(), getY(), alpha);
				}

				if(getState() == GameStates.ACTIVE){
					GameLib.setColor(Color.GRAY);
        GameLib.drawLine(getX() - getRadius() * 2,getY() - getRadius() - 5, getX() + getRadius() * 2, getY() - getRadius() - 5);

        GameLib.setColor(Color.RED);
        GameLib.drawLine(getX() - getRadius() * 2, getY() - getRadius() - 5, getX() - getRadius() * 2 + getRadius() * 4 * (double)getHealth()/getMaxHealth() , getY() - getRadius() - 5);

        double[] xCoords = new double[6];
        double[] yCoords = new double[6];

					GameLib.setColor(Color.PINK);
					   for (int z = 0; z < 6; z++) {
            double angle = Math.toRadians(-90 + z * 60);
            xCoords[z] = (getX() + getRadius() * Math.cos(angle));
            yCoords[z] = (getY() + getRadius() * Math.sin(angle));

        }
      for (int z = 0; z < 6; z++) {
            for (int j = z + 1; j < 6; j++) {
                // Call the provided function using coordinates from our arrays
                GameLib.drawLine(xCoords[z], yCoords[z], xCoords[j], yCoords[j]);
            }
        }
        if(getAbility1State() == true){
        	GameLib.setColor(Color.YELLOW);
        	GameLib.drawCircle(getX(), getY(), getRadius() + 2);}
				}
			}
}
