package fatemaster.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fatemaster.cards.fgoNormal.*;
import fatemaster.cards.noblecards.*;
import fatemaster.relics.ArchetypeORT;

public class AlterOptionEffect extends AbstractGameEffect {
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString("fatemaster:AlterOptionEffect").TEXT;
    private final Color screenColor;
    private boolean openedScreen = false;

    public AlterOptionEffect() {
        this.screenColor = AbstractDungeon.fadeColor.cpy();
        this.duration = 1.5F;
        this.screenColor.a = 0.0F;
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    @Override
    public void update() {
        if (!AbstractDungeon.isScreenUp) {
            this.duration -= Gdx.graphics.getDeltaTime();
            this.updateBlackScreenColor();
        }

        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F + 190.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
            AlterOption.usedIdentify = true;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            ((RestRoom) AbstractDungeon.getCurrRoom()).fadeIn();
        }

        if (this.duration < 1.0F && !this.openedScreen) {
            this.openedScreen = true;

            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            addCardIfNotInDeck(group, new ImitationGodForce());
            addCardIfNotInDeck(group, new BridalSpinWheel());
            addCardIfNotInDeck(group, new IraLupus());
            addCardIfNotInDeck(group, new LostLonginus());
            addCardIfNotInDeck(group, new WickerMan());
            addCardIfNotInDeck(group, new ExcaliburGalatine());
            addCardIfNotInDeck(group, new Overload());
            addCardIfNotInDeck(group, new GardenOfAvalon());
            addCardIfNotInDeck(group, new ElementaryMyDear());
            addCardIfNotInDeck(group, new GreatRamNautilus());
            addCardIfNotInDeck(group, new LaPucelle());
            addCardIfNotInDeck(group, new FirstSunXibalba());
            addCardIfNotInDeck(group, new ParadisChateaudIf());
            addCardIfNotInDeck(group, new YewBow());
            addCardIfNotInDeck(group, new SecondLife());
            addCardIfNotInDeck(group, new LausSaintClaudius());

            if (AbstractDungeon.actNum >= 3) {
                addCardIfNotInDeck(group, new LieLikeVortigern());
                addCardIfNotInDeck(group, new FetchFailnaught());
                addCardIfNotInDeck(group, new Sevendrive());
            }

            //如果你有龙之炉心。
            if (CardHelper.hasCardWithID(DragonCore.ID)) {
                addCardIfNotInDeck(group, new Excalibur());
            }
            //如果你没有羽翼之靴。
            if (!AbstractDungeon.player.hasRelic(WingBoots.ID) && AbstractDungeon.actNum >= 2) {
                addCardIfNotInDeck(group, new EnferChateaudIf());
            }
            //如果你有虚数美术、黄房子或澪标之魂。
            if (CardHelper.hasCardWithID(VoidSpaceFineArts.ID) || CardHelper.hasCardWithID(TheYellowHouse.ID) || CardHelper.hasCardWithID(Insanity.ID)) {
                addCardIfNotInDeck(group, new Desterrennacht());
            }
            //如果你有草莓、梨子、芒果、李家华夫饼或冰淇淋。
            if (AbstractDungeon.player.hasRelic(Strawberry.ID) || AbstractDungeon.player.hasRelic(Waffle.ID) || AbstractDungeon.player.hasRelic(Mango.ID) || AbstractDungeon.player.hasRelic(Pear.ID) || AbstractDungeon.player.hasRelic(IceCream.ID)) {
                addCardIfNotInDeck(group, new Fragarach());
            }
            //如果你有Archetype ORT。
            if (AbstractDungeon.player.hasRelic(ArchetypeORT.ID)) {
                addCardIfNotInDeck(group, new LastSunXibalba());
            }
            //如果你有灵体外质、天鹅绒颈圈或添水。
            if (AbstractDungeon.player.hasRelic(Ectoplasm.ID) || AbstractDungeon.player.hasRelic(VelvetChoker.ID) || AbstractDungeon.player.hasRelic(Sozu.ID)) {
                addCardIfNotInDeck(group, new MahaPralaya());
            }
            //如果你有冥界佑护。
            if (CardHelper.hasCardWithID(BlessingOfKur.ID)) {
                addCardIfNotInDeck(group, new KurKigalIrkalla());
            }
            //如果你有旅途的引导或鹦鹉螺的大冲角。
            if (CardHelper.hasCardWithID(JourneyGuidance.ID) || AbstractDungeon.player.hasRelic(GreatRamNautilus.ID)) {
                addCardIfNotInDeck(group, new SarasvatiMeltout());
            }
            //如果你有十二辉剑。
            if (CardHelper.hasCardWithID(HeroicKing.ID)) {
                addCardIfNotInDeck(group, new JoyeuseOrdre());
            }
            //如果你有见证一切之人。
            if (CardHelper.hasCardWithID(TheOneWhoSawItAll.ID)) {
                addCardIfNotInDeck(group, new EnumaElish());
            }
            //如果你有见证一切之人。
            if (CardHelper.hasCardWithID(GoldenGrail.ID)) {
                addCardIfNotInDeck(group, new SevenBeastCrowns());
            }

            if (group.isEmpty()) {
                this.isDone = true;
                AbstractRoom.waitTimer = 0.0F;
                (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                ((RestRoom) AbstractDungeon.getCurrRoom()).cutFireSound();
            } else {
                AbstractDungeon.gridSelectScreen.open(group, 1, TEXT[0], false, false, false, false);
            }
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
            if (CampfireUI.hidden) {
                AbstractRoom.waitTimer = 0.0F;
                if (AbstractDungeon.player.hasRelic(FusionHammer.ID) && AbstractDungeon.player.hasRelic(CoffeeDripper.ID)) {
                    AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                } else {
                    ((RestRoom) AbstractDungeon.getCurrRoom()).campfireUI.reopen();
                }
                ((RestRoom) AbstractDungeon.getCurrRoom()).cutFireSound();
            }
        }

    }

    private boolean isCardInMasterDeck(String cardID) {
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.cardID.equals(cardID)) {
                return true;
            }
        }
        return false;
    }

    private void addCardIfNotInDeck(CardGroup group, AbstractCard card) {
        if (!isCardInMasterDeck(card.cardID)) {
            group.addToBottom(card);
        }
    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
        } else {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float) Settings.WIDTH, (float) Settings.HEIGHT);
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
            AbstractDungeon.gridSelectScreen.render(sb);
        }

    }

    @Override
    public void dispose() {
    }
}