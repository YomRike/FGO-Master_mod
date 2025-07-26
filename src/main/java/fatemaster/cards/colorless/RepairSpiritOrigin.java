package fatemaster.cards.colorless;

import fatemaster.cards.fgoMasterBase;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class RepairSpiritOrigin extends fgoMasterBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/CommandSpellGuts.png";
    public static final String ID = masterMod.makeId(RepairSpiritOrigin.class.getSimpleName());
    private static final int COST = -2;

    public RepairSpiritOrigin() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        this.addToBot(new SFXAction(masterMod.makeId("CommandSpellSFX")));
        this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 30));
    }
}
