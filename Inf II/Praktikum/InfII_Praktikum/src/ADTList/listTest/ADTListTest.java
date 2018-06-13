package ADTList.listTest;

import static ADTList.list.ADTList.list;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ADTList.list.ADTList;

@RunWith(Theories.class)
public class ADTListTest {

    
   public ADTList<Integer> intList;
    ADTList<Double> dList;

    
    
    @DataPoints
    public static int[] integers() {
	return new int[] {
		-1, -10, -1234567, 1, 10, 1234567, Integer.MIN_VALUE, Integer.MAX_VALUE };
    }
    
    @DataPoints
    public static double[] doubles() {
	return new double[] {2.5, 6.6 ,2.6 , 100.2, -1552.5, -21.2 , 5562.24, Double.MIN_VALUE, Double.MAX_VALUE};
    }
    @DataPoint 
    public static List<Integer> testList = Arrays.asList(2,6,4,2,88,5,3,8,9,234,2,6);
    

    @Theory //isEmpty([])= true
    public void isEmpty_true ()
    {
	intList = list();
	assertTrue(intList.isEmpty());
    }
    
    @Theory //isEmpty(x:l)= false
    public void isEmpty_x_l_False(Integer a)
    {
	intList = list(a);
	//System.out.println(intList.toString());
	//intList.cons(3);
	assertFalse(intList.isEmpty());
    }
    @Theory //elem(x,nil)= false
    public void elem_x_nil_false(Integer a, Double b)
    {
	intList = list();
	assertFalse(intList.elem(a, intList));
	dList = list();
	assertFalse(dList.elem(b, dList));
    }
    
    @Theory //elem(x,cons(x,l))=true
    public void elem_x_cons_x_l_true(Integer a, Double b)
    {
	intList = list(2,3,5,22,6,5);
	intList = intList.cons(a);
	assertTrue(intList.elem(a, intList));
	
	dList = list(22.5,22.6,666.4);
	dList = dList.cons(b);
	assertTrue(dList.elem(b, dList));
	
    }
    @Theory //head(cons(x,l))= x
    public void head_cons_x_l_x(Integer a, Double b)
    {
	intList = list(a*2, 6,2,4);
	intList = intList.cons(a);
	assertTrue(intList.head() == a);
	
	dList = list(b*5);
	dList = dList.cons(b);
	assertTrue(dList.head().equals(b));
    }
    
    @Theory //tail(cons(x,l))= l
    public void tail_cons_x_l_l(Integer x, List<Integer>  a)
    {
	intList = list();
	for(int i=0; i<=a.size()-1; i++)
	{
	    intList = intList.cons(a.get(i));
	}
	Collections.reverse(a);
	
	for(int i=0; i<=a.size()-1; i++)
	{
	    assertTrue(intList.head().equals(a.get(i)));
	    intList = intList.tail();
	}
    }
    
    @Theory //length(nil)= 0
    public void len_nil_zero()
    {
	intList = list();
	assertTrue(intList.length(intList).equals(0));
	
	dList = list();
	assertTrue(dList.length(dList).equals(0));
    }
    
    @Theory //length(cons(x,l))= length(l)+1
    public void len_cons_lenPlusOne(Integer x, List<Integer> a)
    {
	ADTList<Integer> compareList = list();
	intList = list();
	for(int i=0; i<= a.size()-1; i++)
	{
	    compareList = compareList.cons(a.get(i));
	    intList = intList.cons(a.get(i));
	}
	
	compareList= compareList.cons(x);
	assertTrue(compareList.length(compareList) == intList.length(intList)+1);
	
	
    }
}
