package effectiveJava.builder;

public class NutritionFactsMultiContr {

    //食物尺寸
    private int servingSize;
    //食物数量
    private int servings;
    //热量（卡路里）
    private int calories;
    //脂肪含量
    private int fat;
    //食用盐（钠）含量
    private int sodium;
    //糖类含量
    private int carbohydrate;

    public NutritionFactsMultiContr(int servingSize, int servings) {
        this(servingSize,servings,0);
    }

    public NutritionFactsMultiContr(int servingSize, int servings, int calories) {
        this(servingSize,servings,calories,0);
    }

    public NutritionFactsMultiContr(int servingSize, int servings, int calories, int fat) {
        this(servingSize,servings,calories,fat,0);
    }

    public NutritionFactsMultiContr(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize,servings,calories,fat,sodium,0);
    }

    public NutritionFactsMultiContr(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFactsMultiContr nutritionFacts = new NutritionFactsMultiContr(1,2,3,4,5,6);
    }
}
