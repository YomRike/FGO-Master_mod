package fatemaster.event;

import basemod.abstracts.events.PhasedEvent;
import fatemaster.cards.colorless.CrystallizationofWinter;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fatemaster.masterMod;

public class WinterEvent extends PhasedEvent {
    public static final String ID = masterMod.makeId("WinterEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;

    public WinterEvent() {
        super(ID, title, "fgoMasterResources/images/event_Master/WinterEvent.png");
        this.body = DESCRIPTIONS[0];
        this.imageEventText.setDialogOption(OPTIONS[0], new CrystallizationofWinter());
        this.imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                if (buttonPressed == 0) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new CrystallizationofWinter(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    this.screenNum = 1;
                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    this.imageEventText.clearRemainingOptions();
                    return;
                }
                this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                this.screenNum = 2;
                this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                this.imageEventText.clearRemainingOptions();
                return;
            case 1:
            default:
                this.openMap();
        }

    }
}