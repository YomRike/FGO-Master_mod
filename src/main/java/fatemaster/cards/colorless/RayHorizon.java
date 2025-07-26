package fatemaster.cards.colorless;

import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.fgoMasterBase;
import fatemaster.cards.noblecards.HollowHeartAlbion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.ArchetypeORTPower;
import fatemaster.powers.EternalMemoriesTurnPower;
import fatemaster.powers.InvincibilityTurnPower;
import fatemaster.powers.NoblePhantasmCardPower;

public class RayHorizon extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/RayHorizon.png";
    public static final String ID = masterMod.makeId(RayHorizon.class.getSimpleName());
    private static final int COST = 0;

    public RayHorizon() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 50;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new HollowHeartAlbion();
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(50);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FgoNpAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new NoblePhantasmCardPower(p, this.cardsToPreview)));
        if (p.hasPower(ArchetypeORTPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new EternalMemoriesTurnPower(p, 1), 1));
        } else {
            this.addToBot(new ApplyPowerAction(p, p, new InvincibilityTurnPower(p, 1), 1));
        }
    }
}
