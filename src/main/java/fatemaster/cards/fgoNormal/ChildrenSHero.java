package fatemaster.cards.fgoNormal;

import fatemaster.cards.SupportCraft.SupportCraft;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.Enum.CardTagsEnum;

public class ChildrenSHero extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/ChildrenSHero.png";
    public static final String ID = masterMod.makeId(ChildrenSHero.class.getSimpleName());
    private static final int COST = 1;

    public ChildrenSHero() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
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
        AbstractCard theSize = AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 2);
        if (!theSize.hasTag(CardTagsEnum.Noble_Phantasm)) {
            this.addToBot(new MakeTempCardInHandAction(theSize, this.magicNumber));
        } else {
            this.addToBot(new MakeTempCardInHandAction(new SupportCraft(), this.magicNumber));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            if (AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
                canUse = false;
                this.cantUseMessage = (getCardStrings(ID)).EXTENDED_DESCRIPTION[0];
            }
        }

        return canUse;
    }
}
