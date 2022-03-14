package com.assignment.Deeprooted.servicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.assignment.Deeprooted.dto.DSMatch;
import com.assignment.Deeprooted.entities.DemandOrder;
import com.assignment.Deeprooted.entities.SupplyOrder;
import com.assignment.Deeprooted.services.GenerateOutputServiceImpl;
import com.assignment.Deeprooted.services.IGenerateOutputService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class GenerateOutputServiceTestOne{
    @Mock 
    IGenerateOutputService generatedOutput;
  
    @BeforeEach
    void setUp() {
      List<SupplyOrder> supplyOrders=new ArrayList<>();
      supplyOrders.add(new SupplyOrder("s1", LocalTime.parse("09:45"), "tomato", 24, 100));
      supplyOrders.add(new SupplyOrder("s2", LocalTime.parse("09:46"), "tomato", 20, 90));
      supplyOrders.add(new SupplyOrder("s3", LocalTime.parse("09:50"), "tomato", 19, 50));
  
      List<DemandOrder> demandOrders=new ArrayList<>();
         demandOrders.add(new DemandOrder("d1", LocalTime.parse("09:47"), "tomato", 22, 110));
         demandOrders.add(new DemandOrder("d2", LocalTime.parse("09:48"), "tomato", 21, 10));
         demandOrders.add(new DemandOrder("d3", LocalTime.parse("09:49"), "tomato", 21, 40));
         generatedOutput = new GenerateOutputServiceImpl(supplyOrders,demandOrders);
    }

    @Test
    public void testLengthOfOutput(){
      assertEquals(4, generatedOutput.getDemandSupplyMatch().size());
    }
    @Test
    public void testTotalWeightReadyToTrade(){
      List<DSMatch> output=generatedOutput.getDemandSupplyMatch();
      Integer weight=output.stream().collect(Collectors.summingInt(DSMatch::getQuantityInKg));
      assertEquals(140,weight);      
    }

    @Test
    public void testIndividualTotalPriceReadyToTrade(){
      List<DSMatch> output=generatedOutput.getDemandSupplyMatch();
      Integer price=output.stream().collect(Collectors.summingInt(DSMatch::getPricePerKg));
      assertEquals(77,price);      
    }

    @Test
    public void testCompleteTotalPriceReadyToTrade(){
      List<DSMatch> output=generatedOutput.getDemandSupplyMatch();
      int price=0;
      for(DSMatch match:output){
        price+=match.getPricePerKg()*match.getQuantityInKg();
      }
      assertEquals(2750,price);      
    }

    @Test
    public void testOutputMatchObject(){
      List<DSMatch> demandOrderMatches=List.of(new DSMatch("d1","s2",20, 90),
                                                new DSMatch("d1","s3",19, 20),
                                                new DSMatch("d2","s3",19, 10),
                                                new DSMatch("d3","s3",19, 20));

      assertEquals(demandOrderMatches,generatedOutput.getDemandSupplyMatch()); 
    }

    


}
