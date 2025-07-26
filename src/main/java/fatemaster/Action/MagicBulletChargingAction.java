package fatemaster.Action;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import fatemaster.modifier.MagicBulletModifier;

public class MagicBulletChargingAction extends AbstractGameAction {
    private final boolean freeToPlayOnce;
    private final AbstractPlayer p;
    private final int energyOnUse;
    private final boolean upgraded;

    public MagicBulletChargingAction(AbstractPlayer p, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.upgraded = upgraded;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (this.upgraded) {
            ++effect;
        }

        if (effect > 0) {
            int finalEffect = effect;
            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    for (AbstractCard c : AbstractDungeon.player.hand.group) {
                        if (CardModifierManager.hasModifier(c, MagicBulletModifier.MODIFIER_ID)) {
                            ((MagicBulletModifier) CardModifierManager.getModifiers(c, MagicBulletModifier.MODIFIER_ID).get(0)).value += finalEffect;
                            c.initializeDescription();
                        } else {
                            CardModifierManager.addModifier(c, new MagicBulletModifier(finalEffect));
                        }
                        c.superFlash(Color.GOLD.cpy());
                    }

                    this.isDone = true;
                }
            });

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
