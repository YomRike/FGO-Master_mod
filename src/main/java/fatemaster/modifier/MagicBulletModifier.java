package fatemaster.modifier;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import fatemaster.masterMod;
import fatemaster.util.TextureLoader;

import java.util.Collections;
import java.util.List;

public class MagicBulletModifier extends AbstractCardModifier {
    private static final Texture tex = TextureLoader.getTexture("fgoMasterResources/images/powers_Master/MagicBulletPower84.png");
    public static String MODIFIER_ID = masterMod.makeId("MagicBulletModifier");
    public static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(MODIFIER_ID);
    public int value;

    public MagicBulletModifier(int valueIn) {
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
        TooltipInfo ti = new TooltipInfo(
                TipHelper.capitalize(BaseMod.getKeywordTitle("fgomaster:magic_bullets")),
                BaseMod.getKeywordDescription("fgomaster:magic_bullets"));
        return Collections.singletonList(ti);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        //CardCrawlGame.sound.playV(SpireAnniversary5Mod.DETONATOR_KEY, 1.0F);
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(value * 4, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                //AbstractCard c = card.makeStatEquivalentCopy();
                CardModifierManager.removeModifiersById(card, MODIFIER_ID, true);

                this.isDone = true;
            }
        });
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MagicBulletModifier(this.value);
    }
}