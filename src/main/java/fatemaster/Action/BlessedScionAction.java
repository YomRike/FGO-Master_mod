package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.SupportCraft.SupportCraft;
import fatemaster.masterMod;

public class BlessedScionAction extends AbstractGameAction {
    public static final String ID = masterMod.makeId("BlessedScionAction");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = uiStrings.TEXT;
    private final AbstractPlayer p;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    private final boolean upgraded;

    public BlessedScionAction(AbstractCreature target, AbstractCreature source, int amount, boolean upgraded) {
        this.setValues(target, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
        this.p = (AbstractPlayer)target;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (this.duration == DURATION) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() == 1) {
                if (this.p.hand.getBottomCard().hasTag(CardTagsEnum.Noble_Phantasm)) {
                    this.addToTop(new MakeTempCardInDrawPileAction(new SupportCraft(), this.amount, false, true));
                } else {
                    this.addToTop(new MakeTempCardInDrawPileAction(this.p.hand.getBottomCard(), this.amount, false, true));
                }
                if (this.upgraded && this.p.hand.getBottomCard().cost > 0 && this.p.hand.getBottomCard().costForTurn > 0) {
                    this.p.hand.getBottomCard().freeToPlayOnce = true;
                }

                //this.addToTop(new ApplyPowerAction(this.p, this.p, new BlessedScionPower(this.p, this.amount, this.p.hand.getBottomCard())));
                this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
                this.isDone = true;
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard tmpCard = AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard();
                if (tmpCard.hasTag(CardTagsEnum.Noble_Phantasm)) {
                    this.addToTop(new MakeTempCardInDrawPileAction(new SupportCraft(), this.amount, false, true));
                } else {
                    this.addToTop(new MakeTempCardInDrawPileAction(tmpCard, this.amount, false, true));
                }
                if (this.upgraded && this.p.hand.getBottomCard().cost > 0 && this.p.hand.getBottomCard().costForTurn > 0) {
                    tmpCard.freeToPlayOnce = true;
                }
                //this.addToTop(new ApplyPowerAction(this.p, this.p, new BlessedScionPower(this.p, this.amount, tmpCard)));
                AbstractDungeon.player.hand.addToHand(tmpCard);
                this.p.hand.moveToExhaustPile(tmpCard);
                AbstractDungeon.handCardSelectScreen.selectedCards.clear();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }
}
