package simpleGa;

public class MainTest {
	public static void main(String[] args) {
		Fitness.setSolution("1011010010110100100101101010010110101010");
		Population myPop = new Population(100, true);
		Algorithm algorithm = new Algorithm();

		// 不段迭代，进行进化操作。 直到找到期望的基因序列
		int generationCount = 0;
		while (myPop.getFittest().getFitness() < Fitness.getMaxFitness()) {
			generationCount++;
			System.out.println("Generation: " + generationCount + " Fittest: "
					+ myPop.getFittest().getFitness());
			myPop = algorithm.evolvePopulation(myPop);
		}

		System.out.println("Solution found!");
		System.out.println("Generation: " + generationCount);
		System.out.println("Final Fittest Genes:");
		System.out.println(myPop.getFittest());

	}
}
