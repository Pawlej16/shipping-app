import java.lang.classfile.constantpool.DoubleEntry;
import java.util.function.IntToDoubleFunction;

abstract class ShipmentOrder implements SummaryPrintable{
    private String orderNumber;
    private String customerName;
    private double distanceKm;
    private double baseFee;
    private boolean insured;
    private double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public boolean getInsured() {
        return insured;
    }

    public double getLastCalculatedPrice() {
        return lastCalculatedPrice;
    }

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

    public void validateOrder() {
        if (orderNumber == null || orderNumber.isEmpty()) {
            throw new IllegalArgumentException("Order number cannot be empty!!");
        }
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must not be lower or equal to zero!")
        }
    }

    public void validateSpecificRules() {
    }

    public double applyInsurance(double price) {
        if (insured) {
            price = price + price*0.07;
        }
        return price;
    }

    public double applyBusinessDiscount(double price) {
        return price;
    }

    @Override
    String buildSummaryLine() {

    }
}