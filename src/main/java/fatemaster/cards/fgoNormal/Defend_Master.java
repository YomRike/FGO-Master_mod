package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class Defend_Master extends fgoNormalCard {
    public static final String ID = masterMod.makeId(Defend_Master.class.getSimpleName());
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Defend.png";
    private static final int COST = 1;

    public Defend_Master() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseBlock = 5;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractCard.CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }
}
