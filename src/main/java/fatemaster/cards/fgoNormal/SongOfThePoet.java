package fatemaster.cards.fgoNormal;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fatemaster.Action.RelicRapidFireAction;
import fatemaster.Action.SongOfThePoetAction;
import fatemaster.cards.fgoNormalCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;

public class SongOfThePoet extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/SongOfThePoet.png";
    public static final String ID = masterMod.makeId(SongOfThePoet.class.getSimpleName());
    private static final int COST = 1;

    public SongOfThePoet() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 6;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.relics.isEmpty()) {
            this.addToBot(new RelicRapidFireAction(m, p.relics.get(AbstractDungeon.cardRandomRng.random(p.relics.size() - 1))));
        }

        this.addToBot(new SongOfThePoetAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }
}
