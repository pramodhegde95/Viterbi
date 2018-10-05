import java.text.DecimalFormat;

public class Viterbi {

	static DecimalFormat format = new DecimalFormat("0.00");

	public static float[][] viterbiImpl(String seq, float[][] baseState, float[][] observationState, float[] start,
			char[] states) {

		float[][] viterbi = new float[2][seq.length()];// size
		// initial condition
		int startPointer = 0;
		for (int i = 0; i < observationState.length; i++) {
			int flag = 0;
			for (int j = 0; j < states.length; j++) {
				if (states[j] == seq.charAt(0)) {
					break;
				}
				flag++;
			}
			viterbi[i][0] = start[startPointer] + observationState[i][flag];
			startPointer++;
		}

		// other iterations
		for (int i = 1; i < seq.length(); i++) {// from 2nd position
			int flag = 0;
			for (int j = 0; j < states.length; j++) {
				if (states[j] == seq.charAt(i)) {
					break;
				}
				flag++;
			}

			for (int k = 0; k < observationState.length; k++) {
				viterbi[k][i] = observationState[k][flag]
						+ Math.max(viterbi[0][i - 1] + baseState[0][k], viterbi[1][i - 1] + baseState[1][k]);
			}
		}
		return viterbi;
	}

	// get the probable states
	public static String backTracking(float[][] viterbiMat) {
		String result = "";
		for (int j = 0; j < viterbiMat[0].length; j++) {
			int i = 0;
			if (viterbiMat[i][j] < viterbiMat[i + 1][j]) {
				result += "F";
			} else {
				result += "H";
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String sequence = args[0]; // read from the command line

		// probability between states (healthy and fever)
		float[][] baseStateProbability = new float[2][2];
		// probability of each state with transition probability
		float[][] observationStateProbability = new float[2][3];
		// probabiltiy of start state to healthy and fever state
		float[] start = { 0.6f, 0.4f };
		float[] arr = { (float) .7, (float) .3, (float) .5, (float) .5 };

		// matrix to store the values after running viterbi algorithm
		float[][] viterbiMatrix = new float[2][sequence.length()];
		int k = 0;
		int m = 0;
		char[] sequenceState = { 'N', 'C', 'D' };
		float[] seqarr = { 0.1f, 0.4f, 0.5f, 0.6f, 0.3f, 0.1f };

		// healthy fever probability table
		for (int i = 0; i < baseStateProbability.length; i++) {
			for (int j = 0; j < baseStateProbability[0].length; j++) {
				baseStateProbability[i][j] = arr[k];
				k++;
			}
		}

		for (int i = 0; i < observationStateProbability.length; i++) {
			for (int j = 0; j < observationStateProbability[0].length; j++) {
				observationStateProbability[i][j] = seqarr[m];
				m++;
			}
		}

		viterbiMatrix = viterbiImpl(sequence, baseStateProbability, observationStateProbability, start, sequenceState);

		// uncomment to get the viterbi matrix
		// for (int i = 0; i < viterbiMatrix.length; i++) {
		// System.out.print(baseState[i] + " ");
		// for (int j = 0; j < viterbiMatrix[0].length; j++) {
		// System.out.print(format.format(viterbiMatrix[i][j]) + " \t");
		// }
		// System.out.println();
		// }
		//

		// backtracking to get the probable states
		String output = backTracking(viterbiMatrix);

		System.out.println("output: " + output);
	}

}
