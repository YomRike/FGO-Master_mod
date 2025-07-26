package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class AthnGabla extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/AthnGabla.png";
    public static final String ID = masterMod.makeId(AthnGabla.class.getSimpleName());
    private static final int COST = 2;
    public AthnGabla() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, p.maxHealth - p.currentHealth), p.maxHealth - p.currentHealth));
    }
}
