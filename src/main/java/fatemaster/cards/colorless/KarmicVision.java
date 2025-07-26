package fatemaster.cards.colorless;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoMasterBase;
import fatemaster.masterMod;
import fatemaster.powers.monster.CooldownFgoPower;

public class KarmicVision extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/KarmicVision.png";
    public static final String ID = masterMod.makeId(KarmicVision.class.getSimpleName());
    private static final int COST = 1;

    public KarmicVision() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 40;
        this.baseMagicNumber = 30;
        this.magicNumber = this.baseMagicNumber;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(10);
            this.upgradeMagicNumber(20);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(CooldownFgoPower.POWER_ID + ID)) {
            this.addToBot(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
            this.addToBot(new VFXAction(new CollectorCurseEffect(m.hb.cX, m.hb.cY), 2.0F));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            this.addToBot(new FgoNpAction(this.magicNumber));
            this.addToBot(new ApplyPowerAction(p, p, new CooldownFgoPower(p, 6, this)));
        } else {
            this.addToBot(new ReducePowerAction(p, p, CooldownFgoPower.POWER_ID + ID, 1));
        }
    }
}
