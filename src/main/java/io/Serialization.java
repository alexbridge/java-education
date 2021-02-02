package io;

import dto.Car;

import java.io.*;

public class Serialization {
    public static void main(String[] args) {
        ByteArrayOutputStream bOutput = new ByteArrayOutputStream(1024);
        byte[] serialized = new byte[0];

        Car car = new Car();

        try (ObjectOutputStream out = new ObjectOutputStream(bOutput)) {
            out.writeObject(car);
            serialized = bOutput.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (serialized.length > 0) {
            try (ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(serialized)
            )) {
                Car decoded = (Car) in.readObject();
                System.out.println(decoded);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
