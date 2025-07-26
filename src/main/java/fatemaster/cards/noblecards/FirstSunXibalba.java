package fatemaster.cards.noblecards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.cards.AbsNoblePhantasmCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fatemaster.masterMod;
import fatemaster.util.RenderImageLayer;
import fatemaster.util.TextureLoader;

public class FirstSunXibalba extends AbsNoblePhantasmCard {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_noble/FirstSunXibalba.png";
    public static final String IMG_PATH_P = "fgoMasterResources/images/cards_noble/FirstSunXibalba_p.png";
    public static final String ID = masterMod.makeId(FirstSunXibalba.class.getSimpleName());

    public FirstSunXibalba() {
        super(ID, IMG_PATH, 0, CardType.POWER, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(30);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLD, true));
                AbstractDungeon.topLevelEffectsQueue.add(new TimeWarpTurnEndEffect());

                p.applyStartOfTurnPowers();
                p.applyTurnPowers();
                p.applyStartOfTurnPostDrawPowers();
                p.applyEndOfTurnTriggers();
                //++GameActionManager.turn;
                this.isDone = true;
            }
        });

        //this.addToBot(new VFXAction(new SmokeBombEffect(p.hb.cX, p.hb.cY)));
        this.addToBot(new FgoNpAction(this.magicNumber));
    }
}
