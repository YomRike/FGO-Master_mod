package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class EightKindness extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/EightKindness.png";
    public static final String ID = masterMod.makeId(EightKindness.class.getSimpleName());
    private static final int COST = 3;

    public EightKindness() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 2), 2));
        this.addToBot(new ApplyPowerAction(p, p, new RegenPower(p, 2), 2));
        this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, 2), 2));
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, 2), 2));
        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, this.magicNumber), this.magicNumber));
    }

}
