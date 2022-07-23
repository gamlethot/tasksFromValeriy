package Task1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car("Porche", 1990, "Black", 223000, 250.5);
        Car car2 = new Car("Porche", 2000, "Black", 140000, 300);
        Car car3 = new Car("Porche", 2004, "Black", 70000, 350.5);
        Car car4 = new Car("Porche", 2004, "Black", 100000, 450.5);
        Car car5 = new Car("Porche", 2004, "Black", 221000, 550.5);
        Car car6 = new Car("Porche", 2022, "Black", 150000, 650.5);

        CarExecutor carExecutor = new CarExecutor(List.of(car1, car2, car3, car4, car5, car6));

        System.out.println(carExecutor.getCars());
        System.out.println("-------------");
        System.out.println(carExecutor.getCarsByYearInterval(2000, 2004));
        System.out.println("-------------");
        System.out.println(carExecutor.sortCarsByPrice());
        System.out.println("-------------");
        System.out.println(carExecutor.groupByYear());
    }
}