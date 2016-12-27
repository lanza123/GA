package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TSP_GA {
	public static ArrayList<City> destinationCities = new ArrayList<City>();
    String filepath;
    String[] bestSolution;
	public TSP_GA(String filepath){
	    this.filepath = filepath;
    }
    public void Start(){
        int city_amount = 0;
        int city_number;
        int location_x;
        int location_y;
        try {
            Scanner in = new Scanner(new File(filepath));
            int i = 0;
            while (in.hasNext()) {
                if(i == 0){
                    city_amount = in.nextInt();
                    i = i + 1;
                }
                else{
                    city_number = in.nextInt();
                    location_x = in.nextInt();
                    location_y = in.nextInt();
                    destinationCities.add(new City(city_number, location_x, location_y));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Population pop = new Population(city_amount, true);
        bestSolution = pop.solve(pop);
    }

    public void showResult(){
        for(int i = 0 ; i < bestSolution.length ; i ++) {
            System.out.println(bestSolution[i]);
        }
    }

    public void writeToFile(String filepath){
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(new File(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream p = new PrintStream(fs);
        for(int i = 0 ; i < bestSolution.length ; i ++) {
            p.println(bestSolution[i]);
        }
        p.close();
    }
    public static void main(String args[]){
        String tsp_path = "C:\\Users\\admin\\Desktop\\GA-master\\GA-master\\src\\testtsp\\TSP.txt";
        TSP_GA tsp_ga = new TSP_GA(tsp_path);
        tsp_ga.Start();
        tsp_ga.showResult();
        tsp_ga.writeToFile("C:\\Users\\admin\\Desktop\\GA-master\\GA\\src\\output\\TSP-14302010029.txt");
    }
}