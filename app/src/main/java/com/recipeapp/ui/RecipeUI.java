package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {
        try {
            ArrayList<Recipe> recipes = dataHandler.readData(); //読み込み

            if (recipes.isEmpty()) {    //存在しない場合
                System.out.println("No recipes available.");
                return;

            } else {
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
            }

            for(Recipe recipe : recipes) {  //レシピデータの表示
                System.out.println("Recip Name: " + recipe.getName());
                System.out.print("Main Ingredients: ");
                for(Ingredient ingredient : recipe.getIngredients()) {
                    System.out.print((ingredient.getName()) + ",");
                }
                System.out.println();
                System.out.println("-----------------------------------");
            }

        } catch (IOException e) {
            
            System.out.println("Error reading file:" + e.getMessage());
        }
    }
}
