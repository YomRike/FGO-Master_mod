package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.FacelessMoonPower;
import fatemaster.Enum.CardTagsEnum;

public class FacelessMoon extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/FacelessMoon.png";
    public static final String ID = masterMod.makeId(FacelessMoon.class.getSimpleName());
    private static final int COST = 1;

    public FacelessMoon() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagsEnum.Foreigner);
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
        this.addToBot(new ApplyPowerAction(p, p, new FacelessMoonPower(p, this.magicNumber), this.magicNumber));
    }
}
