package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import fatemaster.cards.colorless.Uniform.Technique;
import fatemaster.cards.colorless.Uniform.Destruction;
import fatemaster.cards.colorless.Uniform.Preemption;
import fatemaster.cards.colorless.Uniform.SionSkill;
import fatemaster.masterMod;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.helpers.ImageMaster.loadImage;

public class HalloweenRoyalty extends CustomRelic {
    public static final String ID = masterMod.makeId(HalloweenRoyalty.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/HalloweenRoyalty.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/HalloweenRoyalty.png";

    public HalloweenRoyalty() {
        super(ID, loadImage(IMG), loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public void onTrigger() {
        this.description = this.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof RestRoom && this.counter < 4) {
            this.flash();
            this.counter = 4;
        }
    }

    @Override
    public void onEquip() {
        this.counter = 4;
    }

    @Override
    public void atTurnStartPostDraw() {
        if (this.counter == -1) {
            this.counter += 2;
        } else {
            ++this.counter;
        }

        if (this.counter == 5) {
            this.counter = 0;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            stanceChoices.add(new Destruction());
            stanceChoices.add(new Preemption());
            stanceChoices.add(new Technique());
            if (AbstractDungeon.actNum >= 2) {
                stanceChoices.add(new SionSkill());
            }

            if (AbstractDungeon.player.hasRelic(SkullCandy.ID)) {
                for (AbstractCard c : stanceChoices) {
                    c.upgrade();
                }
            }

            this.addToBot(new ChooseOneAction(stanceChoices));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
