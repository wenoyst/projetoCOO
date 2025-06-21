import java.awt.Color;

import states.GameStates;
import java.util.List;
import java.util.ArrayList;
import Objects.Enemy1;
import Objects.Enemy2;
import Objects.Projectiles;
import Objects.playerProjectiles;
import Objects.Player;
import Objects.background;






public class Main {


	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time.    */

	public static void busyWait(long time){

		while(System.currentTimeMillis() < time) Thread.yield();
	}




	/* Método principal */

	public static void main(String [] args){

		/* Indica que o jogo está em execução */

		boolean running = true;

		/* variáveis usadas no controle de tempo efetuado no main loop */

		long delta;
		long currentTime = System.currentTimeMillis();

		/* variáveis do player */

  // classe do player
  Player player = new Player(3, GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25, 0.25, currentTime, 0, 0, 12.0);



		/* variáveis dos projéteis disparados pelo player */

    // os projeteis do player eh uma lista de objetos do tipo player projectiles
    List<playerProjectiles> pP = new ArrayList<>();

		/* variáveis dos inimigos tipo 1 */
  // os inimigos 1 e 2 sao listas de seu respectives objetos
   List<Enemy1> Enemy1s = new ArrayList<>();
     List<Enemy2> Enemy2s = new ArrayList<>();
   // os projeteis dos inimigos e uma lista de projeteis normais
    List<Projectiles> enemProjectiles = new ArrayList<>();

    List<background> bg1 = new ArrayList<>();
    List<background> bg2 = new ArrayList<>();

		for(int i = 0; i < 50; i++){
     bg1.add(new background(Math.random() * GameLib.WIDTH,	Math.random() * GameLib.HEIGHT));
		}
    		for(int i = 0; i < 20; i++){
     bg2.add(new background(Math.random() * GameLib.WIDTH,	Math.random() * GameLib.HEIGHT));
		}


background.setSpeed1(0.07);
background.setSpeed2(0.045);
		/* iniciado interface gráfica */

		GameLib.initGraphics();
		//GameLib.initGraphics_SAFE_MODE();  // chame esta versão do método caso nada seja desenhado na janela do jogo.

		/*************************************************************************************************/
		/*                                                                                               */
		/* Main loop do jogo                                                                             */
		/* -----------------                                                                             */
		/*                                                                                               */
		/* O main loop do jogo executa as seguintes operações:                                           */
		/*                                                                                               */
		/* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
		/*                                                                                               */
		/* 2) Atualiza estados dos elementos baseados no tempo que correu entre a última atualização     */
		/*    e o timestamp atual: posição e orientação, execução de disparos de projéteis, etc.         */
		/*                                                                                               */
		/* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
		/*                                                                                               */
		/* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
		/*                                                                                               */
		/* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
		/*                                                                                               */
		/*************************************************************************************************/

		while(running){

			/* Usada para atualizar o estado dos elementos do jogo    */
			/* (player, projéteis e inimigos) "delta" indica quantos  */
			/* ms se passaram desde a última atualização.             */

			delta = System.currentTimeMillis() - currentTime;

			/* Já a variável "currentTime" nos dá o timestamp atual.  */

			currentTime = System.currentTimeMillis();

			/***************************/
			/* Verificação de colisões */
			/***************************/

			if(player.getState() == GameStates.ACTIVE){

				/* colisões player - projeteis (inimigo) */

				for(int i = 0; i < enemProjectiles.size(); i++){
       Projectiles proj  = enemProjectiles.get(i);
					double dx = proj.getX() - player.getX();
					double dy = proj.getY() - player.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);

					if(dist < (player.getRadius() + proj.getRadius()) * 0.8){
            player.setHealth(player.getHealth() - 1);
						player.setState(GameStates.EXPLODING);
						player.setExplosionStart(currentTime);
						player.setExplosionEnd(currentTime + 2000);
					}
				}

				/* colisões player - inimigos */

				for(int i = 0; i < Enemy1s.size(); i++){
        Enemy1 enem = Enemy1s.get(i);
					double dx = enem.getX() - player.getX();
					double dy = enem.getY() - player.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);

					if(dist < (player.getRadius() + enem.getRadius()) * 0.8){
            player.setHealth(player.getHealth() - 1);
						player.setState(GameStates.EXPLODING);
						player.setExplosionStart(currentTime);
						player.setExplosionEnd(currentTime + 2000);
					}
				}

				for(int i = 0; i < Enemy2s.size(); i++){
          Enemy2 enem = Enemy2s.get(i);
					double dx = enem.getX() - player.getX();
					double dy = enem.getY() - player.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);

					if(dist < (player.getRadius() + enem.getRadius()) * 0.8){
            player.setHealth(player.getHealth() -1);
						player.setState(GameStates.EXPLODING);
						player.setExplosionStart(currentTime);
						player.setExplosionEnd(currentTime + 2000);
					}
				}
			}

			/* colisões projeteis (player) - inimigos */

			for(int k = 0; k < pP.size(); k++){
      playerProjectiles proj = pP.get(k);
				for(int i = 0; i < Enemy1s.size(); i++){
        Enemy1 enem = Enemy1s.get(i);
					if(enem.getState() == GameStates.ACTIVE){

						double dx = enem.getX() - proj.getX();
						double dy = enem.getY() - proj.getY();
						double dist = Math.sqrt(dx * dx + dy * dy);

						if(dist < enem.getRadius()){

							enem.setState(GameStates.EXPLODING);
							enem.setExplosionStart(currentTime);
							enem.setExplosionEnd(currentTime + 250);
						}
					}
				}

				for(int i = 0; i < Enemy2s.size(); i++){
          Enemy2 enem = Enemy2s.get(i);
					if(enem.getState() == GameStates.ACTIVE){

						double dx = enem.getX() - proj.getX();
						double dy = enem.getY() - proj.getY();
						double dist = Math.sqrt(dx * dx + dy * dy);

						if(dist < enem.getRadius()){

							enem.setState(GameStates.EXPLODING);
							enem.setExplosionStart(currentTime);
							enem.setExplosionEnd(currentTime + 500);
						}
					}
				}
			}

			/***************************/
			/* Atualizações de estados */
			/***************************/

			/* projeteis (player) */

			for(int i = 0; i < pP.size(); i++){
        playerProjectiles proj = pP.get(i);
				if(proj.getState() == GameStates.ACTIVE){
					/* verificando se projétil saiu da tela */
					if(proj.getY() < 0) {proj.setState(GameStates.INACTIVE);}

					else {
						proj.setX(proj.getX() + proj.getDx() * delta);
						proj.setY(proj.getY() + proj.getDy() * delta);
					}
				}
		    else if(proj.getState() == GameStates.INACTIVE) pP.remove(i);
			}

			/* projeteis (inimigos) */

			for(int i = 0; i < enemProjectiles.size(); i++){
        Projectiles proj  = enemProjectiles.get(i);
				if(proj.getState() == GameStates.ACTIVE){

					/* verificando se projétil saiu da tela */
					if(proj.getY() > GameLib.HEIGHT) {

						proj.setState(GameStates.INACTIVE);
					}
					else {

						proj.setX(proj.getX() + proj.getDx() * delta);
						proj.setY(proj.getY() + proj.getDy() * delta);
					}
				}
			}

			/* inimigos tipo 1 */

			for(int i = 0; i < Enemy1s.size(); i++){
      Enemy1 enem = Enemy1s.get(i);
				if(enem.getState() == GameStates.EXPLODING){

					if(currentTime > enem.getExplosionEnd()){

						enem.setState(GameStates.INACTIVE);
					}
				}

				if(enem.getState() == GameStates.ACTIVE){

					/* verificando se inimigo saiu da tela */
					if(enem.getY() > GameLib.HEIGHT + 10) {

						enem.setState(GameStates.INACTIVE);
					}
					else {

						enem.setX(enem.getX() + enem.getDx() * Math.cos(enem.getDy()) * delta);
						enem.setY(enem.getY() + enem.getDx() * Math.sin(enem.getDy()) * delta * (-1.0));
						enem.setDy(enem.getDy() + enem.getDydx() * delta);

						if(currentTime > enem.getCooldown() && enem.getY() < player.getY()){



                enemProjectiles.add(new Projectiles(Math.cos(enem.getDy()) * 0.45, Math.sin(enem.getDy()) * 0.45 * (-1.0), enem.getX(), enem.getY(), 2, 0, 0));
								/*
							  e_projectile_X[free] = enem.getX();
								e_projectile_Y[free] = enem.getY();
								e_projectile_VX[free] = Math.cos(enem.getDy()) * 0.45;
								e_projectile_VY[free] = Math.sin(enem.getDy()) * 0.45 * (-1.0);
								e_projectile_states[free] = Constants.ACTIVE;
                */
								enem.setCooldown((long) (currentTime +  Math.random() * 500));
						}
					}
				}
			if (enem.getState() == GameStates.INACTIVE) Enemy1s.remove(i);
			}

			/* inimigos tipo 2 */

			for(int i = 0; i < Enemy2s.size(); i++){
       Enemy2 enem = Enemy2s.get(i);

				if(enem.getState() == GameStates.EXPLODING){

					if(currentTime > enem.getExplosionEnd()){

						enem.setState(GameStates.INACTIVE);
					}
				}

				if(enem.getState() == GameStates.ACTIVE){

					/* verificando se inimigo saiu da tela */
					if(	enem.getX() < -10 || enem.getX() > GameLib.WIDTH + 10 ) {

						enem.setState(GameStates.INACTIVE);
					}
					else {

						boolean shootNow = false;
						double previousY = enem.getY();

						enem.setX(enem.getX() + enem.getDx() * Math.cos(enem.getDy()) * delta);
						enem.setY(enem.getY() + enem.getDx() * Math.sin(enem.getDy()) * delta * (-1.0));
						enem.setDy(enem.getDy() + enem.getDydx() * delta);

						double threshold = GameLib.HEIGHT * 0.30;

						if(previousY < threshold && enem.getY() >= threshold) {

							if(enem.getX() < GameLib.WIDTH / 2) enem.setDydx(0.003);
							else enem.setDydx(-0.003);
						}

						if(enem.getDydx() > 0 && Math.abs(enem.getDy() - 3 * Math.PI) < 0.05){

							enem.setDydx(0.0);
							enem.setDy(3 * Math.PI);
							shootNow = true;
						}

						if(enem.getDydx() < 0 && Math.abs(enem.getDy()) < 0.05){

							enem.setDydx(0.0);
							enem.setDy(0.0);
							shootNow = true;
						}

						 if(shootNow){

						}
					}
				}
      else if (enem.getState() == GameStates.INACTIVE) Enemy2s.remove(i);
			}


			/* verificando se novos inimigos (tipo 1) devem ser "lançados" */

			if(currentTime > Enemy1.getNextEnemy1()){

				// int free = findFreeIndex(enemy1_states);

      Enemy1s.add(new Enemy1(3, Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0, (3 * Math.PI) / 2, 0.20 + Math.random() * 0.15, currentTime + 500, 0, 0, 9, 0.0));


      Enemy1.setNextEnemy1(currentTime + 2000);
			/* verificando se novos inimigos (tipo 2) devem ser "lançados" */

			if(currentTime > Enemy2.getNextEnemy2()){

         Enemy2s.add(new Enemy2(1, GameLib.WIDTH * 0.20, -10.0, (3 * Math.PI) / 2, 0.42, 0, 0, 0, 12, 0.0));
						Enemy2.setNextEnemy2(currentTime + 2);
				}
			}

			/* Verificando se a explosão do player já acabou.         */
			/* Ao final da explosão, o player volta a ser controlável */
			if(player.getState() == GameStates.EXPLODING){

				if(currentTime > player.getExplosionEnd()){

					player.setState(GameStates.ACTIVE);
				}
			}

			/********************************************/
			/* Verificando entrada do usuário (teclado) */
			/********************************************/

			if(player.getState() == GameStates.ACTIVE){

				if(GameLib.iskeyPressed(GameLib.KEY_UP)) player.setY(player.getY()  - delta * player.getDy());
				if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) player.setY(player.getY() + delta * player.getDy());
				if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) player.setX(player.getX() - delta * player.getDx());
				if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) player.setX(player.getX() + delta * player.getDy());

				if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

					if(currentTime > player.getCooldown()){

            pP.add(new playerProjectiles(0.0, -0.1, player.getX(), player.getY() - (2 * player.getRadius()), 3, 0.0, 0));
            player.setCooldown(currentTime + 500);

          /*
						if(free < projectile_states.length){

							projectile_X[free] = player.getX();
							projectile_Y[free] = player.getY() - 2 * player.getRadius();
							projectile_VX[free] = 0.0;
							projectile_VY[free] = -1.0;
							projectile_states[free] = ACTIVE;
							player.setCooldown(currentTime + 100);
						}
						*/
					}
				}
			}

			if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;

			/* Verificando se coordenadas do player ainda estão dentro */
			/* da tela de jogo após processar entrada do usuário.      */

			if(player.getX() < 0.0) player.setX(0.0);
			if(player.getX() >= GameLib.WIDTH) player.setX(GameLib.WIDTH - 1);
			if(player.getY() < 25.0) player.setY(25.0);
			if(player.getY() >= GameLib.HEIGHT) player.setY(GameLib.HEIGHT - 1);

			/*******************/
			/* Desenho da cena */
			/*******************/

			/* desenhando plano fundo distante */

			GameLib.setColor(Color.DARK_GRAY);
			background.setCount2(background.getCount2() + background.getSpeed2() * delta);

			for(int i = 0; i < bg2.size(); i++){
       background back = bg2.get(i);
				GameLib.fillRect(back.getX(), (back.getY() + background.getCount2()) % GameLib.HEIGHT, 2, 2);
			}

			/* desenhando plano de fundo próximo */

			GameLib.setColor(Color.GRAY);
			background.setCount1(background.getCount1() + background.getSpeed1() * delta);

			for(int i = 0; i < bg1.size(); i++){
      background back = bg1.get(i);
				GameLib.fillRect(back.getX(), (back.getY() + background.getCount1()) % GameLib.HEIGHT, 3, 3);
			}

			/* desenhando player */

			if(player.getState() == GameStates.EXPLODING){

				double alpha = (currentTime - player.getExplosionStart()) / (player.getExplosionEnd() - player.getExplosionStart());
				GameLib.drawExplosion(player.getX(), player.getY(), alpha);
			}
			else{

				GameLib.setColor(Color.BLUE);
				GameLib.drawPlayer(player.getX(), player.getY(), player.getRadius());
			}

			/* deenhando projeteis (player) */

			for(int i = 0; i < pP.size(); i++){
       playerProjectiles proj = pP.get(i);

				if(proj.getState() == GameStates.ACTIVE){

					GameLib.setColor(Color.GREEN);
					GameLib.drawLine(proj.getX(), proj.getY() - 5, proj.getX(), proj.getY() + 5);
					GameLib.drawLine(proj.getX() - 1, proj.getY() - 3, proj.getX() - 1, proj.getY() + 3);
					GameLib.drawLine(proj.getX() + 1, proj.getY() - 3, proj.getX() + 1, proj.getY() + 3);
				}
			}

			/* desenhando projeteis (inimigos) */

			for(int i = 0; i < enemProjectiles.size(); i++){
        Projectiles proj = enemProjectiles.get(i);
				if(proj.getState() == GameStates.ACTIVE){

					GameLib.setColor(Color.RED);
					GameLib.drawCircle(proj.getX(), proj.getY(), proj.getRadius());
				}
			}

			/* desenhando inimigos (tipo 1) */

			for(int i = 0; i < Enemy1s.size(); i++){
      Enemy1 enem = Enemy1s.get(i);

				if(enem.getState() == GameStates.EXPLODING){

					double alpha = (currentTime - enem.getExplosionStart()) / 500;
					GameLib.drawExplosion(enem.getX(), enem.getY(), alpha);
				}

				if(enem.getState() == GameStates.ACTIVE){

					GameLib.setColor(Color.CYAN);
					GameLib.drawCircle(enem.getX(), enem.getY(), enem.getRadius());
				}
			}

			/* desenhando inimigos (tipo 2) */

			for(int i = 0; i < Enemy2s.size(); i++){
       Enemy2 enem = Enemy2s.get(i);
				if(enem.getState() == GameStates.EXPLODING){

					double alpha = ((currentTime - enem.getExplosionStart()) / 500);
					GameLib.drawExplosion(enem.getX(), enem.getY(), alpha);
				}

				if(enem.getState() == GameStates.ACTIVE){

					GameLib.setColor(Color.MAGENTA);
					GameLib.drawDiamond(enem.getX(), enem.getY(), enem.getRadius());
				}
			}

			/* chamada a display() da classe GameLib atualiza o desenho exibido pela interface do jogo. */

			GameLib.display();

			/* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 3 ms. */

			busyWait(currentTime + 3);
		}

		System.exit(0);
	}
}
