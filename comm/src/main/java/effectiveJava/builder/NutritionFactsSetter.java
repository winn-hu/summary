package effectiveJava.builder;
//Ӫ���ɷ�
public class NutritionFactsSetter {

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
