package Objects;
import states.GameStates;

public class Projectiles implements Entity
{
private double Dx;
private double Dy;
private double X;
private double Y;
private int state;
private double radius;
private double EE;
private double ES;



public Projectiles (double Dx, double Dy, double X, double Y,double radius, double ES, double EE){
setDx(Dx);
setDy(Dy);
setX(X);
setY(Y);
setState(GameStates.ACTIVE);
setRadius(radius);
setExplosionStart(ES);
setExplosionEnd(EE);
  }




public double getRadius() {
  return radius;
}

public void setRadius(double radius) {
  this.radius = radius;
}

public void setExplosionEnd(double eE) {
  this.EE = eE;
}

public double getExplosionEnd() {
  return EE;
}

public double getExplosionStart() {
  return ES;
}

public void setExplosionStart(double eS) {
  this.ES = eS;
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
}

