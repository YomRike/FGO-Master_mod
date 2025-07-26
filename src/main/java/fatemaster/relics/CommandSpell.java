package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.cards.colorless.CommandSpellGuts;
import fatemaster.cards.colorless.ReleaseNoblePhantasm;
import fatemaster.cards.colorless.RepairSpiritOrigin;
import fatemaster.masterMod;

import java.util.ArrayList;

public class CommandSpell extends CustomRelic {
    public static final String ID = masterMod.makeId(CommandSpell.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/CommandSpell_01.png";
    private static final String IMG1 = "fgoMasterResources/images/relics_Master/CommandSpell_02.png";
    private static final String IMG2 = "fgoMasterResources/images/relics_Master/CommandSpell_03.png";
    private static final String IMG3 = "fgoMasterResources/images/relics_Master/CommandSpell.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/CommandSpell.png";
    private boolean ClickStart = false;
    private boolean Click = false;

    public CommandSpell() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.FLAT);
        this.counter = 3;
    }

    @Override
    public void setCounter(int c) {
        this.counter = c;
        this.description = this.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[1], this.DESCRIPTIONS[2]));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[3], this.DESCRIPTIONS[4]));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[5], this.DESCRIPTIONS[6]));
        this.initializeTips();
    }

    @Override
    public void onEquip() {
        this.description = this.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[1], this.DESCRIPTIONS[2]));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[3], this.DESCRIPTIONS[4]));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[5], this.DESCRIPTIONS[6]));
        this.initializeTips();
    }

    protected void onRightClick() {
        if (this.counter > 0 && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            stanceChoices.add(new RepairSpiritOrigin());
            stanceChoices.add(new ReleaseNoblePhantasm());

            this.addToBot(new ChooseOneAction(stanceChoices));
            --this.counter;
            if (this.counter == 2) {
                this.setTextureOutline(ImageMaster.loadImage(IMG1), ImageMaster.loadImage(IMG_OTL));
            } else if (this.counter == 1) {
                this.setTextureOutline(ImageMaster.loadImage(IMG2), ImageMaster.loadImage(IMG_OTL));
            } else if (this.counter <= 0) {
                this.setTextureOutline(ImageMaster.loadImage(IMG3), ImageMaster.loadImage(IMG_OTL));
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

    @Override
    public void onTrigger() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new CommandSpellGuts());

        this.addToBot(new ChooseOneAction(stanceChoices));
        this.setCounter(0);
    }
}
