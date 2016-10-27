package assignment08;

import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import lab05.Charter;

/**
 * This is a timing class for a tree with elements that are inserted randomly vs in order
 * @author Patrick Ekel
 *
 */
public class BinarySearchTreeTiming {

			static final int ITER_COUNT = 20;

			public static void main(String[] args) {
				long startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1_000_000_000);
				try (FileWriter fw = new FileWriter(new File("contains.tsv"))) {
					for (int exp = 10; exp <= 19; exp++) {																											
						int size = ((int) Math.pow(2, exp)/2); 
					//	int size = ((int) exp*2); 
						BinarySearchTree<Integer> sortedTree = new BinarySearchTree<Integer>();
						BinarySearchTree<Integer> unsortedTree = new BinarySearchTree<Integer>();										
						// Do the experiment multiple times, and average out the results
						long totalTime = 0;
						for (int iter = 0; iter < ITER_COUNT; iter++) {

							ArrayList<Integer> sortedList = new ArrayList<Integer>();
							ArrayList<Integer> unsortedList = new ArrayList<Integer>();
							for (int i =10; i <= size; i+=10){
								sortedList.add(i);
								unsortedList.add(i);
								Collections.shuffle(unsortedList);
									}
							sortedTree.addAll(sortedList);
					//		sortedTree.addAll(unsortedList);

							// TIME IT!
							long start = System.nanoTime();
							
							for (int i =10; i <=size; i+=10){
							   sortedTree.contains(i);
						//		unsortedTree.contains(i);
							}
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

		


	

