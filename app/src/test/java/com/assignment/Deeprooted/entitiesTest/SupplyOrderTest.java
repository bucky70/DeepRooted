package com.assignment.Deeprooted.entitiesTest;

import java.time.LocalTime;

import com.assignment.Deeprooted.entities.SupplyOrder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SupplyOrderTest")
public class SupplyOrderTest {
    
    @Test
    @DisplayName("getProduceFromDemandOrder method Should Return produce Given DemandOrder")
    public void getDetailsFromSupplyOrder(){
        //Arrange
        SupplyOrder actualSupply = new SupplyOrder("s2", LocalTime.parse("09:45"),"tomato",25,110);

        //Act
        String order_id= actualSupply.getOrderId();
        LocalTime time = actualSupply.getTime();
        String produce = actualSupply.getProduce();
        Integer price_per_kg=actualSupply.getPricePerKg();
        Integer quantity_in_kg=actualSupply.getQuantityInKg();
        
        //Assert
        Assertions.assertEquals("s2",order_id);
        Assertions.assertEquals("09:45",time.toString());
        Assertions.assertEquals("tomato",produce);
        Assertions.assertEquals(25,price_per_kg);
        Assertions.assertEquals(110,quantity_in_kg);
        

    }

    //@Test(expected=NullPointerException.class)
    @Test
    @DisplayName("getDetailsNullPointerCheck method Should check illegal argument exception for Given null SupplyOrder")
    public void getDetailsNullPointerCheck(){     
        Assertions.assertThrows(NullPointerException.class,
              ()->{new SupplyOrder("s1", null,null,25,110);});
    }
}
