package fatemaster.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import fatemaster.hexui_lib.interfaces.CustomCardPortrait;
import fatemaster.hexui_lib.interfaces.CustomCardTypeLocation;
import fatemaster.util.*;

import java.util.ArrayList;

public abstract class FateMagineerCard extends fgoMasterBase implements CustomCardPortrait, CustomCardTypeLocation {
    private final ArrayList<RenderLayer> portraitLayers512 = new ArrayList<>();
    private final ArrayList<RenderLayer> portraitLayers1024 = new ArrayList<>();
    public ArrayList<RenderLayer> cardArtLayers512 = new ArrayList<>();
    public ArrayList<RenderLayer> cardArtLayers1024 = new ArrayList<>();
    public static boolean skin;

    public FateMagineerCard(String id, String img, int cost, AbstractCard.CardType type, CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
        FateMagineerCard.skin = false;
    }

    public FateMagineerCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        FateMagineerCard.skin = true;
    }

    public ArrayList<RenderLayer> getPortraitLayers512() {
        portraitLayers512.clear();
        addCardArtLayers512(portraitLayers512);
        if (!skin) {
            portraitLayers512.add(new RenderImageLayer(TextureLoader.getTexture("fgoMasterResources/images/NobleResources/512/bg_noble.png")));
        }

        return portraitLayers512;
    }

    public ArrayList<RenderLayer> getPortraitLayers1024() {
        portraitLayers1024.clear();
        addCardArtLayers1024(portraitLayers1024);
        portraitLayers1024.add(new RenderImageLayer(TextureLoader.getTexture("fgoMasterResources/images/NobleResources/1024/bg_noble.png")));
        if (Settings.language == Settings.GameLanguage.ZHS) {
            portraitLayers1024.add(new RenderImageLayer(TextureLoader.getTexture("fgoMasterResources/images/NobleResources/1024/bg_noble_zh.png")));
        } else {
            portraitLayers1024.add(new RenderImageLayer(TextureLoader.getTexture("fgoMasterResources/images/NobleResources/1024/bg_noble_eng.png")));
        }

        return portraitLayers1024;
    }

    public FloatPair getCardTypeLocation(FloatPair pair, boolean isBigCard) {
        if (isBigCard) {
            pair.y += 432f;
        } else {
            pair.y += 224f;
        }
        return pair;
    }

    public void addCardArtLayers512(ArrayList<RenderLayer> portraitLayers) {portraitLayers.addAll(cardArtLayers512);}

    public void addCardArtLayers1024(ArrayList<RenderLayer> portraitLayers) {portraitLayers.addAll(cardArtLayers1024);}
}