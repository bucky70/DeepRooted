package com.assignment.Deeprooted.dtoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.assignment.Deeprooted.dto.DSMatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;

public class DSMatchTest {
    @Mock 
    DSMatch demandSupplyMatch;
  
    @BeforeEach
    void setUp() {
        demandSupplyMatch = new DSMatch("d1","s2",100, 30);
    }
    
    @Test
    @DisplayName("testDemandAndSupplyOrders to check orders based on orderID")
    void testDemandAndSupplyOrders() {
        assertEquals("d",demandSupplyMatch.getDemandOrderId().substring(0,1));
        assertEquals("s",demandSupplyMatch.getSupplyOrderId().substring(0,1));
    }
    @Test
    @DisplayName("testNullInputs to check NullPointerException for null inputs")
    void testNullInputs() {
        assertThrows(NullPointerException.class, ()->{new DSMatch(null,null,null,null);});
    }
}
