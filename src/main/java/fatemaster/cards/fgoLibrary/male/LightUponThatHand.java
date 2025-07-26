package fatemaster.cards.fgoLibrary.male;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.modifier.ExcaliburModifier;
import fatemaster.modifier.PretenderModifier;

public class LightUponThatHand extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/LightUponThatHand.png";
    public static final String ID = masterMod.makeId(LightUponThatHand.class.getSimpleName());
    private static final int COST = 1;

    public LightUponThatHand() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 10;
        CardModifierManager.addModifier(this, new PretenderModifier());
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
    }
}
