package com.assignment.Deeprooted.entities;

import java.time.LocalTime;

import java.util.Objects;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class DemandOrder {  //Demand Entity
    private String orderId;
    private LocalTime time;
    private String produce;
    private Integer pricePerKg;
    private Integer quantityInKg;
    
    public DemandOrder(@NonNull String orderId,@NonNull LocalTime time,@NonNull String produce,
                        @NonNull Integer pricePerKg,@NonNull Integer quantityInKg) {
    
        this.orderId = orderId;
        this.time = time;
        this.produce = produce;
        this.pricePerKg = pricePerKg;
        this.quantityInKg = quantityInKg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DemandOrder)) return false;
        DemandOrder demandOrder = (DemandOrder) o;
        return getOrderId().equals(demandOrder.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId());
    }

    @Override
    public String toString() {
        return "DemandOrder [order_id=" + orderId + ", price_per_kg=" + pricePerKg+"/kg"+ ", produce=" + produce
                + ", quantity_in_kg=" + quantityInKg+"kg"+ ", time=" + time + "]";
    }
    
}
