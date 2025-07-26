package fatemaster.cards.noblecards;

import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.BurnDamagePower;

public class LaPucelle extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/LaPucelle.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/LaPucelle_p.png";
    public static final String ID = masterMod.makeId(LaPucelle.class.getSimpleName());

    public LaPucelle() {
        super(ID, IMG_PATH, 4, CardType.ATTACK, CardTarget.ENEMY);
        this.baseDamage = 32;
        this.exhaust = true;
        this.cardsToPreview = new Burn();
        this.cardsToPreview.upgrade();

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
        this.addToBot(new DamageAction(m, new DamageInfo(m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ApplyPowerAction(m, p, new BurnDamagePower(m, this.damage / 2), this.damage / 2));
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview, 1, true, true, false));
    }
}
