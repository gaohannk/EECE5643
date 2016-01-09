package hw4;

import java.text.DecimalFormat;

import common.*;

public class Ex1 {
	final static int LAST = 100;

	public static void main(String[] args) {
		Rngs r = new Rngs();
		for (int k = 0; k < 10; k++) {
			r.selectStream(k);
			int n = 0;
			double sample[] = new double[LAST];
			double total = 0;
			double std = 0;
			int prop1 = 0;
			int prop2 = 0;
			while (n < LAST) {
				sample[n] = exponential(9, r);
				total += sample[n];
				n++;
			}
			double ave = total / n;
			for (int i = 0; i < LAST; i++) {
				std += (sample[i] - ave) * (sample[i] - ave);
			}
			std = Math.sqrt(std / n);
			for (int i = 0; i < LAST; i++) {
				if (sample[i] > ave - 2 * std && sample[i] < ave + 2 * std)
					prop1++;
				if (sample[i] > ave - 3 * std && sample[i] < ave + 3 * std)
					prop2++;
			}
			DecimalFormat f = new DecimalFormat("###0.000");

			System.out.println(f.format((double) prop1 / n) + "  " + f.format((double) prop2 / n));
		}

	}

	static double exponential(double m, Rngs r) {
		/*
		 * --------------------------------------------------- generate an
		 * Exponential random variate, use m > 0.0
		 * ---------------------------------------------------
		 */
		return (-m * Math.log(1.0 - r.random()));
	}
}
