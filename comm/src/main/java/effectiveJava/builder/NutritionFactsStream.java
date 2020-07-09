package effectiveJava.builder;

public class NutritionFactsStream {

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

    public NutritionFactsStream() {
    }

    public int getServingSize() {
        return servingSize;
    }

    public NutritionFactsStream setServingSize(int servingSize) {
        this.servingSize = servingSize;
        return this;
    }

    public int getServings() {
        return servings;
    }

    public NutritionFactsStream setServings(int servings) {
        this.servings = servings;
        return this;
    }

    public int getCalories() {
        return calories;
    }

    public NutritionFactsStream setCalories(int calories) {
        this.calories = calories;
        return this;
    }

    public int getFat() {
        return fat;
    }

    public NutritionFactsStream setFat(int fat) {
        this.fat = fat;
        return this;
    }

    public int getSodium() {
        return sodium;
    }

    public NutritionFactsStream setSodium(int sodium) {
        this.sodium = sodium;
        return this;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public NutritionFactsStream setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
        return this;
    }

    @Override
    public String toString() {
        return "NutritionFactsStream{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }
}
