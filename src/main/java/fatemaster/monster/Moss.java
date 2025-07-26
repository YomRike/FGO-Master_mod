package fatemaster.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;

public class Moss extends AbstractMonster {
    public static final String ID = masterMod.makeId(Moss.class.getSimpleName());
    public static final String IMG = "fgoMasterResources/images/monster/Moss_Sprite.png";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    private final int ritualAmount;
    private boolean firstMove;

    public Moss() {
        this(0.0F, 0.0F);
    }

    public Moss(float x, float y) {
        super(NAME, ID, 54, 0.0F, 0.0F, 320.0F, 320.0F, IMG, x, y);
        if (AbstractDungeon.ascensionLevel >= 7) {
            this.setHp(50, 56);
        } else {
            this.setHp(48, 54);
        }

        if (AbstractDungeon.ascensionLevel >= 2) {
            this.ritualAmount = 2;
        } else {
            this.ritualAmount = 1;
        }

        this.firstMove = true;
        this.damage.add(new DamageInfo(this, 6));
    }

    @Override
    public void usePreBattleAction() {
    }

    @Override
    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                this.addToBot(new AnimateSlowAttackAction(this));
                this.addToBot(new VFXAction(new HemokinesisEffect(this.hb.cX, this.hb.cY, p.hb.cX, p.hb.cY), 0.5F));
                this.addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new CursePower(AbstractDungeon.player, 1), 1));
                break;
            case 3:
                if (AbstractDungeon.ascensionLevel >= 17) {
                    this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, ritualAmount + 1)));
                } else {
                    this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, ritualAmount)));
                }
        }

        this.addToBot(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
        if (this.firstMove) {
            this.firstMove = false;
            this.setMove((byte) 3, Intent.BUFF);
        } else {
            this.setMove((byte) 1, Intent.ATTACK_DEBUFF, this.damage.get(0).base);
        }
    }

    @Override
    public void die() {
        super.die();
    }
}
