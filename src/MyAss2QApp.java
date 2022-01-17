import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class MyAss2QApp {
    public static void main(String[] args) throws Exception {

        try {
            BufferedReader br = new BufferedReader(new FileReader("./lib/cakeOrder.txt"));
            PrintWriter pickOut = new PrintWriter(new FileWriter("./lib/pickup.txt"));
            PrintWriter delOut = new PrintWriter(new FileWriter("./lib/delivery.txt"));
            Queue cakeQ = new Queue();
            Queue tempQ = new Queue();

            // a) b) c) read the data from cakeOrder.txt and insert into cakeQ
            String buffer = null;
            while ((buffer = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(buffer, "*");
                Cake cakeOrder = new Cake(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));
                cakeQ.enqueue(cakeOrder);
            }

            // d)display the data in the cakeQ
            while (!cakeQ.isEmpty()) {
                Cake temp = cakeQ.dequeue();
                System.out.println(temp.toString());
                tempQ.enqueue(temp);
            }

            /*
             * e)The first character of custID is based on the delivery type. if the first
             * character is 'P'
             * mean the customer choose to pickup the cake and if the first character is
             * 'D', the customer
             * choose to have delivery service. Example of custID are P002,D112 and etc.
             * Write the data for delivery
             * into delivery.txt output file and the data for customer that choose self pick
             * up into pickup.txt.
             */

            int p = 1, d = 1;
            pickOut.println("Data for self-pick-up:\n");
            delOut.println("Data for delivery:\n");

            while (!tempQ.isEmpty()) {
                Cake temp = tempQ.dequeue();

                if (temp.getID().charAt(0) == 'P') {
                    pickOut.println(p + ") " + temp.toString());
                    p++;
                } else if (temp.getID().charAt(0) == 'D') {
                    delOut.println(d + ") " + temp.toString());
                    d++;
                }

                cakeQ.enqueue(temp);
            }

            /*
             * f)Display the total quantity order for each cake type and display the cake
             * name of the highest total order
             */

            int total[] = { 0, 0, 0, 0 };
            String cakeNames[] = Cake.cakeNames;

            while (!cakeQ.isEmpty()) {
                Cake temp = cakeQ.dequeue();

                if (temp.getCakeType().equalsIgnoreCase(cakeNames[0]))
                    total[0] = total[0] + temp.geQty();
                else if (temp.getCakeType().equalsIgnoreCase(cakeNames[1]))
                    total[1] = total[1] + temp.geQty();
                else if (temp.getCakeType().equalsIgnoreCase(cakeNames[2]))
                    total[2] = total[2] + temp.geQty();
                else if (temp.getCakeType().equalsIgnoreCase(cakeNames[3]))
                    total[3] = total[3] + temp.geQty();

                tempQ.enqueue(temp);
            }

            System.out.println();
            System.out.println("Total quantity of " + cakeNames[0] + ": " + total[0] + "\n" +
                    "Total quantity of " + cakeNames[1] + ": " + total[1] + "\n" +
                    "Total quantity of " + cakeNames[2] + ": " + total[2] + "\n" +
                    "Total quantity of " + cakeNames[3] + ": " + total[3] + "\n");

            int highValue = 0;
            String highName = null;
            for (int i = 0; i < total.length; i++) {
                if (total[i] > highValue) {
                    highValue = total[i];
                    highName = cakeNames[i];
                }
            }
            System.out.println("Highest total order is " + highValue + " for " + highName);

            /*
             * g)Display the receipt that will display the custID, cakeType, price(using
             * detPrice() method), qty,
             * payment for each order. In order to calculate the payment for each order you
             * need to multiply quantity
             * with the cake price and it is an extra charge of RM 5.00 for delivery
             * service. Lastly, display the total
             * payment for all the orders.
             */

            int i = 1, totalPayment = 0;
            DecimalFormat df = new DecimalFormat("0.00");
            while (!tempQ.isEmpty()) {
                Cake temp = tempQ.dequeue();
                String delType = null;
                double payment = temp.detPrice() * temp.geQty();

                if (temp.getID().charAt(0) == 'P') {
                    delType = "Self Pick-Up";
                } else {
                    delType = "Delivery";
                    payment += 5;
                }
                totalPayment += payment;

                System.out.println("++++++++++++++++++++++++++++++++++\n"
                        + "Customer no " + i + "\n"
                        + "++++++++++++++++++++++++++++++++++\n"
                        + "Customer ID: " + temp.getID() + "\n"
                        + "Cake Name: " + temp.getCakeType() + "\n"
                        + "Price: RM " + df.format(temp.detPrice()) + "\n"
                        + "Quantity: " + temp.geQty() + "\n"
                        + "Delivery / Self Pick-Up: " + delType + "\n"
                        + "Payment: RM " + df.format(payment) + "\n");
                i++;
            }
            System.out.println("Total Payment: RM " + df.format(totalPayment));

            br.close();
            pickOut.close();
            delOut.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    } /*** End of main() Method ***/
}/*** End of Application Class ***/