package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.OneTimeOneMeetingPreparedPower;

public class OneTimeOneMeeting extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/OneTimeOneMeeting.png";
    public static final String ID = masterMod.makeId(OneTimeOneMeeting.class.getSimpleName());
    private static final int COST = 2;

    public OneTimeOneMeeting() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new OneTimeOneMeetingPreparedPower(p, 1), 1));
    }
}
