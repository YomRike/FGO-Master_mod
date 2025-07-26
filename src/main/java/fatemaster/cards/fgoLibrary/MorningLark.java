package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.MorningLarkPower;
import fatemaster.powers.StarGainPower;

public class MorningLark extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/MorningLark.png";
    public static final String ID = masterMod.makeId(MorningLark.class.getSimpleName());
    private static final int COST = 0;

    public MorningLark() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
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
        //this.addToBot(new FgoNpAction(this.magicNumber));
        this.addToBot(new GainEnergyAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new StarGainPower(p, 10), 10));
        this.addToBot(new ApplyPowerAction(p, p, new MorningLarkPower(p, 1), 1));
        //this.addToBot(new ApplyPowerAction(p, p, new DrawReductionPower(p, 1), 1));
    }
}
