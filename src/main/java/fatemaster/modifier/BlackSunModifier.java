package fatemaster.modifier;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fatemaster.masterMod;
import fatemaster.util.TextureLoader;

import java.util.Collections;
import java.util.List;

public class BlackSunModifier extends AbstractCardModifier {
    private static final Texture tex = TextureLoader.getTexture("fgoMasterResources/images/powers_Master/BlackSunPower84.png");
    public static String MODIFIER_ID = masterMod.makeId("BlackSunModifier");
    public static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(MODIFIER_ID);
    public int value;

    public BlackSunModifier(int valueIn) {
        this.value = valueIn;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + uiStrings.TEXT[0] + this.value + uiStrings.TEXT[1];
    }

    @Override
    public String identifier(AbstractCard card) {
        return MODIFIER_ID;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(tex).text(String.valueOf(this.value)).render(card);
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        TooltipInfo ti = new TooltipInfo(GameDictionary.VIGOR.NAMES[0], GameDictionary.keywords.get(GameDictionary.VIGOR.NAMES[0]));
        return Collections.singletonList(ti);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, this.value), this.value));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new BlackSunModifier(this.value);
    }
}
