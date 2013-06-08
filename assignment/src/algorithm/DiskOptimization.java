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

		System.out.println(name + '\n' + "====");
		System.out.println("Order of Access: " + sequence);

		System.out.println("Total Distance = "
				+ working1.substring(0, working1.length() - 1));
		System.out.println("               = "
				+ working2.substring(0, working2.length() - 2));
		System.out.println("               = " + total + '\n');
	}

	public void generateFCFS() {
		int location[] = dp.getSequence();
		printSequence("FCFS", location);
	}

	public void generateSSTF() {
		int location[] = arrangeBySSTF(dp.getCurrent(), dp.getSequence());
		printSequence("SSTF", location);
	}

	public void generateScan(){
		int location[] = arrangeByScan(dp.getPrevious(),dp.getCurrent(),dp.getSequence());
		printSequence("Scan",location);
	}
	
	public void generateLook(){
		int location[] = arrangeByLook(dp.getPrevious(),dp.getCurrent(),dp.getSequence());
		printSequence("Look",location);
	}
	
private int[] arrangeByLook(int previous, int current, int[] sequence) {
		
		int n = sequence.length;
		int look[] = new int[n];
		ArrayList<Integer>scanright = new ArrayList<Integer>();
		ArrayList<Integer>scanleft = new ArrayList<Integer>();
		for(int i=0; i<n;i++){
			look[i] = sequence[i];
		}
		Arrays.sort(look);
		if(previous < current){
			//scanleft.add(dp.getCylinders());
			for(int i=0; i<look.length;i++){
				if(look[i]>current){
					scanright.add(look[i]);
				}
				
				else if(look[i]<current){ //&& look[i] != 0
					scanleft.add(look[i]);
				}

				
			}
			Collections.sort(scanleft);
			Collections.reverse(scanleft);
			Collections.sort(scanright);

			int index = 0;
			for(int i=0; i<scanright.size();i++){
				look[i] = scanright.get(i);
				index+=1;
			}
			
			
			for(int i = 0; i<scanleft.size();i++){
				look[index] = scanleft.get(i);
				index++;
			}
		}
		
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
		int scan[] = new int[n+1];
		ArrayList<Integer>scanright = new ArrayList<Integer>();
		ArrayList<Integer>scanleft = new ArrayList<Integer>();
		for(int i=0; i<n;i++){
			scan[i] = sequence[i];
		}
		Arrays.sort(scan);
		if(previous < current){
			int cylinder = Arrays.binarySearch(scan, dp.getCylinders());
			System.out.println(cylinder);
			if(cylinder >= 0)
				scanr = new int[n];
			else{
				scanr = new int[n+1];
				scanleft.add(dp.getCylinders());
			}
			for(int i=0; i<scan.length;i++){
				if(scan[i]>current){
					scanright.add(scan[i]);
				}
				
				else if(scan[i]<current && scan[i] != 0){
					scanleft.add(scan[i]);
				}

				
			}
			Collections.sort(scanleft);
			Collections.reverse(scanleft);
			Collections.sort(scanright);

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
		
		else if(previous > current){
			int cylinder = Arrays.binarySearch(scan, 0);
			System.out.println(cylinder);
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

			for(int i=0; i<scanleft.size();i++){
				System.out.println(scanleft.get(i));
			}
			
			System.out.println();
			for(int i=0; i<scanright.size();i++){
				System.out.println(scanright.get(i));
			}
			
			int index = 0;
			for(int i=0; i<scanleft.size();i++){
				scanr[i] = scanleft.get(i);
				index+=1;
			}
			
			
			for(int i = 0; i<scanright.size();i++){
				if(cylinder > 0)
				scanr[index-1] = scanright.get(i);
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
}
