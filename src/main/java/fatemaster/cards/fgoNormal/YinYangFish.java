package fatemaster.cards.fgoNormal;

import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class YinYangFish extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/YinYangFish.png";
    public static final String ID = masterMod.makeId(YinYangFish.class.getSimpleName());
    private static final int COST = 1;

    public YinYangFish() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.HEALING);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FgoNpAction(-10));
        this.addToTop(new HealAction(p, p, this.magicNumber));
    }
}
