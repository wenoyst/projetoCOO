import java.util.List;
import GameEngine.*;

public class CollisionManager {

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

    public CollisionManager(Player player, List<playerProjectiles> playerProj, List<Projectiles> enemProj, List<Boss1> funny, List<Boss2> timeLord, List<Enemy1> enem1, List<Enemy2> enem2, List<PowerUp1> pu1, List<PowerUp2> pu2, RenderManager Renderer) {
    this.player = player;
    this.playerProj = playerProj;
    this.enemProj = enemProj;
    this.funny = funny;
    this.enem1 = enem1;
    this.enem2 = enem2;
    this.pu1 = pu1;
    this.pu2 = pu2;
    this.timeLord = timeLord;
    this.Renderer = Renderer;
    }



    public void checkCollisions(long currentTime) {

    	 /* colisões player - projeteis (inimigo) */
	for(int i = 0; i < enemProj.size(); i++){
    Projectiles proj  = enemProj.get(i);
		double dx = proj.getX() - player.getX();
		double dy = proj.getY() - player.getY();
		double dist = Math.sqrt(dx * dx + dy * dy);
    if(dist < (player.getRadius() + proj.getRadius()) * 0.8 && player.getState() == GameStates.ACTIVE){
       proj.setState(GameStates.INACTIVE);
       player.explode(currentTime);
	}
    }



	 /* colisões player - powerups */

	for(int i = 0; i < pu1.size(); i++){
     PowerUp1 p = pu1.get(i);
		 double dx = p.getX() - player.getX();
		 double dy = p.getY() - player.getY();
		 double dist = Math.sqrt(dx * dx + dy * dy);

		 if(dist < (player.getRadius() + p.getRadius()) * 0.8 && player.getState() == GameStates.ACTIVE) p.Collide(currentTime);

	   }


			for(int i = 0; i < pu2.size(); i++){
     PowerUp2 p = pu2.get(i);
		 double dx = p.getX() - player.getX();
		 double dy = p.getY() - player.getY();
		 double dist = Math.sqrt(dx * dx + dy * dy);

		         if(dist < (player.getRadius() + p.getRadius()) * 0.8 && player.getState() == GameStates.ACTIVE)p.collide(currentTime);
	}


   // colissões playeer com inimigo tipo 1 e 2, respectivamente
	for(int i = 0; i < enem1.size(); i++){
    Enemy1 enem = enem1.get(i);
			double dx = enem.getX() - player.getX();
			double dy = enem.getY() - player.getY();
			double dist = Math.sqrt(dx * dx + dy * dy);

			if(dist < (player.getRadius() + enem.getRadius()) * 0.8 && player.getState() == GameStates.ACTIVE)player.explode(currentTime);
	}


	for(int i = 0; i < enem2.size(); i++){
      Enemy2 enem = enem2.get(i);
			double dx = enem.getX() - player.getX();
			double dy = enem.getY() - player.getY();
			double dist = Math.sqrt(dx * dx + dy * dy);

			if(dist < (player.getRadius() + enem.getRadius()) * 0.8 && player.getState() == GameStates.ACTIVE)player.explode(currentTime);
	}

     //colisoes player boss1

	for(int i = 0; i < funny.size(); i++){
     Boss1 fun = funny.get(i);
		 double dx = fun.getX() - player.getX();
		 double dy = fun.getY() - player.getY();
		 double dist = Math.sqrt(dx * dx + dy * dy);

		if(dist < (player.getRadius() + fun.getRadius()) * 0.8 && player.getState() == GameStates.ACTIVE)player.explode(currentTime);
	}

   // colisoes player boss 2
 	for (int i = 0; i < timeLord.size(); i++){
 			Boss2 tl = timeLord.get(i);
		 double dx = tl.getX() - player.getX();
		 double dy = tl.getY() - player.getY();
		 double dist = Math.sqrt(dx * dx + dy * dy);

		 if(dist < (player.getRadius() + tl.getRadius()) * 0.8 && player.getState() == GameStates.ACTIVE)player.explode(currentTime);
	}




	/* colisões projeteis (player) - inimigos */

	for(int k = 0; k < playerProj.size(); k++){
      playerProjectiles proj = playerProj.get(k);





			for(int i = 0; i < enem1.size(); i++){
          Enemy1 enem = enem1.get(i);
					if(enem.getState() == GameStates.ACTIVE){
						double dx = enem.getX() - proj.getX();
						double dy = enem.getY() - proj.getY();
						double dist = Math.sqrt(dx * dx + dy * dy);

						if(dist < enem.getRadius()){

						proj.collide();
					    enem.collide(currentTime, proj.getColor());

				}
			  }
			}


			for(int i = 0; i < enem2.size(); i++){
          Enemy2 enem = enem2.get(i);
					if(enem.getState() == GameStates.ACTIVE){
						double dx = enem.getX() - proj.getX();
						double dy = enem.getY() - proj.getY();
						double dist = Math.sqrt(dx * dx + dy * dy);

						if(dist < enem.getRadius()){

						proj.collide();
					    enem.collide(currentTime, proj.getColor());

				}
			  }
			}


 // boss 1
	for(int i = 0; i < funny.size(); i++){
        Boss1 fun = funny.get(i);
		if(fun.getState() == GameStates.ACTIVE){
			double dx = fun.getX() - proj.getX();
			double dy = fun.getY() - proj.getY();
			double dist = Math.sqrt(dx * dx + dy * dy);
			if(dist < fun.getRadius()){
			  fun.collide(currentTime, proj, enemProj, Renderer);
              fun.activateAbility1(currentTime);
              proj.collide();


				}
			  }
			}

    // boss 2
  	for (int i = 0; i < timeLord.size(); i++){
  			Boss2 tl = timeLord.get(i);
					if(tl.getState() == GameStates.ACTIVE){
						double dx = tl.getX() - proj.getX();
						double dy = tl.getY() - proj.getY();
						double dist = Math.sqrt(dx * dx + dy * dy);

						if(dist < tl.getRadius()){
            tl.collide(currentTime, proj);
						proj.collide();
				}
		      }
			}
		}
    }
}
