package homeworks.hw2;

//~--- non-JDK imports --------------------------------------------------------

/**
 *
 * @author william orem
 */
import homeworks.hw1.ComplexNumber;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.StringTokenizer;

public class EnergyManager {

    /**
     *
     * @param record
     * @return total
     */
    public static ComplexNumber getPowerReport(EnergyRecorder record) {

        // TODO:
        // Return a ComplexNumber whose real part is the sum of P
        // of all the houses (there are 5), and imaginary is
        // the sum of Q of all the houses (again there are 5)
        return ComplexNumber.add(ComplexNumber.add(ComplexNumber.add(record.house1, record.house3),
                ComplexNumber.add(record.house2, record.house4)), record.house5);
    }

    /**
     *
     * @param total
     * @param pricingPerHrs
     * @return revenue
     */
    public static double getTotalRevenue(ComplexNumber total, double pricingPerHrs) {
        ComplexNumber cost = total.times(pricingPerHrs);

        // cost can be real + imag because we charge for the cost of both power supply
        // that is, real and reactive powers
        double revenue = cost.getReal() + cost.getImag();

        return revenue;
    }

    /**
     *
     * @param line string containing houseid appliance type real imag and time
     * @param myEnergy
     * @return true if it worked
     */
    public static boolean parseLine(String line, EnergyRecorder myEnergy) {

        // 2. For each line in the file, parse it and break it into substrings using StringTokenizer(",")
        StringTokenizer st = new StringTokenizer(line, ",");

        // 3. process the event using the EnergyRecorder's object processEvent method
        // (hint: make sure to pass the correct data types)
        // note: remember to finish the implementation
        if (st.countTokens() == 5) {
            int           houseId = Integer.parseInt(st.nextToken());
            String        appType = st.nextToken();
            int           real    = Integer.parseInt(st.nextToken());
            int           imag    = Integer.parseInt(st.nextToken());
            ComplexNumber cNumber = new ComplexNumber(real, imag);
            double        time    = Double.parseDouble(st.nextToken());

            myEnergy.processEvent(houseId, myEnergy.getApplianceType(appType), cNumber, time);

            return true;
        }

        return false;
    }

    public static void printEnergy(EnergyRecorder myEnergy) {
        System.out.println("\tEnergy used per home");
        System.out.printf("\nHouse 1: P=%.4f Q=%.4f\n",myEnergy.house1.getReal(),myEnergy.house1.getImag());
        System.out.printf("House 2: P=%.4f Q=%.4f\n",myEnergy.house2.getReal(),myEnergy.house2.getImag());
        System.out.printf("House 3: P=%.4f Q=%.4f\n",myEnergy.house3.getReal(),myEnergy.house3.getImag());
        System.out.printf("House 4: P=%.4f Q=%.4f\n",myEnergy.house4.getReal(),myEnergy.house4.getImag());
        System.out.printf("House 5: P=%.4f Q=%.4f\n",myEnergy.house5.getReal(),myEnergy.house5.getImag());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        // TODO:
        String         filename = args[0];
        EnergyRecorder myEnergy = new EnergyRecorder();

        // Location of file to read
        File inFile = new File(filename);

        // 1. Read filename using Scanner
        // In eclipse the filename will be on the project's roots directory
        // In command line the filename will be on the same directory where the EnergyManager.class file is
        try (Scanner scanner = new Scanner(inFile)) {
            while (scanner.hasNextLine()) {
                parseLine(scanner.nextLine(), myEnergy);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.print(e.toString());
        }
        // 4. after processing the file, report the total energy used by each house in terms of P and Q
        // 5. use the method in EnergyManager (getPowerReport) for reporting the total energy consumption in terms of P and Q
        // 6. use the method in EnergyManager (getTotalRevenue) for reporting the total revenue based on 0.15/1000 dollars (15 cents) per kilowatt-hour
        printEnergy(myEnergy);

        ComplexNumber total = getPowerReport(myEnergy);

        System.out.printf("\nTotal:   P=%.4f Q=%.4f\n",total.getReal(),total.getImag());
        double report = getTotalRevenue(total, (0.15 / 1000));

        System.out.printf("\nExpected Revenue: $%.2f\n",report);

        
    }
}

