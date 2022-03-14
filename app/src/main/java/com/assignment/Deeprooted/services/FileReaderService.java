package com.assignment.Deeprooted.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.assignment.Deeprooted.entities.DemandOrder;
import com.assignment.Deeprooted.entities.SupplyOrder;
import com.assignment.Deeprooted.exceptions.NoSuchCommandException;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;

import lombok.NonNull;

public class FileReaderService {  //To read data from file
    @NonNull
    private String filePath;
    List<String> supplyResult;
    List<String> demandResult;
    public FileReaderService(@NonNull String filePath) {
        this.filePath=filePath;
        this.supplyResult=new ArrayList<>();
        this.demandResult=new ArrayList<>();
        

    }

   public List<SupplyOrder> getSupplyOrders() throws NoSuchCommandException{
       List<SupplyOrder> supplyOrders=new ArrayList<>();
    try (Stream<String> sLines = Files.lines(Paths.get(filePath))) {
		supplyResult=filterLinesStartsWith(sLines,"s");
    
        for(String entry:supplyResult){
            String[] input=entry.split(" ");
            if(input.length!=5){
                throw new NoSuchCommandException("provided input is not valid");
            }
            String orderId=input[0];
            LocalTime time =LocalTime.parse(input[1]);
            String produce=input[2];
            Integer pricePerKg=Integer.parseInt(input[3].substring(0,input[3].length()-3));
            Integer quantityInKg=Integer.parseInt(input[4].substring(0,input[4].length()-2));
            supplyOrders.add(new SupplyOrder(orderId,time,produce,pricePerKg,quantityInKg));
        }
	}
    catch (IOException e) {
        throw new NoSuchCommandException("An error occurred while reading the input file  " + filePath);
    }
   
    return supplyOrders;
   } 
   public List<DemandOrder> getDemandOrders() throws NoSuchCommandException{
    List<DemandOrder> demandOrders=new ArrayList<>();
    try (Stream<String> dLines = Files.lines(Paths.get(filePath))) {
        demandResult=filterLinesStartsWith(dLines,"d");

     for(String entry:demandResult){
        String[] input=entry.split(" ");
        if(input.length!=5){
            throw new NoSuchCommandException("provided input is not valid");
        }
        String orderId=input[0];
        LocalTime time =LocalTime.parse(input[1]);
        String produce=input[2];
        Integer pricePerKg=Integer.parseInt(input[3].substring(0,input[3].length()-3));
        Integer quantityInKg=Integer.parseInt(input[4].substring(0,input[4].length()-2));
        demandOrders.add(new DemandOrder(orderId,time,produce,pricePerKg,quantityInKg));   
     }           
    }
    catch (IOException x) {
        throw new NoSuchCommandException("An error occurred while reading the input file  " + filePath);
    }
    
    return demandOrders;
 }

    private List<String> filterLinesStartsWith(Stream<String> lines,String input) {
        List<String> result=lines.filter(line->line.startsWith("d"))
                                .collect(Collectors.toList());
                            
        return result;
    }

	
}
