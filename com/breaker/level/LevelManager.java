package com.breaker.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.breaker.Game;
import com.breaker.brick.BrickManager;

public class LevelManager{
		
		public static final int LEVELS = 8;
		
		private List<BrickManager> levels  = new ArrayList<BrickManager>();
		private Game game;
		public static int current = 0;
		public static boolean next = false;
		
			public LevelManager(Game game) {
				this.game = game;
				 for(int i=0;i<LEVELS;i++) {
					 BrickManager bm = new BrickManager(game,3,15,40,12,10,10);
					  levels.add(bm);
				 }

			}
		
			public void update() {
				levels.get(current).update();
				
			}
			public void render(Graphics g) {
			if(levels.get(current).levelCleared()) { 
					clearMessage(g);
					if(current < LEVELS - 1) { 
						current++;
					}
				}
				levels.get(current).drawBricks(g);
			}
			
			
			private void clearMessage(Graphics g) { 
				g.setColor(Color.red);
				g.setFont(new Font("Verdana",Font.PLAIN,25));
				g.drawString("Congratulations You Finished The Game !!!", Game.WIDTH / 2 - 250, Game.HEIGHT / 2);
			}
			
			public BrickManager getCurrentLevel() {
				 return levels.get(current);
			}

}
