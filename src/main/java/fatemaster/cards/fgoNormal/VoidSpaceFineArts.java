package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import fatemaster.Action.VoidSpaceFineArtsAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;
import fatemaster.Enum.CardTagsEnum;

public class VoidSpaceFineArts extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/VoidSpaceFineArts.png";
    public static final String ID = masterMod.makeId(VoidSpaceFineArts.class.getSimpleName());
    private static final int COST = 1;

    public VoidSpaceFineArts() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.tags.add(CardTagsEnum.Foreigner);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(2));
        for (int i = 0; i < 3; ++i) {
            this.addToBot(new ApplyPowerAction(p, p, new CursePower(p, 1), 1));
        }
        this.addToBot(new VoidSpaceFineArtsAction(this.magicNumber));
    }
}
