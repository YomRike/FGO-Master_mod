package fatemaster.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.AbsNoblePhantasmCard;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.masterMod;
import fatemaster.powers.HeroicKingPower;

public class JoyeuseOrdre extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/JoyeuseOrdre.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/JoyeuseOrdre_p.png";
    public static final String ID = masterMod.makeId(JoyeuseOrdre.class.getSimpleName());

    public JoyeuseOrdre() {
        super(ID, IMG_PATH, 1, CardType.POWER, CardTarget.SELF);
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new HeroicKingPower(p)));
    }
}
