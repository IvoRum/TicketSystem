package com.diploma.ticket.system.decission.tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CSV_Helper {
    private static final String SEPARATOR = ",";

    // attributes are the column names
    // attributes are expected to be in the first line
    public static String[] extractAttributesFromData(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine().split(SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Set<Passenger> readTrainData(String filePath){
        Set<Passenger> data = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // remove first heading line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(SEPARATOR);
                try{
                    Passenger p = new Passenger(values[0], values[1], values[2], values[3], values[4]);
                    data.add(p);
                }catch(IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Set<Passenger> readTestData(String filePath){
        Set<Passenger> data = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // remove first heading line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(SEPARATOR);
                try{
                    Passenger p = new Passenger(values[0], values[1], values[2], values[3]);
                    data.add(p);
                }catch(IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

   
}