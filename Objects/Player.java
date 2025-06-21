package Objects;
import states.GameStates;


//dx é a velocidade no plano x, dy é a velocidade no plano y

public class Player implements Entity {
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

public Player(int health, double posX, double posY, double dy, double dx, long cooldown, double EE, double ES, double radius)  {
    setX(posX);
    setY(posY);
    setHealth(health);
    setDy(dy);
    setDx(dx);
    setCooldown(cooldown);
    setRadius(radius);
    setExplosionEnd(EE);
    setExplosionStart(ES);
    setState(GameStates.ACTIVE);

  }
}





