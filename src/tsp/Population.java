package tsp;

public class Population {

    // 保持 路径的 集合 即为种群
    Tour[] tours;
    // Construct a population
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        if (initialise) {
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    // Saves a tour
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }
    
    // Gets a tour from population
    public Tour getTour(int index) {
        return tours[index];
    }

    // 获取当前种群 中 最优的个体
    public Tour getFittest() {
        Tour fittest = tours[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    // Gets population size
    public int populationSize() {
        return tours.length;
    }

    public String[] solve(Population pop) {
        for (int i = 0; i < 10000; i++) {
            if(i % 100 == 0){
                System.out.println(pop.getFittest().getDistance());
            }
            pop = GA.evolvePopulation(pop);
        }
        int size = pop.getFittest().tourSize();
        String [] bestSolution = new String[size + 1];
        bestSolution[0] = String.valueOf(pop.getFittest().getDistance());
        for(int i = 0 ; i < size ; i ++){
            bestSolution[i + 1] = String.valueOf(pop.getFittest().getCity(i).number);
        }
        return bestSolution;
    }
}