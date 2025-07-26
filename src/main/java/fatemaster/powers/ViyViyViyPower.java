package fatemaster.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import fatemaster.masterMod;

public class ViyViyViyPower extends PowerBase {
    public static String POWER_ID = masterMod.makeId(ViyViyViyPower.class.getSimpleName());
    private static final String[] DESCRIPTIONS = (getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private static int IdOffset;
    private final AbstractRelic r;
    private final AbstractPlayer p;
    private final String rName;

    public ViyViyViyPower(AbstractCreature owner, AbstractRelic r) {
        super(POWER_ID + IdOffset, (getPowerStrings(POWER_ID)).NAME);
        IdOffset++;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.r = r;
        this.p = AbstractDungeon.player;
        this.rName = r.name;
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, r);
        r.atBattleStart();

        String path128 = "fgoMasterResources/images/powers_Master/ConcentrateSpellsPower84.png";
        String path48 = "fgoMasterResources/images/powers_Master/ConcentrateSpellsPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void onVictory() {
        this.p.loseRelic(this.r.relicId);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.rName + DESCRIPTIONS[1];
    }
}