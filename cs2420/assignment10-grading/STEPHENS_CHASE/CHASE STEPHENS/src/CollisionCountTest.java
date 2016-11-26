package assignment10;

import java.util.Random;
/**
 * 
 * @author Chase Stephens
 * 
 * Collision count for hash tables. 
 *
 */

public class CollisionCountTest {
	
	public static void main(String[] args){
		
	GoodHashFunctor goodFunc = new GoodHashFunctor(); 
	BadHashFunctor badFunc = new BadHashFunctor(); 
	MediocreHashFunctor medFunc = new MediocreHashFunctor(); 
	
	ChainingHashTable table1 = new ChainingHashTable(100, goodFunc);
	ChainingHashTable table2 = new ChainingHashTable(100, badFunc);
	ChainingHashTable table3 = new ChainingHashTable(100, medFunc);
	QuadProbeHashTable qtable1 = new QuadProbeHashTable(100, goodFunc);
	
	Random rand = new Random();
	Random rand1 = new Random();
	
	for (int i = 0; i < 200000; i++){
		String str = "";
		int length = rand.nextInt(15);
		for(int j = 0; j < length; j++){
			str += rand1.nextInt(100);
		}
		table1.add(str);
		table2.add(str);
		table3.add(str);
		qtable1.add(str);
		if(i%10000 == 0){
			System.out.println(i + " \t " + table1.getCollisionCount()); //chain table good functor
//			System.out.println(i + " \t " + table2.getCollisionCount());  //chain table bad functor
//			System.out.println(+ i + " \t " + table3.getCollisionCount());  //chain table med functor
//			System.out.println(i + " \t " + qtable1.getCollisionCount()); //quad table good functor
		}
	}
  }

}
