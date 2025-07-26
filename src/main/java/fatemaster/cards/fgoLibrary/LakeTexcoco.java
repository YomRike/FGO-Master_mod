package fatemaster.cards.fgoLibrary;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import fatemaster.Action.LakeTexcocoAction;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class LakeTexcoco extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/LakeTexcoco.png";
    public static final String ID = masterMod.makeId(LakeTexcoco.class.getSimpleName());
    private static final int COST = 1;

    public LakeTexcoco() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 10;
        //this.baseBlock = 12;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
            //this.upgradeBlock(3);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F));
        }

        this.addToBot(new LakeTexcocoAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
    }

    /*@Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(WatersidePower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }*/
}
