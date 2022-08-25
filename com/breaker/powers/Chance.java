package com.breaker.powers;

import com.breaker.Game;

import java.awt.Color;
import java.awt.Rectangle;

public class Chance extends Power{

	public Chance(Game game,Rectangle rectangle) {
		super(game,rectangle, Color.pink);
	}

	@Override
	public void activateEffet() {
		pad.setLives(pad.getLives() + 1);
	}

}
