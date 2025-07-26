package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class MerlinMagic extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/MerlinMagic.png";
    public static final String ID = masterMod.makeId(MerlinMagic.class.getSimpleName());
    private static final int COST = 0;

    public MerlinMagic() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 6;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void triggerWhenCopied() {
        this.addToBot(new GainEnergyAction(this.magicNumber));
    }
}
