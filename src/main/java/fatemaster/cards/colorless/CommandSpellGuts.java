package fatemaster.cards.colorless;

import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class CommandSpellGuts extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CommandSpellGuts.png";
    public static final String ID = masterMod.makeId(CommandSpellGuts.class.getSimpleName());
    private static final int COST = -2;

    public CommandSpellGuts() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new HealAction(p, p, p.maxHealth));
        this.addToBot(new FgoNpAction(300));
    }
}
