package fatemaster.cards.SupportCraft;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.FateMagineerCard;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.masterMod;

public class SupportCraft extends FateMagineerCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/SupportCraft.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/SupportCraft_p.png";
    public static final String ID = masterMod.makeId(SupportCraft.class.getSimpleName());
    private static final int COST = 1;

    public SupportCraft() {
        super(ID, IMG_PATH, COST, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 50;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FgoNpAction(this.magicNumber));
    }
}
