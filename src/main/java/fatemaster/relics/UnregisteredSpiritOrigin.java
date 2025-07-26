package fatemaster.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import fatemaster.Action.StarBasketAction;
import fatemaster.masterMod;

public class UnregisteredSpiritOrigin extends CustomRelic {
    public static final String ID = masterMod.makeId(UnregisteredSpiritOrigin.class.getSimpleName());
    private static final String IMG = "fgoMasterResources/images/relics_Master/UnregisteredSpiritOrigin.png";
    private static final String IMG_OTL = "fgoMasterResources/images/relics_Master/outline/UnregisteredSpiritOrigin.png";

    public UnregisteredSpiritOrigin() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, LandingSound.CLINK);
        //this.counter = 0;
    }

    /*@Override
    public void onPlayerEndTurn() {
        if (this.counter < 9) {
            this.counter += 3;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
            this.addToBot(new SkipEnemiesTurnAction());
        } else {
            this.counter = 0;
        }
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        ++this.counter;
        if (this.counter == 12) {
            this.counter = 0;
            this.flash();
            this.pulse = false;
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!mo.isDeadOrEscaped()) {
                    this.addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            mo.takeTurn();
                            this.isDone = true;
                        }
                    });
                }
            }
        } else if (this.counter == 11) {
            this.beginPulse();
            this.pulse = true;
        }
    }*/

    @Override
    public void atBattleStart() {
        //this.addToBot(new StarBasketAction());
        int aliveMonsters = (int) AbstractDungeon.getCurrRoom().monsters.monsters.stream()
                .filter(mo -> !mo.isDeadOrEscaped())
                .count();

        if (aliveMonsters >= 2) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 12));
        }

        this.grayscale = true;

        /*if (this.counter == 11) {
            this.beginPulse();
            this.pulse = true;
        }*/
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
