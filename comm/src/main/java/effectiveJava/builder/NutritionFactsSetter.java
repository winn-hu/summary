package effectiveJava.builder;
//营养成分
public class NutritionFactsSetter {

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

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFactsSetter nutritionFacts = new NutritionFactsSetter();
        nutritionFacts.setCalories(1);
        nutritionFacts.setCarbohydrate(2);
    }
}
