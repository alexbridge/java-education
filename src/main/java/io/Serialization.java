package io;

import dto.Car;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Serialization {
    public static void main(String[] args) {
        Path res = Paths.get("", "tmp", "io", "car");
        Car car = new Car();

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(res.toString())
        )) {
            out.writeObject(car);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(res.toString())
        )) {
            Car decoded = (Car) in.readObject();
            System.out.println(decoded.getType());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
