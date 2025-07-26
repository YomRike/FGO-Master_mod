package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import fatemaster.Action.HeroicKingAction;
import fatemaster.Action.KingToHandAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.HeroicKingPower;

public class HeroicKing extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/HeroicKing.png";
    public static final String ID = masterMod.makeId(HeroicKing.class.getSimpleName());
    private static final int COST = 1;

    public HeroicKing() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 5;
        this.baseMagicNumber = 2;
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
        for (int i = 0; i < this.magicNumber; ++i) {
            if (AbstractDungeon.player.hasPower(HeroicKingPower.POWER_ID)) {
                this.addToBot(new SFXAction("ATTACK_HEAVY"));
                this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
                this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
            } else {
                this.addToBot(new HeroicKingAction(this, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    @Override
    public void tookDamage() {
        this.addToBot(new KingToHandAction(this, 1));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(HeroicKingPower.POWER_ID)) {
            this.rawDescription = getCardStrings(ID).EXTENDED_DESCRIPTION[0];
        }
        if (!AbstractDungeon.player.hasPower(HeroicKingPower.POWER_ID)) {
            this.rawDescription = getCardStrings(ID).DESCRIPTION;
        }

        this.initializeDescription();
    }
}
