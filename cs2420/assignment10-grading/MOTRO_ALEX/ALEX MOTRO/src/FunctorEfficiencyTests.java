package assignment10;

public class FunctorEfficiencyTests {

	private static final int trials = 25;
	
	public static void main(String args[]){
		//Setup of the functors
		BadHashFunctor bad = new BadHashFunctor();
		MediocreHashFunctor med = new MediocreHashFunctor();
		GoodHashFunctor good = new GoodHashFunctor();
		
		/*
		//Testing the Collisions for Different Functors
		System.out.println("Functors Comparison Collisions");
		collisionsTestsQuadProbeHashTable(bad);
		System.out.println();
		collisionsTestsQuadProbeHashTable(med);
		System.out.println();*/
		collisionsTestsQuadProbeHashTable(good);
		System.out.println();
		/*
		//Testing the Time for Different Functors
		System.out.println("Functors Comparison Timing");
		quadProbeRunningTimer(bad);
		System.out.println();
		quadProbeRunningTimer(med);
		System.out.println();*/
		quadProbeRunningTimer(good);
		System.out.println();
		
		//Testing the Collisions for Different Hash Tables
		System.out.println("Hash Tables Comparison Collisions");
		collisionsTestsQuadProbeHashTable(good);
		System.out.println();
		collisionsTestsSeparateChaining(good);
		System.out.println();
		
		//Testing the Add Time for Different Hash Tables
		System.out.println("Hash Tables Comparison Collisions");
		quadProbeRunningTimer(good);
		System.out.println();
		chRunningTimer(good);
		System.out.println();
		
		
		
		
	}
	
	/**
	 * Runs Quadratic Probing collisions tests
	 * @param funct - bad, mediocre, or good hash functor
	 */
	public static void collisionsTestsQuadProbeHashTable(HashFunctor funct){
		QuadProbeHashTable quadProbeHashTble = new QuadProbeHashTable(11, funct);
		long collisions = 0; 
		// warm up
		for (int z = 0; z < 1000000; z++) { 
		}

		for (int n = 4; n <= 13; n += 1) {
			quadProbeHashTble.clear();

				for (int j = 0; j< trials; j++){
					quadProbeHashTble.clear();
					for(int i = 0; i<Math.pow(2, n); i++){
						quadProbeHashTble.add(i+"");
					}
					collisions = collisions + quadProbeHashTble.getCollisions();
					
				}
				System.out.println(collisions/trials);
		}
		System.out.println("");

	}
	
	/**
	 * Runs Separate Chaining collision tests
	 * @param funct - bad, mediocre, or good hash functor
	 */
	public static void collisionsTestsSeparateChaining(HashFunctor funct){
		ChainingHashTable chainingHashTble = new ChainingHashTable(11, funct);
		long collisions = 0; 

		// warm up
		for (int z = 0; z < 1000000; z++) { 
		}

		for (int n = 4; n <= 14; n += 1) {
			chainingHashTble.clear();
				for (int j = 0; j< trials; j++){
					chainingHashTble.clear();
					for(int i = 0; i<Math.pow(2, n); i++){
						chainingHashTble.add(i+"");
					}
					collisions = collisions + chainingHashTble.getCollisions();
					
				}
				System.out.println(collisions/trials);
		}
		System.out.println("");

	}
	
	/**
	 * Runs Quadratic Probing timing tests
	 * @param funct - bad, mediocre, or good hash functor
	 */
	public static void quadProbeRunningTimer(HashFunctor funct){
		long start = 0, mid = 0, end = 0;

		QuadProbeHashTable qp = new QuadProbeHashTable(101, funct);

		// warm up
		for (int z = 0; z < 1000000; z++) { 
		}

		for (int N = 4; N <= 14; N += 1) {
			qp.clear();
			double time = 0;

			for (int j = 0; j < trials; j++) {
				qp.clear();
				start = System.currentTimeMillis();	

				for(int i = 0; i < Math.pow(2, N); i++) {
					qp.add(i+"");
					
				}	

				mid = System.currentTimeMillis();

				for (int i = 0; i< Math.pow(2, N); i++){

				}

				end = System.currentTimeMillis();

				time += (double)((mid - start) - (end - mid)) / (double)N;
			}
			System.out.println(time / trials);
		}
	}
	
	/**
	 * Runs Separate Chaining timing tests
	 * @param funct - bad, mediocre, or good hash functor
	 */
	public static void chRunningTimer(HashFunctor funct){
		long start = 0, mid = 0, end = 0;
		ChainingHashTable ch = new ChainingHashTable(101, funct);

		// warm up
		for (int z = 0; z < 1000000; z++) { 
		}

		for (int N = 4; N <= 14; N += 1) {
			ch.clear();
			double time = 0;

			for (int j = 0; j < trials; j++) {
				ch.clear();
				start = System.currentTimeMillis();	

				for(int i = 0; i < Math.pow(2, N); i++) {
					ch.add(i+"");
					
				}	

				mid = System.currentTimeMillis();

				for (int i = 0; i<Math.pow(2, N); i++){

				}

				end = System.currentTimeMillis();

				time += (double)((mid - start) - (end - mid)) / (double)N;
			}
			System.out.println(time / trials);
		}
	}
}
