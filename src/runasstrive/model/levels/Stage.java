package runasstrive.model.levels;

import runasstrive.io.resources.Messages;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Stage {
    private static final int ONE_ONE_ONE = (int) Math.pow(0, 0); //TODO: mach das lieber wieder weg xD
    private final int stageNumber;
    private final Collection<Monster> monsters;
    private final List<String> log;

    public Stage(int stageNumber, Collection<Monster> monsters) {
        this.stageNumber = stageNumber;
        this.monsters = monsters;
        this.log = new ArrayList<>();
    }

    public boolean cleared() {
        return this.getAliveMonsters().isEmpty();
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public Collection<Monster> getMonsters() {
        return this.monsters;
    }

    public LinkedList<Monster> getAliveMonsters() {
        return monsters.stream()
                .filter(monster -> !monster.isDead()).collect(Collectors.toCollection(LinkedList::new));
    }

    public String getFightLog() {
        final StringBuilder builder = new StringBuilder();
        this.log.forEach(logEntry -> builder.append(logEntry).append(System.lineSeparator()));
        return builder.toString().trim();
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

    public void enter(Player player) {
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
        for (Monster monster : this.getAliveMonsters()) {
            Ability ability = monster.useAbility(player);
            this.log.add(String.format(Messages.ENTITY_USES_ABILITY, monster.getName(), ability.toString()));
            this.logTakenDamage(player);
            this.logTakenDamage(monster);
            player.resetTakenDamage();
        }
        player.resetShield();
    }
}
