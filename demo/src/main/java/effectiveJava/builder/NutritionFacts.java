package effectiveJava.builder;

public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int soodium;
    private final int carbohydrate;

    public NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.soodium = builder.soodium;
        this.carbohydrate = builder.carbohydrate;
    }

    @Override
    public String toString() {
        return "NutritionFacts{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", soodium=" + soodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }

    public static class Builder {
        //Required parameters
        private final int servingSize;
        private final int servings;

        //Optional parameters
        private int calories;
        private int fat;
        private int soodium;
        private int carbohydrate;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val){
            calories = val;
            return this;
        }

        public Builder fat(int val){
            fat = val;
            return this;
        }

        public Builder soodium(int val){
            soodium = val;
            return this;
        }

        public Builder carbohydrate(int val){
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build(){
            return new NutritionFacts(this);
        }

    }

    public static void main(String[] args) {
        NutritionFacts facts = new NutritionFacts.Builder(1, 2).calories(3).fat(4).soodium(5).carbohydrate(6).build();
        System.out.println(facts);
    }
}
