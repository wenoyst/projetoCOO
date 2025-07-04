import java.util.List;
import GameEngine.*;
import java.awt.Color;


public class UpdateManager{


    private List<playerProjectiles> playerProj;
    private List<Projectiles> enemProj;
    private List<Boss1> funny;
    private List<Enemy1> enem1;
    private List<Enemy2> enem2;
    private List<PowerUp1> pu1;
    private List<PowerUp2> pu2;
    private List<Boss2> timeLord;
    private Player player;
    private RenderManager Renderer;
    private List<background1> bg1;
   private List<background2> bg2;

    public UpdateManager(Player player, List<playerProjectiles> playerProj, List<Projectiles> enemProj, List<Boss1> funny, List<Boss2> timeLord, List<Enemy1> enem1, List<Enemy2> enem2, List<PowerUp1> pu1, List<PowerUp2> pu2, List<background1> bg1, List<background2> bg2, RenderManager Renderer) {
    this.player = player;
    this.playerProj = playerProj;
    this.enemProj = enemProj;
    this.funny = funny;
    this.enem1 = enem1;
    this.enem2 = enem2;
    this.pu1 = pu1;
    this.pu2 = pu2;
    this.timeLord = timeLord;
    this.bg1 = bg1;
    this.bg2 = bg2;
    this.Renderer = Renderer;
    }

  public boolean checkUpdates(long delta, long currentTime){

  if (player.update(delta, currentTime) == true) {
playerProjectiles newWeapon = player.fireWeapon(currentTime);
Renderer.register(newWeapon);
playerProj.add(newWeapon);}



	for(int i = 0; i < playerProj.size(); i++){
      playerProjectiles proj = playerProj.get(i);
			if(proj.getState() == GameStates.ACTIVE) proj.update(delta);

		  else if(proj.getState() == GameStates.INACTIVE) {
		     	Renderer.unregister(proj);
		        playerProj.remove(i);
		    }
	}


		for(int i = 0; i < enemProj.size(); i++){
      Projectiles proj = enemProj.get(i);
			if(proj.getState() == GameStates.ACTIVE)proj.update(delta);
		  else if(proj.getState() == GameStates.INACTIVE) {
		        Renderer.unregister(proj);
		        enemProj.remove(i);
		    }
	}


    // powerup 1
		for(int i = 0; i < pu1.size(); i++){
      PowerUp1 p  = pu1.get(i);

			if(p.getState() == GameStates.ACTIVE)p.update(currentTime, delta, player);
			else if (p.getState() == GameStates.INACTIVE) {
			    Renderer.unregister(p);
			    pu1.remove(i);}
			}

					/* powerup 2 */

		for(int i = 0; i < pu2.size(); i++){
        PowerUp2 p  = pu2.get(i);
				if(p.getState() == GameStates.ACTIVE)p.update(currentTime, delta);
				else if (p.getState() == GameStates.INACTIVE) {
                Renderer.unregister(p);
				pu2.remove(i);}
			}
     //inimigo tipo 1

	   for(int i = 0; i < enem1.size(); i++){
     Enemy1 enem = enem1.get(i);
        enem.checkExplosionE(currentTime);
				if(enem.getState() == GameStates.ACTIVE){
		   if (enem.update(currentTime, delta, player.getY()) == true) {
            Projectiles newShoot = enem.Shoot();
            Renderer.register(newShoot);
		    enemProj.add(newShoot);};
			}
			if (enem.getState() == GameStates.INACTIVE) {
			    Renderer.unregister(enem);
			    enem1.remove(i);}}

    			/* inimigos tipo 2 */

			for(int i = 0; i < enem2.size(); i++){
       Enemy2 enem = enem2.get(i);

			 if(enem.getState() == GameStates.EXPLODING){
					if(currentTime > enem.getExplosionEnd()){enem.setState(GameStates.INACTIVE);}
			 }

			if(enem.getState() == GameStates.ACTIVE){

        if(enem.update(delta) == true) {
    Projectiles newShoot = enem.Shoot();
    Renderer.register(newShoot);
    enemProj.add(newShoot);}
				}
      else if (enem.getState() == GameStates.INACTIVE) {
                Renderer.unregister(enem);
                enem2.remove(i);}
			}
//
		for(int i = 0; i < funny.size(); i++){
     Boss1 fun = funny.get(i);
		 if(fun.getState() == GameStates.EXPLODING){
					if(currentTime > fun.getExplosionEnd()){fun.setState(GameStates.INACTIVE);}
				}
				if(fun.getState() == GameStates.ACTIVE){
     if(fun.update(currentTime, delta, player.getY()) == true) {
            Projectiles newFunShoot = fun.Shoot();
                    Renderer.register(newFunShoot);
                    enemProj.add(newFunShoot);}
     if(fun.updateAbility(currentTime) == true) {
Boss1 newFunny = new Boss1(fun.getMaxHealth(), fun.getMaxHealth(), fun.getX() - 20, fun.getY(), fun.getAngle(), fun.getSpeed(), fun.getRadius(), fun.getAngleSpeed(), true);
Renderer.register(newFunny);
funny.add(newFunny);}
			}
			if (fun.getState() == GameStates.INACTIVE) {
			   Renderer.unregister(fun);
			  funny.remove(i);
			  return true;}}





	   for (int i = 0; i < timeLord.size(); i++){
	    Boss2 tl = timeLord.get(i);
		 if(tl.getState() == GameStates.EXPLODING){
					if(currentTime > tl.getExplosionEnd()){tl.setState(GameStates.INACTIVE);}
				}

				if(tl.getState() == GameStates.ACTIVE){
       if (tl.update(currentTime, delta, player.getY()) == true){
     Projectiles newProj = new Projectiles(Color.WHITE,Math.cos(tl.getAngle()) * 0.45, Math.sin(tl.getAngle()) * 0.45 * (-1.0), tl.getX(), tl.getY(), 2);
     Renderer.register(newProj);
     enemProj.add(newProj);
                if(tl.getAbility2State() == true){
                Projectiles newProj1 = new Projectiles(Color.WHITE,Math.cos(tl.getAngle()) * 0.45, Math.sin(tl.getAngle()) * 0.45, tl.getX(), tl.getY(), 2);
                Renderer.register(newProj1);
                enemProj.add(newProj1);
                Projectiles newProj2 = new Projectiles(Color.WHITE,Math.sin(tl.getAngle()) * 0.45, Math.cos(tl.getAngle()) * 0.45 * (-1.0), tl.getX(), tl.getY(), 2);
                Renderer.register(newProj2);
                enemProj.add(newProj2);
                Projectiles newProj3 = new Projectiles(Color.WHITE,Math.sin(tl.getAngle()) * -0.45, Math.cos(tl.getAngle()) * 0.45 * (-1.0), tl.getX(), tl.getY(), 2);
                Renderer.register(newProj3);
                enemProj.add(newProj3);
          }
        }
			}
			if (tl.getState() == GameStates.INACTIVE){
			    Renderer.unregister(tl);
				timeLord.remove(i);
				return true;
			    }
		}

 if(Boss2.getIsTimeStopped() == false)
			background1.setCount(background1.getCount() + background1.getSpeed() * delta);

	 if(Boss2.getIsTimeStopped() == false)
			background2.setCount(background2.getCount() + background2.getSpeed() * delta);

return false;
  }


}
