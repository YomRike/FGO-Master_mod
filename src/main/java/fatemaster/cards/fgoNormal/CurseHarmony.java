package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.CurseHarmonyPower;

public class CurseHarmony extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CurseHarmony.png";
    public static final String ID = masterMod.makeId(CurseHarmony.class.getSimpleName());
    private static final int COST = 1;

    public CurseHarmony() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new CurseHarmonyPower(p, this.magicNumber), this.magicNumber));
    }

}
