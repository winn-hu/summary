package effectiveJava.builder;

public class NutritionFactsTest {
    public static void main(String[] args) {
        NutritionFacts facts = new NutritionFacts.Builder(1, 2)
                .calories(3)
                .fat(4)
                .build();
        System.out.println(facts);

    }
}