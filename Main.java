import java.awt.Color;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import GameEngine.*;
import java.io.File;
import java.io.FileNotFoundException;





public class Main {



	public static void main(String [] args){
  String level1, level2;
  int health = 0;
  level1 = "error";
  level2 ="error";
try {
            File gameConf = new File("save.txt");
            Scanner conf = new Scanner(gameConf);
            health = conf.nextInt();
            level1 = conf.nextLine();
            level2 = conf.nextLine();
            level1 = conf.nextLine();
            level2 = conf.nextLine();
            conf.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
boolean loop = true;
while(loop == true){
    Game start = new Game(level1, health);
if(start.getWin() == true) loop = false;
}

while(loop == true){
Game start = new Game(level2, health);
if(start.getWin() == true) loop = false;
}

		System.exit(0);	}
}
