package fatemaster.cards.fgoNormal;

import fatemaster.cards.fgoNormalCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import fatemaster.masterMod;
import fatemaster.powers.CursePower;
import fatemaster.powers.DefenseDownPower;
import fatemaster.Enum.CardTagsEnum;

public class TheYellowHouse extends fgoNormalCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/TheYellowHouse.png";
    public static final String ID = masterMod.makeId(TheYellowHouse.class.getSimpleName());
    private static final int COST = 1;

    public TheYellowHouse() {
        super(ID, IMG_PATH, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 4;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.shuffleBackIntoDrawPile = true;
        this.tags.add(CardTagsEnum.Foreigner);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; ++i) {
            if (m != null) {
                this.addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.CYAN, Color.WHITE), 0.1F));
            }

            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
        this.addToBot(new ApplyPowerAction(m, p, new DefenseDownPower(m, 2), 2));
        this.addToBot(new ApplyPowerAction(p, p, new CursePower(p, 1), 1));
    }
}
