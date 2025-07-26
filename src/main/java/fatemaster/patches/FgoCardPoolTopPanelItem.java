package fatemaster.patches;

import basemod.ReflectionHacks;
import basemod.TopPanelItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputAction;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.MasterDeckViewScreen;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import fatemaster.Enum.ThmodClassEnum;
import fatemaster.cards.colorless.BlackBarrel;
import fatemaster.cards.colorless.WallOfSnowflakes;
import fatemaster.cards.fgoNormal.Defend_Master;
import fatemaster.cards.fgoNormal.Strike_Master;
import fatemaster.masterMod;
import javassist.CtBehavior;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FgoCardPoolTopPanelItem extends TopPanelItem {
    public static final String ID = masterMod.makeId("FgoCardPoolTopPanelItem");
    private static final float tipYpos = Settings.HEIGHT - 120.0F * Settings.scale;
    private static final Texture IMAGE = ImageMaster.loadImage("fgoMasterResources/images/UI_Master/NobleTopPanel.png");
    private static final UIStrings STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    private static final String[] TEXT = STRINGS.TEXT;
    private static final Set<AbstractDungeon.CurrentScreen> validScreens = new HashSet<>();
    private static final CardGroup poolGroup = new CardGroup(CardGroup.CardGroupType.MASTER_DECK);
    public static boolean open = false;

    static {
        validScreens.add(AbstractDungeon.CurrentScreen.COMBAT_REWARD);
        validScreens.add(AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW);
        validScreens.add(AbstractDungeon.CurrentScreen.DEATH);
        validScreens.add(AbstractDungeon.CurrentScreen.BOSS_REWARD);
        validScreens.add(AbstractDungeon.CurrentScreen.SHOP);
        validScreens.add(AbstractDungeon.CurrentScreen.MAP);
    }

    public float flashTimer;

    public FgoCardPoolTopPanelItem() {
        super(IMAGE, ID);
    }

    @Override
    public boolean isClickable() {
        if (!AbstractDungeon.player.chosenClass.equals(ThmodClassEnum.FGO_MASTER_MALE_CLASS)) {
            return false;
        }
        return (!AbstractDungeon.isScreenUp || (validScreens.contains(AbstractDungeon.screen) && AbstractDungeon.previousScreen != AbstractDungeon.CurrentScreen.GRID && AbstractDungeon.previousScreen != AbstractDungeon.CurrentScreen.BOSS_REWARD) || open);
    }

    @Override
    public void update() {
        updateFlash();
        super.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        if (AbstractDungeon.player.chosenClass.equals(ThmodClassEnum.FGO_MASTER_MALE_CLASS)) {
            boolean ic = isClickable();
            render(sb, ic ? Color.WHITE : Color.DARK_GRAY);
            renderFlash(sb);
            renderHover();
        }
    }

    @Override
    protected void onClick() {
        if (isClickable()) {
            if (open && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                AbstractDungeon.closeCurrentScreen();
                CardCrawlGame.sound.play("DECK_CLOSE", 0.05F);
                open = false;
            } else {
                if (!AbstractDungeon.isScreenUp) {
                    open();
                    return;
                }
                switch (AbstractDungeon.screen) {
                    case COMBAT_REWARD:
                        AbstractDungeon.closeCurrentScreen();
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
                        open();
                        break;
                    case MASTER_DECK_VIEW:
                        AbstractDungeon.closeCurrentScreen();
                        open();
                        break;
                    case DEATH:
                        AbstractDungeon.deathScreen.hide();
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
                        open();
                        break;
                    case BOSS_REWARD:
                        AbstractDungeon.bossRelicScreen.hide();
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
                        open();
                        break;
                    case SHOP:
                        AbstractDungeon.overlayMenu.cancelButton.hide();
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
                        open();
                        break;
                    case MAP:
                        if (AbstractDungeon.dungeonMapScreen.dismissable) {
                            if (AbstractDungeon.previousScreen != null)
                                AbstractDungeon.screenSwap = true;
                            AbstractDungeon.closeCurrentScreen();
                        } else {
                            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
                        }
                        open();
                        break;
                }
            }
        }
    }

    private void open() {
        poolGroup.clear();
        poolGroup.addToTop(new Strike_Master());
        poolGroup.addToTop(new Defend_Master());
        poolGroup.addToTop(new WallOfSnowflakes());
        poolGroup.addToTop(new BlackBarrel());
        for (AbstractCard card : AbstractDungeon.commonCardPool.group) {
            poolGroup.addToTop(card);
        }
        for (AbstractCard card : AbstractDungeon.uncommonCardPool.group) {
            poolGroup.addToTop(card);
        }
        for (AbstractCard card : AbstractDungeon.rareCardPool.group) {
            poolGroup.addToTop(card);
        }

        open = true;
        CardCrawlGame.sound.play("RELIC_DROP_MAGICAL");
        AbstractDungeon.deckViewScreen.open();
    }

    public void flash() {
        this.flashTimer = 2.0F;
    }

    private void updateFlash() {
        if (this.flashTimer != 0.0F) {
            this.flashTimer -= Gdx.graphics.getDeltaTime();
        }
    }

    public void renderHover() {
        if ((getHitbox()).hovered) {
            TipHelper.renderGenericTip((getHitbox()).x - 120.0F * Settings.scale, tipYpos, TEXT[0], TEXT[1]);
        }
    }

    public void renderFlash(SpriteBatch sb) {
        float tmp = Interpolation.exp10In.apply(0.0F, 4.0F, this.flashTimer / 2.0F);
        sb.setBlendFunction(770, 1);
        sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.flashTimer * 2.0F));

        float halfWidth = this.image.getWidth() / 2.0F;
        float halfHeight = this.image.getHeight() / 2.0F;
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, this.image.getWidth(), this.image.getHeight(), Settings.scale + tmp, Settings.scale + tmp, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, this.image.getWidth(), this.image.getHeight(), Settings.scale + tmp * 0.66F, Settings.scale + tmp * 0.66F, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, this.image.getWidth(), this.image.getHeight(), Settings.scale + tmp / 3.0F, Settings.scale + tmp / 3.0F, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);

        sb.setBlendFunction(770, 771);
    }

    @SpirePatch(clz = TopPanel.class, method = "updateDeckViewButtonLogic")
    public static class DefinitelyNotViewingPool {
        @SpireInsertPatch(locator = Locator.class, localvars = {"clickedDeckButton"})
        public static void viewingDeck(TopPanel __instance, boolean clickedDeckButton) {
            if (clickedDeckButton) {
                if (FgoCardPoolTopPanelItem.open && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                    AbstractDungeon.closeCurrentScreen();
                }
                FgoCardPoolTopPanelItem.open = false;
            }
        }

        private static class Locator
                extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(InputAction.class, "isJustPressed");
                return LineFinder.findInOrder(ctMethodToPatch, methodCallMatcher);
            }
        }
    }


    @SpirePatch(clz = MasterDeckViewScreen.class, method = "updateControllerInput")
    public static class ControllerUseAlt {
        @SpireInsertPatch(locator = Locator.class, localvars = {"deck"})
        public static void viewAlt(MasterDeckViewScreen __instance, @ByRef(type = "com.megacrit.cardcrawl.cards.CardGroup") Object[] deck) {
            if (FgoCardPoolTopPanelItem.open) {
                deck[0] = FgoCardPoolTopPanelItem.poolGroup;
            }
        }

        private static class Locator
                extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(CardGroup.class, "group");
                return LineFinder.findInOrder(ctMethodToPatch, fieldAccessMatcher);
            }
        }
    }

    @SpirePatch(clz = MasterDeckViewScreen.class, method = "updatePositions")
    public static class UpdateAltPositions {
        @SpireInsertPatch(locator = Locator.class, localvars = {"cards"})
        public static void updateAlt(MasterDeckViewScreen __instance, @ByRef ArrayList<?>[] cards) {
            if (FgoCardPoolTopPanelItem.open) {
                cards[0] = FgoCardPoolTopPanelItem.poolGroup.group;
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "size");
                return LineFinder.findInOrder(ctMethodToPatch, methodCallMatcher);
            }
        }
    }

    @SpirePatch(clz = MasterDeckViewScreen.class, method = "updateClicking")
    public static class UpdateAltClick {
        @SpireInsertPatch(locator = Locator.class, localvars = {"hoveredCard", "clickStartedCard"})
        public static SpireReturn<?> openAlt(MasterDeckViewScreen __instance, AbstractCard hovered, @ByRef(type = "com.megacrit.cardcrawl.cards.AbstractCard") Object[] clickStartedCard) {
            if (FgoCardPoolTopPanelItem.open) {
                CardCrawlGame.cardPopup.open(hovered, FgoCardPoolTopPanelItem.poolGroup);
                clickStartedCard[0] = null;
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(SingleCardViewPopup.class, "open");
                return LineFinder.findInOrder(ctMethodToPatch, methodCallMatcher);
            }
        }
    }

    @SpirePatch(clz = MasterDeckViewScreen.class, method = "calculateScrollBounds")
    public static class CalcAltBounds {
        @SpirePrefixPatch
        public static SpireReturn<?> calcAlt(MasterDeckViewScreen __instance) {
            if (FgoCardPoolTopPanelItem.open) {
                if (FgoCardPoolTopPanelItem.poolGroup.size() > 10) {
                    int scrollTmp = FgoCardPoolTopPanelItem.poolGroup.size() / 5 - 2;
                    if (FgoCardPoolTopPanelItem.poolGroup.size() % 5 != 0) {
                        scrollTmp++;
                    }
                    ReflectionHacks.setPrivate(__instance, MasterDeckViewScreen.class, "scrollUpperBound", Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * (AbstractCard.IMG_HEIGHT * 0.75F + Settings.CARD_VIEW_PAD_Y));
                } else {
                    ReflectionHacks.setPrivate(__instance, MasterDeckViewScreen.class, "scrollUpperBound", Settings.DEFAULT_SCROLL_LIMIT);
                }
                ReflectionHacks.setPrivate(__instance, MasterDeckViewScreen.class, "prevDeckSize", FgoCardPoolTopPanelItem.poolGroup.size());
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = MasterDeckViewScreen.class, method = "hideCards")
    public static class HideAltCards {
        @SpireInsertPatch(locator = Locator.class, localvars = {"cards"})
        public static void hideAlt(MasterDeckViewScreen __instance, @ByRef ArrayList<?>[] cards) {
            if (FgoCardPoolTopPanelItem.open) {
                cards[0] = FgoCardPoolTopPanelItem.poolGroup.group;
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "size");
                return LineFinder.findInOrder(ctMethodToPatch, methodCallMatcher);
            }
        }
    }

    @SpirePatch(clz = MasterDeckViewScreen.class, method = "render")
    public static class RenderAlt {
        @SpirePrefixPatch
        public static SpireReturn<?> rendering(MasterDeckViewScreen __instance, SpriteBatch sb) {
            if (FgoCardPoolTopPanelItem.open) {
                AbstractCard hoveredCard = ReflectionHacks.getPrivate(__instance, MasterDeckViewScreen.class, "hoveredCard");
                if (hoveredCard == null) {
                    FgoCardPoolTopPanelItem.poolGroup.renderMasterDeck(sb);
                } else {
                    FgoCardPoolTopPanelItem.poolGroup.renderMasterDeckExceptOneCard(sb, hoveredCard);
                    hoveredCard.renderHoverShadow(sb);
                    hoveredCard.render(sb);
                }

                FgoCardPoolTopPanelItem.poolGroup.renderTip(sb);
                FontHelper.renderDeckViewTip(sb, FgoCardPoolTopPanelItem.TEXT[2], 96.0F * Settings.scale, Settings.CREAM_COLOR);
                if ((Float) ReflectionHacks.getPrivate(__instance, MasterDeckViewScreen.class, "scrollUpperBound") > 500.0F * Settings.scale) {
                    ((ScrollBar) ReflectionHacks.getPrivate(__instance, MasterDeckViewScreen.class, "scrollBar")).render(sb);
                }
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
