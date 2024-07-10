# Demand-Supply Matching Program

This program implements a demand-supply matching system for an online market maker. The goal is to match supply orders from farmers/traders with demand orders from customers/traders based on specific rules to maximize profit for the market maker.

## Execution
./gradlew build

gradle clean

gradle jar

cd to the jar folder in build and execute either of the following code

java -jar app.jar \DeepRooted\app\src\main\resources\sampleInput1.txt
java -jar app.jar \DeepRooted\app\src\main\resources\sampleInput2.txt

## Functionality

1. **Supply Orders**: Farmers/Traders publish the availability of produce with details such as quantity and price.
2. **Demand Orders**: Customers/Traders publish their requirements for produce, including the quantity and the best price they can offer.

Both types of orders are stored in a demand-supply ledger. The program matches demand with supply whenever a new order is published.

## Matching Rules

1. **Price Priority**: Priority is given to "lower supply price - higher demand price" matching to maximize profit.
2. **Supplier Price Guarantee**: The supplier is always given the price they have asked for, regardless of the price offered on the demand side.
3. **FIFO**: Within the same price range, first-in-first-out (FIFO) rule is applied. The first supply must be matched with the first demand.
4. **Trade Generation**: A trade is generated when the buy price is greater than or equal to the sell price. The trade is recorded at the price of the supply regardless of the demand price.

## Input and Output

The program accepts orders from standard input and writes trades to standard output.

### Input Format

- `<order-id> <time> <produce> <price/kg> <quantity in kg>`

  Order IDs starting with `s` are supply orders, and those starting with `d` are demand orders.

### Output Format

- `<demand-order-id> <supply-order-id> <price/kg> <quantity in kg>`

## Example 1

### Input

```
s1 09:45 tomato 24/kg 100kg
s2 09:46 tomato 20/kg 90kg 
d1 09:47 tomato 22/kg 110kg 
d2 09:48 tomato 21/kg 10kg
d3 09:49 tomato 21/kg 40kg
s3 09:50 tomato 19/kg 50kg
```

### Output

```
d1 s2 20/kg 90kg 
d1 s3 19/kg 20kg
d2 s3 19/kg 10kg
d3 s3 19/kg 20kg 
```

## Example 2

### Input

```
d1 09:47 tomato 110/kg 1kg
d2 09:45 potato 110/kg 10kg
d3 09:48 tomato 110/kg 10kg
s1 09:45 potato 110/kg 1kg
s2 09:45 potato 110/kg 7kg
s3 09:45 potato 110/kg 2kg
s4 09:45 tomato 110/kg 11kg
```

### Output

```
d2 s1 110/kg 1kg 
d2 s2 110/kg 7kg 
d2 s3 110/kg 2kg 
d1 s4 110/kg 1kg 
d3 s4 110/kg 10kg 
```

## Implementation

The program should handle the following:
1. Reading input orders.
2. Storing orders in the demand-supply ledger.
3. Matching orders based on the specified rules.
4. Outputting the trades.

### Steps to Implement

1. **Data Structures**: Use queues for supply and demand orders to ensure FIFO order within the same price.
2. **Order Processing**: When a new order is added, attempt to match it with existing orders.
3. **Matching Logic**: Implement the logic to prioritize lower supply prices and higher demand prices, ensuring the supplier's price is guaranteed.
4. **Trade Output**: Generate and output trades as matches are found.


