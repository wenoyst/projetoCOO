import Interfaces.*;
import GameEngine.GameStates;
import java.awt.Color;

public class Boss2 implements Entity,Drawable{
private static boolean isTimeStopped = false;
private static long timeStoppedFinal = 0;


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
private int maxHealth;
private int ability1State;
private double ability2Cooldown;

private boolean ability2State;
private double ability2Ending;
private double speed1;


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


public void setSpeed1(double speed1) {
  this.speed1 = speed1;
}
public double getSpeed1() {
  return speed1;
}

public double getAbility2Ending() {
  return ability2Ending;
}

public void setAbility2Ending(double ability2Ending) {
  this.ability2Ending = ability2Ending;
}

public boolean getAbility2State(){
  return ability2State;}

public void setAbility2State(boolean ability2State){
    this.ability2State = ability2State;}

public int getAbility1State(){return ability1State;}

public static void setTimeStoppedFinal(long timeStoppedFinal) {
  Boss2.timeStoppedFinal = timeStoppedFinal;
}

public static long getTimeStoppedFinal() {
  return timeStoppedFinal;
}

public void setMaxHealth(int maxHealth) {
  this.maxHealth = maxHealth;
}
public int getMaxHealth() {
  return maxHealth;
}

public static void setIsTimeStopped(boolean isTimeStopped) {
  Boss2.isTimeStopped = isTimeStopped;
}

public static boolean getIsTimeStopped(){return isTimeStopped;}

public void setAbility1State(int ability1State) {
  this.ability1State = ability1State;
}

public void setAbility2Cooldown(double ability2Cooldown) {
  this.ability2Cooldown = ability2Cooldown;
}

public double getAbility2Cooldown() {
  return ability2Cooldown;
}



  public Boss2(int health,int maxHealth, double posX, double posY, double angle, double speed, double radius, double angleSpeed){
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
  setAbility1State(0);
  setAbility2Cooldown(0);
  setSpeed1(speed * 3/2);
  }

public void takeDamage(playerProjectiles proj){
		if(proj.getColor() == Color.WHITE)setHealth(getHealth() - playerProjectiles.shoot);
		if(proj.getColor() == Color.GREEN)setHealth(getHealth() - playerProjectiles.chargedShoot);
		if(proj.getColor() == Color.YELLOW)setHealth(getHealth() - playerProjectiles.goldenChargedShoot);
  }

public void explode(long currentTime){
  	setState(GameStates.EXPLODING);

		setExplosionStart(currentTime);

		setExplosionEnd(currentTime + 250);
	}


public void activateAbility2(long currentTime, playerProjectiles proj){
              setAbility2State(true);
              setX(GameLib.WIDTH * Math.random());
              setY(GameLib.HEIGHT * Math.random());
              setAbility2Ending(currentTime + 2500);
              if(getSpeed() < 0)setSpeed(-getSpeed());
              if(getSpeed1() < 0)setSpeed1(-getSpeed1());
              setAbility1State(0);

  }


public void collide(long currentTime, playerProjectiles proj){

	if(getHealth() <= 0)explode(currentTime);
	else {
  if (getAbility2State() == false && getAbility2Cooldown() < currentTime && Boss2.getIsTimeStopped() == false && proj.getColor() != Color.YELLOW)
	activateAbility2(currentTime, proj);

	else takeDamage(proj);
	}
}

public boolean update(long currentTime, long delta, double playerY){

					if(getAbility2State() == false) {


				  if(getAbility1State() == 0 && getX() > 480){
				  	setAbility1State(1);
				    setSpeed(-getSpeed());}
				  if(getAbility1State() == 1 && (getX() < 0 || getY() > 650)){
				  setAbility1State(2);
				  setSpeed(-getSpeed());}
				  if(getAbility1State() == 2 && getX() > 480){
				  	setAbility1State(3);
				  	setSpeed(-getSpeed());
				  	setSpeed1(-getSpeed1());}
				  if(getAbility1State() == 3 && (getX() < 0 || getY() < 30)){
				  setAbility1State(0);
				  setSpeed(-getSpeed());
				  setSpeed1(-getSpeed1());}

					setX(getX() + getSpeed() * Math.sin(getAngle()) * -delta);
						if(getAbility1State() == 1 || getAbility1State() == 3)setY(getY() + getSpeed1() * Math.sin(getAngle()) * -delta);
          setAngle(getAngle() + getAngleSpeed() * delta);}

          if(getAbility2State() == true && getAbility2Ending() < currentTime){
        setX(240);
        setY(60);
        setAbility2State(false);
        setAbility2Cooldown(currentTime + 10000);}

          if(Boss2.getIsTimeStopped() == false && getAbility2State() == false){
             double chance = Math.random();

             if (chance >= 0 && chance <=0.0001){
          	    Boss2.setIsTimeStopped(true);
                Boss2.setTimeStoppedFinal(currentTime + 10000);}
          }
        if(Boss2.getIsTimeStopped() == true && Boss2.getTimeStoppedFinal() < currentTime)Boss2.setIsTimeStopped(false);


					if(currentTime > getCooldown() && getY() < playerY){
		setCooldown((long) (currentTime +  Math.random() * 500));
		return true;
		}

return false;
  }

public void draw(long currentTime){

				if(getState() == GameStates.EXPLODING){

					double alpha = (currentTime - getExplosionStart()) / 500;
					GameLib.drawExplosion(getX(), getY(), alpha);
				}

				if(getState() == GameStates.ACTIVE){
	GameLib.setColor(Color.GRAY);
        GameLib.drawLine(getX() - getRadius() * 2,getY() - getRadius() - 5, getX() + getRadius() * 2, getY() - getRadius() - 5);

        GameLib.setColor(Color.RED);
        GameLib.drawLine(getX() - getRadius() * 2,getY() - getRadius() - 5, getX() - getRadius() * 2 + getRadius() * 4 * (double)getHealth()/getMaxHealth() , getY() - getRadius() - 5);


					GameLib.setColor(Color.WHITE);

        // Linha horizontal de cima
        GameLib.drawLine((int)getX() - getRadius(), (int)getY() - getRadius(), (int)getX() + getRadius(), getY() - getRadius());

        // Linha horizontal de baixo
        GameLib.drawLine((int)getX() - getRadius(), (int)getY() + getRadius(), (int)getX() +getRadius(), (int)getY() + getRadius());

        // Linha da esquerda de cima para direita de baixo
        GameLib.drawLine((int)getX() - getRadius(), (int)getY() - getRadius(), (int)getX() + getRadius(), (int)getY() + getRadius());

        // Linha da esquerda de baixo para direita de cima
        GameLib.drawLine((int)getX() + getRadius(), (int)getY() - getRadius(), (int)getX() - getRadius(), (int)getY() +getRadius());

				// linha da esquerda de cima para o meio
        GameLib.drawLine((int)getX() - getRadius(), (int)getY() - getRadius(), (int)getX(), (int)getY() + getRadius());

       	// linha da direita de cima para o meio
       	GameLib.drawLine((int)getX() + getRadius(), (int)getY() - getRadius(), (int)getX(), (int)getY() + getRadius());

				}                }
}
