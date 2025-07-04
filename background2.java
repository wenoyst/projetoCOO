import java.awt.Color;

// classe do plano de fundo

import Interfaces.Drawable;

public class background2 implements Drawable{
private double x;
private double y;
private static double speed;

private static double count = 0;

public static void setCount(double count) {
  background2.count = count;
}

public static double getCount() {
  return count;
}

public background2(double x, double y){
setX(x);
setY(y);
}

public static void setSpeed(double sd) {
  background2.speed = sd;
}

public static double getSpeed() {
  return speed;
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

public void draw(long currentTime) {

		  	GameLib.setColor(Color.DARK_GRAY);
		  GameLib.fillRect(getX(), (getY() + getCount()) % GameLib.HEIGHT, 3, 3);



}
}

