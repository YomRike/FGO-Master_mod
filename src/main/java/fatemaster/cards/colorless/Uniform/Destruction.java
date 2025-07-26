package fatemaster.cards.colorless.Uniform;

import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import fatemaster.masterMod;
import fatemaster.relics.DecisiveBattleUniform;

public class Destruction extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Destruction.png";
    public static final String ID = masterMod.makeId(Destruction.class.getSimpleName());
    private static final int COST = -2;

    public Destruction() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber), this.magicNumber));

        if (p.hasRelic(DecisiveBattleUniform.ID)) {
            AbstractRelic uniform = p.getRelic(DecisiveBattleUniform.ID);
            uniform.counter -= 5;
            uniform.flash();
        }
    }
}
