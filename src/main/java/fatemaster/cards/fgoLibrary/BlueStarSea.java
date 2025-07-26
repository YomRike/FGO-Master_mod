package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class BlueStarSea extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/BlueStarSea.png";
    public static final String ID = masterMod.makeId(BlueStarSea.class.getSimpleName());
    private static final int COST = 1;

    public BlueStarSea() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(4);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new HealAction(p, p, this.magicNumber));
        this.addToBot(new DrawCardAction(p, 1));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, this.magicNumber), this.magicNumber));
    }
}
