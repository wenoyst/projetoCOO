import java.awt.Color;
import Interfaces.*;
import GameEngine.GameStates;

public class PowerUp2 implements Entity,Drawable {

private static final long PowerUp2Duration = 30000;

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
  PowerUp2.powerEnd = powerEnd;
}

public static void setActive(boolean isActive) {
  PowerUp2.isActive = isActive;
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

public PowerUp2(Color color, double dx, double dy, double X, double Y, double radius){
setColor(color);
setDx(dx);
setDy(dy);
setX(X);
setY(Y);
setRadius(radius);
setState(GameStates.ACTIVE);
}


public void update(long currentTime,long delta){
	/* verificando se projétil saiu da tela */
		if(getY() > GameLib.HEIGHT) setState(GameStates.INACTIVE);
		else {
					setX(getX() + getDx() * delta);
					setY(getY() + getDy() * delta);
			}
		if(PowerUp2.getActive() == true && PowerUp2.getPowerEnd() < currentTime){
			PowerUp2.setActive(false);}
  }

public void collide(long currentTime){
    setState(GameStates.INACTIVE);
    PowerUp2.setActive(true);
    PowerUp2.setPowerEnd(currentTime + PowerUp2Duration);
  }

public void draw(long currentTime){
   	if(getState() == GameStates.ACTIVE){

				GameLib.setColor(getColor());
				// linha horizontal de cima
				GameLib.drawLine(getX() - getRadius() , getY() - getRadius(), getX() + getRadius(), getY() - getRadius());
        // linha horizontal de baixo
        GameLib.drawLine(getX() - getRadius() , getY() + getRadius(), getX() + getRadius(), getY() + getRadius());
        // linha vertical esquerda
        GameLib.drawLine(getX() - getRadius() , getY() - getRadius(), getX() - getRadius(), getY() + getRadius());
        // linha vertical direita
        GameLib.drawLine(getX() + getRadius() , getY() - getRadius(), getX() + getRadius(), getY() + getRadius());
		}
  }
}
