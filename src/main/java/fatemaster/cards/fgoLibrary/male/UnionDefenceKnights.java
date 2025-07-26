package fatemaster.cards.fgoLibrary.male;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;

public class UnionDefenceKnights extends fgoLibraryBase {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/UnionDefenceKnights.png";
    public static final String ID = masterMod.makeId(UnionDefenceKnights.class.getSimpleName());
    private static final int COST = 2;

    public UnionDefenceKnights() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 15;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : p.hand.group) {
                    if ((c.rarity == CardRarity.BASIC || c.rarity == CardRarity.COMMON) && !c.isEthereal) {
                        c.superFlash();
                        c.upgrade();
                        c.retain = true;
                    }
                }
                this.isDone = true;
            }
        });
    }
}
