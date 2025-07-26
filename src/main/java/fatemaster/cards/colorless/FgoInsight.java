package fatemaster.cards.colorless;

import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;
import fatemaster.masterMod;
import fatemaster.powers.StarGainPower;

public class FgoInsight extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/FgoInsight.png";
    public static final String ID = masterMod.makeId(FgoInsight.class.getSimpleName());
    private static final int COST = 0;

    public FgoInsight() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new LightBulbEffect(p.hb)));
        } else {
            this.addToBot(new VFXAction(new LightBulbEffect(p.hb), 0.2F));
        }

        this.addToBot(new DrawCardAction(p, this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new StarGainPower(p, 4), 4));
    }
}
