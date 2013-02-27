//Sony Theakanath
//Data Structures

/**
   Part 1: Try out at least 2 of the programming problems chosen from all of 
           the Exercises in sections 1.1-1.5      
*/

public class Beginning_Theakanath { public static void main(String[]args) { } }

/**
   Programming Problem 1 and 2: Section 1.2
   Create class Computer and Notebook
*/

class Computer {
   private String modelname, processorname;
   private double numofexpansionslots, timeuntilbatterydischarge, cost;

   public Computer() {
      modelname = "blank";
      processorname = "blank";
      numofexpansionslots = 0;
      timeuntilbatterydischarge = 0;
      cost = 0;
   }
   
   public Computer(String modelname, String processorname, double numofexpansionslots, double cost, double timeuntilbatterydischarge) {
      this.modelname = modelname;
      this.processorname = processorname;
      this.numofexpansionslots = numofexpansionslots;
      this.timeuntilbatterydischarge = timeuntilbatterydischarge;
      this.cost = cost;
   }
   
   public String getname() {
      return modelname;
   }
   
   public void writename(String newname) {
      modelname = newname;
   }
   
   public String getprocessor() {
      return processorname;
   }
   
   public void writeprocessor(String newname) {
      processorname = newname;
   }
   
   public double getslots() {
      return numofexpansionslots;
   }
   
   public void writeslots(double newslots) {
      numofexpansionslots = newslots;
   }
   
   public double gettime() {
      return timeuntilbatterydischarge;
   }
   
   public void writetime(double newtime) {
      timeuntilbatterydischarge = newtime;
   }
   
   public double getcost() {
      return cost;
   }
   
   public void writecost(double newcost) {
      cost = newcost;
   }
}

class Notebook extends Computer {
   private double wirelessavailable;
   
   public Notebook(String modelname, String processorname, double numofexpansionslots, double cost, double timeuntilbatterydischarge, double wirelessavailable) {
      super(modelname, processorname, numofexpansionslots, timeuntilbatterydischarge, cost);
      this.wirelessavailable = wirelessavailable;
   }
   
   public double getwireless() {
      return wirelessavailable;
   }
   
   public void writewireless(double yes) {
      this.wirelessavailable = yes;
   }
}
   