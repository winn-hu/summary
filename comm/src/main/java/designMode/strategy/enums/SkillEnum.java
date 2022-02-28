package designMode.strategy.enums;

public enum SkillEnum {

    /**
     * 使用lamda表达式实现Skill接口
     */
    FREE_SKROKE(() -> {
        System.out.println("I can free stroke.");
    }),
    BREAST_STROKE(() -> {
        System.out.println("I can breaststroke.");
    });

    private Skill skill;
    SkillEnum(Skill skill) {
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }
}
