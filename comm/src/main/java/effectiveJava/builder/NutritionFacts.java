package effectiveJava.builder;
//营养成分
public class NutritionFacts {

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

    /**
     * NutritionFacts是不可变的，不对外提供构造器
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

        //必填字段
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        //可选字段
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
