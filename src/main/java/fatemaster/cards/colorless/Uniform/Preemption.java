package fatemaster.cards.colorless.Uniform;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.relics.DecisiveBattleUniform;

public class Preemption extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Preemption.png";
    public static final String ID = masterMod.makeId(Preemption.class.getSimpleName());
    private static final int COST = -2;

    public Preemption() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 30;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new FgoNpAction(this.magicNumber));

        if (p.hasRelic(DecisiveBattleUniform.ID)) {
            AbstractRelic uniform = p.getRelic(DecisiveBattleUniform.ID);
            uniform.counter -= 6;
            uniform.flash();
        }
    }
}
