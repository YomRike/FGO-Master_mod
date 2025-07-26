package fatemaster.cards.colorless.potionCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoMasterBase;
import fatemaster.masterMod;
import fatemaster.powers.NPDamagePower;

public class TheBlackGrail extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TheBlackGrail.png";
    public static final String ID = masterMod.makeId(TheBlackGrail.class.getSimpleName());
    private static final int COST = -2;

    public TheBlackGrail() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 60;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(20);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void onChoseThisOption() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NPDamagePower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }
}
