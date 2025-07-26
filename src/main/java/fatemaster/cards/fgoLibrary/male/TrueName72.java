package fatemaster.cards.fgoLibrary.male;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.EvasionPower;

public class TrueName72 extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TrueName72.png";
    public static final String ID = masterMod.makeId(TrueName72.class.getSimpleName());
    private static final int COST = 0;

    public TrueName72() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                /*if (upgraded) {
                    for (AbstractPower power : p.powers) {
                        if (power.type == AbstractPower.PowerType.DEBUFF &&
                                !power.ID.equals(VulnerablePower.POWER_ID)) {
                            this.addToTop(new RemoveSpecificPowerAction(p, p, power.ID));
                        }
                    }
                } else {
                    for (AbstractPower power : p.powers) {
                        if (power.type == AbstractPower.PowerType.DEBUFF &&
                                !power.ID.equals(VulnerablePower.POWER_ID) &&
                                !power.ID.equals(FrailPower.POWER_ID) &&
                                !power.ID.equals(WeakPower.POWER_ID)) {
                            this.addToTop(new RemoveSpecificPowerAction(p, p, power.ID));
                        }
                    }
                }*/

                if (p.hasPower(VulnerablePower.POWER_ID)) {
                    this.addToBot(new ApplyPowerAction(p, p, new EvasionPower(p, magicNumber), magicNumber));
                }

                this.isDone = true;
            }
        });
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(VulnerablePower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
