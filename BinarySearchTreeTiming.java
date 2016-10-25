package assignment08;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import lab05.Charter;

public class BinarySearchTreeTiming {

	
		/**
		 * This class times methods of the LinkedListStack class
		 * @author Patrick Ekel
		 *
		 */
			static final int ITER_COUNT = 1000;

			public static void main(String[] args) {

				long startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1_000_000_000);

				try (FileWriter fw = new FileWriter(new File("contains.tsv"))) { 

					for (int exp = 10; exp <= 25; exp++) {
																											
						int size = (int) Math.pow(2, exp); 
						BinarySearchTree<String> test = new BinarySearchTree<String>();
						char [] charPool = "abcdefghijklmnopqrs".toCharArray();
						StringBuilder builder = new StringBuilder();
						Random random = new Random();
						for ( int i = 0; i < 10; i++){
							char ch = charPool[random.nextInt(charPool.length)];
							builder.append(ch);
						}
						String print = builder.toString();
						char[] sortIt = print.toCharArray();
						Arrays.sort(sortIt);
						String sortPrint = String.valueOf(sortIt);

						ArrayList<String> t = new ArrayList<String>();
				//		t.add(print);
						t.add(sortPrint);

						//test.add(print);
		//				test.add(sortPrint);
				//		test.writeDot("dotTime");
							
						// Do the experiment multiple times, and average out the results
						long totalTime = 0;
						
						for (int iter = 0; iter < ITER_COUNT; iter++) {
							test.addAll(t);
							// TIME IT!
							long start = System.nanoTime();
							
						//	test.contains("ZZZ");
							t.contains("ZZZ");

							long stop = System.nanoTime();
							totalTime += stop - start;
						}
						double averageTime = totalTime / (double) ITER_COUNT;
						System.out.println(size + "\t" + averageTime); // print to console
																	
						fw.write(size + "\t" + averageTime + "\n"); // write to file.
					//	test.writeDot("dotTime");

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				Charter charter = new Charter();
				charter.createChart("contains.tsv", "chart.png");
				System.out.println("DONE");

			}
		}

		


	

