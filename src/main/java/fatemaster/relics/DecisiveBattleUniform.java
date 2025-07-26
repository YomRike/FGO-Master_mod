package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.cards.colorless.Uniform.Technique;
import fatemaster.cards.colorless.Uniform.Destruction;
import fatemaster.cards.colorless.Uniform.Preemption;
import fatemaster.cards.colorless.Uniform.SionSkill;
import fatemaster.masterMod;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.helpers.ImageMaster.loadImage;

public class DecisiveBattleUniform extends CustomRelic {
    public static final String ID = masterMod.makeId(DecisiveBattleUniform.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/DecisiveBattleUniform.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/DecisiveBattleUniform.png";
    private boolean ClickStart = false;
    private boolean Click = false;

    public DecisiveBattleUniform() {
        super(ID, loadImage(IMG), loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.FLAT);
        this.counter = 10;
    }

    @Override
    public void onTrigger() {
        this.description = this.DESCRIPTIONS[11];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[12] + (this.counter >= 5 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[13]));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[14] + (this.counter >= 6 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[15]));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[16] + (this.counter >= 7 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[17]));
        if (AbstractDungeon.actNum >= 2) {
            this.tips.add(new PowerTip(this.DESCRIPTIONS[18] + (this.counter >= 12 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[19]));
        }
        this.initializeTips();
    }

    @Override
    public void setCounter(int c) {
        this.counter = c;
        if (AbstractDungeon.player.hasRelic(SkullCandy.ID)) {
            this.description = this.DESCRIPTIONS[11];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[12] + (this.counter >= 5 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[13]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[14] + (this.counter >= 6 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[15]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[16] + (this.counter >= 7 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[17]));
            if (AbstractDungeon.actNum >= 2) {
                this.tips.add(new PowerTip(this.DESCRIPTIONS[18] + (this.counter >= 12 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[19]));
            }
            this.initializeTips();
        } else {
            this.description = this.DESCRIPTIONS[0];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[1] + (this.counter >= 5 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[2]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[3] + (this.counter >= 6 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[4]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[5] + (this.counter >= 7 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[6]));
            if (AbstractDungeon.actNum >= 2) {
                this.tips.add(new PowerTip(this.DESCRIPTIONS[7] + (this.counter >= 12 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[8]));
            }
            this.initializeTips();
        }
    }

    @Override
    public void onEquip() {
        if (AbstractDungeon.player.hasRelic(SkullCandy.ID)) {
            this.description = this.DESCRIPTIONS[11];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[12] + (this.counter >= 5 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[13]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[14] + (this.counter >= 6 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[15]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[16] + (this.counter >= 7 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[17]));
            if (AbstractDungeon.actNum >= 2) {
                this.tips.add(new PowerTip(this.DESCRIPTIONS[18] + (this.counter >= 12 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[19]));
            }
            this.initializeTips();
        } else {
            this.description = this.DESCRIPTIONS[0];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[1] + (this.counter >= 5 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[2]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[3] + (this.counter >= 6 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[4]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[5] + (this.counter >= 7 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[6]));
            if (AbstractDungeon.actNum >= 2) {
                this.tips.add(new PowerTip(this.DESCRIPTIONS[7] + (this.counter >= 12 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[8]));
            }
            this.initializeTips();
        }
    }

    @Override
    public void atTurnStart() {
        if (this.counter < 12) {
            this.flash();
            this.counter++;
        }
        if (AbstractDungeon.player.hasRelic(SkullCandy.ID)) {
            this.description = this.DESCRIPTIONS[11];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[12] + (this.counter >= 5 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[13]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[14] + (this.counter >= 6 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[15]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[16] + (this.counter >= 7 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[17]));
            if (AbstractDungeon.actNum >= 2) {
                this.tips.add(new PowerTip(this.DESCRIPTIONS[18] + (this.counter >= 12 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[19]));
            }
            this.initializeTips();
        } else {
            this.description = this.DESCRIPTIONS[0];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[1] + (this.counter >= 5 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[2]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[3] + (this.counter >= 6 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[4]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[5] + (this.counter >= 7 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[6]));
            if (AbstractDungeon.actNum >= 2) {
                this.tips.add(new PowerTip(this.DESCRIPTIONS[7] + (this.counter >= 12 ? this.DESCRIPTIONS[9] : this.DESCRIPTIONS[10]), this.DESCRIPTIONS[8]));
            }
            this.initializeTips();
        }
    }

    @Override
    public void atBattleStart() {
        if (this.counter >= 5) {
            //计数大于0时遗物闪烁。
            this.beginLongPulse();
        }
    }

    protected void onRightClick() {
        if (this.counter >= 5) {
            if (AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
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
                if (this.counter <= 5) {
                    this.stopPulse();
                }
            }
        }
    }

    public void update() {
        super.update();
        if (this.ClickStart && InputHelper.justReleasedClickRight) {
            if (this.hb.hovered) {
                this.Click = true;
            }

            this.ClickStart = false;
        }

        if (this.isObtained && this.hb != null && this.hb.hovered && InputHelper.justClickedRight) {
            this.ClickStart = true;
        }

        if (this.Click) {
            this.Click = false;
            onRightClick();
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
