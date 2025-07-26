package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.BurnDamagePower;
import fatemaster.relics.MisoPotato;

public class ItsInevitable extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/ItsInevitable.png";
    public static final String ID = masterMod.makeId(ItsInevitable.class.getSimpleName());
    private static final int COST = 1;

    public ItsInevitable() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 10;
        this.isMultiDamage = true;
        this.isEthereal = true;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
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
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        if (p.hasRelic(MisoPotato.ID)) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                this.addToBot(new ApplyPowerAction(mo, p, new BurnDamagePower(mo, 2), 2, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        this.addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
    }
}
