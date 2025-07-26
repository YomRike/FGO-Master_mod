package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class UndeadBird extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/UndeadBird.png";
    public static final String ID = masterMod.makeId(UndeadBird.class.getSimpleName());
    private static final int COST = 2;

    public UndeadBird() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(3);
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*for (int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new RandomCardWithTagAction(CardTags.HEALING));
        }*/
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int count = 0;
                for(AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.rarity == CardRarity.COMMON) {
                        ++count;
                    }
                }

                for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c.rarity == CardRarity.COMMON) {
                        ++count;
                    }
                }

                for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c.rarity == CardRarity.COMMON) {
                        ++count;
                    }
                }

                this.addToTop(new HealAction(p, p, count + magicNumber));
                this.isDone = true;
            }
        });
    }
}
