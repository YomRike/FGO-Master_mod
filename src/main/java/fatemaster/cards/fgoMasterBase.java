package fatemaster.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import fatemaster.cards.fgoNormal.*;
import fatemaster.characters.master_male;
import fatemaster.masterMod;

import java.util.ArrayList;
import java.util.List;

public abstract class fgoMasterBase extends CustomCard {
    public static String ID = masterMod.makeId(fgoMasterBase.class.getSimpleName());
    public static CardStrings TIPS = CardCrawlGame.languagePack.getCardStrings(ID);

    public fgoMasterBase(String id, String img, int cost, AbstractCard.CardType type, CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        this(id, (getCardStrings(id)).NAME, img, cost, (getCardStrings(id)).DESCRIPTION, type, color, rarity, target);
    }

    public fgoMasterBase(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);

        if (AbstractDungeon.player instanceof master_male) {
            this.setBackgroundTexture(
                    "fgoMasterResources/images/512/bg_colorless_master_s_reward.png",
                    "fgoMasterResources/images/1024/bg_colorless_master_reward.png");
        }
    }

    protected static CardStrings getCardStrings(String id) {
        return CardCrawlGame.languagePack.getCardStrings(id);
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        ArrayList<TooltipInfo> list = new ArrayList<>();
        if (getForeignerCardID()) {
            list.add(new TooltipInfo(TIPS.NAME, TIPS.DESCRIPTION));
        }
        return list;
    }

    private boolean getForeignerCardID() {
        return this.cardID.equals(LivingFlame.ID)
                || this.cardID.equals(BackAgain.ID)
                || this.cardID.equals(AbyssLight.ID)
                || this.cardID.equals(VoidSpaceFineArts.ID)
                || this.cardID.equals(TheYellowHouse.ID)
                || this.cardID.equals(Insanity.ID)
                || this.cardID.equals(FacelessMoon.ID);
    }
}
