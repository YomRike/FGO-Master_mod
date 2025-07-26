package fatemaster.patches.skin;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.FateMagineerCard;
import fatemaster.patches.MainMenuUIFgoPatch.SkinType;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;

public class SkinPreviewCard extends FateMagineerCard {
    public SkinType skin;

    public SkinPreviewCard(SkinType skin) {
        super("", getLocalizedName(skin), null, -2, "", CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.skin = skin;
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture("fgoMasterResources/images/NobleResources/512/bg_fullportrait_skin.png")));
        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(skin.getPath())));
    }

    private static String getLocalizedName(SkinType skin) {
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("fatemaster:PackMainMenuUI");
        String[] TEXT = uiStrings.TEXT;

        int index = skin.ordinal() + 2;
        if (index < TEXT.length) {
            return TEXT[index];
        }

        return skin.name();
    }

    @Override
    public void upgrade() {}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p != null) {
            p.img = ImageMaster.loadImage(skin.getPath());
        }
    }
}