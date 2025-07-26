package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.orbs.Dark;
import fatemaster.cards.fgoLibraryBase;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;

public class MysticEyes extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/AeberReckoning.png";
    public static final String ID = masterMod.makeId(MysticEyes.class.getSimpleName());
    private static final int COST = 1;

    public MysticEyes() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        //this.returnToHand = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new ViceCrushEffect(m.hb.cX, m.hb.cY), 0.5F));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new ApplyPowerAction(m, p, new CursePower(m, this.magicNumber), this.magicNumber));
        this.addToBot(new ChannelAction(new Dark()));
    }
}
