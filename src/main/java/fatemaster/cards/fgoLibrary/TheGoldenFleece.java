package fatemaster.cards.fgoLibrary;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class TheGoldenFleece extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TheGoldenFleece.png";
    public static final String ID = masterMod.makeId(TheGoldenFleece.class.getSimpleName());
    private static final int COST = -2;

    public TheGoldenFleece() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 175;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(75);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void onRemoveFromMasterDeck() {
        AbstractDungeon.player.gainGold(this.magicNumber);
        CardCrawlGame.sound.play("GOLD_GAIN");
    }
}
