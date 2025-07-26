package fatemaster.potions;

import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import fatemaster.cards.colorless.potionCards.Kaleidoscope;
import fatemaster.cards.colorless.potionCards.TheBlackGrail;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import fatemaster.masterMod;

import java.util.ArrayList;

public class ExtremelySpicyMapoTofu extends CustomPotion {
    public static final String ID = masterMod.makeId(ExtremelySpicyMapoTofu.class.getSimpleName());
    public static final Color NOBLE = CardHelper.getColor(255, 215, 0);
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final String NAME = potionStrings.NAME;
    private static final PotionRarity RARITY = PotionRarity.RARE;
    private static final PotionSize POTION_SIZE = PotionSize.BOTTLE;

    public ExtremelySpicyMapoTofu() {
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
                TipHelper.capitalize(BaseMod.getKeywordTitle("fgomaster:np")),
                BaseMod.getKeywordDescription("fgomaster:np")));
        this.tips.add(new PowerTip(
                TipHelper.capitalize(BaseMod.getKeywordTitle("fgomaster:np_damage")),
                BaseMod.getKeywordDescription("fgomaster:np_damage")));
    }

    @Override
    public void use(AbstractCreature target) {
        InputHelper.moveCursorToNeutralPosition();
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new Kaleidoscope());
        stanceChoices.add(new TheBlackGrail());
        this.addToBot(new ChooseOneAction(stanceChoices));
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 0;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new ExtremelySpicyMapoTofu();
    }
}
