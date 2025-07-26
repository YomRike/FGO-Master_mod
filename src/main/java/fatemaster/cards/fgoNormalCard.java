package fatemaster.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import fatemaster.Enum.AbstractCardEnum;

public abstract class fgoNormalCard extends fgoMasterBase {
    public fgoNormalCard(String id, String img, int cost, AbstractCard.CardType type, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, img, cost, type, AbstractCardEnum.Master_COLOR, rarity, target);
    }
}
