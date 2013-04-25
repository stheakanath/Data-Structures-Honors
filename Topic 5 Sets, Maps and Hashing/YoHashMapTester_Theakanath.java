//Sony Theakanath
//Data Structures

/**
   My HashMap works well, it handles collisions well. I tested it by creating 
   4 values into the map. My checking if it works I make sure it gives the 
   correct value to the key, and then it should output the correct values, as 
   demonstrated by the print lines. The app then handles collisions by overwriting 
   the values in the specified key. 
*/

public class YoHashMapTester_Theakanath {
   public static void main(String[]args) {
      HashMap<String, String> map = new HashMap<String, String>(16);
      map.put("Sony", "Bellarmine");
      map.put("Sony", "Testing");
      map.put("Bryce", "St. Francis High School");
      map.put("Took", "Bellarmine");
      System.out.println(map.get("Sony"));
      System.out.println(map.get("Bryce"));
      System.out.println(map.get("Took"));
   }
} 


/**
   This class is bascially what it says, it's a hashmap which 
   stores a key and a value and that into an array. The complexion
   time for this is O(n) at the worst case. 
*/
class HashMap<String, Object> {
   private Node<String, Object>[] values;
   
   /**
      Default constructor it makes the value array so that 
      everything can be updated. 
   */ 
   public HashMap(int count) {  
      values = new Node[count];  
   } 
   
   /**
      Gets the object based on the key you are searching for in
      the Map. It returns an error if the key is not found. 
   */
   public Object get(String key) {
      int index = key.hashCode() % values.length;
      Node<String, Object> entry = values[index];  
      while (entry != null && !key.equals(entry.getKey()))
         entry = entry.getNext();  
      if(entry != null)
         return entry.getValue();
      return null;
   } 
 
   /**
      Put puts the key and the value in the array. If there is a 
      duplicate it puts the new value in the array and then deletes 
      the old value. 
   */
   public void put(String key, Object value) {  
      int index = key.hashCode() % values.length;  
      Node<String, Object> entry = values[index];  
      if (entry != null) {  
         boolean finished = false;  
         while(finished == false) {  
            if (key.equals(entry.getKey())) {  
               finished = true; 
               entry.setValue(value);  
            } else if (entry.getNext() == null) {  
               finished = true;
               entry.setNext(new Node<String, Object>(key, value));  
            }   
            entry = entry.getNext();  
         }  
      } else
         values[index] = new Node<String, Object>(key, value);  
   } 
     
   /**
      Node class, modified to work with map. 
   */
   private class Node<String, Object> {
      private Node<String, Object> next;
      private final String key;
      private Object value;
      
      public Node(String key, Object value) {
         this.key = key;
         this.setValue(value);
      }
     
      public String getKey() { return key; }
      public void setValue(Object value) { this.value = value; }
      public Object getValue() { return value; }
      public void setNext(Node<String, Object> next) { this.next = next; }
      public Node<String, Object> getNext() { return next; } 
   }
}