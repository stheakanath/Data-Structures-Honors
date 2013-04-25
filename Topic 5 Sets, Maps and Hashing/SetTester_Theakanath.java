//Sony Theakanath
//Data Structures

import java.util.Set;
import java.util.TreeSet;

/**
   7.1.1 - The first three lines creates a hashset and then adds "hello" and "bye" to the
           hashset. The fourth and line duplicates the hello and bye so there are two of them
           in the same set. The fifth line and sixth line creates a treeset and adds "123" and then
           the seventh line adds 123 to the hashset. Afterwards the next line returns true
           and then returns false. The 10th line returns false, and 11th returns true. The 12th
           line keeps "123" only in list s. And then the 13th and 14th lines returns true.
            
   7.1.4 - It would only reference the setA causing it to not make a difference when the items
           are being changed in the statements below, therefore it is necessary to create a new
           Object to store the values. 
           
   I miss autocomplete from Xcode. JGrasp is terrible. :(
*/
public class SetTester_Theakanath {
   public static void main(String[]args) {
      Set<String> a = new TreeSet<String>();
      Set<String> b = new TreeSet<String>();
      Set<String> ccopyexamplea = new TreeSet<String>();
      Set<String> ccopyexampleb = new TreeSet<String>();
      Set<String> ccopyexamplec = new TreeSet<String>();
      Set<String> ccopyexampled = new TreeSet<String>();
      a.add("a");
      a.add("b");
      b.add("z");
      b.add("y");
      b.add("a");
      //c.add("j");
      
      //A
      ccopyexamplea.addAll(a);
      ccopyexamplea.addAll(b);
      System.out.println("Items for A: " + ccopyexamplea);
      
      //B
      ccopyexampleb.addAll(a);
      ccopyexampleb.retainAll(b);
      System.out.println("Items for B: " + ccopyexampleb);
      
      //C
      ccopyexamplec.addAll(a);
      ccopyexamplec.removeAll(b);
      System.out.println("Items for C: " + ccopyexamplec);
      
      //D
      if(subSetCheck(a, b))
         ccopyexampled.addAll(a);
      else
         ccopyexampled.addAll(b);
      System.out.println("Items for D: " + ccopyexamplec);
      
      
   }
   
   private static boolean subSetCheck(Set<String> a, Set<String> b) {
      Set<String> c = new TreeSet<String>();
      c.addAll(a);
      c.retainAll(b);
      return c.isEmpty();
   }
}