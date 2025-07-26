package fatemaster.cards.noblecards;

import fatemaster.Action.FastLoseHPAction;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.BurnDamagePower;

public class ParadisChateaudIf extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/ParadisChateaudIf.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/ParadisChateaudIf_p.png";
    public static final String ID = masterMod.makeId(ParadisChateaudIf.class.getSimpleName());

    public ParadisChateaudIf() {
        super(ID, IMG_PATH, CardType.SKILL, CardTarget.ENEMY);
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(BurnDamagePower.POWER_ID)) {
            for (int i = 0; i < m.getPower(BurnDamagePower.POWER_ID).amount; ++i) {
                this.addToBot(new FastLoseHPAction(m, p, this.magicNumber, AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }
}
