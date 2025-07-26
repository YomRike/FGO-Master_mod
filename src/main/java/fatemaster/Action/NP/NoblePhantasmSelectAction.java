package fatemaster.Action.NP;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.noblecards.*;
import fatemaster.powers.NoblePhantasmCardPower;

public class NoblePhantasmSelectAction extends AbstractGameAction {
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("fatemaster:NPText").TEXT;
    private final boolean upgraded;

    public NoblePhantasmSelectAction(boolean upgraded, int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.upgraded = upgraded;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            if (AbstractDungeon.player.hasPower(NoblePhantasmCardPower.POWER_ID)) {
                //宝具牌被固定。
                group.addToTop(new HollowHeartAlbion());
            } else {
                //至少有这三张。
                group.addToTop(new EternalMemories());
                group.addToTop(new BeautifulJourney());
                group.addToTop(new Failnaught());
                for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
                    if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
                        if (card.upgraded) {
                            card.upgrade();
                            group.addToTop(card);
                        } else {
                            group.addToTop(card);
                        }
                    }
                }

                if (group.isEmpty()) {
                    this.isDone = true;
                    return;
                }
            }

            for (AbstractCard c : group.group) {
                UnlockTracker.markCardAsSeen(c.cardID);
            }

            if (this.upgraded) {
                for (AbstractCard c : group.group) {
                    c.upgrade();
                }
            }

            AbstractDungeon.gridSelectScreen.open(group, 1, NPTEXT[2], false, false, true, false);
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractCard cStudy = AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeCopy();
                if (AbstractDungeon.gridSelectScreen.selectedCards.get(0).upgraded || this.upgraded) {
                    cStudy.upgrade();
                }
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cStudy, this.amount));
                if (AbstractDungeon.player.hasPower(NoblePhantasmCardPower.POWER_ID)) {
                    this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, NoblePhantasmCardPower.POWER_ID));
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }
        }
        this.tickDuration();
    }
}
