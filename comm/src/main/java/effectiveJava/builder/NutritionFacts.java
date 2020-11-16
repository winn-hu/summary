package effectiveJava.builder;
//Ӫ���ɷ�
public class NutritionFacts {

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

    /**
     * NutritionFacts�ǲ��ɱ�ģ��������ṩ������
     * @param builder
     */
    private NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }


    public static class Builder {
        private int servingSize;
        private int servings;
        private int calories;
        private int fat;
        private int sodium;
        private int carbohydrate;

        //�����ֶ�
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        //��ѡ�ֶ�
        public Builder calories(int val){
            this.calories = val;
            return this;
        }

        public Builder fat(int val){
            this.fat = val;
            return this;
        }

        public Builder sodium(int val){
            this.sodium = val;
            return this;
        }

        public Builder carbohydrate(int val){
            this.carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public static void main(String[] args) {
        NutritionFacts facts = new NutritionFacts.Builder(1, 2)
                                                .calories(3)
                                                .fat(4)
                                                .build();
    }
}
