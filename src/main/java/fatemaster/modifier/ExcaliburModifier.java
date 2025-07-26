package fatemaster.modifier;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import fatemaster.masterMod;

public class ExcaliburModifier extends AbstractCardModifier {
    public static String ID = masterMod.makeId("ExcaliburModifier");

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ExcaliburModifier();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}