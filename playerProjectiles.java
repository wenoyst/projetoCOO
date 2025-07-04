import Interfaces.*;
import GameEngine.GameStates;
import java.awt.Color;



public class playerProjectiles implements Entity, Drawable
{

public static final int shoot = 1;
public static final int chargedShoot = 3;
public static final int goldenChargedShoot = 10;


private double Dx;
private double Dy;
private double X;
private double Y;
private int state;
private double radius;
private Color color;



public Color getColor() {
  return color;
}

public void setColor(Color color) {
  this.color = color;
}

public playerProjectiles (Color color, double Dx, double Dy, double X, double Y,double radius){
setDx(Dx);
setDy(Dy);
setX(X);
setY(Y);
setState(GameStates.ACTIVE);
setRadius(radius);
setColor(color);
  }


public double getRadius() {
  return radius;
}

public void setRadius(double radius) {
  this.radius = radius;
}

public void setY(double y) {
  Y = y;
}
public void setX(double x) {
  X = x;
}

public double getX() {
  return X;
}


public double getDx() {
  return Dx;
}
public double getDy() {
  return Dy;
}

public void setDx(double dx) {
  Dx = dx;
}

public void setDy(double dy) {
  Dy = dy;
}

public void setState(int state) {
  this.state = state;
}
public int getState() {
  return state;
}

public double getY() {
  return Y;
}


public void update(long delta){
		if(getY() < 0) {setState(GameStates.INACTIVE);}

			else {
						setX(getX() + getDx() * delta);
						setY(getY() + getDy() * delta);
					 }
  }

public void collide(){

setState(GameStates.INACTIVE);
  }

public void draw(long currentTime){

		if(getState() == GameStates.ACTIVE){
					 	GameLib.setColor(getColor());
				  if(Boss2.getIsTimeStopped() == true && (getColor() == Color.WHITE || getColor() == Color.GREEN)) GameLib.setColor(Color.GRAY);
					GameLib.drawLine(getX(), getY() - 5, getX(), getY() + 5);
					GameLib.drawLine(getX() - 1, getY() - 3, getX() - 1, getY() + 3);
					GameLib.drawLine(getX() + 1, getY() - 3, getX() + 1, getY() + 3);
		}
	};
}
