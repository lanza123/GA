package zero_one;

import java.util.Random;

public class Population {
    Knapsack[] knapsacks;
    Random random = new Random();
    private double bestFitness;
    private Item[] bestUnit;
    private double[] fitness;
    private double irate;
    private double arate1;
    private double arate2;
    int scale;
    int len;


    public Population(int scale, int len, double capacity, double irate, double arate1, double arate2) {
        this.scale = scale;
        this.len = len;
        this.irate = irate;
        this.arate1 = arate1;
        this.arate2 = arate2;
        fitness = new double[scale];
        knapsacks = new Knapsack[scale];
        bestUnit = new Item[len];
        for (int i = 0; i < scale; i++) {
            Knapsack knapsack = new Knapsack(capacity);
            knapsacks[i] = knapsack;
        }
        for(int i = 0; i < scale; i++) {
//            int count = 0; //防止初始化耗费太多计算资源
            for(int j = 0; j < capacity;) {
                int k = random.nextInt(len);
                if(knapsacks[i].getItemAddOrNot(k)) {
//                    if(count == 3) {
//                        break;
//                    }
//                    count++;
                    continue;
                } else {
                    knapsacks[i].addItem(k);
                    j += knapsacks[i].getItem(k).getWeight();
//                    count = 0;
                }
            }
        }
    }

    private double evaluate(Knapsack knapsack) {
        double profitSum = 0;
        double weightSum = 0;
        int size = knapsack.getItemSize();
        for (int i = 0; i < size; i++) {
            if (knapsack.getItem(i).getAddOrNot()) {
                weightSum += knapsack.getItem(i).getWeight();
                profitSum += knapsack.getItem(i).getValue();
            }
        }
        if (weightSum > knapsack.getCapacity()) {
            return 0;
        } else {
            return profitSum;
        }
    }

    //计算种群所有个体的适应度
    protected void calcFitness() {
        for(int i = 0; i < scale; i++) {
            fitness[i] = evaluate(knapsacks[i]);
        }
    }

    //记录最优个体
    protected void recBest() {
        for (int i = 0; i < scale; i++) {
            if (fitness[i] > bestFitness) {
                bestFitness = fitness[i];
                for (int j = 0; j < len; j++) {
                    bestUnit[j] = knapsacks[i].getItem(j);
                }
            }
        }
    }

    //遗传算法
    protected String[] solve() {
        for (int i = 0; i < 50; i++) {
            if(i % 10 == 0) {
                System.out.println(bestFitness);
            }
            //计算种群适应度值
            calcFitness();
            //记录最优个体
            recBest();
            //进行种群选择
            this.knapsacks = GA.select(knapsacks, fitness);
            //进行交叉
            this.knapsacks = GA.intersect(knapsacks, irate);
            //发生变异
            this.knapsacks = GA.aberra(knapsacks, arate1, arate2);
        }
        String[] bestSolution = new String[bestUnit.length + 1];
        bestSolution[0] = String.valueOf(bestFitness);
        double pp = 0;
        for (int i = 0; i < bestUnit.length; i++) {
            if (bestUnit[i].getAddOrNot()) {
                pp = pp + bestUnit[i].getValue();
                bestSolution[i+1] = "1";
            } else {
                bestSolution[i+1] = "0";
            }
        }
        return bestSolution;
    }
}