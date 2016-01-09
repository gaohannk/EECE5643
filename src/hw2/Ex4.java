package hw2;

import java.text.DecimalFormat;
import java.util.Random;

import common.Rng;

public class Ex4 {

	public static void main(String[] args) {
		int N = 1000000;
		int n = 0;
		Rng r = new Rng();
		r.putSeed(-1);
		for (int i = 0; i < N; i++) {
			double theta1=Uniform(-Math.PI,Math.PI,r);
			double x1= Math.cos(theta1);
			double y1= Math.sin(theta1);
			double theta2=Uniform(-Math.PI,Math.PI,r);
			double x2= Math.cos(theta2);
			double y2= Math.sin(theta2);
			if((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)>1)
				n++;
		}
		DecimalFormat f = new DecimalFormat("###0.00000");
		System.out.println("probability is " + f.format((double) n/N));
	}

	public static double Uniform(double a, double b, Rng r) {
		return a + (b - a) * r.random();
	}
}
