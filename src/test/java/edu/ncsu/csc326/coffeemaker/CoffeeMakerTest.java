/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Sarah Heckman
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
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
		recipe2.setAmtChocolate("20");
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
	}


	/**
	 * Add the three valid recipe and still not throw in
	 * 		the RecipeException
	 * @throws RecipeException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddCorrectNumberOfRecipes() throws RecipeException {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
	}

	/**
	 * Add the four valid recipe ,then the program should throw in
	 * 		the RecipeException
	 * @throws RecipeException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test (expected = RecipeException.class)
	public void testAddWrongNumberOfRecipes() throws RecipeException {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.addRecipe(recipe4);
	}

	/**
	 * There is a valid recipe, then add the new unique recipe name
	 * We get the true when add the new recipe.
	 */
	@Test
	public void testAddTheUniqueNameOfRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertTrue(coffeeMaker.addRecipe(recipe2));
	}

	/**
	 * There is a valid recipe, then add the new Ununique recipe name
	 * We get the false when add the new recipe.
	 */
	@Test
	public void testAddTheNotUniqueNameOfRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertFalse(coffeeMaker.addRecipe(recipe1));
	}

	/**
	 * Two recipe is added into the RecipeBook.
	 * Delete the exist recipe.
	 * We get the deleted coffee name. 
	 */
	@Test
	public void testDeleteExistedRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertEquals("Coffee", coffeeMaker.deleteRecipe(0));
	}

	/**
	 * Two recipe is added into the RecipeBook.
	 * Delete the unexist recipe.
	 * We get null value. 
	 */
	@Test
	public void testDeleteNotExistedRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertEquals(null, coffeeMaker.deleteRecipe(2));
	}

	/** 
	 * Add a recipe into the book, then edit to the new valid recipe.
	 * The recipe name may not change.
	 * We get the new edited recipe.
	 */
	@Test
	public void testEditExistedRecipe() {
		coffeeMaker.addRecipe(recipe2);
		assertEquals("Mocha", coffeeMaker.editRecipe(0, recipe3));
		assertEquals("Latte", coffeeMaker.getRecipes()[0].getName());
	}

	/** 
	 * Add a recipe into the book, then edit to the new invalid recipe.
	 * The recipe name may not change.
	 * We get the same recipe.
	 */
	@Test
	public void testEditNotExistedRecipe() {
		coffeeMaker.addRecipe(recipe2);
		assertEquals(null, coffeeMaker.editRecipe(1, recipe3));
		assertEquals("Mocha", coffeeMaker.getRecipes()[0].getName());
	}

		/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}

	/**
	 * Add a recipe to the list and make that coffee
	 * check the remainder ingrediants in invetory
	 * We get all remainder ingrediants.
	 */
	@Test
	public void testCheckInventory() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.makeCoffee(0, 50);
		String expected_result = "Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n";
		assertEquals(expected_result, coffeeMaker.checkInventory());
	}

	/**
	 * Add a recipe to the book.
	 * Check the recipe is valid and have enough money.
	 * We get the correct change.
	 */
	@Test
	public void testPurchaseBeverageEnoughMoney() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals("Coffee", coffeeMaker.getRecipes()[0].getName());
		assertEquals(100, coffeeMaker.makeCoffee(0, 150));
	}

	/**
	 * Add a recipe to the book.
	 * Check the recipe is valid but do not have enough money.
	 * We get the correct change.
	 */
	@Test
	public void testPurchaseBeverageNotHaveBeverage() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals("Coffee", coffeeMaker.getRecipes()[0].getName());
		assertEquals(30, coffeeMaker.makeCoffee(0, 30));
	}

	/**
	 * Add a recipe to the book.
	 * Check the recipe is invalid and have enough money.
	 * We get the correct change.
	 */
	@Test
	public void testPurchaseBeverageNotEnoughMoney() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(null, coffeeMaker.editRecipe(1, recipe3));
		assertEquals(100, coffeeMaker.makeCoffee(1, 100));
	}

	/**
	 * Given a coffee maker with two valid recipe
	 * When we make coffee, selecting the invalid recipe index and 
	 * 		paying the valid money.
	 * Return the correct change back.
	 */
	@Test
	public void testMakeCoffeeOrderNotInRecipes() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertEquals(100, coffeeMaker.makeCoffee(2, 100));
	}
}
