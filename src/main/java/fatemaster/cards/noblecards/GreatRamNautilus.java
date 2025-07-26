package fatemaster.cards.noblecards;

import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.WatersidePower;

public class GreatRamNautilus extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/GreatRamNautilus.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/GreatRamNautilus_p.png";
    public static final String ID = masterMod.makeId(GreatRamNautilus.class.getSimpleName());

    public GreatRamNautilus() {
        super(ID, IMG_PATH, CardType.ATTACK, CardTarget.ENEMY);
        this.baseDamage = 34;
        this.exhaust = true;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(8);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (p.hasPower(WatersidePower.POWER_ID)) {
            this.addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        if (AbstractDungeon.player.hasPower(WatersidePower.POWER_ID)) {
            this.baseDamage += this.baseDamage / 2;
        }
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber;
        if (AbstractDungeon.player.hasPower(WatersidePower.POWER_ID)) {
            this.baseDamage += this.baseDamage / 2;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
}
