package effectiveJava.builder;

public class NutritionFactsStream {

    //ʳ��ߴ�
    private int servingSize;
    //ʳ������
    private int servings;
    //��������·�
    private int calories;
    //֬������
    private int fat;
    //ʳ���Σ��ƣ�����
    private int sodium;
    //���ຬ��
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
