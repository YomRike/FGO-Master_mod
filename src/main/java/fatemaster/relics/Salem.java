package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fatemaster.masterMod;
import fatemaster.Enum.CardTagsEnum;

public class Salem extends CustomRelic {
    public static final String ID = masterMod.makeId(Salem.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/Salem.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/Salem.png";

    public Salem() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1];
    }

    @Override
    public void setCounter(int c) {
        this.counter = c;
        if (this.counter == 0) {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
        } else {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3] + this.counter + this.DESCRIPTIONS[4];
        }

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void onMasterDeckChange() {
        this.counter = 0;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.hasTag(CardTagsEnum.Foreigner)) {
                ++this.counter;
            }
        }

        if (this.counter == 0) {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
        } else {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3] + this.counter + this.DESCRIPTIONS[4];
        }

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void onEquip() {
        this.counter = 0;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.hasTag(CardTagsEnum.Foreigner)) {
                ++this.counter;
            }
        }

        if (this.counter == 0) {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
        } else {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3] + this.counter + this.DESCRIPTIONS[4];
        }

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void atTurnStart() {
        if (this.counter > 0) {
            this.flash();
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, this.counter), this.counter));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }
}
