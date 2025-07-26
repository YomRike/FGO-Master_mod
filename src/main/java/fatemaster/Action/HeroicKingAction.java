package fatemaster.Action;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;

public class HeroicKingAction extends AbstractGameAction {
    private final AbstractCard card;
    private final AbstractGameAction.AttackEffect effect;

    public HeroicKingAction(AbstractCard card, AbstractGameAction.AttackEffect effect) {
        this.card = card;
        this.effect = effect;
    }

    @Override
    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        float randomValue1 = -200.0F + AbstractDungeon.cardRng.random() * (250.0F - (-200.0F));
        float randomValue2 = -200.0F + AbstractDungeon.cardRng.random() * (250.0F - (-200.0F));
        if (this.target != null) {
            this.card.calculateCardDamage((AbstractMonster) this.target);
            this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), this.effect));
            //this.addToTop(new HKExpungeVFXAction((AbstractMonster) this.target));
            this.addToTop(new VFXAction(new AnimatedSlashEffect(this.target.hb.cX, this.target.hb.cY - 30.0F * Settings.scale, 500.0F, randomValue1, randomValue2, 3.0F, Color.VIOLET, Color.SKY)));
            this.addToTop(new SFXAction("ATTACK_FAST", 0.2F));
            this.addToTop(new SFXAction("ATTACK_WHIFF_1", 0.2F));
        }

        this.isDone = true;
    }
}
