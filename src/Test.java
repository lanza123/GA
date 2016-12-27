import zero_one.Zero_One_GA;

/**
 * Created by Jindiwei on 2016/12/24.
 */
public class Test {
    public static void main(String[] args){
        String testknapsack_path = "/Users/Jindiwei/IdeaProjects/GA/src/testknapsack/Knapsack.txt";
        String tsp_path = "/Users/Jindiwei/IdeaProjects/GA/src/testtsp/TSP.txt";
        Zero_One_GA zero_one_knapsack = new Zero_One_GA(testknapsack_path);
        zero_one_knapsack.Start();
        //zero_one_knapsack.showResult();
        zero_one_knapsack.writeToFile("/Users/Jindiwei/IdeaProjects/GA/src/output/Knapsack-14302010029.txt");

//        TSP_GA tsp_ga = new TSP_GA(tsp_path);
//        tsp_ga.Start();
//        tsp_ga.showResult();
//        tsp_ga.writeToFile("/Users/Jindiwei/IdeaProjects/GA/src/output/TSP-14302010029.txt");

    }
}
