package fatemaster.monster;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.colorless.BlackBarrel;
import fatemaster.cards.colorless.KarmicVision;
import fatemaster.cards.colorless.WallOfSnowflakes;
import fatemaster.cards.noblecards.Camelot;
import fatemaster.cards.noblecards.TsumukariMuramasa;
import fatemaster.characters.master;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;
import fatemaster.powers.HPRegenPower;
import fatemaster.powers.monster.CurseCondensingPower;
import fatemaster.powers.monster.FaerieKnightPower;
import fatemaster.powers.monster.PowerofGrudgePower;
import fatemaster.powers.monster.TaintedCursePower;

public class CalamityofNorwich extends AbstractMonster {
    public static final String ID = masterMod.makeId(CalamityofNorwich.class.getSimpleName());
    public static final String IMG = "fgoMasterResources/images/monster/CalamityofNorwich_.png";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    private int moveCount = 0;
    private final int HealAmt;
    private boolean usedExcretion = false;

    public CalamityofNorwich() {
        this(0.0F, 0.0F);
    }

    public CalamityofNorwich(float x, float y) {
        super(NAME, ID, 140, 0.0F, 0.0F, 500.0F, 320.0F, IMG, x, y);
        this.type = EnemyType.BOSS;
        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(230);
        } else {
            setHp(220);
        }

        int dmg1;
        if (AbstractDungeon.ascensionLevel >= 4) {
            dmg1 = 8;
        } else {
            dmg1 = 7;
        }

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.HealAmt = 35;
        } else {
            this.HealAmt = 30;
        }

        this.damage.add(new DamageInfo(this, dmg1));
    }

    @Override
    public void usePreBattleAction() {
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
            CardCrawlGame.music.unsilenceBGM();
            AbstractDungeon.scene.fadeOutAmbiance();
            AbstractDungeon.getCurrRoom().playBgmInstantly("Grand_Battle3.mp3");
        }
        this.addToBot(new ApplyPowerAction(this, this, new TaintedCursePower(this)));
        this.addToBot(new ApplyPowerAction(this, this, new PowerofGrudgePower(this)));

        //this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(this, 3), 3));
        if (AbstractDungeon.player instanceof master) {
            if (!CardHelper.hasCardWithID(Camelot.ID)) {
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Camelot(), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            }
            this.addToBot(new FgoNpAction(100));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FaerieKnightPower(AbstractDungeon.player)));

            AbstractCard cm1 = new WallOfSnowflakes();
            AbstractCard cm2 = new BlackBarrel();
            AbstractCard cm3 = new KarmicVision();
            //cm1.setCostForTurn(0);
            cm1.upgrade();
            cm2.upgrade();
            cm3.upgrade();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cm3, 1));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cm1, 1));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cm2, 1));
        } else {
            if (AbstractDungeon.ascensionLevel >= 19) {
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 12), 12));
            } else {
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 15), 15));
            }
        }
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HPRegenPower(AbstractDungeon.player, 6), 6));
    }

    @Override
    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                this.addToBot(new VFXAction(this, new ShockWaveEffect(this.hb.cX, this.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.75F));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.BLACK, this.hb.cX, this.hb.cY), 0.33F));
                this.addToBot(new SFXAction("ATTACK_FIRE"));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.PURPLE, this.hb.cX, this.hb.cY), 0.33F));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.CYAN, this.hb.cX, this.hb.cY), 0.0F));
                this.addToBot(new VFXAction(this, new BorderLongFlashEffect(Color.MAGENTA), 0.0F, true));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new CursePower(AbstractDungeon.player, 2), 2));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new CurseCondensingPower(AbstractDungeon.player, 2), 2));

                this.addToBot(new AnimateFastAttackAction(this));
                this.addToBot(new VFXAction(new HemokinesisEffect(this.hb.cX, this.hb.cY, p.hb.cX, p.hb.cY), 0.5F));
                this.addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
                break;
            case 2:
                this.addToBot(new VFXAction(this, new ShockWaveEffect(this.hb.cX, this.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.75F));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.BLACK, this.hb.cX, this.hb.cY), 0.33F));
                this.addToBot(new SFXAction("ATTACK_FIRE"));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.PURPLE, this.hb.cX, this.hb.cY), 0.33F));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.CYAN, this.hb.cX, this.hb.cY), 0.0F));
                this.addToBot(new VFXAction(this, new BorderLongFlashEffect(Color.MAGENTA), 0.0F, true));

                if (AbstractDungeon.ascensionLevel >= 19) {
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 2, true), 2));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, 2, true), 2));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 2, true), 2));
                } else {
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 1, true), 1));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, 1, true), 1));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 1, true), 1));
                }

                if (AbstractDungeon.player.hasPower(CursePower.POWER_ID)) {
                    int CurAmt = AbstractDungeon.player.getPower(CursePower.POWER_ID).amount;
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new CursePower(AbstractDungeon.player, CurAmt), CurAmt));
                }

                this.addToBot(new AnimateSlowAttackAction(this));
                this.addToBot(new VFXAction(new HemokinesisEffect(this.hb.cX, this.hb.cY, p.hb.cX, p.hb.cY), 0.5F));
                this.addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
                break;
            case 3:
                this.addToBot(new VFXAction(this, new ShockWaveEffect(this.hb.cX, this.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.75F));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.BLACK, this.hb.cX, this.hb.cY), 0.33F));
                this.addToBot(new SFXAction("ATTACK_FIRE"));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.PURPLE, this.hb.cX, this.hb.cY), 0.33F));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.CYAN, this.hb.cX, this.hb.cY), 0.0F));
                this.addToBot(new VFXAction(this, new BorderLongFlashEffect(Color.MAGENTA), 0.0F, true));

                if (AbstractDungeon.player.hasPower(CursePower.POWER_ID)) {
                    int CurAmt = AbstractDungeon.player.getPower(CursePower.POWER_ID).amount;
                    this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, this, CursePower.POWER_ID));
                    this.addToBot(new ApplyPowerAction(this, this, new CursePower(this, CurAmt), CurAmt));
                    this.addToBot(new GainBlockAction(this, this, 20));
                    this.addToBot(new HealAction(this, this, 10));
                }

                this.addToBot(new AnimateSlowAttackAction(this));
                this.addToBot(new VFXAction(new HemokinesisEffect(this.hb.cX, this.hb.cY, p.hb.cX, p.hb.cY), 0.5F));
                this.addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
                break;
            case 4:
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.BLACK, this.hb.cX, this.hb.cY), 0.33F));
                this.addToBot(new SFXAction("ATTACK_FIRE"));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.PURPLE, this.hb.cX, this.hb.cY), 0.33F));
                this.addToBot(new VFXAction(this, new VerticalAuraEffect(Color.CYAN, this.hb.cX, this.hb.cY), 0.0F));
                this.addToBot(new VFXAction(this, new BorderLongFlashEffect(Color.MAGENTA), 0.0F, true));

                if (this.hasPower(CursePower.POWER_ID)) {
                    int CurAmt = this.getPower(CursePower.POWER_ID).amount;
                    if (CurAmt <= 14) {
                        this.addToBot(new RemoveSpecificPowerAction(this, this, CursePower.POWER_ID));
                        this.addToBot(new HealAction(this, this, this.HealAmt));
                        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new CursePower(AbstractDungeon.player, 3), 3));
                    }

                    if (CurAmt <= 29 && CurAmt >= 14) {
                        this.addToBot(new RemoveSpecificPowerAction(this, this, CursePower.POWER_ID));
                        this.addToBot(new HealAction(this, this, this.HealAmt * 2));
                        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new CursePower(AbstractDungeon.player, 6), 6));
                    }

                    if (CurAmt >= 30) {
                        this.addToBot(new RemoveSpecificPowerAction(this, this, CursePower.POWER_ID));
                        this.addToBot(new HealAction(this, this, this.HealAmt * 3));
                        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new CursePower(AbstractDungeon.player, 9), 9));
                    }
                }
        }

        this.addToBot(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int i) {
        if (this.currentHealth < this.maxHealth / 2 && !this.usedExcretion) {
            this.usedExcretion = true;
            this.setMove(MOVES[3], (byte) 4, Intent.UNKNOWN);
        } else {
            switch (this.moveCount) {
                case 1:
                    this.setMove(MOVES[1], (byte) 2, Intent.ATTACK_DEBUFF, this.damage.get(0).base);
                    ++this.moveCount;
                    break;
                case 2:
                    this.setMove(MOVES[2], (byte) 3, Intent.ATTACK_DEFEND, this.damage.get(0).base);
                    this.moveCount = 0;
                    break;
                default:
                    this.setMove(MOVES[0], (byte) 1, Intent.ATTACK_DEBUFF, this.damage.get(0).base);
                    ++this.moveCount;
            }
        }
    }

    @Override
    public void die() {
        super.die();
        //无元剑制
        if (!CardHelper.hasCardWithID(TsumukariMuramasa.ID) && AbstractDungeon.player instanceof master) {
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new TsumukariMuramasa(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        }
    }
}
