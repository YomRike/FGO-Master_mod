package fatemaster.cards.noblecards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.subscribers.PretenderSubscriber;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.EternalMemoriesPower;

public class EternalMemories extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/EternalMemories.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/EternalMemories_p.png";
    public static final String ID = masterMod.makeId(EternalMemories.class.getSimpleName());

    public EternalMemories() {
        super(ID, IMG_PATH, CardType.POWER, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagsEnum.Round_Table_Knight);

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
        //this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        this.addToBot(new RemoveDebuffsAction(p));
        this.addToBot(new ApplyPowerAction(p, p, new EternalMemoriesPower(p, this.magicNumber), this.magicNumber));
    }
}
