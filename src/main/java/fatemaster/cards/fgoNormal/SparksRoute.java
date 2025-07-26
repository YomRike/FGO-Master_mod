package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import fatemaster.Action.SparksRouteAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.StarGainPower;

public class SparksRoute extends fgoNormalCard {
    public static final String ID = masterMod.makeId(SparksRoute.class.getSimpleName());
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/SparksRoute.png";
    private static final int COST = 0;

    public SparksRoute() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        //this.returnToHand = true;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int theSize = AbstractDungeon.player.hand.size();
        this.addToBot(new SparksRouteAction());
        this.addToBot(new ShuffleAction(AbstractDungeon.player.drawPile, false));
        this.addToBot(new DrawCardAction(p, theSize - 1));
        //this.addToBot(new ApplyPowerAction(p, p, new StarGainPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new DrawCardAction(p, this.magicNumber));
    }
}
