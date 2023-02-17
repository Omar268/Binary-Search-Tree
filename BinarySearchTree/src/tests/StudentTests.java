package tests;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import implementation.BinarySearchTree;
import implementation.TreeIsEmptyException;
import implementation.TreeIsFullException;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	
	/* Remove the following test and add your tests */
	@Test
	public void test1() {
		BinarySearchTree<String, Integer> test1 = new BinarySearchTree<String, Integer>(String.CASE_INSENSITIVE_ORDER, 10);
		try {
			test1.add("C",5);
		} catch (TreeIsFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			test1.add("D",5);
		} catch (TreeIsFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			test1.add("E",5);
		} catch (TreeIsFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			test1.add("B",10);
		} catch (TreeIsFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(test1.toString());
		try {
			System.out.println(test1.getMaximumKeyValue().getKey());
		} catch (TreeIsEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(test1.getMinimumKeyValue().getKey());
		} catch (TreeIsEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test1.find("v").getKey();
		System.out.println("find:" + test1.find("v").getKey());
		System.out.println(test1.find("B").getValue());
	}
}
