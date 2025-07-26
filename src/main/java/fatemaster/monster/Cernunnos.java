package fatemaster.monster;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.status.CurseDisaster;
import fatemaster.characters.master;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;
import fatemaster.powers.InvincibilityTurnPower;
import fatemaster.powers.PutOnFakeFacePower;
import fatemaster.powers.monster.CurseCondensingPower;
import fatemaster.powers.monster.CurseDecayedFleshPower;
import fatemaster.powers.monster.GainsCurseLayerPower;
import fatemaster.powers.monster.ProphesizedChildPower;

public class Cernunnos extends AbstractMonster {
    public static final String ID = masterMod.makeId(Cernunnos.class.getSimpleName());
    public static final String IMG = "fgoMasterResources/images/monster/Cernunnos.png";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    private final int HPAmt;
    private final int strengthAmt;
    private boolean grimalkin;
    private boolean fae;
    private boolean condensing;

    public Cernunnos() {
        this(0.0F, 0.0F);
    }

    public Cernunnos(float x, float y) {
        super(NAME, ID, 500, 0.0F, 550.0F * Settings.scale, 320.0F, 320.0F, IMG, x, y - 550.0F * Settings.scale);
        this.type = EnemyType.BOSS;
        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(534);
        } else {
            this.setHp(500);
        }

        int Damage;
        if (AbstractDungeon.ascensionLevel >= 4) {
            Damage = 12;
            this.strengthAmt = 4;
        } else {
            Damage = 10;
            this.strengthAmt = 2;
        }

        this.HPAmt = 4;
        this.grimalkin = true;
        this.fae = true;
        this.condensing = true;
        this.damage.add(new DamageInfo(this, Damage));
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BEYOND");

        this.addToBot(new ApplyPowerAction(this, this, new CurseDecayedFleshPower(this)));
        this.addToBot(new ApplyPowerAction(this, this, new GainsCurseLayerPower(this)));
        this.addToBot(new ApplyPowerAction(this, this, new CursePower(this, 7), 7));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ProphesizedChildPower(AbstractDungeon.player)));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PutOnFakeFacePower(AbstractDungeon.player)));
    }

    @Override
    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                //this.addToBot(new VFXAction(this, new ShockWaveEffect(this.hb.cX, this.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.75F));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.BLACK, this.hb.cX, this.hb.cY), 0.33F));
                this.addToBot(new SFXAction("ATTACK_FIRE"));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.DARK_GRAY, this.hb.cX, this.hb.cY), 0.33F));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.PURPLE, this.hb.cX, this.hb.cY), 0.0F));
                this.addToBot(new VFXAction(this, new BorderLongFlashEffect(Color.RED), 0.0F, true));

                this.addToBot(new AnimateFastAttackAction(this));
                this.addToBot(new VFXAction(new HemokinesisEffect(this.hb.cX, this.hb.cY, p.hb.cX, p.hb.cY), 0.5F));
                this.addToBot(new DamageAction(p, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
                this.addToBot(new ApplyPowerAction(this, this, new CursePower(this, 1), 1));
                break;
            case 2:
                this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, this.strengthAmt), this.strengthAmt));
                this.addToBot(new ApplyPowerAction(this, this, new InvincibilityTurnPower(this, 1), 1));
                if (p.currentHealth > this.HPAmt) {
                    for (int j = 0; j < this.HPAmt; ++j) {
                        this.addToBot(new VFXAction(new FlyingOrbEffect(p.hb.cX, p.hb.cY)));
                    }
                    this.addToBot(new LoseHPAction(p, p, this.HPAmt));
                } else {
                    for (int j = 0; j < p.currentHealth; ++j) {
                        this.addToBot(new VFXAction(new FlyingOrbEffect(p.hb.cX, p.hb.cY)));
                    }
                    this.addToBot(new LoseHPAction(p, p, p.currentHealth));
                }
                break;
            case 3:
                if (p.currentHealth > this.HPAmt * 2) {
                    for (int j = 0; j < this.HPAmt * 2; ++j) {
                        this.addToBot(new VFXAction(new FlyingOrbEffect(p.hb.cX, p.hb.cY)));
                    }
                    this.addToBot(new LoseHPAction(p, p, this.HPAmt * 2));
                    this.addToBot(new HealAction(this, this, this.HPAmt * 2));
                } else {
                    for (int j = 0; j < p.currentHealth; ++j) {
                        this.addToBot(new VFXAction(new FlyingOrbEffect(p.hb.cX, p.hb.cY)));
                    }
                    this.addToBot(new LoseHPAction(p, p, p.currentHealth));
                    this.addToBot(new HealAction(this, this, p.currentHealth));
                }
                if (p instanceof master) {
                    this.addToBot(new FgoNpAction(-20));
                }
                break;
            case 4:
                this.addToBot(new ApplyPowerAction(p, this, new CursePower(p, 1), 1));
                this.addToBot(new ApplyPowerAction(p, this, new CursePower(p, 1), 1));
                this.addToBot(new ApplyPowerAction(p, this, new CursePower(p, 2), 2));
                this.addToBot(new ApplyPowerAction(p, this, new CurseCondensingPower(p, 2), 2));
                break;
            case 5:
                this.addToBot(new ReducePowerAction(this, this, CursePower.POWER_ID, 1));
                this.addToBot(new ApplyPowerAction(this, this, new PlatedArmorPower(this, 4), 4));
                this.addToBot(new HealAction(this, this, 10));
                break;
            case 6:
                this.addToBot(new ReducePowerAction(this, this, CursePower.POWER_ID, 3));
                if (AbstractDungeon.ascensionLevel >= 19) {
                    this.addToBot(new MakeTempCardInDiscardAction(new CurseDisaster(), 5));
                } else {
                    this.addToBot(new MakeTempCardInDiscardAction(new CurseDisaster(), 3));
                }
        }

        this.addToBot(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
        if (this.hasPower(CursePower.POWER_ID) && this.getPower(CursePower.POWER_ID).amount >= 11 && !this.lastMove((byte) 6)) {
            this.setMove((byte) 6, Intent.STRONG_DEBUFF);
        } else if (num < 70) {
            this.setMove((byte) 1, Intent.ATTACK_DEBUFF, this.damage.get(0).base);
        } else {
            if (this.grimalkin) {
                this.grimalkin = false;
                this.setMove((byte) 2, Intent.BUFF);
            } else if (this.fae) {
                this.fae = false;
                this.setMove((byte) 3, Intent.DEBUFF);
            } else if (this.condensing) {
                this.condensing = false;
                this.setMove((byte) 4, Intent.DEBUFF);
            } else {
                this.setMove((byte) 5, Intent.BUFF);
            }
        }
    }

    @Override
    public void die() {
        super.die();
    }
}
