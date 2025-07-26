package fatemaster.event;

import basemod.abstracts.events.PhasedEvent;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import fatemaster.cards.fgoLibrary.*;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fatemaster.cards.fgoNormal.LevelSlash;
import fatemaster.cards.fgoLibrary.OneTimeOneMeeting;
import fatemaster.masterMod;

public class FGOLibrary extends PhasedEvent {
    public static final String ID = masterMod.makeId("FGOLibrary");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;
    private boolean pickCard = false;
    private final int healAmt;
    private final int maxHPAmt;
    public FGOLibrary() {
        super(ID, title, "fgoMasterResources/images/event_Master/FGOLibrary.png");
        this.body = DESCRIPTIONS[0];
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.healAmt = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.2F);
            this.maxHPAmt = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.15F);
        } else {
            this.healAmt = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.33F);
            this.maxHPAmt = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.1F);
        }

        this.imageEventText.setDialogOption(OPTIONS[0] + this.maxHPAmt + OPTIONS[5]);
        this.imageEventText.setDialogOption(OPTIONS[1] + this.healAmt + OPTIONS[2]);
    }

    @Override
    public void update() {
        super.update();
        if (this.pickCard && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeCopy();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        if (this.screenNum == 0) {
            if (buttonPressed == 0) {
                this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                this.screenNum = 1;
                this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                this.imageEventText.clearRemainingOptions();
                this.pickCard = true;
                CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                for(int i = 0; i < 20; ++i) {
                    AbstractCard card = AbstractDungeon.getCard(AbstractDungeon.rollRarity()).makeCopy();
                    boolean containsDupe = true;

                    while(containsDupe) {
                        containsDupe = false;

                        for(AbstractCard c : group.group) {
                            if (c.cardID.equals(card.cardID)) {
                                containsDupe = true;
                                card = AbstractDungeon.getCard(AbstractDungeon.rollRarity()).makeCopy();
                                break;
                            }
                        }
                    }

                    if (group.contains(card)) {
                        --i;
                    } else {
                        for(AbstractRelic r : AbstractDungeon.player.relics) {
                            r.onPreviewObtainCard(card);
                        }

                        group.addToBottom(card);
                    }
                }

                for(AbstractCard c : group.group) {
                    UnlockTracker.markCardAsSeen(c.cardID);
                }

                AbstractDungeon.gridSelectScreen.open(group, 1, OPTIONS[4], false);
                AbstractDungeon.player.decreaseMaxHealth(this.maxHPAmt);
                return;
            }
            this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
            AbstractDungeon.player.heal(this.healAmt, true);
            this.screenNum = 1;
            this.imageEventText.updateDialogOption(0, OPTIONS[3]);
            this.imageEventText.clearRemainingOptions();
        } else {
            this.openMap();
        }
    }
}
