package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ThirdEyeEffect;
import fatemaster.cards.fgoNormalCard;
import fatemaster.masterMod;

public class TakeRomance extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TakeRomance.png";
    public static final String ID = masterMod.makeId(TakeRomance.class.getSimpleName());
    private static final int COST = 0;

    public TakeRomance() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p != null) {
            this.addToBot(new VFXAction(new ThirdEyeEffect(p.hb.cX, p.hb.cY)));
        }

        this.addToBot(new ScryAction(this.magicNumber));
        this.addToBot(new DrawCardAction(p, 1));
        //this.addToBot(new UpdateMasterImgAction());
    }
}
