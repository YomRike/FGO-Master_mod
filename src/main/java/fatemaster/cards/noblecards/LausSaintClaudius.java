package fatemaster.cards.noblecards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import fatemaster.cards.AbsNoblePhantasmCard;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.masterMod;
import fatemaster.powers.TheaterPower;

public class LausSaintClaudius extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/LausSaintClaudius.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/LausSaintClaudius_p.png";
    public static final String ID = masterMod.makeId(LausSaintClaudius.class.getSimpleName());

    public LausSaintClaudius() {
        super(ID, IMG_PATH, 1, CardType.POWER, CardTarget.SELF);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new GrandFinalEffect(), 0.7F));
        } else {
            this.addToBot(new VFXAction(new GrandFinalEffect(), 1.0F));
        }

        this.addToBot(new ApplyPowerAction(p, p, new TheaterPower(p, this.magicNumber, 3), this.magicNumber));
    }
}
