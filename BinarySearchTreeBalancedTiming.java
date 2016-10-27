package assignment08;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import lab05.Charter;


/**
 * This is a timing class for a auto balancing tree and a non auto balancing tree
 * @author Patrick Ekel and Will Stout
 *
 */
public class BinarySearchTreeBalancedTiming {

	
		/**
		 * This class times methods of the LinkedListStack class
		 * @author Patrick Ekel
		 *
		 */
			static final int ITER_COUNT = 20;

			public static void main(String[] args) {

				long startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1_000_000_000);

				try (FileWriter fw = new FileWriter(new File("contains.tsv"))) { 

					
					for (int exp = 10; exp <= 19; exp++) {
																											
						int size = ((int) Math.pow(2, exp)/2); 
					//	int size = ((int) exp*2); 

						BinarySearchTree<Integer> myTree = new BinarySearchTree<Integer>();
						TreeSet<Integer> javaTree = new TreeSet<Integer>();						
						
							
						// Do the experiment multiple times, and average out the results
						long totalTime = 0;
						
						
						for (int iter = 0; iter < ITER_COUNT; iter++) {

							ArrayList<Integer> sortedList = new ArrayList<Integer>();
							ArrayList<Integer> unsortedList = new ArrayList<Integer>();
							for (int i =10; i <= size; i+=10){
								unsortedList.add(i);
								Collections.shuffle(unsortedList);
							}
											
			//				javaTree.addAll(unsortedList);
			//				myTree.addAll(unsortedList);


							
							// TIME IT!
							long start = System.nanoTime();
							javaTree.addAll(unsortedList);
						//	myTree.addAll(unsortedList);


							//for (int i =10; i <=size; i+=10){
							 //  javaTree.contains(i);
							//	myTree.contains(i);
								//Collections.shuffle(unsortedList);								
							//	javaTree.add(unsortedList.get(i));
				//				myTree.addAll(unsortedList);

							//}

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