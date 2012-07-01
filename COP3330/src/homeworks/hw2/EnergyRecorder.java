package homeworks.hw2;

//~--- non-JDK imports --------------------------------------------------------

/**
 *
 * @author william orem
 */
import homeworks.hw1.ComplexNumber;

public class EnergyRecorder {

    /**
     * This field stores the P and Q for house1
     */
    ComplexNumber house1;

    /**
     * This field stores the P and Q for house2
     */
    ComplexNumber house2;

    /**
     * This field stores the P and Q for house3
     */
    ComplexNumber house3;

    /**
     * This field stores the P and Q for house4
     */
    ComplexNumber house4;

    /**
     * This field stores the P and Q for house5
     */
    ComplexNumber house5;

    /**
     * Constructs a new EnergyRecorder for the 5 houses
     */
    public EnergyRecorder() {
        house1 = new ComplexNumber();
        house2 = new ComplexNumber();
        house3 = new ComplexNumber();
        house4 = new ComplexNumber();
        house5 = new ComplexNumber();
    }

    /**
     * Returns an ApplianceType representation of the string
     * @param type the string whose value is the name of the appliance
     * @return the ApplianceType representation of type
     */
    public ApplianceType getApplianceType(String type) {
        if (type.equalsIgnoreCase("LIGHT")) {
            return ApplianceType.LIGHT;
        } else if (type.equalsIgnoreCase("FAN")) {
            return ApplianceType.FAN;
        } else if (type.equalsIgnoreCase("AC")) {
            return ApplianceType.AC;
        } else if (type.equalsIgnoreCase("FRIDGE")) {
            return ApplianceType.FRIDGE;
        } else if (type.equalsIgnoreCase("RANGE")) {
            return ApplianceType.RANGE;
        }

        return null;
    }

    /**
     * Based on an event, adds the load usage of the appliance to the corresponding house.
     * @param houseId the id of the house in the event (1 - 5)
     * @param type the type of appliance in ApplianceType representation
     * @param load the load impedance of the appliance
     * @param time the amount of the time the appliance was used
     */
    public void processEvent(int houseId, ApplianceType type, ComplexNumber load, double time) {

        /*
         *  TODO:
         * Remember: for LIGHT and FAN the I is 120/load
         *                       for AC, FRIDGE, and RANGE the I is 240/load
         * Then:         compute the values of P and Q following
         *                       P = real(load) * |I|^2
         *                       Q = imag(load) * |I|^2
         * Finally: multiply P and Q by the time
         *                      (hint: there is a method in ComplexNumber that can help)
         */
        ComplexNumber I;
        ComplexNumber admitance = load.pow(-1);
        double        i;

        // compute the proper value of I
        switch (type) {
            case LIGHT :
            case FAN :
                i = 120;

                break;

            case AC :
            case FRIDGE :
            case RANGE :
                i = 240;

                break;

            default :
                i = 0;
        }

        I = admitance.times(i);

        ComplexNumber output = load.times(I.magnitude2());

        // store the P and Q into the corresponding house
        switch (houseId) {
            case 1 :
                house1 = ComplexNumber.add(house1, output.times(time));
                break;

            case 2 :
                house2 = ComplexNumber.add(house2, output.times(time));
                break;

            case 3 :
                house3 = ComplexNumber.add(house3, output.times(time));
                break;

            case 4 :
                house4 = ComplexNumber.add(house4, output.times(time));
                break;

            case 5 :
                house5 = ComplexNumber.add(house5, output.times(time));
                break;
        }
    }
}


