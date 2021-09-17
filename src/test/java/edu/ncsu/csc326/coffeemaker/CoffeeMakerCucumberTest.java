package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import static org.mockito.Mockito.*;

import io.cucumber.java.en.*;

public class CoffeeMakerCucumberTest {

    /**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	private RecipeBook recipeBook;
	private Inventory inventory;
	private CoffeeMaker mockCoffeeMaker;
	private Recipe[] recipeList;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;


    @Given("The coffee maker make the coffee and show the price.")
    public void the_coffee_maker_make_the_coffee_and_show_the_price() throws RecipeException {
        coffeeMaker = new CoffeeMaker();
		recipeBook = mock(RecipeBook.class);
		inventory = new Inventory();
		mockCoffeeMaker = new CoffeeMaker(recipeBook, inventory);
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");

        //Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("2");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");

        recipeList = new Recipe[] {recipe1, recipe2, recipe3, recipe4};

    }

    @When("The customer get recipe from coffee maker.")
    public void the_customer_get_recipe_from_coffee_maker() {
        when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
    }

    @Then("The customer order the recipe number {int} and pay {int} baht, then get the correct balance so equal {int}.")
    public void the_customer_recieve_the_ordered_coffee_and_show_the_balance(Integer recipe, Integer purchase, Integer balance) {
        assertEquals(balance.intValue(), mockCoffeeMaker.makeCoffee(recipe-1, purchase));
    }

}
