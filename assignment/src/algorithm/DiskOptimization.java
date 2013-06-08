package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class DiskOptimization {
	Properties p = new Properties();
	DiskParameter dp = null;

	public static void main(String args[]) {
		new DiskOptimization("diskq1.properties");
	}

	public DiskOptimization(String filename) {
		try {
			p.load(new BufferedReader(new FileReader(filename)));
			dp = new DiskParameter(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		generateAnalysis();
	}

	public void generateAnalysis() {
		generateFCFS();
		generateSSTF();
		generateScan();
		generateLook();
	}

	public void printSequence(String name, int location[]) {
		String sequence = "";
		String working1 = "";
		String working2 = "";
		int total = 0;
		sequence += dp.getCurrent();
		int previous = dp.getCurrent();
		for (int i = 0; i < location.length; i++) {
			int current = location[i];
			sequence += "," + current;
			int d = Math.abs(previous - current);

			working1 += "|" + previous + "-" + current + "|+";
			working2 += d + " + ";
			total += d;
			previous = current;
		}

		System.out.println("Order of Access: " + sequence);

		System.out.println("Total Distance = "
				+ working1.substring(0, working1.length() - 1));
		System.out.println("               = "
				+ working2.substring(0, working2.length() - 2));
		System.out.println("               = " + total + '\n');
	}

	public void generateFCFS() {
		System.out.println("FCFS" + '\n' + "====");
		int location[] = dp.getSequence();
		printSequence("FCFS", location);
	}

	public void generateSSTF() {
		System.out.println("SSTF" + '\n' + "====");
		int location[] = arrangeBySSTF(dp.getCurrent(), dp.getSequence());
		printSequence("SSTF", location);
	}

	public void generateScan(){
		System.out.println("Scan" + '\n' + "====");
		int location[] = arrangeByScan(dp.getPrevious(),dp.getCurrent(),dp.getSequence());
		printSequence("Scan",location);
	}
	
	public void generateLook(){
		System.out.println("Look" + '\n' + "====");
		int location[] = arrangeByLook(dp.getPrevious(),dp.getCurrent(),dp.getSequence());
		printSequence("Look",location);
	}
	
private int[] arrangeByLook(int previous, int current, int[] sequence) {
	
	//create empty array to hold rearranged values
		int n = sequence.length;
		int look[] = new int[n];
		
		//Arrays to hold values left and right of current value
		ArrayList<Integer>scanright = new ArrayList<Integer>();
		ArrayList<Integer>scanleft = new ArrayList<Integer>();
		
		//Copying sequence array into look[]
		for(int i=0; i<n;i++){
			look[i] = sequence[i];
		}
		System.out.println("Initial Sequence: "+ printOutArray(look));
		
		//Sort look[]
		Arrays.sort(look);
		System.out.println("After 1st sort: "+printOutArray(look));
		
		//If scanning right 1st
		if(previous < current){
			System.out.println("Scanning to the right... Current = "+current);
			for(int i=0; i<look.length;i++){
				//Put all values larger than current to right
				if(look[i]>current){
					scanright.add(look[i]);
				}
				//Other values to left
				else if(look[i]<current){ 
					scanleft.add(look[i]);
				}
			}
			
			System.out.println("Numbers on the right of current = "+printOutArray(scanright));
			System.out.println("Numbers on the left of current = "+printOutArray(scanleft));
			
			//Organize arrays
			Collections.sort(scanleft);
			System.out.println("Sorted left = "+printOutArray(scanleft));
			//Reverse the numbers on the left because after scanning the last number on the right, it will scan the closest(largest) number on the left
			Collections.reverse(scanleft);
			System.out.println("Reversed left = "+printOutArray(scanleft));
			Collections.sort(scanright);
			System.out.println("Sorted right = "+printOutArray(scanright));

			//Combine both sides into one array
			//Insert scanright 1st because scanning starts to the right
			int index = 0; //To keep track where is the last scanright element in look array so that we know where to start inserting scanleft 
			for(int i=0; i<scanright.size();i++){
				look[i] = scanright.get(i);
				index+=1;
			}
			
			//Insert scanleft
			for(int i = 0; i<scanleft.size();i++){
				look[index] = scanleft.get(i);
				index++;
			}
			System.out.println("Combine left and right = "+printOutArray(look));
		}
		
		//For cases where scanning starts to the left
		else if(previous > current){

			for(int i=0; i<look.length;i++){
				if(look[i]<current){
					scanleft.add(look[i]);
				}
				
				else if(look[i]>current){ //&& look[i] != dp.getCylinders()
					scanright.add(look[i]);
				}

				
			}
			Collections.sort(scanleft);
			Collections.reverse(scanleft);
			Collections.sort(scanright);

			int index = 0;
			for(int i=0; i<scanleft.size();i++){
				look[i] = scanleft.get(i);
				index+=1;
			}
			
			
			for(int i = 0; i<scanright.size();i++){
				look[index] = scanright.get(i);
				index++;
			}

		}
		
		return look;
	}

	private int[] arrangeByScan(int previous, int current, int[] sequence) {
		
		int n = sequence.length;
		int scanr[] = null;
		int scan[] = new int[n+1]; //Nid one more element for extreme left/right values in sequence. That element has default value of 0.
		ArrayList<Integer>scanright = new ArrayList<Integer>();
		ArrayList<Integer>scanleft = new ArrayList<Integer>();
		for(int i=0; i<n;i++){
			scan[i] = sequence[i];
		}
		Arrays.sort(scan);
		System.out.println("Sorted = "+printOutArray(scan));
		
		//Scan right 1st
		if(previous < current){
			int cylinder = Arrays.binarySearch(scan, dp.getCylinders());
			//get zero
			if(cylinder >= 0)
				scanr = new int[n];
			else{
				scanr = new int[n+1];
				scanleft.add(dp.getCylinders());
			}
			for(int i=0; i<scan.length;i++){
				//separate values larger than current
				if(scan[i]>current){
					scanright.add(scan[i]);
				}
				
				else if(scan[i]<current && scan[i] != 0){
					//for other values
					scanleft.add(scan[i]);
				}

				
			}
			Collections.sort(scanleft);
			Collections.reverse(scanleft);
			//Once last right value is scanned,nearest(largest) left value will be scanned 
			System.out.println("Left side = "+printOutArray(scanleft));
			Collections.sort(scanright);

			//combine left and right arrays
			int index = 0;
			for(int i=0; i<scanright.size();i++){
				scanr[i] = scanright.get(i);
				index+=1;
			}
			
			
			for(int i = 0; i<scanleft.size();i++){
				scanr[index] = scanleft.get(i);
				index++;
			}
		}
		
		//If need scan left 1st
		else if(previous > current){
			int cylinder = Arrays.binarySearch(scan, 0);
			if(cylinder > 0){
				scanr = new int[n];
			}
			
			else{
				scanr = new int[n+1];
			}
			for(int i=0; i<scan.length;i++){
				if(scan[i]<current){
					scanleft.add(scan[i]);
				}
				
				else if(scan[i]>current){
					scanright.add(scan[i]);
				}

				
			}
			Collections.sort(scanleft);
			Collections.reverse(scanleft);
			Collections.sort(scanright);

			int index = 0;
			for(int i=0; i<scanleft.size();i++){
				scanr[i] = scanleft.get(i);
				index+=1;
			}
			
			
			for(int i = 0; i<scanright.size();i++){
				//for sequences that includes right end of cylinder
				if(cylinder > 0)
				scanr[index-1] = scanright.get(i);
				//for sequences that includes left end of cylinder
				else
				scanr[index] = scanright.get(i);
				index++;
			}
		}
		
		return scanr;
	}

	private int[] arrangeBySSTF(int current, int sequence[]) {
		int n = sequence.length;
		int sstf[] = new int[n];
		for (int i = 0; i < n; i++) {
			sstf[i] = sequence[i];
		}

		int ii = -1;
		for (int i = 0; i < n; i++) {
			int minimum = Integer.MAX_VALUE;
			ii = i;
			for (int j = i; j < n; j++) {
				int distance = Math.abs(current - sstf[j]);
				if (distance < minimum) {
					ii = j;
					minimum = distance;
				}
			}
			int tmp = sstf[i];
			sstf[i] = sstf[ii];
			sstf[ii] = tmp;
			current = sstf[i];
		}
		return sstf;
	}
	public  String printOutArray(int[] array)
	{
		String printSequence="";
		for(int i=0; i<array.length;i++){
			int printOut = array[i];
			printSequence+=printOut+",";
		}
	    return printSequence;
	}
	
	public  String printOutArray(ArrayList<Integer> array)
	{
		String printSequence="";
		for(int i=0; i<array.size();i++){
			int printOut = array.get(i);
			printSequence+=printOut+",";
		}
	    return printSequence;
	}
}
