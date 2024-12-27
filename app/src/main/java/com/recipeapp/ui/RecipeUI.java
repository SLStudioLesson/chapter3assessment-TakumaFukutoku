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
                        addNewRecipe();
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
                ArrayList<Ingredient> ingredients = recipe.getIngredients();    //材料のリスト

                for(int i = 0; i < ingredients.size();i++) {    //for文のでループ
                    System.out.print(ingredients.get(i).getName());
                    if (i < ingredients.size() -1) {
                        System.out.print(",");
                    }
                }
                System.out.println();
                System.out.println("-----------------------------------");
            }

        } catch (IOException e) {
            
            System.out.println("Error reading file:" + e.getMessage());
        }
    }

    private void addNewRecipe() {
        try {
            System.out.println("Adding a new recipe.");     //レシピ名入力
            System.out.print("Enter recipe name: ");

            String recipeName = reader.readLine();

            ArrayList<Ingredient> ingredients = new ArrayList<>();  //材料を格納するリスト
            String ingredientName;

            System.out.println("Enter ingredients (type 'done' when finished):");

            while (true) {
                System.out.print("Ingredient: ");
                ingredientName = reader.readLine();

                if (ingredientName.equals("done")) {    //doneでループ終了
                    break;
                } else {
                ingredients.add(new Ingredient(ingredientName));    //材料をリストに追加
                }
            }
            Recipe newRecipe = new Recipe(recipeName, ingredients);     //インスタンス生成
            dataHandler.writeData(newRecipe);
            System.out.println("Recipe added successfully.");

        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}
