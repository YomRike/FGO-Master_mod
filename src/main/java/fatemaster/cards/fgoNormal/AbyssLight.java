package fatemaster.cards.fgoNormal;

import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ExpungeVFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.NPDamagePower;
import fatemaster.Enum.CardTagsEnum;

public class AbyssLight extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/AbyssLight.png";
    public static final String ID = masterMod.makeId(AbyssLight.class.getSimpleName());
    private static final int COST = 2;

    public AbyssLight() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 15;
        this.baseMagicNumber = 15;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagsEnum.Foreigner);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
            this.upgradeMagicNumber(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExpungeVFXAction(m));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new FgoNpAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new NPDamagePower(p, this.magicNumber), this.magicNumber));
    }
}
