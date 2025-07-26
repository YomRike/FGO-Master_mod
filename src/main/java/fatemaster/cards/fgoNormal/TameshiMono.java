package fatemaster.cards.fgoNormal;

import fatemaster.Action.TameshiMonoAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class TameshiMono extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TameshiMono.png";
    public static final String ID = masterMod.makeId(TameshiMono.class.getSimpleName());
    private static final int COST = 1;

    public TameshiMono() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 11;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new TameshiMonoAction());
        this.addToBot(new GainBlockAction(p, p, this.block));
    }
}
