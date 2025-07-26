package fatemaster.cards.fgoNormal;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class DoppelgangerMaster extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Doppelganger.png";
    public static final String ID = masterMod.makeId(DoppelgangerMaster.class.getSimpleName());
    private static final int COST = 2;

    public DoppelgangerMaster() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new SFXAction("ATTACK_WHIFF_2", 0.3F));
            this.addToBot(new SFXAction("ATTACK_FAST", 0.2F));
            this.addToBot(new VFXAction(new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, 200.0F, 290.0F, 3.0F, Color.VIOLET, Color.PINK)));
        }

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        if (m != null) {
            this.addToBot(new SFXAction("ATTACK_WHIFF_1", 0.2F));
            this.addToBot(new SFXAction("ATTACK_FAST", 0.2F));
            this.addToBot(new VFXAction(new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, -200.0F, 250.0F, 3.0F, Color.VIOLET, Color.CYAN)));
        }

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        /*this.addToBot(new DoppelgangerMasterAction());
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            this.addToTop(new EmptyDeckShuffleAction());
        }*/
    }

    @Override
    public void applyPowers() {
        boolean hasPlayedAttackCard = false;

        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()) {
            for (AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
                if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
                    hasPlayedAttackCard = true;
                    break;
                }
            }
        }

        if (hasPlayedAttackCard) {
            modifyCostForCombat(-99);
        }
        super.applyPowers();
    }
}
