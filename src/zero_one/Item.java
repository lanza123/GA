package zero_one;

/**
 * Created by Jindiwei on 2016/12/24.
 */
public class Item {
    private double weight;
    private double value;
    private boolean addOrNot = false;

    public Item(double weight, double value){
        this.weight = weight;
        this.value = value;
    }

    public double getWeight(){
        return weight;
    }

    public double getValue(){
        return value;
    }

    public void Add(){
        addOrNot = true;
    }

    public boolean getAddOrNot(){
        return addOrNot;
    }

    public void setAddOrNot(boolean addOrNot){
        this.addOrNot = addOrNot;
    }
}
