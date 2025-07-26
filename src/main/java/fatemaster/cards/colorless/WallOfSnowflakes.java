package fatemaster.cards.colorless;

import fatemaster.Enum.AbstractCardEnum;
import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.WallOfSnowflakesPower;

public class WallOfSnowflakes extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/WallOfSnowflakes.png";
    public static final String ID = masterMod.makeId(WallOfSnowflakes.class.getSimpleName());
    private static final int COST = 1;

    public WallOfSnowflakes() {
        super(ID, IMG_PATH, COST, CardType.SKILL, AbstractCardEnum.Other_COLOR, CardRarity.BASIC, CardTarget.SELF);
        this.baseBlock = 6;
        this.baseMagicNumber = 20;
        this.magicNumber = this.baseMagicNumber;
        /*this.exhaust = true;
        this.selfRetain = true;*/
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2);
            this.upgradeMagicNumber(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new WallOfSnowflakesPower(p, this.magicNumber), this.magicNumber));
    }
}
