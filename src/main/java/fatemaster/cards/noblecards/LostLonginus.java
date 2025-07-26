package fatemaster.cards.noblecards;

import fatemaster.Enum.CardTagsEnum;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;

public class LostLonginus extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/LostLonginus.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/LostLonginus_p.png";
    public static final String ID = masterMod.makeId(LostLonginus.class.getSimpleName());

    public LostLonginus() {
        super(ID, IMG_PATH, CardType.ATTACK, CardTarget.ALL_ENEMY);
        this.baseDamage = 24;
        this.isMultiDamage = true;
        this.baseMagicNumber = 3;
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
            this.upgradeDamage(6);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        for (AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped()) {
                if (m2.hasPower("Invincible")) {
                    this.addToBot(new RemoveSpecificPowerAction(m2, p, "Invincible"));
                }
            }
        }
    }
}
