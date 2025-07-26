package fatemaster.cards.noblecards;

import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;

public class EnumaElish extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/EnumaElish.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/EnumaElish_p.png";
    public static final String ID = masterMod.makeId(EnumaElish.class.getSimpleName());

    public EnumaElish() {
        super(ID, IMG_PATH, CardType.ATTACK, CardTarget.ALL_ENEMY);
        this.baseDamage = 40;
        this.isMultiDamage = true;
        this.exhaust = true;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}
