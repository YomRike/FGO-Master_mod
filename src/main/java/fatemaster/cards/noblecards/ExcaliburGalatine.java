package fatemaster.cards.noblecards;

import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.CriticalDamageUpPower;
import fatemaster.powers.SunlightPower;

public class ExcaliburGalatine extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/ExcaliburGalatine.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/ExcaliburGalatine_p.png";
    public static final String ID = masterMod.makeId(ExcaliburGalatine.class.getSimpleName());

    public ExcaliburGalatine() {
        super(ID, IMG_PATH, 4, CardType.POWER, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagsEnum.Round_Table_Knight);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SunlightPower(p)));
        //this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 50), 50));
    }
}
