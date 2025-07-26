package fatemaster.cards.colorless;

import fatemaster.Action.ViyViyViyAction;
import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class Shvibzik extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Shvibzik.png";
    public static final String ID = masterMod.makeId(Shvibzik.class.getSimpleName());
    private static final int COST = 2;

    public Shvibzik() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.exhaust = true;
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        this.addToBot(new ViyViyViyAction());
    }
}
