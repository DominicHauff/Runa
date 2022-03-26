package runasstrive.builder;

import runasstrive.builder.supplier.DeckSupplier;
import runasstrive.builder.supplier.DieBagSupplier;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.dice.Die;
import runasstrive.model.levels.GameLevel;

import java.util.List;
import java.util.Stack;

public class RunaStriveBuilder {

    public static RunasStrive buildGame() {
        final Player player = PlayerBuilder.buildPlayer();
        final Stack<GameLevel> levels = LevelBuilder.buildLevels();
        final List<Ability> deck = DeckSupplier.getDeck();
        final Stack<Die> dieBag = DieBagSupplier.getDieBag();

        return new RunasStrive(dieBag, deck, player, levels);
    }

}
