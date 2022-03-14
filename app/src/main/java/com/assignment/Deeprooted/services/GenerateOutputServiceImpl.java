package com.assignment.Deeprooted.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import com.assignment.Deeprooted.dto.DSMatch;
import com.assignment.Deeprooted.entities.DemandOrder;
import com.assignment.Deeprooted.entities.SupplyOrder;

public class GenerateOutputServiceImpl implements IGenerateOutputService{  //Service that contains business logic and returns DTO
  
    private List<SupplyOrder> supplyOrders;
    private List<DemandOrder> demandOrders;

    public GenerateOutputServiceImpl(List<SupplyOrder> supplyOrders, List<DemandOrder> demandOrders) {
        this.supplyOrders = supplyOrders;
        this.demandOrders = demandOrders;
    }

    @Override
    public List<DSMatch> getDemandSupplyMatch(){
        List<DSMatch> result=new ArrayList<>();

        List<DemandOrder> demandOrders = sortDemandOrders(this.demandOrders);
        List<SupplyOrder> supplyOrders  = sortSupplyOrders(this.supplyOrders);

        for(DemandOrder demandOrder:demandOrders){ 
            for(SupplyOrder supplyOrder:supplyOrders){
             if(demandOrder.getProduce().equals(supplyOrder.getProduce())){
                 if(demandOrder.getPricePerKg()>=supplyOrder.getPricePerKg() && demandOrder.getQuantityInKg()!=0 &&supplyOrder.getQuantityInKg()!=0){
                      result.add(new DSMatch(
                    demandOrder.getOrderId(), supplyOrder.getOrderId(), supplyOrder.getPricePerKg(),Math.min(supplyOrder.getQuantityInKg(),demandOrder.getQuantityInKg())));
           
                    if(demandOrder.getQuantityInKg()>supplyOrder.getQuantityInKg()){
                     demandOrder.setQuantityInKg(demandOrder.getQuantityInKg()-supplyOrder.getQuantityInKg());
                     supplyOrder.setQuantityInKg(0);
                    }
                    else{
                     supplyOrder.setQuantityInKg(supplyOrder.getQuantityInKg()-demandOrder.getQuantityInKg());
                     demandOrder.setQuantityInKg(0);
                     
                    }
                 }
             }
         }
        }
        
        return result;
    }

    private List<SupplyOrder> sortSupplyOrders(List<SupplyOrder> supplyOrders) {  //sort Supply orders in Ascending order

        Comparator<SupplyOrder> byPriceSupply = Comparator.comparing(SupplyOrder::getPricePerKg);
        Comparator<SupplyOrder> byTimeSupply = Comparator.comparing(SupplyOrder::getTime);
        
        Collections.sort(supplyOrders, byTimeSupply.thenComparing(byPriceSupply));

        return supplyOrders;
    }

    private List<DemandOrder> sortDemandOrders(List<DemandOrder> demandOrders) {  //sort Demand orders in Descending order

        Comparator<DemandOrder> byPriceDemand = Comparator.comparing(DemandOrder::getPricePerKg).reversed();
        Comparator<DemandOrder> byTimeDemand = Comparator.comparing(DemandOrder::getTime);
         
        Collections.sort(demandOrders, byTimeDemand.thenComparing(byPriceDemand));
        return demandOrders;
    }
    
}



