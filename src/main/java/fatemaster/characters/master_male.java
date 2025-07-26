package fatemaster.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import fatemaster.Enum.AbstractCardEnum;
import fatemaster.Enum.ThmodClassEnum;
import fatemaster.cards.colorless.BlackBarrel;
import fatemaster.cards.colorless.WallOfSnowflakes;
import fatemaster.cards.fgoLibrary.*;
import fatemaster.cards.fgoNormal.*;
import fatemaster.masterMod;
import fatemaster.relics.UnregisteredSpiritOrigin;

import java.util.ArrayList;
import java.util.Map;

public class master_male extends CustomPlayer {
    //返回一个颜色
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    private static final String[] ORB_TEXTURES = new String[]{
            "fgoMasterResources/images/UI_Master/EPanel/layer5.png",
            "fgoMasterResources/images/UI_Master/EPanel/layer4.png",
            "fgoMasterResources/images/UI_Master/EPanel/layer3.png",
            "fgoMasterResources/images/UI_Master/EPanel/layer2.png",
            "fgoMasterResources/images/UI_Master/EPanel/layer1.png",
            "fgoMasterResources/images/UI_Master/EPanel/layer0.png",
            "fgoMasterResources/images/UI_Master/EPanel/layer5d.png",
            "fgoMasterResources/images/UI_Master/EPanel/layer4d.png",
            "fgoMasterResources/images/UI_Master/EPanel/layer3d.png",
            "fgoMasterResources/images/UI_Master/EPanel/layer2d.png",
            "fgoMasterResources/images/UI_Master/EPanel/layer1d.png"};
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(masterMod.makeId("SpireHeartMaleText")).TEXT;

    public master_male(String name) {
        //构造方法，初始化参数
        super(name, ThmodClassEnum.FGO_MASTER_MALE_CLASS, ORB_TEXTURES,
                "fgoMasterResources/images/UI_Master/energyBlueVFX.png", LAYER_SPEED, null, null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;

        initializeClass(
                "fgoMasterResources/images/char_Master/fgo_master_male.png",
                "fgoMasterResources/images/char_Master/shoulder2.png",
                "fgoMasterResources/images/char_Master/shoulder1.png",
                "fgoMasterResources/images/char_Master/fallen.png",
                getLoadout(),
                0.0F, 5.0F,
                240.0F, 300.0F,
                new EnergyManager(3)
        );
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        //添加初始卡组
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike_Master.ID);
        retVal.add(Strike_Master.ID);
        retVal.add(Strike_Master.ID);
        retVal.add(Strike_Master.ID);
        retVal.add(Defend_Master.ID);
        retVal.add(Defend_Master.ID);
        retVal.add(Defend_Master.ID);
        retVal.add(Defend_Master.ID);
        retVal.add(BlackBarrel.ID);
        retVal.add(WallOfSnowflakes.ID);
        //retVal.add(CharismaOfHope.ID);

        /*retVal.add(EternalMemories.ID);
        retVal.add(BeautifulJourney.ID);
        retVal.add(Failnaught.ID);*/
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(UnregisteredSpiritOrigin.ID);
        UnlockTracker.markRelicAsSeen(UnregisteredSpiritOrigin.ID);
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        //选人物界面的文字描述
        return new CharSelectInfo(
                TEXT[1],
                TEXT[2],
                72,
                72,
                0,
                99,
                5,
                this,
                getStartingRelics(),
                getStartingDeck(),
                false
        );
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return TEXT[3];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        //选择卡牌颜色
        return AbstractCardEnum.Master_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return SILVER;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new DreamUponTheStars();
    }

    @Override
    public Color getCardTrailColor() {
        return SILVER;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 6;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playV(masterMod.makeId("MASTER_CHOOSE"), 0.8F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        return TEXT[4];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new master_male(this.name);
    }

    @Override
    public Color getSlashAttackColor() {
        return SILVER;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    @Override
    public String getVampireText() {
        return TEXT[5];
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[0];
    }

    @Override
    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
    }

    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel("fgoMasterResources/images/char_Master/Victory1.png"));
        panels.add(new CutscenePanel("fgoMasterResources/images/char_Master/Victory2.png"));
        panels.add(new CutscenePanel("fgoMasterResources/images/char_Master/Victory3.png"));
        return panels;
    }

    @Override
    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
        ArrayList<AbstractCard> cardPool = super.getCardPool(tmpPool);
        cardPool.removeIf(c -> c.type == AbstractCard.CardType.STATUS || c.rarity == AbstractCard.CardRarity.SPECIAL);

        for (Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (card.color.equals(AbstractCardEnum.Other_COLOR) && card.rarity != AbstractCard.CardRarity.BASIC && (!UnlockTracker.isCardLocked(c.getKey()) || Settings.isDailyRun)) {
                tmpPool.add(card);
            }
        }

        //宝具牌体系。
        tmpPool.remove(CardLibrary.getCard(CharismaOfHope.ID));
        tmpPool.remove(CardLibrary.getCard(SwordOfSelection.ID));
        tmpPool.remove(CardLibrary.getCard(YinYangFish.ID));
        tmpPool.remove(CardLibrary.getCard(VoidSpaceFineArts.ID));
        tmpPool.remove(CardLibrary.getCard(GraceUnexpectedBirth.ID));
        tmpPool.remove(CardLibrary.getCard(SongOfThePoet.ID));
        tmpPool.remove(CardLibrary.getCard(AnimalDialogue.ID));
        tmpPool.remove(CardLibrary.getCard(GrailOfFieryHeavens.ID));
        tmpPool.remove(CardLibrary.getCard(KnightoftheLake.ID));
        tmpPool.remove(CardLibrary.getCard(AbyssLight.ID));
        tmpPool.remove(CardLibrary.getCard(Insanity.ID));
        tmpPool.remove(CardLibrary.getCard(Borrowingfrom.ID));
        tmpPool.remove(CardLibrary.getCard(DoppelgangerMaster.ID));
        tmpPool.remove(CardLibrary.getCard(EndOfADream.ID));
        tmpPool.remove(CardLibrary.getCard(BlessingOfKur.ID));
        tmpPool.remove(CardLibrary.getCard(TeslaCoil.ID));
        tmpPool.remove(CardLibrary.getCard(KnightStance.ID));
        tmpPool.remove(CardLibrary.getCard(WildRule.ID));
        tmpPool.remove(CardLibrary.getCard(ComeOn.ID));

        //非宝具牌体系
        tmpPool.remove(CardLibrary.getCard(JourneyGuidance.ID));
        tmpPool.remove(CardLibrary.getCard(AtTheWell.ID));
        tmpPool.remove(CardLibrary.getCard(WindsweptSlash.ID));
        tmpPool.remove(CardLibrary.getCard(HolyShroud.ID));
        tmpPool.remove(CardLibrary.getCard(StarHunter.ID));
        tmpPool.remove(CardLibrary.getCard(StarPioneer.ID));
        tmpPool.remove(CardLibrary.getCard(CharismaOfTheJade.ID));
        tmpPool.remove(CardLibrary.getCard(GoldenGrail.ID));
        tmpPool.remove(CardLibrary.getCard(CrownedWithLife.ID));
        tmpPool.remove(CardLibrary.getCard(TeachingsoftheEternal.ID));
        tmpPool.remove(CardLibrary.getCard(GloriousStrike.ID));
        tmpPool.remove(CardLibrary.getCard(Swingby.ID));
        tmpPool.remove(CardLibrary.getCard(TakeRomance.ID));
        tmpPool.remove(CardLibrary.getCard(LivingFlame.ID));
        tmpPool.remove(CardLibrary.getCard(QueensCovenant.ID));

        tmpPool.remove(CardLibrary.getCard(MerlinMagic.ID));
        tmpPool.remove(CardLibrary.getCard(BlessedScion.ID));
        tmpPool.remove(CardLibrary.getCard(ChildrenSHero.ID));
        tmpPool.remove(CardLibrary.getCard(TheOneWhoSawItAll.ID));
        tmpPool.remove(CardLibrary.getCard(ItsInevitable.ID));

        tmpPool.remove(CardLibrary.getCard(AthnGabla.ID));
        tmpPool.remove(CardLibrary.getCard(BloomInSpring.ID));
        tmpPool.remove(CardLibrary.getCard(PrimevalRune.ID));
        tmpPool.remove(CardLibrary.getCard(DeathOfDeath.ID));
        tmpPool.remove(CardLibrary.getCard(UndeadBird.ID));
        tmpPool.remove(CardLibrary.getCard(ReplicaAgateram.ID));
        tmpPool.remove(CardLibrary.getCard(MysticEyes.ID));

        /*tmpPool.add(CardLibrary.getCard(TrueName72.ID));
        tmpPool.add(CardLibrary.getCard(CircuitConnect.ID));
        tmpPool.add(CardLibrary.getCard(Revelation.ID));
        tmpPool.add(CardLibrary.getCard(HeroEli.ID));*/

        /*tmpPool.add(CardLibrary.getCard(Barrage.ID));
        tmpPool.add(CardLibrary.getCard(Consume.ID));
        tmpPool.add(CardLibrary.getCard(Buffer.ID));
        tmpPool.add(CardLibrary.getCard(Recycle.ID));
        tmpPool.add(CardLibrary.getCard(Capacitor.ID));
        tmpPool.add(CardLibrary.getCard(Coolheaded.ID));
        tmpPool.add(CardLibrary.getCard(BallLightning.ID));
        tmpPool.add(CardLibrary.getCard(Seek.ID));
        tmpPool.add(CardLibrary.getCard(Defragment.ID));
        tmpPool.add(CardLibrary.getCard(Loop.ID));
        tmpPool.add(CardLibrary.getCard(MeteorStrike.ID));
        tmpPool.add(CardLibrary.getCard(Claw.ID));*/

        return cardPool;
    }
}
