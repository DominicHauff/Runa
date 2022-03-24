package runasstrive.model.cards.entity;

import mockingobjects.TestMonsterBuilder;
import mockingobjects.TestPlayerBuilder;
import org.junit.jupiter.api.Test;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.cards.entity.type.CharacterType;
import runasstrive.model.cards.entity.type.MonsterType;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @Test
    void getType() {
        Monster monster = new TestMonsterBuilder().setType(MonsterType.FIRE).createTestMonster();
        assertEquals(MonsterType.FIRE, monster.getType());
    }

    @Test
    void setType() {
        Monster monster = new TestMonsterBuilder().createTestMonster();
        monster.setType(MonsterType.LIGHTNING);
        assertEquals(MonsterType.LIGHTNING, monster.getType());
    }

    @Test
    void getHp() {
        Monster monster = new TestMonsterBuilder().setHp(100).createTestMonster();
        assertEquals(100, monster.getHp());
    }

    @Test
    void getFp() {
        Monster monster = new TestMonsterBuilder().createTestMonster();
        assertEquals(0, monster.getFp());
        Entity<CharacterType> player = new TestPlayerBuilder().createTestPlayer();
        assertEquals(1, player.getFp());
    }

    @Test
    void shield() {

    }

    @Test
    void reflect() {
    }

    @Test
    void takeDamage() {
    }

    @Test
    void getReflectedPhysicalDamage() {
    }

    @Test
    void getReflectedMagicalDamage() {
    }

    @Test
    void focus() {
    }

    @Test
    void breakFocus() {
    }

    @Test
    void getAbilities() {
    }

    @Test
    void nextAbility() {
    }

    @Test
    void isDead() {
    }

    @Test
    void useAbility() {
    }

    @Test
    void testUseAbility() {
    }

    @Test
    void getTakenMagicDamage() {
    }

    @Test
    void getTakenPhysicalDamage() {
    }
}