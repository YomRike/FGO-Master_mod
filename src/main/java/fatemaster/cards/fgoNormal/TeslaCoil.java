package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import fatemaster.masterMod;
import fatemaster.powers.NPRatePower;

public class TeslaCoil extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TeslaCoil.png";
    public static final String ID = masterMod.makeId(TeslaCoil.class.getSimpleName());
    private static final int COST = 1;

    public TeslaCoil() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("THUNDERCLAP", 0.05F));
        if (!m.isDeadOrEscaped()) {
            this.addToBot(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.05F));
        }

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, true));
        this.addToBot(new ApplyPowerAction(p, p, new NPRatePower(p, this.magicNumber), this.magicNumber));
    }
}
