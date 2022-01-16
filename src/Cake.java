/**
 *
 * Group members name and matric number:
 * 1) Muhammad Syahmi Bin Mustaffa            2020881574
 * 2) Amirul Hafidz Bin Noor Azmir            2020458048
 * 3) Muhammad Amirul Syafiq Bin Mohd Nor     2020620108
 * 4) Muhammad Zahiruddin Bin Othman          2020892534
 * Class : CS1103A
 */

public class Cake {
   private String custID; // D001, P003
   private String cakeType; // D24 Chocolate Cake,Red Velvet,Burnt Cheese Cake,Black Forest
   private int qty;
   private double price;
   static public String cakeNames[] = {
         "D24 Chocolate Cake",
         "Red Velvet",
         "Burnt Cheese Cake",
         "Black Forest"
   };

   public Cake(String ID, String cakeType, int qty) {
      this.custID = ID;
      this.cakeType = cakeType;
      this.qty = qty;
   }

   public void setID(String ID) {
      this.custID = ID;
   }

   public void setCakeType(String cakeType) {
      this.cakeType = cakeType;
   }

   public void setQty(int qty) {
      this.qty = qty;
   }

   // 2.a)Write the retriever method for custID, cakeType and qty
   public String getID() {
      return custID;
   }

   public String getCakeType() {
      return cakeType;
   }

   public int getQty() {
      return qty;
   }

   /*
    * 2.b)Write the detPrice() method that will return the price of cake based on
    * cakeType.Refer 2_GroupProject.docx
    */

   public double detPrice() {
      if (cakeType.equalsIgnoreCase(cakeNames[0]))
         price = 120;
      else if (cakeType.equalsIgnoreCase(cakeNames[1]))
         price = 80;
      else if (cakeType.equalsIgnoreCase(cakeNames[2]))
         price = 100;
      else if (cakeType.equalsIgnoreCase(cakeNames[3]))
         price = 80;
      else
         price = -1;

      return price;
   }

   /*
    * 2.c)Write the toString method that will return the output look like as below:
    * Customer ID:XXXX Cake Type:XXXXXXX Price: RM XX.XX Quantity:XX
    */

   public String toString() {
      String priceStr = String.format("%.2f", detPrice());
      String qtyStr = String.format("%d", qty);

      String output = String.format("Customer ID: %-5s Cake Type: %-20s Price: RM %-7s Quantity: %-6s",
            custID, cakeType, priceStr, qtyStr);
      return output;
   }
}
