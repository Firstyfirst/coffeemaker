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

import static org.mockito.Mockito.*;
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
	private RecipeBook recipeBook;
	private Inventory inventory;
	private CoffeeMaker mockCoffeeMaker;
	private Recipe[] recipeList;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	private Recipe recipe6;
	private Recipe recipe7;
	private Recipe recipe8;

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

		//Set up for r5
		recipe5 = new Recipe();
		recipe5.setName("Pure Chocolate");
		recipe5.setAmtChocolate("50");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("0");
		recipe5.setAmtSugar("0");
		recipe5.setPrice("1000");
		
		//Set up for r6
		recipe6 = new Recipe();
		recipe6.setName("Pure Coffee");
		recipe6.setAmtChocolate("0");
		recipe6.setAmtCoffee("50");
		recipe6.setAmtMilk("0");
		recipe6.setAmtSugar("0");
		recipe6.setPrice("1000");
		
		//Set up for r7
		recipe7 = new Recipe();
		recipe7.setName("Pure Milk");
		recipe7.setAmtChocolate("0");
		recipe7.setAmtCoffee("0");
		recipe7.setAmtMilk("50");
		recipe7.setAmtSugar("0");
		recipe7.setPrice("1000");
		
		//Set up for r8
		recipe8 = new Recipe();
		recipe8.setName("Pure Sugar");
		recipe8.setAmtChocolate("0");
		recipe8.setAmtCoffee("0");
		recipe8.setAmtMilk("0");
		recipe8.setAmtSugar("50");
		recipe8.setPrice("1000");

		recipeList = new Recipe[] {recipe1, recipe2, recipe3, recipe4, recipe5, recipe6, recipe7, recipe8};
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
		when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
		assertEquals("Coffee", mockCoffeeMaker.deleteRecipe(0));
	}

	/**
	 * Two recipe is added into the RecipeBook.
	 * Delete the unexist recipe.
	 * We get null value. 
	 */
	@Test
	public void testDeleteNotExistedRecipe() {
		when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
		assertEquals(null, mockCoffeeMaker.deleteRecipe(20));
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
		when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
		mockCoffeeMaker.makeCoffee(0, 50);
		String expected_result = "Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n";
		assertEquals(expected_result, mockCoffeeMaker.checkInventory());
	}

	/**
	 * Add a recipe to the book.
	 * Check the recipe is valid and have enough money.
	 * We get the correct change.
	 */
	@Test
	public void testPurchaseBeverageEnoughMoney() {
		when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
		assertEquals(100, mockCoffeeMaker.makeCoffee(0, 150));
	}

	/**
	 * Add a recipe to the book.
	 * Check the recipe is valid but do not have enough money.
	 * We get the correct change.
	 */
	@Test
	public void testPurchaseBeverageNotHaveBeverage() {
		when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
		assertEquals(30, mockCoffeeMaker.makeCoffee(0, 30));
	}

	/**
	 * Add a recipe to the book.
	 * Check the recipe is invalid and have enough money.
	 * We get the correct change.
	 */
	@Test
	public void testPurchaseBeverageNotEnoughMoney() {
		when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
		assertEquals(null, mockCoffeeMaker.editRecipe(1, recipe3));
		assertEquals(100, mockCoffeeMaker.makeCoffee(1, 100));
	}

	/**
	 * Given a coffee maker with two valid recipe
	 * When we make coffee, selecting the invalid recipe index and 
	 * 		paying the valid money.
	 * Return the correct change back.
	 */
	@Test
	public void testMakeCoffeeOrderNotInRecipes() {
		when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
		assertEquals(100, mockCoffeeMaker.makeCoffee(5, 100));
	}

	/**
	 * There is a coffee recipe, then buy coffee until 
	 * 		the ingrediant out of stock
	 * Return the correct change back.
	 */
	@Test
	public void testMakeCoffeeNotEnoughIngrediant() {
		when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
		// the first three order still have enough ingrediant
		assertEquals(0, mockCoffeeMaker.makeCoffee(3, 65));
		assertEquals(0, mockCoffeeMaker.makeCoffee(3, 65));
		assertEquals(0, mockCoffeeMaker.makeCoffee(3, 65));
		// the fourth do not have enough ingrediant
		assertEquals(65, mockCoffeeMaker.makeCoffee(3, 65));
	}

	/**
	 * Add one recipe which exceed more than 
	 * 		amount of chocolate in inventory
	 * when customer buy the coffee, then
	 * return the correct change. 
	 */
	@Test
	public void testInventoryNotEnoughChocolate() {
		coffeeMaker.addRecipe(recipe5);
		assertEquals(1000, coffeeMaker.makeCoffee(0, 1000));
	}

	/**
	 * Add one recipe which exceed more than 
	 * 		amount of coffee in inventory
	 * when customer buy the coffee, then
	 * return the correct change. 
	 */
	@Test
	public void testInventoryNotEnoughCoffee() {
		coffeeMaker.addRecipe(recipe6);
		assertEquals(1000, coffeeMaker.makeCoffee(0, 1000));
	}

	/**
	 * Add one recipe which exceed more than 
	 * 		amount of milk in inventory
	 * when customer buy the coffee, then
	 * return the correct change. 
	 */
	@Test
	public void testInventoryNotEnoughMilk() {
		coffeeMaker.addRecipe(recipe7);
		assertEquals(1000, coffeeMaker.makeCoffee(0, 1000));
	}

	/**
	 * Add one recipe which exceed more than 
	 * 		amount of sugar in inventory
	 * when customer buy the coffee, then
	 * return the correct change. 
	 */
	@Test
	public void testInventoryNotEnoughSugar() {
		coffeeMaker.addRecipe(recipe8);
		assertEquals(1000, coffeeMaker.makeCoffee(0, 1000));
	}

	/**
	 * When add the chocolate to inventory which is not integer.
	 * Throw the inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 * 
	 */
	@Test(expected = InventoryException.class)
	public void testAddChocolateNotInteger() throws InventoryException {
		coffeeMaker.addInventory("0", "0", "0", "abcd");
	}

	/**
	 * When add the chocolate to inventory which is not positive integer.
	 * Throw the inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 * 
	 */
	@Test(expected = InventoryException.class)
	public void testAddChocolateNotPositiveInteger() throws InventoryException {
		coffeeMaker.addInventory("0", "0", "0", "-15");
	}

	/**
	 * When add the coffee to inventory which is not integer.
	 * Throw the inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 * 
	 */
	@Test(expected = InventoryException.class)
	public void testAddCoffeeNotInteger() throws InventoryException {
		coffeeMaker.addInventory("abcd", "0", "0", "0");
	}

	/**
	 * When add the coffee to inventory which is not positive integer.
	 * Throw the inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 * 
	 */
	@Test(expected = InventoryException.class)
	public void testAddCoffeeNotPositiveInteger() throws InventoryException {
		coffeeMaker.addInventory("-15", "0", "0", "0");
	}

	/**
	 * When add the milk to inventory which is not integer.
	 * Throw the inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 * 
	 */
	@Test(expected = InventoryException.class)
	public void testAddMilkNotInteger() throws InventoryException {
		coffeeMaker.addInventory("0", "abcd", "0", "0");
	}

	/**
	 * When add the milk to inventory which is not positive integer.
	 * Throw the inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 * 
	 */
	@Test(expected = InventoryException.class)
	public void testAddMilkNotPositiveInteger() throws InventoryException {
		coffeeMaker.addInventory("0", "-15", "0", "0");
	}

	/**
	 * When add the sugar to inventory which is not integer.
	 * Throw the inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 * 
	 */
	@Test(expected = InventoryException.class)
	public void testAddSugarNotInteger() throws InventoryException {
		coffeeMaker.addInventory("0", "0", "abcd", "0");
	}


	/**
	 * When add the sugar to inventory which is not positive integer.
	 * Throw the inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 * 
	 */
	@Test(expected = InventoryException.class)
	public void testAddSugarNotPositiveInteger() throws InventoryException {
		coffeeMaker.addInventory("0", "0", "-15", "0");
	}

	/**
	 * When add the sugar to inventory which is positive integer.
	 * Should not throwed in the exception.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 * 
	 */
	@Test(expected = InventoryException.class)
	public void testAddSugarPositiveInteger() throws InventoryException {
		coffeeMaker.addInventory("0", "0", "15", "0");
	}
}
