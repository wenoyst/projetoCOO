package Objects;


//classe inigimo generico, dx é a velocidade, dy é o angulo, e  dydx é a velocidade de rotacao do angulo.

public class EnemyGeneric extends Player {

private double dydx;

public double getDydx() {
  return dydx;
}


public void setDydx(double dydx) {
  this.dydx = dydx;
}

EnemyGeneric(int health, double posX, double posY, double dy, double dx, long cooldown, double EE, double ES, double radius, double dydx){
  super(health, posX, posY, dy, dx, cooldown, EE, ES, radius);

  setDydx(dydx);

  }


}


