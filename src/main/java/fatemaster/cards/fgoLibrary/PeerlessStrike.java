package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class PeerlessStrike extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/PeerlessStrike.png";
    public static final String ID = masterMod.makeId(PeerlessStrike.class.getSimpleName());
    private static final int COST = 0;

    public PeerlessStrike() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 30;
        this.tags.add(CardTags.STRIKE);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new GrandFinalEffect(), 0.7F));
        } else {
            this.addToBot(new VFXAction(new GrandFinalEffect(), 1.0F));
        }
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            if ((float) p.currentHealth > (float) p.maxHealth / 2.0F) {
                canUse = false;
                this.cantUseMessage = (getCardStrings(ID)).EXTENDED_DESCRIPTION[0];
            }
        }

        return canUse;
    }

    @Override
    public void triggerOnGlowCheck() {
        AbstractPlayer p = AbstractDungeon.player;
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if ((float) p.currentHealth <= (float) p.maxHealth / 2.0F && p.currentHealth > 0) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
}
