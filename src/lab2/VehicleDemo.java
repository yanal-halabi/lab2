package lab2;

//Step 1: Superclass Engine and its subclasses

abstract class Engine {
 public abstract String getEngineType();
}

class CombustionEngine extends Engine {
 @Override
 public String getEngineType() {
     return "Combustion Engine";
 }
}

class ElectricEngine extends Engine {
 @Override
 public String getEngineType() {
     return "Electric Engine";
 }
}

class HybridEngine extends Engine {
 @Override
 public String getEngineType() {
     return "Hybrid Engine";
 }
}

//Step 2: Class Manufacture

class Manufacture {
 private String name;
 private String country;

 public Manufacture(String name, String country) {
     this.name = name;
     this.country = country;
 }

 // Setters and getters
 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getCountry() {
     return country;
 }

 public void setCountry(String country) {
     this.country = country;
 }

 @Override
 public String toString() {
     return "Manufacture: " + name + " (" + country + ")";
 }
}

//Step 3: Superclass Vehicle with abstract method ShowCharacteristics()

abstract class Vehicle {
 protected Manufacture manufacture;
 protected Engine engine;

 public Vehicle(Manufacture manufacture, Engine engine) {
     this.manufacture = manufacture;
     this.engine = engine;
 }

 public abstract void ShowCharacteristics();
}

//Step 4: Subclasses of Vehicle (ICEV, BEV, HybridV)

class ICEV extends Vehicle {
 public ICEV(Manufacture manufacture, CombustionEngine engine) {
     super(manufacture, engine);
 }

 @Override
 public void ShowCharacteristics() {
     System.out.println("ICEV Characteristics:");
     System.out.println(manufacture);
     System.out.println("Engine Type: " + engine.getEngineType());
 }
}

class BEV extends Vehicle {
 public BEV(Manufacture manufacture, ElectricEngine engine) {
     super(manufacture, engine);
 }

 @Override
 public void ShowCharacteristics() {
     System.out.println("BEV Characteristics:");
     System.out.println(manufacture);
     System.out.println("Engine Type: " + engine.getEngineType());
 }
}

class HybridV extends Vehicle {
 public HybridV(Manufacture manufacture, HybridEngine engine) {
     super(manufacture, engine);
 }

 @Override
 public void ShowCharacteristics() {
     System.out.println("HybridV Characteristics:");
     System.out.println(manufacture);
     System.out.println("Engine Type: " + engine.getEngineType());
 }
}

//Step 5: Main class to demonstrate

public class VehicleDemo {
 public static void main(String[] args) {
     // Create Manufacture objects
     Manufacture toyota = new Manufacture("Toyota", "Japan");
     Manufacture tesla = new Manufacture("Tesla", "USA");
     Manufacture ford = new Manufacture("Ford", "USA");

     // Create Vehicle arrays
     Vehicle[] vehicles = new Vehicle[3];

     // Create ICEV, BEV, and HybridV objects
     vehicles[0] = new ICEV(toyota, new CombustionEngine());
     vehicles[1] = new BEV(tesla, new ElectricEngine());
     vehicles[2] = new HybridV(ford, new HybridEngine());

     // Step 6: Demonstrate ShowCharacteristics() for each vehicle
     for (Vehicle vehicle : vehicles) {
         vehicle.ShowCharacteristics();
         System.out.println("------------------------");
     }
 }
}
