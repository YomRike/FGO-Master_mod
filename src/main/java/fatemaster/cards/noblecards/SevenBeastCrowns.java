package fatemaster.cards.noblecards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.Action.SevenBeastCrownsAction;
import fatemaster.cards.AbsNoblePhantasmCard;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.masterMod;

import java.util.ArrayList;
import java.util.List;

public class SevenBeastCrowns extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/SevenBeastCrowns.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/SevenBeastCrowns_p.png";
    public static final String ID = masterMod.makeId(SevenBeastCrowns.class.getSimpleName());

    public SevenBeastCrowns() {
        super(ID, IMG_PATH, -1, CardType.POWER, CardTarget.SELF);
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SevenBeastCrownsAction(p, this.freeToPlayOnce, this.energyOnUse, this.upgraded));
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        ArrayList<TooltipInfo> list = new ArrayList<>(super.getCustomTooltipsTop());
        list.add(new TooltipInfo((getCardStrings(ID)).EXTENDED_DESCRIPTION[0], (getCardStrings(ID)).EXTENDED_DESCRIPTION[1]));
        return list;
    }
}
