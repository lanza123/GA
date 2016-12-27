package zero_one;

import java.util.Random;

public class Population {
    Knapsack[] knapsacks;
    Random random = new Random();
    private float bestFitness;
    private Item[] bestUnit;
    private float[] fitness;
    private float irate;
    private float arate1;
    private float arate2;
    int scale;
    int len;


    public Population(int scale, int len, float capacity, float irate, float arate1, float arate2) {
        this.scale = scale;
        this.len = len;
        this.irate = irate;
        this.arate1 = arate1;
        this.arate2 = arate2;
        fitness = new float[scale];
        knapsacks = new Knapsack[scale];
        for (int i = 0; i < scale; i++) {
            Knapsack knapsack = new Knapsack(capacity);
            knapsacks[i] = knapsack;
        }
        for(int i = 0; i < scale; i++) {
            int count = 0; //防止初始化耗费太多计算资源
            for(int j = 0; j < capacity;) {
                int k = random.nextInt(len);
                if(knapsacks[i].getItemAddOrNot(k)) {
                    if(count == 3) {
                        break;
                    }
                    count++;
                    continue;
                } else {
                    knapsacks[i].addItem(k);
                    j += knapsacks[i].getItem(k).getWeight();
                    count = 0;
                }
            }
        }
    }

    private float evaluate(Knapsack knapsack) {
        float profitSum = 0;
        float weightSum = 0;
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
        for(int i = 0; i < scale; i++) {
            if(fitness[i] > bestFitness) {
                bestFitness = fitness[i];
                System.out.println(bestFitness);
                bestUnit = new Item[len];
                for(int j = 0; j < len; j++) {
                    bestUnit[j] = knapsacks[i].getItem(j);
                }
            }
        }
    }

    //遗传算法
    protected String[] solve() {
        for (int i = 0; i < 5000; i++) {
//            if(i%100 == 0) {
//                System.out.println(bestFitness);
//            }
            //计算种群适应度值
            calcFitness();
            //记录最优个体
            recBest();
            //进行种群选择
            knapsacks = GA.select(knapsacks, fitness);
            //进行交叉
            knapsacks = GA.intersect(knapsacks, irate);
            //发生变异
            knapsacks = GA.aberra(knapsacks, arate1, arate2);

        }
//        for(int i = 0 ; i < bestUnit.length ; i ++){
//            System.out.println(bestUnit[i].getAddOrNot());
//        }


        String[] bestSolution = new String[bestUnit.length + 1];
        bestSolution[0] = String.valueOf(bestFitness);
        float pp = 0;
        for (int i = 0; i < bestUnit.length; i++) {
            if (bestUnit[i].getAddOrNot()) {
                pp += bestUnit[i].getValue();
                bestSolution[i+1] = "1";
            } else {
                bestSolution[i+1] = "0";
            }
        }
        System.out.println("@@@" + pp);
        System.exit(0);

        return bestSolution;
    }
}