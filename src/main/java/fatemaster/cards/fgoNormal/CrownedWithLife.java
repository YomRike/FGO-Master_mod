package fatemaster.cards.fgoNormal;

import fatemaster.cards.colorless.SoulOfWaterChannels;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class CrownedWithLife extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CrownedWithLife.png";
    public static final String ID = masterMod.makeId(CrownedWithLife.class.getSimpleName());
    private static final int COST = -2;

    public CrownedWithLife() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new SoulOfWaterChannels();
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
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void triggerWhenDrawn() {
        this.addToTop(new MakeTempCardInHandAction(this.cardsToPreview, this.magicNumber));
        this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }
}
