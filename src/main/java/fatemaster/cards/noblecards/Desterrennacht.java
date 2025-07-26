package fatemaster.cards.noblecards;

import fatemaster.Action.DesterrennachtAction;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.CriticalDamageUpPower;
import fatemaster.powers.DesterrennachtPower;
import fatemaster.powers.StarRegenPower;
import fatemaster.Enum.CardTagsEnum;

public class Desterrennacht extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/Desterrennacht.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/Desterrennacht_p.png";
    public static final String ID = masterMod.makeId(Desterrennacht.class.getSimpleName());

    public Desterrennacht() {
        super(ID, IMG_PATH, 4, CardType.POWER, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagsEnum.Foreigner);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DesterrennachtAction());
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mo, p, new DesterrennachtPower(mo, this.magicNumber), this.magicNumber));
        }
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        this.addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 50), 50));
        this.addToBot(new ApplyPowerAction(p, p, new StarRegenPower(p, 10), 10));
    }
}
