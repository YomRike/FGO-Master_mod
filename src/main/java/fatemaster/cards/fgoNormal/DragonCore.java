package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fatemaster.Action.ExcaliburAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

import java.util.ArrayList;

public class DragonCore extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/DragonCore.png";
    public static final String ID = masterMod.makeId(DragonCore.class.getSimpleName());
    private static final int COST = 1;

    public DragonCore() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type != AbstractCard.CardType.ATTACK) {
                        AbstractCard cm = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
                        cm.setCostForTurn(0);
                        this.addToTop(new ExcaliburAction(c, cm));
                    }
                }

                /*for (int i = 0; i < cardsToExhaust.size(); i++) {
                    AbstractCard cm = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
                    cm.setCostForTurn(0);
                    this.addToBot(new MakeTempCardInHandAction(cm, true));
                }*/

                /*for (AbstractCard c : cardsToExhaust) {
                    this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }*/

                this.isDone = true;
            }
        });
    }
}
