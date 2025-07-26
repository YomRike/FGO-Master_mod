package fatemaster.cards.colorless;

import fatemaster.Action.CurtainoftheNightAction;
import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class ProofAndRebuttal extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/ProofAndRebuttal.png";
    public static final String ID = masterMod.makeId(ProofAndRebuttal.class.getSimpleName());
    private static final int COST = 0;

    public ProofAndRebuttal() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, 1));
        if (!AbstractDungeon.player.hand.isEmpty()) {
            this.addToBot(new CurtainoftheNightAction());
        }
    }
}
