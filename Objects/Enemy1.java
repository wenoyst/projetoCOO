package Objects;

public class Enemy1 extends EnemyGeneric  {
private static long nextEnemy1 = 200;

public static void setNextEnemy1(long nextEnemy1) {
  Enemy1.nextEnemy1 = nextEnemy1;
}

public static long getNextEnemy1() {
  return nextEnemy1;
}



public Enemy1(int health, double posX, double posY, double dy, double dx, long cooldown, double EE, double ES, double radius, double dydx){
  super(health, posX, posY, dy, dx, cooldown, EE, ES, radius, dydx);

  }


}


