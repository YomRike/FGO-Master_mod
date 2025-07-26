package fatemaster.cards.fgoLibrary.male;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.powers.BurnDamagePower;

public class Galvanism extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Galvanism.png";
    public static final String ID = masterMod.makeId(Galvanism.class.getSimpleName());
    private static final int COST = 1;
    private boolean isSummerMode = false;

    public Galvanism(boolean isPreviewCard) {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 9;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        if (!isPreviewCard) {
            this.cardsToPreview = new Galvanism(true);
            ((Galvanism)this.cardsToPreview).changeType();
        }
    }

    public Galvanism() {
        this(false);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
            this.upgradeMagicNumber(1);
            if (this.cardsToPreview != null) this.cardsToPreview.upgrade();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("THUNDERCLAP", 0.05F));
        if (!m.isDeadOrEscaped()) {
            this.addToBot(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.05F));
        }

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, true));

        if (!isSummerMode) {
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber, AbstractGameAction.AttackEffect.NONE));
        } else {
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber, AbstractGameAction.AttackEffect.NONE));
        }

        this.addToBot(new ApplyPowerAction(p, p, new BurnDamagePower(p, 1), 1, AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                superFlash();
                changeType();
                this.isDone = true;
            }
        });
    }

    public void changeType() {
        this.isSummerMode = !this.isSummerMode;
        if (this.cardsToPreview != null)
            ((Galvanism) this.cardsToPreview).changeType();
        if (!isSummerMode) {
            this.name = (getCardStrings(ID)).NAME + (this.upgraded ? "+" : "");
            this.rawDescription = (getCardStrings(ID)).DESCRIPTION;
        } else {
            this.name = (getCardStrings(ID)).EXTENDED_DESCRIPTION[0] + (this.upgraded ? "+" : "");
            this.rawDescription = (getCardStrings(ID)).EXTENDED_DESCRIPTION[1];
        }

        this.initializeDescription();
        this.initializeTitle();
        this.loadCardImage("fgoMasterResources/images/cards_Master/" +
                (!isSummerMode ? "Galvanism" : "SummerGalvanism") +
                ".png");
    }
}
