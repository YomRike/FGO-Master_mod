package fatemaster.potions;

import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import fatemaster.masterMod;

public class ElixirofRejuvenation extends CustomPotion {
    public static final String ID = masterMod.makeId(ElixirofRejuvenation.class.getSimpleName());
    public static final Color NOBLE = CardHelper.getColor(255, 215, 0);
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final String NAME = potionStrings.NAME;
    private static final PotionRarity RARITY = PotionRarity.RARE;
    private static final PotionSize POTION_SIZE = PotionSize.SPHERE;

    public ElixirofRejuvenation() {
        super(NAME, ID, RARITY, POTION_SIZE, null);
        this.labOutlineColor = NOBLE;
        this.isThrown = false;
    }

    @Override
    public void initializeData() {
        this.potency = getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.potency));
        this.addToBot(new RemoveAllPowersAction(AbstractDungeon.player, true));
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 10;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new ElixirofRejuvenation();
    }
}