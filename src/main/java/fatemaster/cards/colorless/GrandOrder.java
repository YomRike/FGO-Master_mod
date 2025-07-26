package fatemaster.cards.colorless;

import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

import java.util.ArrayList;
import java.util.Iterator;

public class GrandOrder extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/GrandOrder.png";
    public static final String ID = masterMod.makeId(GrandOrder.class.getSimpleName());
    private static final int COST = 2;

    public GrandOrder() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 99999;
        this.isMultiDamage = true;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator<AbstractMonster> var1 = AbstractDungeon.getMonsters().monsters.iterator();
        do {
            if (!var1.hasNext()) {
                return;
            }

            m = var1.next();
        } while (m.type == AbstractMonster.EnemyType.BOSS);
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        AbstractCard playedKey = this;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.player.masterDeck.group.removeIf(card -> card == playedKey ||
                        (card.uuid.equals(playedKey.uuid) && card.upgraded == playedKey.upgraded)
                );

                AbstractDungeon.actionManager.cardsPlayedThisCombat.removeIf(c -> c == playedKey);
                isDone = true;
            }
        });
    }

}
