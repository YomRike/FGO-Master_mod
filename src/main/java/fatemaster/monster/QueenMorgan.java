package fatemaster.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fatemaster.Action.QueenMorganAction;
import fatemaster.cards.noblecards.RoadlessCamelot;
import fatemaster.cards.noblecards.TsumukariMuramasa;
import fatemaster.characters.master;
import fatemaster.masterMod;
import fatemaster.powers.monster.FaeQueenPower;
import fatemaster.powers.monster.NowStormPower;
import fatemaster.powers.monster.OrkneyPower;
import fatemaster.powers.monster.ProphesizedChildPower;

public class QueenMorgan extends AbstractMonster {
    public static final String ID = masterMod.makeId(QueenMorgan.class.getSimpleName());
    public static final String IMG = "fgoMasterResources/images/monster/QueenMorgan.png";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    private final int blockAmt;
    private int numTurns = 0;
    private int heavyAttack = 0;
    private boolean orkney;
    private boolean storm;

    public QueenMorgan() {
        this(0.0F, 0.0F);
    }

    public QueenMorgan(float x, float y) {
        super(NAME, ID, 300, 0.0F, 0.0F, 320.0F, 320.0F, IMG, x, y);
        this.type = EnemyType.BOSS;
        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(320);
        } else {
            setHp(300);
        }

        int flailDmg;
        int beamDmg;
        if (AbstractDungeon.ascensionLevel >= 4) {
            flailDmg = 14;
            beamDmg = 20;
            this.blockAmt = 12;
        } else {
            flailDmg = 12;
            beamDmg = 18;
            this.blockAmt = 10;
        }

        this.orkney = true;
        this.storm = true;
        this.damage.add(new DamageInfo(this, flailDmg));
        this.damage.add(new DamageInfo(this, beamDmg));
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_CITY");

        this.addToBot(new ApplyPowerAction(this, this, new FaeQueenPower(this)));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ProphesizedChildPower(AbstractDungeon.player)));
    }

    @Override
    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new AnimateFastAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                if (this.hasPower(NowStormPower.POWER_ID)) {
                    this.addToBot(new QueenMorganAction());
                }

                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new AnimateFastAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                this.addToBot(new GainBlockAction(this, this, this.blockAmt));
                if (this.hasPower(NowStormPower.POWER_ID)) {
                    this.addToBot(new QueenMorganAction());
                }

                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new AnimateFastAttackAction(this));
                //AbstractDungeon.actionManager.addToBottom(new VFXAction(new LaserBeamEffect(this.hb.cX, this.hb.cY + 60.0F * Settings.scale), 1.5F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 4:
                if (AbstractDungeon.ascensionLevel >= 19) {
                    this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 3), 3));
                } else {
                    this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 2), 2));
                }
                this.addToBot(new ApplyPowerAction(this, this, new OrkneyPower(this, 5), 5));
                break;
            case 5:
                this.addToBot(new ApplyPowerAction(this, this, new NowStormPower(this)));
        }

        this.addToBot(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
        if (this.numTurns == 3) {
            this.setMove((byte) 3, Intent.ATTACK, this.damage.get(1).base);
            this.numTurns = 0;
            ++this.heavyAttack;
        } else if (this.heavyAttack == 1 && this.orkney) {
            this.orkney = false;
            this.setMove(MOVES[0], (byte) 4, Intent.BUFF);
        } else if (this.heavyAttack == 2 && this.storm) {
            this.storm = false;
            this.setMove(MOVES[1], (byte) 5, Intent.BUFF);
        } else {
            if (AbstractDungeon.aiRng.randomBoolean()) {
                this.setMove((byte) 1, Intent.ATTACK, this.damage.get(0).base, 2, true);
            } else {
                this.setMove((byte) 2, Intent.ATTACK_DEFEND, this.damage.get(0).base);
            }
            ++this.numTurns;
        }
    }

    @Override
    public void die() {
        super.die();
        //业已无法抵达的理想乡
        if (!CardHelper.hasCardWithID(RoadlessCamelot.ID) && AbstractDungeon.player instanceof master) {
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new RoadlessCamelot(), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
        }
    }
}
