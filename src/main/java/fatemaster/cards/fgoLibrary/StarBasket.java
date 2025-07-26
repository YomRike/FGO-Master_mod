package fatemaster.cards.fgoLibrary;

import fatemaster.Action.StarBasketAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import fatemaster.Action.StarBasketAttackAction;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class StarBasket extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/StarBasket.png";
    public static final String ID = masterMod.makeId(StarBasket.class.getSimpleName());
    private static final int COST = 1;

    public StarBasket() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 14;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        this.addToBot(new StarBasketAttackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
        /*this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new GainBlockAction(m, p, this.magicNumber));*/
        this.addToBot(new StarBasketAction());
    }
}
