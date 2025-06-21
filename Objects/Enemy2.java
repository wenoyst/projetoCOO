package Objects;


// devido a forma que esse inimigo foi implementado no código original, ele so esta spawnando 1 por vez, ele e bom candidado para minions de algum boss, entao nao decidi arrumar ainda

public class Enemy2 extends EnemyGeneric  {
private static long nextEnemy2 = 200;

public static void setNextEnemy2(long nextEnemy2) {
  Enemy2.nextEnemy2 = nextEnemy2;
}

public static double getNextEnemy2() {
  return nextEnemy2;
}



public Enemy2(int health, double posX, double posY, double dy, double dx, long cooldown, double EE, double ES, double radius, double dydx){
  super(health, posX, posY, dy, dx, cooldown, EE, ES, radius, dydx);

  }


}


