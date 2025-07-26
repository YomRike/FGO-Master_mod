package fatemaster.cards.noblecards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.vfx.combat.ThirdEyeEffect;
import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;

public class Failnaught extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/Failnaught.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/Failnaught_p.png";
    public static final String ID = masterMod.makeId(Failnaught.class.getSimpleName());

    public Failnaught() {
        super(ID, IMG_PATH, CardType.ATTACK, CardTarget.ENEMY);
        this.baseDamage = 32;
        this.tags.add(CardTagsEnum.Round_Table_Knight);
        this.exhaust = true;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(8);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RemoveAllBlockAction(m, p));
        if (m.hasPower(ArtifactPower.POWER_ID)) {
            this.addToBot(new RemoveSpecificPowerAction(m, p, ArtifactPower.POWER_ID));
        }
        this.addToBot(new VFXAction(new ThirdEyeEffect(m.hb.cX, m.hb.cY)));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
}
