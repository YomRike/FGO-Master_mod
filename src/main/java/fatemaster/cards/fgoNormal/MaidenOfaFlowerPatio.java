package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;

public class MaidenOfaFlowerPatio extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/MaidenOfaFlowerPatio.png";
    public static final String ID = masterMod.makeId(MaidenOfaFlowerPatio.class.getSimpleName());
    private static final int COST = 1;

    public MaidenOfaFlowerPatio() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 7;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
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
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new CursePower(p, this.magicNumber), this.magicNumber));
    }
}
