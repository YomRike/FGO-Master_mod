package fatemaster.cards.SupportCraft;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.FateMagineerCard;
import fatemaster.cards.fgoNormal.BlessedScion;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;

public class ExcaliburSword extends FateMagineerCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/Excalibur.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/Excalibur_p.png";
    public static final String ID = masterMod.makeId(ExcaliburSword.class.getSimpleName());
    private static final int COST = 1;

    public ExcaliburSword() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 12;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(6);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}
