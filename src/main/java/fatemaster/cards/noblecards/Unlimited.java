package fatemaster.cards.noblecards;

import fatemaster.Action.WaitFgoAction;
import fatemaster.Action.lor.ChangeSceneEffect;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.FadeWipeParticle;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.UnlimitedPower;

public class Unlimited extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/Unlimited.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/Unlimited_p.png";
    public static final String CHANGE = "fgoMasterResources/images/vfx_master/UnlimitedBg.png";
    public static final String ID = masterMod.makeId(Unlimited.class.getSimpleName());

    public Unlimited() {
        super(ID, IMG_PATH, 0, CardType.POWER, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.topLevelEffects.add(new FadeWipeParticle());
        this.addToBot(new WaitFgoAction(1.0F));
        this.addToBot(new VFXAction(new ChangeSceneEffect(ImageMaster.loadImage(CHANGE))));
        CardCrawlGame.music.silenceTempBgmInstantly();
        CardCrawlGame.music.silenceBGMInstantly();
        AbstractDungeon.getCurrRoom().playBgmInstantly("UBW_Extended.mp3");
        this.addToBot(new ApplyPowerAction(p, p, new UnlimitedPower(p, this.magicNumber), this.magicNumber));
    }
}
