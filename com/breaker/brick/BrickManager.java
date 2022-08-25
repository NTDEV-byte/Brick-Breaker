package com.breaker.brick;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.breaker.Entity;
import com.breaker.Game;
import com.breaker.ball.Ball;
import com.breaker.powers.Balls;
import com.breaker.powers.Gun;
import com.breaker.powers.Power;

public class BrickManager{
	
		private Game game = null;
		private Brick bricks[][];
		private List<Power> powers = new ArrayList<Power>();
		private int perL,perC;
		private int w,h;
		private int spx,spy;
		
					public BrickManager(Game game,int perL,int perC,int w,int h,int spx,int spy) {
							bricks = new Brick[perL][perC];
							this.perL = perL;
							this.perC = perC;
							this.w = w;
							this.h = h;
							this.spx = (spx <= 1) ? 2 : spx;
							this.spy = (spy <= 1) ? 2 : spy;
							this.game = game;
							Gun g = new Gun(game);
							g.init(game);
							powers.add(g);
							generateHorizontal();
					}
					
					public void update() {
						 updatePowers();
						 releasePower();
						 activateEffects();
						 removePowers();
						 cleanGame();
					}
						
					public void drawBricks(Graphics g) {
						for(int i=0;i<bricks.length;i++) { 
							for(int j=0;j<bricks[i].length;j++) {
								Rectangle r = bricks[i][j].getBounds();
								bricks[i][j].update();
								g.setColor(bricks[i][j].getColor());
								g.fillRect(r.x, r.y, r.width, r.height);
							}
						}
						renderPowers(g);
					}
					
					private void releasePower() {
						for(int i=0;i<bricks.length;i++) {
							 for(int j=0;j<bricks[i].length;j++) { 
								  if(bricks[i][j].hasBeenDestroyed()) { 
									  if(bricks[i][j].isGifted()) {
										    Brick current = bricks[i][j];
										    Power p = Power.generateRandomPower(game,current.getBounds().x, current.getBounds().y);
										   	powers.add(p);
										   	current.setGifted(false);
									  }
								  }
							 }
						}
					}
					
					private void activateEffects() {
						 for(int i=0;i<powers.size();i++) { 
							 if(powers.get(i).getBounds().intersects(game.getPad().getBounds())) { 
								 	powers.get(i).activateEffet();
							 }
						 }
					}
					
					private void updatePowers() { 
						for(Power p:  powers) {
							p.update();
						}
					}
					
					private void renderPowers(Graphics g) {
						for(Power p:  powers) {
							p.render(g);
							if(p instanceof Balls) { 
								 ((Balls) p).renderB(g);
							}
						}
					}
					
					private void removePowers() { 
						for(int i=0;i<powers.size();i++) { 
							if (powers.get(i).hasBeenDestroyed() || powers.get(i).isCatched()) { 
								   	if(!(powers.get(i) instanceof Balls))powers.remove(i);
								   	else 
								   		if((powers.get(i) instanceof Balls)) { 
								   			  if(powers.get(i).hasBeenDestroyed()) { 
								   				  powers.remove(i);
								   			  }
								   	}
							  }
						}
					}

					public boolean collisionOccured(Ball b) {
						for(int i=0;i<bricks.length;i++) { 
							for(int j=0;j<bricks[i].length;j++) {
								  if(b.getBounds().intersects(bricks[i][j].getBounds())) { 
									   bricks[i][j].setResistance(bricks[i][j].getResistance() - b.getDamage());
									   return true;
								  }
								}
							}
						return false;
					}
					
					public boolean collisionOccuredWithEntity(Entity e) {
						for(int i=0;i<bricks.length;i++) { 
							for(int j=0;j<bricks[i].length;j++) {
								  if(e.getBounds().intersects(bricks[i][j].getBounds())) { 
									   bricks[i][j].setResistance(bricks[i][j].getResistance() - e.getDamage());
									   return true;
								  }
								}
							}
						return false;
					}
					
					public boolean levelCleared() { 
						for(int i=0;i<bricks.length;i++) { 
							 for(int j=0;j<bricks[i].length;j++) { 
								   if(!(bricks[i][j].hasBeenDestroyed())) {
									    return false;
								   }
							 }
						}
						return true;
					}
					
					private void cleanGame() { 
						for(int i=0;i<bricks.length;i++) { 
							for(int j=0;j<bricks[i].length;j++) { 
									if(bricks[i][j].hasBeenDestroyed()) { 
										bricks[i][j].setBounds(new Rectangle(0,0));
								}	
							}
						}
					}
					/**m�thodes de Generations  **/
					public void generateHorizontal() {
						int tempX = 10;
						int tempY = 10;
						
						  for(int i=0;i<bricks.length;i++) {
							   for(int j=0;j<bricks[i].length;j++) {
								     bricks[i][j] = new Brick(game,new Rectangle(j * w + tempX,i * h + tempY,w,h));
								     tempX+=spx;
							   }
							   tempY+=spy;
							   tempX = 10;
						  }
					}
					
					
					public static void DestroyAll(BrickManager manager) { 
						for(int i=0;i<manager.bricks.length;i++) {
							  for(int j=0;j<manager.bricks[i].length;j++) {
								   manager.bricks[i][j].destroy();
							  }
						}
					}
					
					
					public int getPerL() {
						return perL;
					}

					public int getPerC() {
						return perC;
					}
}
