package ADTSetTest;

import static org.junit.Assert.assertTrue;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ADTSet.ADTSet;
import ADTSet.ListSet;
import list.ADTList;

@RunWith(Theories.class)
public class ADTSetTest {
    
    ADTSet<Integer> testSet;
    ADTSet<Integer> referenceTestSet;
    
    @DataPoints
    public static int[] integers() {
	return new int[] {
		-1, -10, -1234567, 1, 10, 1234567, Integer.MIN_VALUE, Integer.MAX_VALUE };
    }
    
    @DataPoint 
    public static ADTSet<Integer> TestSet = ListSet.set(75,6,4,2,88,5,3,8,9,234,2,6);
    
    @DataPoint
    public static ADTList<Integer> testList = ADTList.list(2,6,3,88,5,9,15);

    @Theory // vx : x e A -> x e B
    public void subsetTest(ADTList<Integer> a, int... b) {
	testSet = ListSet.fromList(a);
	referenceTestSet = ListSet.fromList(a);
	
	for(int i=0; i<=b.length; i++)
	{
	    referenceTestSet.insert(b[i]);
	}
	
	for(int i=0; i< a.length(a); i++)
	{
	    assertTrue(testSet.member(a.head()) && referenceTestSet.member(a.head()));
	}
	
	assertTrue(testSet.isSubsetOf(referenceTestSet));
	
    }

}
