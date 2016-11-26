package assignment10;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class HashTableJunitTest {

	BadHashFunctor a = new BadHashFunctor();
	MediocreHashFunctor a2 = new MediocreHashFunctor();
	GoodHashFunctor a3 = new GoodHashFunctor();
	ChainingHashTable abc2 = new ChainingHashTable(10, a);

	QuadProbeHashTable abc = new QuadProbeHashTable(10, a);

	@Test
	public void QuadprobehashtableaddTest() throws Exception {
		abc.add("taylor");
		abc.add("dakota");
		abc.add("kylie");
		assertEquals(true, abc.contains("taylor"));
		assertEquals(true, abc.contains("dakota"));
		assertEquals(true, abc.contains("kylie"));
	}

	@Test
	public void QuadprobehashtablecontainTest() throws Exception {
		abc.add("taylor");
		abc.add("dakota");
		abc.add("kylie");
		assertEquals(true, abc.contains("taylor"));
		assertEquals(true, abc.contains("dakota"));
		assertEquals(true, abc.contains("kylie"));
	}

	@Test
	public void QuadprobehashtableclearTest() throws Exception {
		abc.add("kylie");
		abc.clear();
		assertEquals(true, abc.isEmpty());
		assertEquals(0, abc.size());
	}
	
	@Test
	public void QuadprobehashtableaddallTest() throws Exception {
		ArrayList<String> list=new ArrayList<String>();
		list.add("taylor");
		list.add("dakota");
		list.add("kylie");
		abc.addAll(list);
		assertEquals(true, abc.contains("taylor"));
		assertEquals(true, abc.contains("dakota"));
		assertEquals(true, abc.contains("kylie"));
		
	}

	@Test
	public void ChainingHashTableaddTest() throws Exception {
		abc2.add("taylor");
		abc2.add("dakota");
		abc2.add("kylie");
		assertEquals(true, abc2.contains("taylor"));
		assertEquals(true, abc2.contains("dakota"));
		assertEquals(true, abc2.contains("kylie"));
	}

	@Test
	public void ChainingHashTablecontainTest() throws Exception {
		abc2.add("taylor");
		abc2.add("dakota");
		abc2.add("kylie");
		assertEquals(true, abc2.contains("taylor"));
		assertEquals(true, abc2.contains("dakota"));
		assertEquals(true, abc2.contains("kylie"));
	}
	@Test
	public void ChainingHashTableclearTest() throws Exception {
		abc2.add("taylor");
		abc2.clear();
		assertEquals(true, abc2.isEmpty());
		assertEquals(0, abc2.size());
	}
	
	@Test
	public void ChainingHashTableaddallTest() throws Exception {
		ArrayList<String> list=new ArrayList<String>();
		list.add("taylor");
		list.add("dakota");
		list.add("kylie");
		abc2.addAll(list);
		assertEquals(true, abc2.contains("taylor"));
		assertEquals(true, abc2.contains("dakota"));
		assertEquals(true, abc2.contains("kylie"));
		
	}

}
