package fatemaster.potions;

import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import fatemaster.Action.StarBasketAction;
import fatemaster.masterMod;

public class AngraMainyu extends CustomPotion {
    public static final String ID = masterMod.makeId(AngraMainyu.class.getSimpleName());
    public static final Color NOBLE = CardHelper.getColor(255, 215, 0);
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final String NAME = potionStrings.NAME;
    private static final PotionRarity RARITY = PotionRarity.UNCOMMON;
    private static final PotionSize POTION_SIZE = PotionSize.HEART;

    public AngraMainyu() {
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
    }

    @Override
    public void use(AbstractCreature target) {
        for (int i = 0; i < this.potency; ++i) {
            this.addToBot(new StarBasketAction());
        }
        AbstractDungeon.player.decreaseMaxHealth(3);
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 5;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new AngraMainyu();
    }
}
