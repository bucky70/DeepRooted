package com.assignment.Deeprooted.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class DSMatch{    //DemandSupplyMatch DTO entity
    private String demandOrderId;
    private String supplyOrderId;
    private Integer pricePerKg;
    private Integer quantityInKg;

    public DSMatch(@NonNull String demandOrderId,@NonNull String supplyOrderId,
                                  @NonNull Integer pricePerKg,@NonNull Integer quantityInKg) {

        this.demandOrderId = demandOrderId;
        this.supplyOrderId = supplyOrderId;
        this.pricePerKg = pricePerKg;
        this.quantityInKg = quantityInKg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DSMatch)) return false;
        DSMatch response = (DSMatch) o;
        return (getDemandOrderId()+getSupplyOrderId()).equals(response.getDemandOrderId()+response.getSupplyOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDemandOrderId()+getSupplyOrderId());
    }

    @Override
    public String toString() {
        // return  "DS Match{" +
        //         "demand-order-id='" + demand_order_id + '\'' +
        //         ", supply-order-id='" + supply_order_id + '\'' +
        //         ", price/kg='" + price_per_kg + '\'' +
        //         ", quantity in kg='" + quantity_in_kg + '\'' +
        //         '}';
            
        return  demandOrderId + " " +
                supplyOrderId + " " +
                pricePerKg +"/kg"+ " " +
                quantityInKg +"kg" ;
               
        
    }

    
    
}
