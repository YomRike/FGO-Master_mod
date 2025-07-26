package fatemaster.cards.noblecards;

import fatemaster.Action.EnferChateaudIfAction;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;

public class EnferChateaudIf extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/EnferChateaudIf.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/EnferChateaudIf_p.png";
    public static final String ID = masterMod.makeId(EnferChateaudIf.class.getSimpleName());

    public EnferChateaudIf() {
        super(ID, IMG_PATH, CardType.POWER, CardTarget.SELF);
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
         this.addToBot(new EnferChateaudIfAction());
    }
}
