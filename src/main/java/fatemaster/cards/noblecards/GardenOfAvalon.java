package fatemaster.cards.noblecards;

import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.EnergyRegenPower;
import fatemaster.powers.GardenOfAvalonPower;
import fatemaster.powers.StarRegenPower;

public class GardenOfAvalon extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/GardenOfAvalon.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/GardenOfAvalon_p.png";
    public static final String ID = masterMod.makeId(GardenOfAvalon.class.getSimpleName());

    public GardenOfAvalon() {
        super(ID, IMG_PATH, 2, CardType.POWER, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagsEnum.Round_Table_Knight);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new GardenOfAvalonPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new EnergyRegenPower(p, 5), 5));
        this.addToBot(new ApplyPowerAction(p, p, new StarRegenPower(p, 5), 5));
    }
}
