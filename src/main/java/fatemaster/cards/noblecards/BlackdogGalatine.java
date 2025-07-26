package fatemaster.cards.noblecards;

import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;
import fatemaster.powers.MaxHPPower;

public class BlackdogGalatine extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/BlackdogGalatine.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/BlackdogGalatine_p.png";
    public static final String ID = masterMod.makeId(BlackdogGalatine.class.getSimpleName());

    public BlackdogGalatine() {
        super(ID, IMG_PATH, CardType.ATTACK, CardTarget.ALL_ENEMY);
        this.baseDamage = 5;
        this.isMultiDamage = true;
        this.baseMagicNumber = 12;
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
            this.upgradeDamage(2);
            this.upgradeMagicNumber(4);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, true));
        for (int i = 0; i < 4; ++i) {
            this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
        }

        this.addToBot(new ApplyPowerAction(p, p, new MaxHPPower(p, this.magicNumber), this.magicNumber));
    }
}
