package fatemaster.event;

import basemod.abstracts.events.PhasedEvent;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fatemaster.Enum.AbstractCardEnum;
import fatemaster.masterMod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRng;

public class TestTheWatersAct3 extends PhasedEvent {
    public static final String ID = masterMod.makeId("TestTheWatersAct3");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;
    private int maxHPAmt;

    public TestTheWatersAct3() {
        super(ID, title, "fgoMasterResources/images/event_Master/TestTheWatersAct3.png");

        if (AbstractDungeon.ascensionLevel >= 15) {
            this.maxHPAmt = 6;
        } else {
            this.maxHPAmt = 4;
        }

        this.body = DESCRIPTIONS[0];
        int heal = AbstractDungeon.player.maxHealth;
        if (heal >= this.maxHPAmt) {
            this.imageEventText.setDialogOption(OPTIONS[0] + this.maxHPAmt + OPTIONS[3]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[4] + this.maxHPAmt + OPTIONS[5], true);
        }
        this.imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
            AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
            disCard.current_x = -1000.0F * Settings.xScale;
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(disCard, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            AbstractDungeon.cardRewardScreen.discoveryCard = null;
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                if (buttonPressed == 0) {
                    this.getRandomMemory();
                    this.reward();
                    AbstractDungeon.player.decreaseMaxHealth(this.maxHPAmt);

                    this.maxHPAmt += 6;
                    int heal = AbstractDungeon.player.maxHealth;
                    if (heal >= this.maxHPAmt) {
                        this.imageEventText.updateDialogOption(0, OPTIONS[1] + this.maxHPAmt + OPTIONS[3]);
                    } else {
                        this.imageEventText.updateDialogOption(0, OPTIONS[4] + this.maxHPAmt + OPTIONS[5], true);
                    }

                    this.screenNum = 0;
                    //this.imageEventText.clearRemainingOptions();
                } else {
                    this.openMap();
                }
                break;
            case 1:
                this.openMap();
        }
    }

    private void getRandomMemory() {
        ArrayList<String> memories = new ArrayList<>();
        memories.add(DESCRIPTIONS[1]);
        memories.add(DESCRIPTIONS[2]);
        memories.add(DESCRIPTIONS[3]);
        memories.add(DESCRIPTIONS[4]);
        Collections.shuffle(memories, new Random(AbstractDungeon.miscRng.randomLong()));
        this.imageEventText.updateBodyText(memories.get(0));
    }

    private void reward() {
        AbstractDungeon.getCurrRoom().rewards.clear();
        AbstractDungeon.cardRewardScreen.customCombatOpen(this.generateCardChoices(), CardRewardScreen.TEXT[1], true);

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        this.screenNum = 0;
    }


    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> drp = new ArrayList<>();

        while (drp.size() != 3) {
            boolean dupe = false;
            ArrayList<String> tmp = new ArrayList<>();
            for (Map.Entry<String, AbstractCard> stringAbstractCardEntry : CardLibrary.cards.entrySet()) {
                if (stringAbstractCardEntry.getValue().color == AbstractCardEnum.Other_COLOR) {
                    tmp.add(stringAbstractCardEntry.getKey());
                }
            }

            AbstractCard cStudy = CardLibrary.cards.get(tmp.get(cardRng.random(0, tmp.size() - 1))).makeCopy();

            for (AbstractCard c : drp) {
                if (c.cardID.equals(cStudy.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                drp.add(cStudy.makeCopy());
            }
        }

        return drp;
    }
}
