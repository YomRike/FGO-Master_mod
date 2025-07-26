package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class WildHunt extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/WildHunt.png";
    public static final String ID = masterMod.makeId(WildHunt.class.getSimpleName());
    private static final int COST = 0;

    public WildHunt() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            //this.upgradeBaseCost(1);
            /*this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();*/
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractPower power : p.powers) {
                    this.addToTop(new RemoveSpecificPowerAction(p, p, power.ID));
                }
                this.isDone = true;
            }
        });
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        /*if (!this.upgraded) {
            this.addToBot(new RandomCardWithTagAction(CardTagsEnum.Round_Table_Knight));
        } else {
            this.addToBot(new RandomCardWithTagAction(CardTagsEnum.Round_Table_Knight, false, true));
        }
        this.addToBot(new ApplyPowerAction(p, p, new NPDamageWildPower(p, this.magicNumber), this.magicNumber));*/
    }
}
