package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.LivingFlamePower;
import fatemaster.Enum.CardTagsEnum;

public class LivingFlame extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/LivingFlame.png";
    public static final String ID = masterMod.makeId(LivingFlame.class.getSimpleName());
    private static final int COST = 2;

    public LivingFlame() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagsEnum.Foreigner);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new LivingFlamePower(p, this.magicNumber), this.magicNumber));
    }
}
