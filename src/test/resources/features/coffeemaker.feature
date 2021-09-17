Feature: Make the coffee
    A customer purchase the coffee from coffee maker by paying the money. The coffeemaker bring the ingrediant from
inventory to make the coffee. Next, calculate the correct balance and show to the customer.

    Background: Customer purchase the coffee from coffee maker.
        Given The coffee maker make the coffee and show the price.
    
    Scenario: buy the coffee with exact amount of money.
        When The customer get recipe from coffee maker.
        Then The customer order the recipe number 1 and pay 50 baht, then get the correct balance so equal 0.

    Scenario: buy the coffee with exceed amount of money.
        When The customer get recipe from coffee maker.
        Then The customer order the recipe number 2 and pay 100 baht, then get the correct balance so equal 25.

    Scenario: buy the coffee with less amount of money.
        When The customer get recipe from coffee maker.
        Then The customer order the recipe number 3 and pay 50 baht, then get the correct balance so equal 50.