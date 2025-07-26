package fatemaster.cards.fgoNormal;

import fatemaster.Action.MagicBulletChargingAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class MagicBulletCharging extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/MagicBulletCharging.png";
    public static final String ID = masterMod.makeId(MagicBulletCharging.class.getSimpleName());
    private static final int COST = -1;

    public MagicBulletCharging() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MagicBulletChargingAction(p, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
    }
}
