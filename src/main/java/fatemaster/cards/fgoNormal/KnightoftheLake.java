package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.NPRatePower;

public class KnightoftheLake extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/KnightoftheLake.png";
    public static final String ID = masterMod.makeId(KnightoftheLake.class.getSimpleName());
    private static final int COST = 1;

    public KnightoftheLake() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FgoNpAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new NPRatePower(p, 2), 2));
    }
}
