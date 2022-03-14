package com.assignment.Deeprooted.entitiesTest;

import java.time.LocalTime;

import com.assignment.Deeprooted.entities.DemandOrder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DemandOrderTest")
public class DemandOrderTest {
    
    @Test
    @DisplayName("getProduceFromDemandOrder method Should Return produce Given DemandOrder")
    public void getProduceFromDemandOrder(){
        //Arrange
        DemandOrder actualDemand = new DemandOrder("d2", LocalTime.parse("09:45"),"tomato",25,110);

        //Act
        String order_id= actualDemand.getOrderId();
        LocalTime time = actualDemand.getTime();
        String produce = actualDemand.getProduce();
        Integer price_per_kg=actualDemand.getPricePerKg();
        Integer quantity_in_kg=actualDemand.getQuantityInKg();
        
        //Assert
        Assertions.assertEquals("d2",order_id);
        Assertions.assertEquals("09:45",time.toString());
        Assertions.assertEquals("tomato",produce);
        Assertions.assertEquals(25,price_per_kg);
        Assertions.assertEquals(110,quantity_in_kg);
    }

    @Test
    @DisplayName("getDetailsNullPointerCheck method Should check illegal argument exception for Given null SupplyOrder")
    public void getDetailsNullPointerCheck(){     
        Assertions.assertThrows(NullPointerException.class,
              ()->{new DemandOrder(null, null,null,25,110);});
    }
}
