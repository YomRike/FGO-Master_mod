package fatemaster.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;

public class SecondLife extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/SecondLife.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/SecondLife_p.png";
    public static final String ID = masterMod.makeId(SecondLife.class.getSimpleName());

    public SecondLife() {
        super(ID, IMG_PATH, 2, CardType.POWER, CardTarget.SELF);
        this.baseMagicNumber = 25;
        this.magicNumber = this.baseMagicNumber;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(25);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExhumeAction(true));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower("Minion")) {
                this.addToBot(new InstantKillAction(mo));
                this.addToBot(new FgoNpAction(this.magicNumber));
            }
        }
    }
}
