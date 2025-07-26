package fatemaster.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class PowerBase extends AbstractPower {
    public PowerBase(String id, String name) {
        this.ID = id;
        this.name = name;
    }

    protected static PowerStrings getPowerStrings(String id) {
        return CardCrawlGame.languagePack.getPowerStrings(id);
    }
}
