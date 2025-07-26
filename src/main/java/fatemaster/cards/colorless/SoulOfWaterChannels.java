package fatemaster.cards.colorless;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;
import fatemaster.Enum.CardTagsEnum;

public class SoulOfWaterChannels extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/SoulOfWaterChannels.png";
    public static final String ID = masterMod.makeId(SoulOfWaterChannels.class.getSimpleName());
    private static final int COST = 0;

    public SoulOfWaterChannels() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.selfRetain = true;
        this.tags.add(CardTagsEnum.Foreigner);
        this.portraitImg = ImageMaster.loadImage(IMG_PATH);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new CursePower(p, 1), 1));

        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.1F));
        } else {
            this.addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.5F));
        }

        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber), this.magicNumber));
    }
}
