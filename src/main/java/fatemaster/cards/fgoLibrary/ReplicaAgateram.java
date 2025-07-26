package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class ReplicaAgateram extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/ReplicaAgateram.png";
    public static final String ID = masterMod.makeId(ReplicaAgateram.class.getSimpleName());
    private static final int COST = 0;

    public ReplicaAgateram() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 6;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(4);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        for (AbstractPower power : p.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                this.addToBot(new ReducePowerAction(p, p, power, 1));
                this.addToBot(new GainBlockAction(p, p, this.block));
            }
        }
    }
}
