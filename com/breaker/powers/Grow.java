package com.breaker.powers;

import com.breaker.Game;

import java.awt.Color;
import java.awt.Rectangle;

public class Grow extends Power{

	public static final int AMOUNT = 20;
	
	public Grow(Game game, Rectangle rectangle) {
		super(game,rectangle, Color.green);
	}

	@Override
	public void activateEffet() {
		pad.setBounds(new Rectangle(pad.getBounds().x,pad.getBounds().y,pad.getBounds().width + AMOUNT,pad.getBounds().height));
	}

		
	
	
	
}
