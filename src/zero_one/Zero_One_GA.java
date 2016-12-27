package zero_one;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Zero_One_GA {
	public static ArrayList<Item> ItemList = new ArrayList<Item>();
	String filepath;
	String[] bestSolution;
	public Zero_One_GA(String filepath){
		this.filepath = filepath;
	}
	public void Start(){
		float weight;
		float value;
		float capacity = 0;
		int quantity = 0;
		try {
			Scanner in = new Scanner(new File(filepath));
			int i = 0;
			while (in.hasNext()) {
				if (i == 0) {
					capacity = in.nextFloat();
					quantity = in.nextInt();
					i = i + 1;
				} else {
					weight = in.nextFloat();
					value = in.nextFloat();
					ItemList.add(new Item(weight, value));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Population pop = new Population(200, quantity, capacity, 0.5f, 0.05f, 0.1f);
		this.bestSolution = pop.solve();
	}

	public void showResult() {
        for (int i = 0; i < bestSolution.length; i++) {

            System.out.println(i + " " + 1);
        }
    }

    public void writeToFile(String filepath) {
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(new File(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream p = new PrintStream(fs);
        for (int i = 0; i < bestSolution.length; i++) {
            if(i == 0){
                p.println(bestSolution[0]);
            }else {
                p.println((i + 1) + " " + bestSolution[i]);
            }
        }
        p.close();
    }
	public static void main(String[] args) {
		String testknapsack_path = "C:\\Users\\admin\\Desktop\\GA-master\\GA-master\\src\\testknapsack\\Knapsack.txt";
		Zero_One_GA zero_one_knapsack = new Zero_One_GA(testknapsack_path);
		zero_one_knapsack.Start();
		zero_one_knapsack.writeToFile("C:\\Users\\admin\\Desktop\\GA-master\\GA-master\\src\\output\\Knapsack-14302010029.txt");
	}
}
