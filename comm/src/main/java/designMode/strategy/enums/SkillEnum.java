package designMode.strategy.enums;

public enum SkillEnum {

    /**
     * ʹ��lamda���ʽʵ��Skill�ӿ�
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
