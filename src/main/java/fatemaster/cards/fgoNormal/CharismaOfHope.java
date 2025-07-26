package fatemaster.cards.fgoNormal;

import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class CharismaOfHope extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CharismaOfHope.png";
    public static final String ID = masterMod.makeId(CharismaOfHope.class.getSimpleName());
    private static final int COST = 1;

    public CharismaOfHope() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseBlock = 7;
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
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
        this.addToBot(new FgoNpAction(this.magicNumber));

        /*if (AbstractDungeon.actNum == 2) {
            this.addToBot(new FgoNpAction(25, true));
            this.addToBot(new DrawCardAction(p, 1));
        } else if (AbstractDungeon.actNum > 2) {
            this.addToBot(new FgoNpAction(30, true));
            this.addToBot(new DrawCardAction(p, 2));
        } else {
            this.addToBot(new FgoNpAction(20, true));
        }*/
    }

    /*@Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.actNum == 2) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        } else if (AbstractDungeon.actNum > 2) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
        }

        this.initializeDescription();
    }*/

    /*public void renderCardTip(SpriteBatch sb) {
        super.renderCardTip(sb);

        if (this.isLocked || (AbstractDungeon.player != null && (AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode))) {
            return;
        }

        if (AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (AbstractDungeon.player.hoveredCard == this) {
                FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, "+" + this.costForTurn * 3 + "% 宝具值", this.hb.cX, this.current_y,  Color.WHITE.cpy());
            }
        }
    }*/
}
