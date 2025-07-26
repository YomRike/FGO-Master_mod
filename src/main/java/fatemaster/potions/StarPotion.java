package fatemaster.potions;

import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import fatemaster.masterMod;
import fatemaster.powers.StarGainPower;

public class StarPotion extends CustomPotion {
    public static final String ID = masterMod.makeId(StarPotion.class.getSimpleName());
    public static final Color NOBLE = CardHelper.getColor(255, 215, 0);
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final String NAME = potionStrings.NAME;
    private static final PotionRarity RARITY = PotionRarity.COMMON;
    private static final PotionSize POTION_SIZE = PotionSize.MOON;

    public StarPotion() {
        super(NAME, ID, RARITY, POTION_SIZE, null);
        this.labOutlineColor = NOBLE;
        this.isThrown = false;
    }

    @Override
    public void initializeData() {
        this.potency = getPotency();
        this.description = potionStrings.DESCRIPTIONS[0];

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(
                TipHelper.capitalize(BaseMod.getKeywordTitle("fgomaster:star")),
                BaseMod.getKeywordDescription("fgomaster:star")));
    }

    @Override
    public void use(AbstractCreature target) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StarGainPower(AbstractDungeon.player, this.potency), this.potency));
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 10;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new StarPotion();
    }
}
