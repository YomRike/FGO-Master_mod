package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class SwanLake extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/SwanLake.png";
    public static final String ID = masterMod.makeId(SwanLake.class.getSimpleName());
    private static final int COST = 1;

    public SwanLake() {
        this(0);
    }

    public SwanLake(int upgrades) {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 2;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(1);
        this.upgradeMagicNumber(1);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = (getCardStrings(ID)).NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        //this.addToBot(new ApplyPowerAction(p, p, new WatersidePower(p, this.magicNumber), this.magicNumber));
    }
}
