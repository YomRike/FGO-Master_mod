package fatemaster.event;

import basemod.abstracts.events.PhasedEvent;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import fatemaster.masterMod;
import fatemaster.relics.BB;

public class DevilSlot extends PhasedEvent {
    public static final String ID = masterMod.makeId("DevilSlot");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;
    private final int damage;

    public DevilSlot() {
        super(ID, title, "fgoMasterResources/images/event_Master/DevilSlot.png");

        if (AbstractDungeon.ascensionLevel >= 15) {
            this.damage = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.12F);
        } else {
            this.damage = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.08F);
        }

        this.body = DESCRIPTIONS[0];
        this.imageEventText.setDialogOption(OPTIONS[0], new BB());
        this.imageEventText.setDialogOption(OPTIONS[1] + this.damage + OPTIONS[3]);
    }

    @Override
    public void update() {
        super.update();
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.effectList.add(new PurgeCardEffect(c));
            AbstractDungeon.player.masterDeck.removeCard(c);
            AbstractDungeon.gridSelectScreen.selectedCards.remove(c);
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                if (buttonPressed == 0) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    if (AbstractDungeon.player.hasRelic(BB.ID)) {
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new Circlet());
                    } else {
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new BB());
                    }
                    this.imageEventText.clearRemainingOptions();
                } else {
                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, this.damage));
                    if (!CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).isEmpty()) {
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[2], false);
                    }
                    this.imageEventText.clearRemainingOptions();
                }

                this.screenNum = 1;
                break;
            case 1:
                this.openMap();
        }
    }
}
