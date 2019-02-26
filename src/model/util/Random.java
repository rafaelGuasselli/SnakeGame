package model.util;

public class Random {
	public static int range(int min, int max) {
		return (int)Math.round(Math.random() * (max - min)) + min;
	}
}