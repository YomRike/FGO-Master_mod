package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.StarGainPower;
import fatemaster.Enum.CardTagsEnum;

public class Swingby extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Swingby.png";
    public static final String ID = masterMod.makeId(Swingby.class.getSimpleName());
    private static final int COST = 1;

    public Swingby() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        //this.isEthereal = true;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagsEnum.Foreigner);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            /*this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();*/
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int theSize = AbstractDungeon.player.hand.size();
        this.addToTop(new DiscardAction(p, p, theSize, false));
        if (theSize >= 2) {
            this.addToBot(new GainBlockAction(p, p, this.magicNumber * (theSize - 1)));
            this.addToBot(new ApplyPowerAction(p, p, new StarGainPower(p, this.magicNumber * (theSize - 1)), this.magicNumber * (theSize - 1)));
        }
    }
}
