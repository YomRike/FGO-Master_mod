package fatemaster.cards.noblecards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.vfx.combat.ThirdEyeEffect;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;

public class LieLikeVortigern extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/LieLikeVortigern.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/LieLikeVortigern_p.png";
    public static final String ID = masterMod.makeId(LieLikeVortigern.class.getSimpleName());

    public LieLikeVortigern() {
        super(ID, IMG_PATH, CardType.ATTACK, CardTarget.ALL_ENEMY);
        this.baseDamage = 25;
        this.isMultiDamage = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(7);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new VFXAction(new ThirdEyeEffect(mo.hb.cX, mo.hb.cY)));
            this.addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -2), -2));
            this.addToBot(new ApplyPowerAction(mo, p, new IntangiblePlayerPower(mo, this.magicNumber), this.magicNumber));
        }

        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));
    }
}
