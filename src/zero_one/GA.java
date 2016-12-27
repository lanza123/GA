package zero_one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GA {
    static Random random = new Random();
    protected static Knapsack[] select(Knapsack[] knapsacks, double[] fitness) {
        int scale = knapsacks.length;
        int len = knapsacks[0].getItemSize();
        double capacity = knapsacks[0].getCapacity();
        SortFitness[] sortFitness = new SortFitness[scale];
        for(int i = 0; i < scale; i++) {
            sortFitness[i] = new SortFitness();
            sortFitness[i].index = i;
            sortFitness[i].fitness = fitness[i];
        }
        Arrays.sort(sortFitness);
        Knapsack[] tmpKnapsacks = new Knapsack[scale];
        for (int i = 0; i < scale; i++) {
            Knapsack knapsack = new Knapsack(capacity);
            tmpKnapsacks[i] = knapsack;
        }

        //保留前20%的个体
        int reserve = (int)(scale * 0.2);
        for(int i = 0; i < reserve; i++) {
            for(int j = 0; j < len; j++) {
                int index = sortFitness[i].index;
                tmpKnapsacks[i].setItemAddOrNot(j, knapsacks[index].getItemAddOrNot(j));
            }
        }

        //再随机80%的个体出来
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < scale; i++) {
            list.add(i);
        }
        for(int i = reserve; i < scale; i++) {
            int selectid = list.remove((int)(list.size()*Math.random()));
            for(int j = 0; j < len; j++) {
                tmpKnapsacks[i].setItemAddOrNot(j, knapsacks[selectid].getItemAddOrNot(j));
            }
        }
        return tmpKnapsacks;
    }

    //进行交叉
    protected static Knapsack[] intersect(Knapsack[] knapsacks, double irate) {
        int scale = knapsacks.length;
        int len = knapsacks[0].getItemSize();
        for(int i = 0; i < scale; i = i + 2) {
            for (int j = 0; j < len; j++) {
                if (Math.random() < irate) {
                    boolean tmp = knapsacks[i].getItemAddOrNot(j);
                    knapsacks[i].setItemAddOrNot(j, knapsacks[i + 1].getItemAddOrNot(j));
                    knapsacks[i + 1].setItemAddOrNot(j, tmp);
                }
            }
        }
        return knapsacks;
    }

    //变异
    protected static Knapsack[] aberra(Knapsack[] knapsacks, double arate1, double arate2) {
        int scale = knapsacks.length;
        int len = knapsacks[0].getItemSize();
        for(int i = 0; i < scale; i++) {
            if(Math.random() > arate1) {
                continue;
            }
            for(int j = 0; j < len; j++) {
                if(Math.random() < arate2) {
                    knapsacks[i].setItemAddOrNot(j, Math.random() > 0.5 ? true : false);
                }
            }
        }
        return knapsacks;
    }

    static class SortFitness implements Comparable<SortFitness>{
        int index;
        double fitness;
        public int compareTo(SortFitness c) {
            double cfitness = c.fitness;
            if(fitness > cfitness) {
                return -1;
            } else if(fitness < cfitness) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}