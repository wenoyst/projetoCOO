import java.awt.Color;
import Interfaces.*;
import GameEngine.GameStates;

public class PowerUp1 implements Entity,Drawable {

private static final long PowerUp1Duration = 60000;

private static boolean isActive;

private static double powerEnd;

private int state;

private double X;

private double Y;

private double Radius;

private Color color;

private double Dx;

private double Dy;

// getters

public double getX() {
  return X;
}

public double getY() {
  return Y;
}

public Color getColor() {
  return color;
}

public double getRadius() {
  return Radius;
}

public static double getPowerEnd() {
  return powerEnd;
}

public static boolean getActive(){
  return isActive;}

public int getState() {
  return state;
}

public double getDx() {
  return Dx;
}

public double getDy() {
  return Dy;
}

// setters

public static void setPowerEnd(double powerEnd) {
  PowerUp1.powerEnd = powerEnd;
}

public static void setActive(boolean isActive) {
  PowerUp1.isActive = isActive;
}

public void setColor(Color color) {
  this.color = color;
}

public void setRadius(double radius) {
  Radius = radius;
}

public void setX(double x) {
  X = x;
}

public void setY(double y) {
  Y = y;
}
public void setState(int state) {
  this.state = state;
}

public void setDy(double dy) {
  Dy = dy;
}
public void setDx(double dx) {
  Dx = dx;
}

public PowerUp1(Color color, double dx, double dy, double X, double Y, double radius){
setColor(color);
setDx(dx);
setDy(dy);
setX(X);
setY(Y);
setRadius(radius);
setState(GameStates.ACTIVE);
}

public void update(long currentTime,long delta, Player player){
		if(getY() > GameLib.HEIGHT) setState(GameStates.INACTIVE);
		else {
						setX(getX() + getDx() * delta);
						setY(getY() + getDy() * delta);
					}
if(PowerUp1.getActive() == true && PowerUp1.getPowerEnd() < currentTime){
			PowerUp1.setActive(false);
			player.setDx(player.getDx() - 0.2);
			player.setDy(player.getDy() - 0.2);
    }
  }

void Collide(long currentTime){
        setState(GameStates.INACTIVE);
        PowerUp1.setActive(true);
        PowerUp1.setPowerEnd(currentTime + PowerUp1Duration);
  }

public void draw(long currentTime){
   	if(getState() == GameStates.ACTIVE){
					GameLib.setColor(getColor());
					GameLib.drawCircle(getX(), getY(), getRadius());
					GameLib.drawCircle(getX(), getY(), getRadius() + 2);
					GameLib.drawCircle(getX(), getY(), getRadius() + 3);
		}
  }
}
