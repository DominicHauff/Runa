package runasstrive.model.levels;

import runasstrive.io.resources.Messages;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Stage {
    private final int stageNumber;
    private final Collection<Monster> monsters;
    private final List<String> log;


    public Stage(int stageNumber, Collection<Monster> monsters) {
        this.stageNumber = stageNumber;
        this.monsters = monsters;
        this.log = new ArrayList<>();
    }

    public boolean cleared() {
        return this.monsters.isEmpty();
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public Collection<Monster> getMonsters() {
        return this.monsters;
    }

    public Collection<Monster> getAliveMonsters() {
        return monsters.stream()
                .filter(monster -> !monster.isDead())
                .collect(Collectors.toList());
    }

    public String getFightLog() {
        final StringBuilder builder = new StringBuilder();
        this.log.forEach(logEntry -> builder.append(logEntry).append(System.lineSeparator()));
        return builder.toString().trim();
    }

    private void logTakenDamage(Entity<?> entity) {
        if (entity.isDead()) {
            this.log.add(String.format(Messages.ENTITY_DIES, entity.getName()));
        } else {
            if (entity.getTakenMagicDamage() > 0) {
                this.log.add(String.format(Messages.TAKE_MAGIC_DAMAGE,
                        entity.getName(), entity.getTakenPhysicalDamage()));
            }
            if (entity.getTakenPhysicalDamage() > 0) {
                this.log.add(String.format(Messages.TAKE_PHYSICAL_DAMAGE,
                        entity.getName(), entity.getTakenPhysicalDamage()));
            }
        }
    }

    public void enter(Player player) {
        this.log.clear();
        if (player.getCardToPlay().targetRequired()) {
            //TODO: fix null for .getTarget()
            player.useAbility(player.getTarget());
            this.logTakenDamage(player.getTarget());
            this.logTakenDamage(player);

        } else {
            player.useAbility();
        }
        for (Monster monster : this.getAliveMonsters()) {
            Ability ability = monster.useAbility(player);
            this.log.add(String.format(Messages.ENTITY_USES_ABILITY, monster.getName(), ability.toString()));
            this.logTakenDamage(player);
            this.logTakenDamage(monster);
        }
    }
}
