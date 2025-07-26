package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;
import fatemaster.powers.InsanityPower;
import fatemaster.Enum.CardTagsEnum;

public class Insanity extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/Insanity.png";
    public static final String ID = masterMod.makeId(Insanity.class.getSimpleName());
    private static final int COST = 1;

    public Insanity() {
        super(ID, IMG_PATH, COST, CardType.POWER, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagsEnum.Foreigner);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new InsanityPower(p, this.magicNumber), this.magicNumber));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(CursePower.POWER_ID)) {
                int CurseAmt = 0;
                CurseAmt += (mo.getPower(CursePower.POWER_ID)).amount;
                this.addToBot(new ApplyPowerAction(p, p, new CursePower(p, CurseAmt), CurseAmt));
                this.addToBot(new RemoveSpecificPowerAction(mo, p, CursePower.POWER_ID));
            }
        }

    }
}
