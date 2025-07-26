package fatemaster.cards.status;

import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;

public class CurseDisaster extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CurseDisaster.png";
    public static final String ID = masterMod.makeId(CurseDisaster.class.getSimpleName());
    private static final int COST = -2;

    public CurseDisaster() {
        super(ID, IMG_PATH, COST, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerWhenDrawn() {
        this.addToBot(new SFXAction("ATTACK_PIERCING_WAIL"));
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(AbstractDungeon.player, new ShockWaveEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
        } else {
            this.addToBot(new VFXAction(AbstractDungeon.player, new ShockWaveEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
        }

        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CursePower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new CursePower(mo, this.magicNumber), this.magicNumber));
        }
    }
}
