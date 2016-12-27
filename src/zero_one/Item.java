package zero_one;

/**
 * Created by Jindiwei on 2016/12/24.
 */
public class Item {
    private float weight;
    private float value;
    private boolean addOrNot = false;

    public Item(float weight, float value){
        this.weight = weight;
        this.value = value;
    }

    public float getWeight(){
        return weight;
    }

    public float getValue(){
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
