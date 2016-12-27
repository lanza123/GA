package simpleGa;

public class Population {
    Individual[] individuals;
    int size;
    /*
     * 构造方法
     */
    // 创建一个种群
    public Population(int populationSize, boolean initialise) {
        individuals = new Individual[populationSize];
        size = populationSize;
        if (initialise) {
            for (int i = 0; i < size; i++) {
                Individual newIndividual = new Individual(40);
                newIndividual.generateIndividual();
                individuals[i] = newIndividual;
            }
        }
    }

    /* Getters */
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        for (int i = 0; i < size; i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int getSize() {
        return size;
    }

    public void saveIndividual(int index, Individual individual){
        individuals[index] = individual;
    }
}