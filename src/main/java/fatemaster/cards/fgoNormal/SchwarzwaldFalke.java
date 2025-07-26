package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import fatemaster.masterMod;
import fatemaster.powers.SchwarzwaldFalkePower;

public class SchwarzwaldFalke extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/SchwarzwaldFalke.png";
    public static final String ID = masterMod.makeId(SchwarzwaldFalke.class.getSimpleName());
    private static final int COST = 3;

    public SchwarzwaldFalke() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
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
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new OfferingEffect(), 0.1F));
        } else {
            this.addToBot(new VFXAction(new OfferingEffect(), 0.5F));
        }

        this.addToBot(new ApplyPowerAction(p, p, new SchwarzwaldFalkePower(p, this.magicNumber), this.magicNumber));
    }
}
