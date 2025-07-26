package fatemaster.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.WatersidePower;

public class SarasvatiMeltout extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/SarasvatiMeltout.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/SarasvatiMeltout_p.png";
    public static final String ID = masterMod.makeId(SarasvatiMeltout.class.getSimpleName());

    public SarasvatiMeltout() {
        super(ID, IMG_PATH, 0, CardType.POWER, CardTarget.SELF);
        this.baseMagicNumber = 60;
        this.magicNumber = this.baseMagicNumber;
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(20);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new WatersidePower(p)));
        this.addToBot(new FgoNpAction(this.magicNumber));
    }
}
