package com.breaker.powers;

import java.awt.Color;
import java.awt.Rectangle;

public class Chance extends Power{

	public Chance(Rectangle rectangle) {
		super(rectangle, Color.pink);
	}

	@Override
	public void activateEffet() {
		pad.setLives(pad.getLives() + 1);
	}

}
