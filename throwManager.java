import java.util.Scanner;
import java.io.File;
import java.awt.Color;
import java.util.List;
import java.io.FileNotFoundException;

public class throwManager{

private String[] things;
private Scanner levelConf;
private long nextThing;
private File levelFile;


    private List<Boss1> funny;
    private List<Enemy1> enem1;
    private List<Enemy2> enem2;
    private List<PowerUp1> pu1;
    private List<PowerUp2> pu2;
    private List<Boss2> timeLord;
    private RenderManager Renderer;

    public throwManager(List<Projectiles> enemProj, List<Boss1> funny, List<Boss2> timeLord, List<Enemy1> enem1, List<Enemy2> enem2, List<PowerUp1> pu1, List<PowerUp2> pu2, RenderManager Renderer, File levelFile) {
    this.funny = funny;
    this.enem1 = enem1;
    this.enem2 = enem2;
    this.pu1 = pu1;
    this.pu2 = pu2;
    this.timeLord = timeLord;
    this.Renderer = Renderer;
    this.levelFile = levelFile;

    try {
      this.levelConf = new Scanner(this.levelFile);}
     catch (FileNotFoundException e) {
            System.out.println("erro");
            e.printStackTrace();
        }
  }


public long getNextThing() {
  return nextThing;
}



void getThings(long currentTime){
String Thing = levelConf.nextLine();
things = Thing.split(" ");

if (things[0].equals("BOSS")){
			nextThing = Long.parseLong(things[3]) + currentTime;}
else nextThing = Long.parseLong(things[2]) + currentTime;

	}



void throwThings(long currentTime){
if (things[0].equals("INIMIGO")){
		if (Integer.parseInt(things[1]) == 1){
		Enemy1 newEnem = new Enemy1(3, Double.parseDouble(things[3]), Double.parseDouble(things[4]), (3 * Math.PI) / 2, 0.20 + Math.random() * 0.15, 9,0,Color.YELLOW);
		Renderer.register(newEnem);
		enem1.add(newEnem);}
    else {
    Enemy2 newEnem2 = new Enemy2(1, Double.parseDouble(things[3]), Double.parseDouble(things[4]), (3 * Math.PI) / 2, 0.42, 12,0, Color.PINK);
    Renderer.register(newEnem2);
    enem2.add(newEnem2);}
		}

if(things[0].equals("POWERUP")){
        if(Integer.parseInt(things[1]) == 1) {
        PowerUp1 newPu1 = new PowerUp1(Color.GREEN, 0, 0.20 + Math.random() * 0.15, Double.parseDouble(things[3]), Double.parseDouble(things[4]), 3);
        Renderer.register(newPu1);
        pu1.add(newPu1);}
        else {
        PowerUp2 newPu2 = new PowerUp2(Color.YELLOW, 0, 0.20 + Math.random() * 0.15, Double.parseDouble(things[3]), Double.parseDouble(things[4]), 3);
        Renderer.register(newPu2);
        pu2.add(newPu2);};
		}

if (things[0].equals("CHEFE")){
if(Integer.parseInt(things[1]) == 1){
        Boss1 newFunny = new Boss1(Integer.parseInt(things[2]), Integer.parseInt(things[2]), Double.parseDouble(things[4]), Double.parseDouble(things[5]), (3 * Math.PI)/2, 0.20 + Math.random() * 0.15, 20, 0.0, false);
			  Renderer.register(newFunny);
			  funny.add(newFunny);
			}
	else{
Boss2 timeLord1 = new Boss2(Integer.parseInt(things[2]), Integer.parseInt(things[2]), Double.parseDouble(things[4]), Double.parseDouble(things[5]), (3 * Math.PI)/2, 0.20 + Math.random() * 0.15 * 2/3, 20, 0.0);
timeLord.add(0, timeLord1);
			    Renderer.register(timeLord1);}
		}


if(levelConf.hasNextLine()) getThings(currentTime);
		else nextThing = -1;
}



}
