package simpleGa;

public class Individual {

    private int geneLength;
    //基因序列
    private byte[] genes;
    // 个体的 适应值
    private int fitness = 0;

    public Individual(int geneLength){
        this.geneLength = geneLength;
        genes = new byte[geneLength];
    }
    // 创建一个随机的 基因个体
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }

    
    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = Fitness.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}