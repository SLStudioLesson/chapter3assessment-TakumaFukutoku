import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            //入力された値によっての条件分岐
            if (choice.equals("1")) {   //1の場合
                CSVDataHandler csvDataHandler = new CSVDataHandler();   //インスタンス生成
                RecipeUI recipeUI = new RecipeUI(csvDataHandler);
                recipeUI.displayMenu(); //displayMenuメソッドで表示
            
            } else if (choice.equals("2")) {    //2の場合
                JSONDataHandler jsonDataHandler = new JSONDataHandler();    //インスタンス生成
                RecipeUI recipeUI = new RecipeUI(jsonDataHandler);
                recipeUI.displayMenu(); //displayMenuメソッドで表示

            } else {    //それ以外の値
                CSVDataHandler csvDataHandler = new CSVDataHandler();   //インスタンス生成
                RecipeUI recipeUI = new RecipeUI(csvDataHandler);
                recipeUI.displayMenu(); //displayMenuメソッドで表示
            }
            

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}