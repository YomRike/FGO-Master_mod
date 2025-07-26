package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class MaraPapiyas extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/MaraPapiyas.png";
    public static final String ID = masterMod.makeId(MaraPapiyas.class.getSimpleName());
    private static final int COST = 1;

    public MaraPapiyas() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
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
        /*AbstractDungeon.player.decreaseMaxHealth(2);
        this.addToBot(new HealAction(p, p, this.magicNumber));*/
        this.addToBot(new DrawCardAction(p, this.magicNumber));
        for (AbstractPower power : p.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                this.addToBot(new DrawCardAction(p, 1));
            }
        }
    }

    @Override
    public void applyPowers() {
        int debuffAmt = 0;
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                ++debuffAmt;
            }
        }
        if (debuffAmt >= 1) {
            this.rawDescription = (getCardStrings(ID)).DESCRIPTION + (getCardStrings(ID)).EXTENDED_DESCRIPTION[0] + debuffAmt + (getCardStrings(ID)).EXTENDED_DESCRIPTION[1];
        }
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }
}
