package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
            String line;
            while ((line = reader.readLine())!= null) { //一行ごとに読み込み
                String[] items = line.split(",");//一行ごとの要素を分解して格納
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for(int i = 1; i < items.length; i++) { //材料をループさせる
                    Ingredient ingredient = new Ingredient(items[i]);
                    ingredients.add(ingredient);
                }
                Recipe recipe = new Recipe(items[0], ingredients);  //レシピ名を最初の項目に
                recipes.add(recipe);    //レシピをリストに追加
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return recipes;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = recipe.getName(); //レシピ名の書きこみ

            ArrayList<Ingredient> ingredients = recipe.getIngredients();//材料をカンマ区切りで追加
            for(int i = 0; i < ingredients.size(); i++) {
                line = line +  ", " + ingredients.get(i).getName();    //一行にくっつける
            }

            writer.write(line); //書き込み
            writer.newLine();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }

}
