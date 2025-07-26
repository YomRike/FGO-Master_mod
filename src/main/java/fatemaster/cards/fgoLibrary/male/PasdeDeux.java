package fatemaster.cards.fgoLibrary.male;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import fatemaster.cards.fgoLibraryBase;
import fatemaster.masterMod;
import fatemaster.modifier.PretenderModifier;
import fatemaster.subscribers.PretenderSubscriber;

import java.util.ArrayList;
import java.util.Comparator;

public class PasdeDeux extends fgoLibraryBase implements PretenderSubscriber {
    public static final String IMG_PATH = "fgoMasterResources/images/cards_Master/PasdeDeux.png";
    public static final String ID = masterMod.makeId(PasdeDeux.class.getSimpleName());
    private static final int COST = -2;

    public PasdeDeux() {
        super(ID, IMG_PATH, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        CardModifierManager.addModifier(this, new PretenderModifier());
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            //CardModifierManager.removeSpecificModifier(this, new PretenderModifier(), true);
            this.rawDescription = (getCardStrings(ID)).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            this.cantUseMessage = (getCardStrings(ID)).EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

    @Override
    public void onPretender(AbstractCard card, AbstractPlayer p) {
        PasdeDeuxCard();
    }

    private void PasdeDeuxCard() {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> possibleCards = new ArrayList<>();

                for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if (c.canUpgrade()) {
                        possibleCards.add(c);
                    }
                }

                if (!possibleCards.isEmpty()) {
                    possibleCards.sort(Comparator.comparing(c -> c.rarity));

                    AbstractCard.CardRarity lowestRarity = possibleCards.get(0).rarity;
                    ArrayList<AbstractCard> lowestRarityCards = new ArrayList<>();
                    for (AbstractCard c : possibleCards) {
                        if (c.rarity == lowestRarity) {
                            lowestRarityCards.add(c);
                        }
                    }

                    AbstractCard theCard = lowestRarityCards.get(AbstractDungeon.miscRng.random(0, lowestRarityCards.size() - 1));
                    theCard.upgrade();
                    AbstractDungeon.player.bottledCardUpgradeCheck(theCard);
                    AbstractDungeon.effectsQueue.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                    AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(theCard.makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                    this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                }

                this.isDone = true;
            }
        });
        /*if (AbstractDungeon.player instanceof master_male) {
            AbstractDungeon.topLevelEffects.add(new FadeWipeParticle());
            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractDungeon.player.img = TextureLoader.getTexture("fgoMasterResources/images/char_Master/fgo_master_male_pasdeDeux.png");
                    this.isDone = true;
                }
            });
        }*/
    }
}
