package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import fatemaster.masterMod;
import fatemaster.powers.DefenseDownPower;

public class IntoxicatedMeal extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/IntoxicatedMeal.png";
    public static final String ID = masterMod.makeId(IntoxicatedMeal.class.getSimpleName());
    private static final int COST = 1;

    public IntoxicatedMeal() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.target = CardTarget.ALL_ENEMY;
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
            this.addToBot(new ApplyPowerAction(m, p, new DefenseDownPower(m, this.magicNumber), this.magicNumber));
        } else {
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                this.addToBot(new ApplyPowerAction(monster, p, new PoisonPower(monster, p, this.magicNumber), this.magicNumber));
                this.addToBot(new ApplyPowerAction(monster, p, new DefenseDownPower(monster, this.magicNumber), this.magicNumber));
            }
        }
    }
}
