package fatemaster.cards.fgoLibrary.male;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.vfx.PaladinEffect;

public class Paladin extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Paladin.png";
    public static final String ID = masterMod.makeId(Paladin.class.getSimpleName());
    private static final int COST = 4;

    public Paladin(boolean isPreviewCard) {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 40;
        this.baseDamage = 40;
        this.isMultiDamage = true;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        if (!isPreviewCard) {
            this.cardsToPreview = new Paladin(true);
            ((Paladin) this.cardsToPreview).changeType(CardType.ATTACK);
        }
    }

    public Paladin() {
        this(false);
    }

    public void changeType(CardType type) {
        if (this.cardsToPreview != null)
            ((Paladin) this.cardsToPreview).changeType(this.type);
        if (type == CardType.SKILL) {
            this.type = CardType.SKILL;
            this.target = CardTarget.SELF;
            this.name = (getCardStrings(ID)).NAME + (this.upgraded ? "+" : "");
            this.rawDescription = (getCardStrings(ID)).DESCRIPTION;
        } else {
            this.type = CardType.ATTACK;
            this.target = CardTarget.ALL_ENEMY;
            this.name = (getCardStrings(ID)).EXTENDED_DESCRIPTION[0] + (this.upgraded ? "+" : "");
            this.rawDescription = (getCardStrings(ID)).EXTENDED_DESCRIPTION[1];
        }

        this.initializeDescription();
        this.initializeTitle();
        this.loadCardImage("fgoMasterResources/images/cards_Master/" +
                (this.type == CardType.SKILL ? "Paladin" : "Kyrielight") +
                ".png");
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(10);
            this.upgradeBlock(10);
            this.upgradeMagicNumber(1);
            if (this.cardsToPreview != null) this.cardsToPreview.upgrade();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.type == CardType.SKILL) {
            this.addToBot(new GainBlockAction(p, p, this.block));
            this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1), 1));
        } else {
            boolean foundBoss = false;
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
                if (mo.type == AbstractMonster.EnemyType.BOSS) {
                    foundBoss = true;
                    break;
                }
            }

            this.addToBot(new SFXAction("ATTACK_HEAVY"));
            this.addToBot(new VFXAction(p, new PaladinEffect(p.dialogX + 20.0F * Settings.scale, p.dialogY - 80.0F * Settings.scale), 0.1F));
            if (foundBoss) {
                this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.damage * 3, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
            } else {
                this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.NONE));
            }

            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!mo.isDeadOrEscaped()) {
                    this.addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber), -this.magicNumber));
                }
            }

        }

        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                superFlash();
                changeType(type == CardType.SKILL ? CardType.ATTACK : CardType.SKILL);
                this.isDone = true;
            }
        });
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        if (this.type == CardType.ATTACK)
            ((Paladin) card).changeType(this.type);
        if (this.cardsToPreview != null) {
            card.cardsToPreview.block = this.cardsToPreview.block;
            card.cardsToPreview.baseBlock = this.cardsToPreview.baseBlock;
            card.cardsToPreview.damage = this.cardsToPreview.damage;
            card.cardsToPreview.baseDamage = this.cardsToPreview.baseDamage;
        }
        card.cost = this.cost;
        card.costForTurn = this.costForTurn;
        return card;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (mo.type == AbstractMonster.EnemyType.BOSS) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }
}
