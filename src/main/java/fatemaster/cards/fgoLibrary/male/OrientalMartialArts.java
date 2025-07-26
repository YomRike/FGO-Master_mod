package fatemaster.cards.fgoLibrary.male;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.modifier.PretenderModifier;
import fatemaster.subscribers.PretenderSubscriber;

public class OrientalMartialArts extends fgoLibraryBase implements PretenderSubscriber {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/OrientalMartialArts.png";
    public static final String ID = masterMod.makeId(OrientalMartialArts.class.getSimpleName());
    private static final int COST = 1;

    public OrientalMartialArts() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 12;
        this.baseBlock = 6;
        CardModifierManager.addModifier(this, new PretenderModifier());
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeBlock(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void onPretender(AbstractCard card, AbstractPlayer p) {
        this.addToBot(new GainBlockAction(p, p, this.block));
    }
}
