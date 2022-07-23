package Task1;

public class Car {

    private final String name;
    private final int createYear;
    private final String color;
    private final double price;
    private final double enginePower;

    public Car(String name, int createYear, String color, double price, double enginePower) {
        this.name = name;
        this.createYear = createYear;
        this.color = color;
        this.price = price;
        this.enginePower = enginePower;
    }

    public String getName() {
        return name;
    }

    public int getCreateYear() {
        return createYear;
    }

    public String getColor() {
        return color;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "main.java.Task1.Car{" +
                "name='" + name + '\'' +
                ", createYear=" + createYear +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", enginePower=" + enginePower +
                '}';
    }
}
