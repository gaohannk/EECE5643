package hw5;

public class MeanValueAnalysis {

	public static void main(String[] arg) {
		// System.out.println("please input device number K");
		int numdevice = 7;
		int population = 5;
		int servicetime[] = { 20, 5, 15, 10, 10, 15, 20 };
		double visitratio[] = { 1, 1, 0.6, 0.4, 0.4, 0.3, 0.3 };
		double[][] rt = new double[numdevice][population+1];
		double[][] tput = new double[numdevice][population+1];
		double[][] qlen = new double[numdevice][population+1];
		for (int k = 1; k <= population; k++) {
			for (int i = 0; i < numdevice; i++) {
				rt[i][k] = servicetime[i] * (1 + qlen[i][k-1]);
			}
			for (int i = 0; i < numdevice; i++) {
				double sum = 0;
				for (int j = 0; j < numdevice; j++) {
					sum += rt[j][k] * visitratio[j] / visitratio[i];
				}
				tput[i][k] = k / sum;
				qlen[i][k] = tput[i][k] * rt[i][k];
			}
		}
		// System.out.println("System Throughput : " + tput[0][population-1]);
		for (int i = 0; i < numdevice; i++) {
			System.out.println("Device " + i + " Queue Length : " + qlen[i][population]);
		}
		for (int i = 0; i < numdevice; i++) {
			System.out.println("Device " + i + " Throughput : " + tput[i][population]);
		}
	}
}
