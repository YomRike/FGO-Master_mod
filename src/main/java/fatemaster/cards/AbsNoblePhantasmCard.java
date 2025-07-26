package fatemaster.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import fatemaster.Enum.AbstractCardEnum;
import fatemaster.Enum.CardTagsEnum;
import fatemaster.masterMod;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsNoblePhantasmCard extends FateMagineerCard {
    public static String ID = masterMod.makeId(AbsNoblePhantasmCard.class.getSimpleName());
    public static CardStrings TIPS = CardCrawlGame.languagePack.getCardStrings(ID);

    public AbsNoblePhantasmCard(String id, String img, int cost, CardType type, CardTarget target) {
        super(id, img, cost, type, AbstractCardEnum.Noble_Phantasm_COLOR, CardRarity.SPECIAL, target);
        this.selfRetain = true;
        this.tags.add(CardTagsEnum.Noble_Phantasm);
    }

    public AbsNoblePhantasmCard(String id, String img, CardType type, CardTarget target) {
        super(id, img, 2, type, AbstractCardEnum.Noble_Phantasm_COLOR, CardRarity.SPECIAL, target);
        this.selfRetain = true;
        this.tags.add(CardTagsEnum.Noble_Phantasm);
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        ArrayList<TooltipInfo> list = new ArrayList<>(super.getCustomTooltipsTop());
        list.add(new TooltipInfo(TIPS.NAME, TIPS.DESCRIPTION));
        return list;
    }

    /*@Override
    public void triggerWhenDrawn() {
        if (this.inBottleFlame || this.inBottleLightning || this.inBottleTornado) {
            this.addToTop(new DrawCardAction(AbstractDungeon.player, 2));
            this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }
    }*/

    /*@Override
    public void onRetained() {
        this.addToBot(new ReduceCostAction(this));
    }*/
}