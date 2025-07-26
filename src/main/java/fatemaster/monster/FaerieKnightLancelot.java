package fatemaster.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.RitualPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.noblecards.Camelot;
import fatemaster.cards.noblecards.InnocenceAroundight;
import fatemaster.characters.master;
import fatemaster.masterMod;
import fatemaster.powers.performance.ArtsCardPower;
import fatemaster.powers.performance.BusterCardPower;
import fatemaster.powers.performance.QuickCardPower;

public class FaerieKnightLancelot extends AbstractMonster {
    public static final String ID = masterMod.makeId(FaerieKnightLancelot.class.getSimpleName());
    public static final String IMG = "fgoMasterResources/images/monster/FaerieKnightLancelot.png";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public FaerieKnightLancelot() {
        this(0.0F, 0.0F);
    }

    public FaerieKnightLancelot(float x, float y) {
        super(NAME, ID, 101, -20.0F, 0.0F, 240.0F, 320.0F, IMG, x, y);
        if (AbstractDungeon.ascensionLevel >= 8) {
            this.setHp(130, 134);
        } else {
            this.setHp(124, 128);
        }

        int AroundightDmg;
        if (AbstractDungeon.ascensionLevel >= 3) {
            AroundightDmg = 15;
        } else {
            AroundightDmg = 14;
        }

        this.damage.add(new DamageInfo(this, AroundightDmg));
    }

    @Override
    public void usePreBattleAction() {
        this.addToBot(new ApplyPowerAction(this, this, new RitualPower(this, 3, false)));
        if (AbstractDungeon.ascensionLevel >= 18) {
            this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 3)));
        }

        if (AbstractDungeon.player instanceof master) {
            if (!CardHelper.hasCardWithID(Camelot.ID)) {
                //理想之城
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Camelot(), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            }
            this.addToBot(new FgoNpAction(100));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BusterCardPower(AbstractDungeon.player, 2), 2));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtsCardPower(AbstractDungeon.player, 2), 2));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new QuickCardPower(AbstractDungeon.player, 2), 2));
        } else {
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 30));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 3), 3));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 3), 3));
        }
    }

    @Override
    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.nextMove == 1) {
            this.addToBot(new AnimateSlowAttackAction(this));
            this.addToBot(new DamageAction(p, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }

        this.addToBot(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
        this.setMove(MOVES[0], (byte) 1, Intent.ATTACK, this.damage.get(0).base);
    }

    @Override
    public void die() {
        super.die();
        //尚未知晓的无垢湖光
        if (AbstractDungeon.player instanceof master && !CardHelper.hasCardWithID(InnocenceAroundight.ID)) {
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new InnocenceAroundight(), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
        }
    }
}
