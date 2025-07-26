package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.SealNPPower;

import static com.megacrit.cardcrawl.core.Settings.language;

public class GraceUnexpectedBirth extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/GraceUnexpectedBirth.png";
    public static final String ID = masterMod.makeId(GraceUnexpectedBirth.class.getSimpleName());
    private static final int COST = 1;

    public GraceUnexpectedBirth() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 10;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
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
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new SealNPPower(p, 1), 1));
        //this.addToBot(new ApplyPowerAction(p, p, new DrawReductionPower(p, 1), 1));
    }
}
