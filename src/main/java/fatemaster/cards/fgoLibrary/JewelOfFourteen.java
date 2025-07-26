package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import fatemaster.cards.fgoLibraryBase;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.powers.BurnDamagePower;

public class JewelOfFourteen extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/JewelOfFourteen.png";
    public static final String ID = masterMod.makeId(JewelOfFourteen.class.getSimpleName());
    private static final int COST = 2;

    public JewelOfFourteen() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = 7;
        this.magicNumber = this.baseMagicNumber;
        //this.exhaust = true;
        //this.cardsToPreview = new FlamesofApplause();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            /*this.cardsToPreview.upgrade();
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();*/
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new JewelOfFourteenAction(this.upgraded, 10));
        this.addToBot(new ApplyPowerAction(m, p, new BurnDamagePower(m, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (m.hasPower(BurnDamagePower.POWER_ID)) {
                    this.addToBot(new LoseHPAction(m, p, m.getPower(BurnDamagePower.POWER_ID).amount, AbstractGameAction.AttackEffect.FIRE));
                }
                this.isDone = true;
            }
        });
    }
}
