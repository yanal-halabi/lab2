package lab2;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

// Car class definition
class Car {
    int id;
    String make;
    String model;
    int yearOfManufacture;
    String color;
    double price;
    String registrationNumber;

    public Car(int id, String make, String model, int yearOfManufacture, String color, double price, String registrationNumber) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.price = price;
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return id + ", " + make + ", " + model + ", " + yearOfManufacture + ", " + color + ", " + price + ", " + registrationNumber;
    }
}

// CarFilter class definition with filtering and saving functionality
public class CarFilter {
    public static void main(String[] args) {
        // Create an array of Car objects
        Car[] cars = {
            new Car(1, "Toyota", "Camry", 2015, "Red", 15000, "ABC123"),
            new Car(2, "Honda", "Civic", 2018, "Blue", 20000, "XYZ456"),
            new Car(3, "Toyota", "Corolla", 2017, "Black", 17000, "LMN789"),
            new Car(4, "Ford", "Mustang", 2016, "Yellow", 25000, "JKL012"),
            new Car(5, "Honda", "Accord", 2019, "White", 22000, "DEF345")
        };

        // Save cars of a given brand to a file
        saveCarsToFile(filterCarsByMake(cars, "Toyota"), "cars_toyota.txt");

        // Save cars of a given model that have been in use for more than n years
        int nYears = 5;
        saveCarsToFile(filterCarsByModelAndYears(cars, "Civic", nYears), "cars_civic_more_than_" + nYears + "_years.txt");

        // Save cars of a given year of manufacture with a price higher than the specified amount
        int yearOfManufacture = 2017;
        double minPrice = 16000;
        saveCarsToFile(filterCarsByYearAndPrice(cars, yearOfManufacture, minPrice), "cars_year_" + yearOfManufacture + "_price_above_" + minPrice + ".txt");
    }

    // Filter cars by make (brand)
    public static List<Car> filterCarsByMake(Car[] cars, String make) {
        return filterCars(cars, car -> car.make.equalsIgnoreCase(make));
    }

    // Filter cars by model and number of years in use
    public static List<Car> filterCarsByModelAndYears(Car[] cars, String model, int years) {
        int currentYear = java.time.Year.now().getValue();
        return filterCars(cars, car -> car.model.equalsIgnoreCase(model) && (currentYear - car.yearOfManufacture) > years);
    }

    // Filter cars by year of manufacture and price
    public static List<Car> filterCarsByYearAndPrice(Car[] cars, int yearOfManufacture, double minPrice) {
        return filterCars(cars, car -> car.yearOfManufacture == yearOfManufacture && car.price > minPrice);
    }

    // General filter method
    public static List<Car> filterCars(Car[] cars, java.util.function.Predicate<Car> predicate) {
        return java.util.Arrays.stream(cars).filter(predicate).collect(Collectors.toList());
    }

    // Save cars to a file
    public static void saveCarsToFile(List<Car> cars, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Car car : cars) {
                writer.write(car.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
 Running this program will create three text files:
-cars_toyota.txt (Toyota cars),
-cars_civic_more_than_5_years.txt (Honda Civic cars older than 5 years),
-cars_year_2017_price_above_16000.txt (cars from 2017 priced above $16,000).
 */
