package fatemaster.cards.noblecards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.Action.SuitenNikkoAction;
import fatemaster.cards.AbsNoblePhantasmCard;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.masterMod;

import static com.megacrit.cardcrawl.core.Settings.language;

public class SuitenNikko extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/SuitenNikko.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/SuitenNikko_p.png";
    public static final String ID = masterMod.makeId(SuitenNikko.class.getSimpleName());

    public SuitenNikko() {
        super(ID, IMG_PATH, CardType.POWER, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 35;
        this.magicNumber = this.baseMagicNumber;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(15);
        }
    }

    @Override
    public float getTitleFontSize() {
        if (language != Settings.GameLanguage.ZHS) {
            return 16.0F;
        }

        return -1.0F;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SuitenNikkoAction(1));
        //this.addToBot(new HealAction(p, p, this.magicNumber));
        this.addToBot(new FgoNpAction(this.magicNumber));
    }
}
