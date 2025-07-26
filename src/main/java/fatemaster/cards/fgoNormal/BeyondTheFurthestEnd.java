package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.BeyondTheFurthestEndPower;

import static com.megacrit.cardcrawl.core.Settings.language;

public class BeyondTheFurthestEnd extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/BeyondTheFurthestEnd.png";
    public static final String ID = masterMod.makeId(BeyondTheFurthestEnd.class.getSimpleName());
    private static final int COST = 1;

    public BeyondTheFurthestEnd() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public float getTitleFontSize() {
        if (language != Settings.GameLanguage.ZHS) {
            return 16.0F;
        }

        return -1.0F;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new BeyondTheFurthestEndPower(p, this.magicNumber), this.magicNumber));
    }
}
