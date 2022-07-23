package Task1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarExecutor {

    private List<Car> cars;

    public CarExecutor(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getCarsByYearInterval(int fromYear, int tillYear){
        return cars.stream()
                .filter(car -> car.getCreateYear() >= fromYear && car.getCreateYear() <= tillYear)
                .collect(Collectors.toList());
    }

    public List<Car> sortCarsByPrice() {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Car>> groupByYear(){
        return cars.stream()
                .collect(Collectors.groupingBy(Car::getCreateYear));
    }

}
