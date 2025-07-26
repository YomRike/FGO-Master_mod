package fatemaster.cards.fgoNormal;

import fatemaster.Action.AttackComeOnAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class ComeOn extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/ComeOn.png";
    public static final String ID = masterMod.makeId(ComeOn.class.getSimpleName());
    private static final int COST = 0;

    public ComeOn() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 7;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AttackComeOnAction(this, AbstractGameAction.AttackEffect.FIRE));
    }
}
