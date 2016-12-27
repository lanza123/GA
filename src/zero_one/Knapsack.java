package zero_one;

import java.util.ArrayList;

/**
 * Created by Jindiwei on 2016/12/23.
 */
public class Knapsack {
    private ArrayList<Item> knapsack = new ArrayList<>();
    private float capacity;
    public Knapsack(float capacity){
        for (int i = 0; i < Zero_One_GA.ItemList.size(); i++) {
            float weight = Zero_One_GA.ItemList.get(i).getWeight();
            float value= Zero_One_GA.ItemList.get(i).getValue();
            this.capacity = capacity;
            knapsack.add(new Item(weight, value));
        }
    }

    public float getCapacity(){
        return capacity;
    }

    public int getItemSize(){
        return knapsack.size();
    }

    public Item getItem(int itemPosition) {
        return knapsack.get(itemPosition);
    }

    public boolean getItemAddOrNot(int itemPosition){
        return knapsack.get(itemPosition).getAddOrNot();
    }

    public void addItem(int itemPosition){
        knapsack.get(itemPosition).Add();
    }

    public void setItemAddOrNot(int itemPosition, boolean addOrNot){
        knapsack.get(itemPosition).setAddOrNot(addOrNot);
    }
}
