package fatemaster.cards.colorless.Uniform;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import fatemaster.Action.TheOneWhoSawItAllAction;
import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.relics.DecisiveBattleUniform;

public class SionSkill extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/SionSkill.png";
    public static final String ID = masterMod.makeId(SionSkill.class.getSimpleName());
    private static final int COST = -2;

    public SionSkill() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new TheOneWhoSawItAllAction(this.magicNumber));

        if (p.hasRelic(DecisiveBattleUniform.ID)) {
            AbstractRelic uniform = p.getRelic(DecisiveBattleUniform.ID);
            uniform.counter -= 12;
            uniform.flash();
        }
    }
}
