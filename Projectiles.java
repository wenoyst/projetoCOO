import Interfaces.*;
import GameEngine.GameStates;
import java.awt.Color;



public class Projectiles implements Entity, Drawable
{
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

public Projectiles (Color color, double Dx, double Dy, double X, double Y,double radius){
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

	if(getY() < 0) setState(GameStates.INACTIVE);

	else {
				setX(getX() + getDx() * delta);
				setY(getY() + getDy() * delta);
			 }
	}

public void draw(long currentTime){

		if(getState() == GameStates.ACTIVE){
					GameLib.setColor(getColor());
					GameLib.drawCircle(getX(), getY(), getRadius());
		}
	};
}

