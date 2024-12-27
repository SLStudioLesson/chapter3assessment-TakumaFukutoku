package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;
    
    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }
    
    @Override
    public ArrayList<Recipe> readData() throws IOException {

        ArrayList<Recipe> recipes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                String[] items = line.split(",");//一行ごとの要素を分解して格納
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for(int i = 1; i < items.length; i++) { //材料をループさせる
                    Ingredient ingredient = new Ingredient(items[i]);
                    ingredients.add(ingredient);
                }
                Recipe recipe = new Recipe(items[0], ingredients);
                recipes.add(recipe);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return recipes;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException {
    
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }

}
