package Objects;


// classe do plano de fundo

public class background{
private double x;
private double y;
private static double speed1;
private static double speed2;

private static double count1 = 0;
private static double count2 = 0;

public static void setCount1(double count1) {
  background.count1 = count1;
}

public static double getCount1() {
  return count1;
}

public static void setCount2(double count2) {
  background.count2 = count2;
}

public static double getCount2() {
  return count2;
}


public background(double x, double y){
setX(x);
setY(y);
}

public static void setSpeed1(double sd) {
  background.speed1 = sd;
}

public static double getSpeed1() {
  return speed1;
}

public static void setSpeed2(double speed2) {
  background.speed2 = speed2;
}

public static double getSpeed2() {
  return speed2;
}

public void setX(double x) {
  this.x = x;
}

public double getX() {
  return x;
}

public void setY(double y) {
  this.y = y;
}

public double getY() {
  return y;
  }
}

