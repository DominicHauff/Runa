package runasstrive.model.levels;

import runasstrive.view.resources.Messages;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a stage containing a
 * fixed amount of monsters Runa has to defeat in order
 * to clear the stage.
 *
 * @author ugget
 * @version 1.0
 */
public class Stage {
    private static final int ONE_ONE_ONE = (int) Math.pow(0, 0); //TODO: mach das lieber wieder weg xD
    private final int stageNumber;
    private final Collection<Monster> monsters;
    private final List<String> log;

    /**
     * This method constructs a new Stage object.
     *
     * @param stageNumber the number of the stage
     * @param monsters the monsters Runa has to defeat
     */
    public Stage(int stageNumber, Collection<Monster> monsters) {
        this.stageNumber = stageNumber;
        this.monsters = monsters;
        this.log = new ArrayList<>();
    }

    private void logTakenDamage(Entity<?> entity) {
        //TODO: magic numbers
        if (entity.getTakenMagicDamage() > 0) {
            this.log.add(String.format(Messages.TAKE_MAGIC_DAMAGE,
                    entity.getName(), entity.getTakenMagicDamage()));
        }
        if (entity.getTakenPhysicalDamage() > 0) {
            this.log.add(String.format(Messages.TAKE_PHYSICAL_DAMAGE,
                    entity.getName(), entity.getTakenPhysicalDamage()));
        }
        if (entity.isDead()) {
            this.log.add(String.format(Messages.ENTITY_DIES, entity.getName()));
        }
    }

    /**
     * This method creates a new fight log and
     * logs all relevant information during the fight.
     *
     * @param player the player
     * @return the fight log
     */
    public FightLog enter(Player player) {
        this.log.clear();
        if (player.getCardToPlay().targetRequired()) {
            final Monster target = this.getAliveMonsters().size() == ONE_ONE_ONE
                    ? this.getAliveMonsters().getFirst() : player.getTarget();
            player.useAbility(target);
            this.logTakenDamage(target);
            this.logTakenDamage(player);
            target.resetShield();
            target.resetTakenDamage();
            player.resetTakenDamage();
        } else {
            player.useAbility();
        }
        this.getAliveMonsters().forEach(monster -> {
            monster.focus(monster.getFocusLevel());
            if (monster.hasGainedFp()) {
                this.log.add(String.format(Messages.GAIN_FOCUS_POINTS, monster.getName(), monster.getGainedFp()));
            }
        });
        for (Monster monster : this.getAliveMonsters()) {
            Ability ability = monster.useAbility(player);
            this.log.add(String.format(Messages.ENTITY_USES_ABILITY, monster.getName(), ability.toString()));
            this.logTakenDamage(player);
            if (player.isDead()) break;
            this.logTakenDamage(monster);
            player.resetTakenDamage();
            monster.resetTakenDamage();
        }
        player.focus(player.getFocusLevel());
        player.resetShield();
        if (player.hasGainedFp() && !player.isDead()) {
            this.log.add(String.format(Messages.GAIN_FOCUS_POINTS, player.getName(), player.getGainedFp()));
        }
        return new FightLog(this, this.log);
    }

    /**
     * @return whether the stage is cleared
     */
    public boolean cleared() {
        return this.getAliveMonsters().isEmpty();
    }

    /**
     * @return the stage number
     */
    public int getStageNumber() {
        return stageNumber;
    }

    /**
     * @return the stages monsters Runa has to defeat
     */
    public Collection<Monster> getMonsters() {
        return this.monsters;
    }

    /**
     * @return a list of all monsters that are still alive
     */
    public LinkedList<Monster> getAliveMonsters() {
        return monsters.stream()
                .filter(monster -> !monster.isDead()).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public String toString() {
        StringBuilder monsterStringBuilder = new StringBuilder();
        this.getAliveMonsters().forEach(monster ->
                monsterStringBuilder.append(monster.toString()).append(System.lineSeparator()));
        return monsterStringBuilder.toString().trim();
    }
}
