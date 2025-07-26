package fatemaster.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fatemaster.masterMod;
import fatemaster.patches.MainMenuUIFgoPatch;
import fatemaster.patches.skin.SkinPreviewCard;

public class ChooseSkinAction extends AbstractGameAction {
    public static final String ID = masterMod.makeId("ChooseSkinAction");
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public ChooseSkinAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            CardGroup deep = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER0));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER1));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER2));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER3));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER4));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER5));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER6));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER7));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER8));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER9));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER10));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER11));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER12));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER13));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER14));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER15));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER16));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER17));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.MASTER18));
            deep.addToTop(new SkinPreviewCard(MainMenuUIFgoPatch.SkinType.CAT_ARCUEID));

            AbstractDungeon.gridSelectScreen.open(deep, 1, TEXT[0], false, false, true, false);
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                if (selectedCard instanceof SkinPreviewCard) {
                    SkinPreviewCard skinCard = (SkinPreviewCard) selectedCard;
                    skinCard.use(AbstractDungeon.player, null);
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }
        }
        this.tickDuration();
    }
}
