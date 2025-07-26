package fatemaster.cards.fgoLibrary.male;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import fatemaster.Action.ExcaliburAction;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class TheThirdEye extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TheThirdEye.png";
    public static final String ID = masterMod.makeId(TheThirdEye.class.getSimpleName());
    private static final int COST = 1;

    public TheThirdEye() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeBaseCost(0);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type != CardType.SKILL) {
                        AbstractCard cm = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.SKILL).makeCopy();
                        //cm.setCostForTurn(0);
                        this.addToTop(new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber), magicNumber));
                        this.addToTop(new ExcaliburAction(c, cm));
                    }
                }

                this.isDone = true;
            }
        });
    }
}
