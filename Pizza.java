public class Pizza {
    private String name;
    private String size;
    private double price;
    private String topping; // Single topping

    /**
     * Constructor to initialize a Pizza object.
     *
     * @param name    the name of the pizza
     * @param size    the size of the pizza (small, medium, large)
     * @param price   the price of the pizza
     * @param topping the topping on the pizza
     */
    public Pizza(String name, String size, double price, String topping) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.topping = topping;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    @Override
    public String toString() {
        return "Pizza{name='" + name + "', size='" + size + "', price=" + price +
                ", topping='" + topping + "'}";
    }
}