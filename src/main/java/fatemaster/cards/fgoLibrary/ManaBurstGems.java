package fatemaster.cards.fgoLibrary;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.ManaBurstGemsPower;

public class ManaBurstGems extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/ManaBurstGems.png";
    public static final String ID = masterMod.makeId(ManaBurstGems.class.getSimpleName());
    private static final int COST = 0;

    public ManaBurstGems() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ManaBurstGemsPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new VFXAction(p, new VerticalAuraEffect(Color.FIREBRICK, p.hb.cX, p.hb.cY), 0.0F));
        this.addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
    }
}
