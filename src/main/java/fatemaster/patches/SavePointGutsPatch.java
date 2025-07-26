package fatemaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import fatemaster.powers.ComeOnPower;
import fatemaster.powers.DeathOfDeathPower;
import fatemaster.powers.GutsPower;
import fatemaster.powers.TheaterPower;
import fatemaster.relics.CommandSpell;
import javassist.CtBehavior;

public class SavePointGutsPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "damage")
    public static class hasEnoughEnergyPatcher {
        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn<Void> Insert(AbstractPlayer p) {
            if (p.hasPower(GutsPower.POWER_ID)) {
                p.currentHealth = 0;
                p.getPower(GutsPower.POWER_ID).onSpecificTrigger();

                //如果你有毅力：治愈。
                if (p.hasPower(ComeOnPower.POWER_ID)) {
                    p.getPower(ComeOnPower.POWER_ID).onSpecificTrigger();
                }

                return SpireReturn.Return(null);
            }

            if (p.hasPower(TheaterPower.POWER_ID)) {
                p.currentHealth = 0;
                p.getPower(TheaterPower.POWER_ID).onSpecificTrigger();
                return SpireReturn.Return(null);
            }

            if (p.hasPower(DeathOfDeathPower.POWER_ID)) {
                p.currentHealth = 0;
                p.getPower(DeathOfDeathPower.POWER_ID).onSpecificTrigger();
                return SpireReturn.Return(null);
            }

            if (p.hasRelic(CommandSpell.ID) && p.getRelic(CommandSpell.ID).counter == 3) {
                p.currentHealth = 0;
                p.getRelic(CommandSpell.ID).onTrigger();

                return SpireReturn.Return(null);
            }

            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "isDead");
                return LineFinder.findInOrder(ctBehavior, fieldAccessMatcher);
            }
        }
    }
}
