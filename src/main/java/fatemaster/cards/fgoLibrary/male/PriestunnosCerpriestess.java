package fatemaster.cards.fgoLibrary.male;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.BurnDamagePower;
import fatemaster.powers.CursePower;

public class PriestunnosCerpriestess extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/PriestunnosCerpriestess.png";
    public static final String ID = masterMod.makeId(PriestunnosCerpriestess.class.getSimpleName());
    private static final int COST = 0;

    public PriestunnosCerpriestess() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 3;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (m.hasPower(CursePower.POWER_ID)) {
                    this.addToTop(new ApplyPowerAction(m, p, new CursePower(m, magicNumber), magicNumber));
                }
                if (m.hasPower(BurnDamagePower.POWER_ID)) {
                    this.addToTop(new ApplyPowerAction(m, p, new BurnDamagePower(m, magicNumber), magicNumber));
                }
                if (m.hasPower(PoisonPower.POWER_ID)) {
                    this.addToTop(new ApplyPowerAction(m, p, new PoisonPower(m, p, magicNumber), magicNumber));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && (m.hasPower(CursePower.POWER_ID) || m.hasPower(BurnDamagePower.POWER_ID) || m.hasPower(PoisonPower.POWER_ID))) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }
}
