package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.effect.FateRemindEffect;
import fatemaster.masterMod;
import fatemaster.powers.TaisuiSPower;

public class TaisuiSMisfortune extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TaisuiSMisfortune.png";
    public static final String ID = masterMod.makeId(TaisuiSMisfortune.class.getSimpleName());
    private static final int COST = 0;

    public TaisuiSMisfortune() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isInnate = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.music.silenceTempBgmInstantly();
        CardCrawlGame.music.silenceBGMInstantly();
        AbstractDungeon.getCurrRoom().playBgmInstantly("Shimousa.mp3");
        AbstractDungeon.topLevelEffects.add(new FateRemindEffect((getCardStrings(ID)).EXTENDED_DESCRIPTION[0]));

        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new OfferingEffect(), 0.1F));
        } else {
            this.addToBot(new VFXAction(new OfferingEffect(), 0.5F));
        }
        this.addToBot(new GainEnergyAction(1));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mo, p, new TaisuiSPower(mo, this.magicNumber), this.magicNumber));
        }
    }
}
