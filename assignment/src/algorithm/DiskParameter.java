package algorithm; 

import java.util.Properties;

public class DiskParameter {
	int previous;
	int current;
	int sequence[];
	int cylinders;

	public DiskParameter(Properties p) {
		/*
		 * diskq1.properties will be in the format Cylinders=5000
		 * Position.Current=143 Position.Previous=125
		 * Sequence=86,1470,913,1774,948,1509,1022,1750,130
		 */
		this.cylinders = Integer.parseInt(p.getProperty("Cylinders"));
		this.current = Integer.parseInt(p.getProperty("Position.Current"));
		this.previous = Integer.parseInt(p.getProperty("Position.Previous"));
		/*
		 * Get values for cylinders and positions above, create an array to take
		 * in the values of sequence and split using a delimiter
		 */
		String token[] = p.getProperty("Sequence").split(",");
		sequence = new int[token.length];
		for (int i = 0; i < token.length; i++) {
			sequence[i] = Integer.parseInt(token[i]);
		}

		/*
		 * method to check for errors in cylinders and sequence
		 */
		for (int i = 0; i < sequence.length; i++) {
			if (sequence[i] > cylinders) {
				System.err
						.println("There is a mismatch between cylinders and sequence");
				System.exit(0);
			}
		}
	}

	public int getPrevious() {
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int[] getSequence() {
		return sequence;
	}

	public void setSequence(int[] sequence) {
		this.sequence = sequence;
	}

	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}

}
