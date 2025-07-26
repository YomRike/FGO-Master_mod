package fatemaster.cards.noblecards;

import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.DefenseDownPower;

public class Overload extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/Overload.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/Overload_p.png";
    public static final String ID = masterMod.makeId(Overload.class.getSimpleName());

    public Overload() {
        super(ID, IMG_PATH, CardType.ATTACK, CardTarget.ENEMY);
        this.baseDamage = 34;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagsEnum.Round_Table_Knight);
        this.exhaust = true;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(9);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RemoveAllBlockAction(m, p));
        this.addToBot(new DamageAction(m, new DamageInfo(m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ApplyPowerAction(m, p, new DefenseDownPower(m, this.magicNumber), this.magicNumber));
    }
}
