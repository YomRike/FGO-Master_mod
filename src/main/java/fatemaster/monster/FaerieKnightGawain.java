package fatemaster.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FadingPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fatemaster.cards.noblecards.BlackdogGalatine;
import fatemaster.characters.master;
import fatemaster.masterMod;
import fatemaster.powers.monster.FoulWeatherPower;

public class FaerieKnightGawain extends AbstractMonster {
    public static final String ID = masterMod.makeId(FaerieKnightGawain.class.getSimpleName());
    public static final String IMG = "fgoMasterResources/images/monster/FaerieKnightGawain.png";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    private int moveCount = 0;

    public FaerieKnightGawain() {
        this(0.0F, 0.0F);
    }

    public FaerieKnightGawain(float x, float y) {
        super(NAME, ID, 36, 0.0F, 0.0F, 320.0F, 320.0F, IMG, x, y);
        if (AbstractDungeon.ascensionLevel >= 8) {
            this.setHp(35, 40);
        } else {
            this.setHp(32, 36);
        }

        int bashDmg;
        int heavyDmg;
        if (AbstractDungeon.ascensionLevel >= 3) {
            bashDmg = 8;
            heavyDmg = 30;
        } else {
            bashDmg = 6;
            heavyDmg = 24;
        }

        this.damage.add(new DamageInfo(this, heavyDmg));
        this.damage.add(new DamageInfo(this, bashDmg));
    }

    @Override
    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new FoulWeatherPower(this, 70), 70));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new FadingPower(this, 7)));
    }

    @Override
    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                this.addToBot(new AnimateSlowAttackAction(this));
                this.addToBot(new DamageAction(p, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                if (AbstractDungeon.ascensionLevel >= 18) {
                    this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 1), 1));
                }
                break;
            case 2:
                this.addToBot(new AnimateSlowAttackAction(this));
                this.addToBot(new DamageAction(p, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
        }

        this.addToBot(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
       if (this.moveCount == 3) {
            this.setMove((byte) 2, Intent.ATTACK, this.damage.get(0).base);
            this.moveCount = 0;
        } else {
            this.setMove((byte) 1, Intent.ATTACK, this.damage.get(1).base);
            ++this.moveCount;
        }
    }

    @Override
    public void die() {
        super.die();
        //捕食日轮之角
        if (AbstractDungeon.player instanceof master && !CardHelper.hasCardWithID(BlackdogGalatine.ID)) {
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new BlackdogGalatine(), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
        }
    }
}
