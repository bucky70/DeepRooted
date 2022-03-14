package com.assignment.Deeprooted.servicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.assignment.Deeprooted.dto.DSMatch;
import com.assignment.Deeprooted.entities.DemandOrder;
import com.assignment.Deeprooted.entities.SupplyOrder;
import com.assignment.Deeprooted.services.IGenerateOutputService;
import com.assignment.Deeprooted.services.GenerateOutputServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class GenerateOutputServiceTestTwo{
    @Mock 
    IGenerateOutputService generatedOutput;
  
    @BeforeEach
    void setUp() {
      List<SupplyOrder> supplyOrders=new ArrayList<>();
      supplyOrders.add(new SupplyOrder("s1", LocalTime.parse("09:45"), "potato",  110,1));
      supplyOrders.add(new SupplyOrder("s2", LocalTime.parse("09:45"), "potato",  110,7));
      supplyOrders.add(new SupplyOrder("s3", LocalTime.parse("09:45"), "potato",  110,2));
      supplyOrders.add(new SupplyOrder("s4", LocalTime.parse("09:45"), "tomato",  110,11));
  
      List<DemandOrder> demandOrders=new ArrayList<>();
      demandOrders.add(new DemandOrder("d1", LocalTime.parse("09:47"), "tomato", 110, 1));
      demandOrders.add(new DemandOrder("d2", LocalTime.parse("09:45"), "potato",  110,10));
      demandOrders.add(new DemandOrder("d3", LocalTime.parse("09:48"), "tomato",  110,10));
         generatedOutput = new GenerateOutputServiceImpl(supplyOrders,demandOrders);
    }

    @Test
    public void testLengthOfOutput(){
      assertEquals(5, generatedOutput.getDemandSupplyMatch().size());
    }
    @Test
    public void testTotalWeightReadyToTrade(){
      List<DSMatch> output=generatedOutput.getDemandSupplyMatch();
      Integer weight=output.stream().collect(Collectors.summingInt(DSMatch::getQuantityInKg));
      assertEquals(21,weight);      
    }

    @Test
    public void testIndividualTotalPriceReadyToTrade(){
      List<DSMatch> output=generatedOutput.getDemandSupplyMatch();
      Integer price=output.stream().collect(Collectors.summingInt(DSMatch::getPricePerKg));
      assertEquals(550,price);      
    }
    @Test
    public void testCompleteTotalPriceReadyToTrade(){
      List<DSMatch> output=generatedOutput.getDemandSupplyMatch();
      int price=0;
      for(DSMatch match:output){
        price+=match.getPricePerKg()*match.getQuantityInKg();
      }
      assertEquals(2310,price);      
    }

    @Test
    public void testOutputMatchObject(){
      List<DSMatch> demandOrderMatches=List.of(new DSMatch("d2","s1",110, 1),
                                                new DSMatch("d2","s2",110, 7),
                                                new DSMatch("d2","s3",110, 2),
                                                new DSMatch("d1","s4",110, 1),
                                                new DSMatch("d3","s4",110, 10));

      assertEquals(demandOrderMatches,generatedOutput.getDemandSupplyMatch()); 
    }

    


}
