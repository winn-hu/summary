package effectiveJava.builder;

public class NutritionFactsStreamTest {

    public static void main(String[] args) {
        NutritionFactsStream nutritionFactsStream = new NutritionFactsStream().setCalories(1).setFat(2);
        nutritionFactsStream.setServings(9);
        System.out.println(nutritionFactsStream);
    }
}