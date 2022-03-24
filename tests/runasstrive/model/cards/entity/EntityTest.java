package runasstrive.model.cards.entity;

import mockingobjects.TestMonsterBuilder;
import mockingobjects.TestPlayerBuilder;
import org.junit.jupiter.api.Test;
import runasstrive.model.cards.entity.monster.Monster;
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
        Entity<CharacterType> player = new TestPlayerBuilder().createTestPlayer();
        player.shield(15, 42);
        assertEquals(15, player.physicalShield);
        assertEquals(42, player.magicShield);
    }

    @Test
    void reflect() {
        Entity<CharacterType> player = new TestPlayerBuilder().createTestPlayer();
        player.reflect(10, 10);
        assertEquals(10, player.reflectPhysicalDamage);
        assertEquals(10, player.reflectMagicDamage);
    }

    @Test
    void takeDamage() {
        Entity<CharacterType> player = new TestPlayerBuilder().setHp(10).createTestPlayer();
        player.physicalShield = 0;
        player.magicShield = 0;
        player.reflectMagicDamage = 0;
        player.reflectPhysicalDamage = 0;
        player.takeDamage(0, 4);
        assertEquals(6, player.hp);
        player.takeDamage(3, 0);
        assertEquals(3, player.hp);
        player.takeDamage(2, 1);
        assertEquals(0, player.hp);
        player.takeDamage(12, 100);
        assertEquals(-112, player.hp);
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