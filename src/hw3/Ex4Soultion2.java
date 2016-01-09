package hw3;

import java.awt.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import common.*;

public class Ex4Soultion2 {

	static long LAST = 1000000; /* number of jobs processed */
	static double START = 0.0; /* initial time */
	static double sarrival = START; /* Why did I do this? */

	public static void main(String[] args) {
		long index = 0; /* job index */
		double arrival = START; /* time of arrival */
		double delay = 0; /* delay in queue */
		double service; /* service time */
		double wait; /* delay + service */
		int capacity = 1;
		// double departure[] = new double[capacity + 1];
		Ssq2Sum sum = new Ssq2Sum();
		sum.initSumParas();
		int reject = 0;
		Rng r = new Rng();
		r.putSeed(123456789);
		ArrayList<Double> departure = new ArrayList<Double>();
		LinkedList<Integer> a = new LinkedList<Integer>();
		departure.add(0.0);
		boolean isbreak = false;
		while (index < LAST) {
			index++;
			arrival = getArrival(r);
			/*
			 * System.out.println(depature.size()); if (depature.size() ==
			 * capacity + 1) { if (arrival < depature.get(0)){ reject++;
			 * continue; } for (int i = depature.size(); i > 0; i--) { if
			 * (arrival > depature.get(i - 1)) { delay =
			 * depature.get(depature.size() - 1) - arrival; wait = delay +
			 * getService(r); depature.add(arrival + wait); for (int j = 0; j <
			 * i; j++) depature.remove(0); break; } } } else { for (int i =
			 * depature.size(); i > 0; i--) { if (arrival > depature.get(i - 1))
			 * { delay = depature.get(depature.size() - 1) - arrival; wait =
			 * delay + getService(r); depature.add(arrival + wait); for (int j =
			 * 0; j < i; j++) depature.remove(0); break; } } }
			 */

			if (arrival < departure.get(departure.size() - 1)) {
				if (departure.size() < capacity + 1) {
					delay = departure.get(departure.size() - 1)
							- arrival; /* delay in queue */
					service = getService(r);
					wait = delay + service;
					departure.add(arrival + wait);
				} else {
					if (arrival < departure.get(0)) {
						reject++;
						continue;
					} else {
						for (int i = 0; i < departure.size() - 1; i++) {
							if (arrival > departure.get(i) && arrival < departure.get(i + 1)) {
								for (int j = 0; j <= i; j++)
									departure.remove(0);
								break;
							}
						}
						delay = departure.get(departure.size() - 1) - arrival;
						service = getService(r);
						wait = delay + service;
						departure.add(arrival + wait);
					}
				}
			} else {
				departure.clear();
				delay = 0.0; /* no delay */
				service = getService(r);
				wait = delay + service;
				departure.add(arrival + wait);
			}
		}
		DecimalFormat f = new DecimalFormat("###0.00000");

		System.out.println("\nfor " + index + " jobs");
		System.out.println("   rejection ............. =   " + f.format((double) reject / index));
	}

	static double exponential(double m, Rng r) {
		/*
		 * --------------------------------------------------- generate an
		 * Exponential random variate, use m > 0.0
		 * ---------------------------------------------------
		 */
		return (-m * Math.log(1.0 - r.random()));
	}

	static double uniform(double a, double b, Rng r) {
		/*
		 * ------------------------------------------------ generate an Uniform
		 * random variate, use a < b
		 * ------------------------------------------------
		 */
		return (a + (b - a) * r.random());
	}

	static double getArrival(Rng r) {
		/*
		 * ------------------------------ generate the next arrival time
		 * ------------------------------
		 */
		// static double sarrival = START;
		sarrival += exponential(2.0, r);
		return (sarrival);
	}

	static double getService(Rng r) {
		/*
		 * ------------------------------ generate the next service time
		 * ------------------------------
		 */
		return (uniform(1.0, 2.0, r));
	}

}
