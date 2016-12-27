package simpleGa;

public class Algorithm {

    /* GA 算法的参数 */
    private double uniformRate = 0.5; //交叉概率
    private double mutationRate = 0.015; //突变概率
    private int tournamentSize = 5; //淘汰数组的大小
    private boolean elitism = true; //精英主义

    public Algorithm(){

    }

    public Algorithm(double uniformRate, double mutationRate, int tournamentSize, boolean elitism){
        this.uniformRate = uniformRate;
        this.mutationRate = mutationRate;
        this.tournamentSize = tournamentSize;
        this.elitism = elitism;
    }


    /* Public methods */
    
    // 进化一个种群
    public Population evolvePopulation(Population pop) {
    	// 存放新一代的种群
        Population newPopulation = new Population(pop.getSize(), false);

        // 把最优秀的那个 放在第一个位置. 
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.getSize(); i++) {
        	//随机选择两个 优秀的个体
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            //进行交叉
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population  突变
        for (int i = elitismOffset; i < newPopulation.getSize(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // 进行两个个体的交叉 (暂且想象为make love的过程吧)。 交叉的概率为uniformRate
    private Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual(40);
        // 随机的从 两个个体中选择 
        for (int i = 0; i < indiv1.size(); i++) {
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // 突变个体。 突变的概率为 mutationRate
    private void mutate(Individual indiv) {
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // 生成随机的 0 或 1
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }

    // 随机选择一个较优秀的个体，用了进行交叉
    private Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournamentPop = new Population(tournamentSize, false);
        //随机选择 tournamentSize 个放入 tournamentPop 中
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getSize());
            tournamentPop.saveIndividual(i, pop.getIndividual(randomId));
        }
        // 找到淘汰数组中最优秀的
        Individual fittest = tournamentPop.getFittest();
        return fittest;
    }

    public void setUniformRate(double rate){
        this.uniformRate = rate;
    }

    public void setMutationRate(double rate){
        this.mutationRate = rate;
    }

    public void setTournamentSize(int size){
        this.tournamentSize = size;
    }



}