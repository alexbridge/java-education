package garage;

import garage.dto.Car;
import garage.dto.Quad;
import garage.dto.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class DefaultGarage {
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(){{
            add(new Car());
            add(new Quad());
            add(new Vehicle());
        }};
    }
}
