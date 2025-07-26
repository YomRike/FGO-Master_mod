package fatemaster.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import fatemaster.characters.master;
import fatemaster.powers.NPRatePower;
import fatemaster.powers.SealNPPower;

public class PatchCostAmtKey {
    private static final String[] NPTEXT = CardCrawlGame.languagePack.getUIString("fatemaster:NPText").TEXT;
    @SpirePatch(clz = AbstractCard.class, method = "renderCardTip")
    public static class PatchSkeletonKeyCanUpgrade {
        @SpirePrefixPatch
        public static void Prefix(AbstractCard __instance, SpriteBatch sb) {
            if (!(AbstractDungeon.player instanceof master) || __instance.isLocked || AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode) {
                return;
            }

            if (isInCombat()) {
                if (AbstractDungeon.player.hoveredCard == __instance && __instance.costForTurn > -2 && __instance.costForTurn != 0 && !AbstractDungeon.player.hasPower(SealNPPower.POWER_ID)) {
                    int costModifier = getCostModifier();
                    int costAmt = calculateCostAmount(__instance.costForTurn, costModifier);

                    FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "+" + costAmt + "% " + NPTEXT[0], __instance.hb.cX, __instance.hb.height + 24.0F * Settings.scale, Color.WHITE.cpy());
                }
            }
        }

        private static boolean isInCombat() {
            return AbstractDungeon.getCurrMapNode() != null &&
                    AbstractDungeon.getCurrRoom() != null &&
                    AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT;
        }

        private static int getCostModifier() {
            boolean hasGoldLaw = AbstractDungeon.player.hasPower(NPRatePower.POWER_ID);

            if (master.fgoNp >= 200) {
                return hasGoldLaw ? 20 : 10;
            } else if (master.fgoNp >= 100) {
                return hasGoldLaw ? 14 : 7;
            } else {
                return hasGoldLaw ? 10 : 5;
            }
        }

        private static int calculateCostAmount(int costForTurn, int costModifier) {
            return costForTurn == -1 ? EnergyPanel.totalCount * costModifier : costForTurn * costModifier;
        }
    }
}
