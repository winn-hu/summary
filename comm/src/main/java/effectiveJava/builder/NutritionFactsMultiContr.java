package effectiveJava.builder;

public class NutritionFactsMultiContr {

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
