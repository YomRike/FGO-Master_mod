package fatemaster;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.eventUtil.AddEventParams;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import fatemaster.Action.NP.FgoNpAction;
import fatemaster.Button.NoblePhantasmButton;
import fatemaster.Enum.AbstractCardEnum;
import fatemaster.Enum.CardTagsEnum;
import fatemaster.Enum.ThmodClassEnum;
import fatemaster.blight.fufu;
import fatemaster.cards.fgoMasterBase;
import fatemaster.characters.master;
import fatemaster.characters.master_male;
import fatemaster.effect.FateRemindEffect;
import fatemaster.event.*;
import fatemaster.monster.*;
import fatemaster.patches.FgoCardPoolTopPanelItem;
import fatemaster.potions.AngraMainyu;
import fatemaster.potions.ElixirofRejuvenation;
import fatemaster.potions.ExtremelySpicyMapoTofu;
import fatemaster.potions.StarPotion;
import fatemaster.powers.EvasionPower;
import fatemaster.powers.NPRatePower;
import fatemaster.relics.*;
import fatemaster.util.FgoMasterHelper;
import fatemaster.util.TextureLoader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.megacrit.cardcrawl.core.Settings.language;
import static fatemaster.Enum.ThmodClassEnum.FGO_MASTER_MALE_CLASS;
import static fatemaster.Enum.ThmodClassEnum.Master_CLASS;

@SpireInitializer
public class masterMod implements
        PostBattleSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        EditRelicsSubscriber,
        EditCardsSubscriber,
        EditStringsSubscriber,
        OnCardUseSubscriber,
        EditKeywordsSubscriber,
        AddAudioSubscriber,
        OnStartBattleSubscriber,
        OnPlayerDamagedSubscriber,
        PostDungeonUpdateSubscriber,
        StartGameSubscriber,
        PostDungeonInitializeSubscriber {

    public static final String CARD_ENERGY_ORB = "fgoMasterResources/images/UI_Master/energyOrb.png";
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    public static final Color NOBLE = CardHelper.getColor(255, 215, 0);
    //攻击、技能、能力牌的背景图片(512)
    private static final String Other_CC = "fgoMasterResources/images/512/bg_colorless_master_s.png";
    private static final String ENERGY_ORB_CC = "fgoMasterResources/images/512/masterOrb_s.png";
    //攻击、技能、能力牌的背景图片(1024)
    private static final String Other_CC_PORTRAIT = "fgoMasterResources/images/1024/bg_colorless_master.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "fgoMasterResources/images/1024/masterOrb.png";
    //宝具牌
    private static final String ATTACK_Noble = "fgoMasterResources/images/512/bg_empty_512.png";
    private static final String SKILL_Noble = "fgoMasterResources/images/512/bg_empty_512.png";
    private static final String POWER_Noble = "fgoMasterResources/images/512/bg_empty_512.png";
    //攻击、技能、能力牌的背景图片(1024)
    private static final String ATTACK_Noble_PORTRAIT = "fgoMasterResources/images/1024/bg_empty_1024.png";
    private static final String SKILL_Noble_PORTRAIT = "fgoMasterResources/images/1024/bg_empty_1024.png";
    private static final String POWER_Noble_PORTRAIT = "fgoMasterResources/images/1024/bg_empty_1024.png";
    //角色图标。
    private static final String FGO_MASTER_BUTTON = "fgoMasterResources/images/charSelect/MasterButton.png";
    private static final String FGO_MASTER_MALE_BUTTON = "fgoMasterResources/images/charSelect/MasterButton_02.png";
    //默认背景图片。
    private static final String MARISA_PORTRAIT = "fgoMasterResources/images/charSelect/MasterPortrait1.png";
    private static final String FGO_MASTER_MALE_PORTRAIT = "fgoMasterResources/images/charSelect/MasterPortrait3.png";
    public static SpireConfig saveData;
    public static boolean startCommandSpellEffect = false;
    public static boolean startUniformEffect = false;
    public static boolean roundTableEffect = false;
    public static NoblePhantasmButton noble;

    static {
        try {
            saveData = new SpireConfig("the fgo master", "fateGrandMod.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public masterMod() {
        //构造方法，初始化各种参数
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.Master_COLOR, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, Other_CC, Other_CC, Other_CC, ENERGY_ORB_CC, Other_CC_PORTRAIT, Other_CC_PORTRAIT, Other_CC_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
        BaseMod.addColor(AbstractCardEnum.Other_COLOR, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, Other_CC, Other_CC, Other_CC, ENERGY_ORB_CC, Other_CC_PORTRAIT, Other_CC_PORTRAIT, Other_CC_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
        BaseMod.addColor(AbstractCardEnum.Noble_Phantasm_COLOR, NOBLE, NOBLE, NOBLE, NOBLE, NOBLE, NOBLE, NOBLE, ATTACK_Noble, SKILL_Noble, POWER_Noble, ENERGY_ORB_CC, ATTACK_Noble_PORTRAIT, SKILL_Noble_PORTRAIT, POWER_Noble_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
    }

    public static String getImagePath(String file) {
        return "fgoMasterResources/images/" + file;
    }

    public static void initialize() {
        new masterMod();
    }

    public static String makeId(String id) {
        return "fatemaster:" + id;
    }

    @NotNull
    private static ModPanel getModPanel() {
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("fatemaster:startCommandSpellEffect");
        ModPanel settingsPanel = new ModPanel();
        ModLabeledToggleButton startCommandSpellEffectOption = new ModLabeledToggleButton(
                uiStrings.TEXT[0], 370.0F, 700.0F, Color.WHITE, FontHelper.buttonLabelFont, startCommandSpellEffect, settingsPanel,
                (label) -> {
                }, (me) -> {
            startCommandSpellEffect = me.enabled;
            saveData.setBool("startCommandSpellEffect", startCommandSpellEffect);
            try {
                saveData.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ModLabeledToggleButton startUniformOption = new ModLabeledToggleButton(
                uiStrings.TEXT[1], 370.0F, 630.0F, Color.WHITE, FontHelper.buttonLabelFont, startUniformEffect, settingsPanel,
                (label) -> {
                }, (me) -> {
            startUniformEffect = me.enabled;
            saveData.setBool("startUniformEffect", startUniformEffect);
            try {
                saveData.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ModLabeledToggleButton roundTableEffectOption = new ModLabeledToggleButton(
                uiStrings.TEXT[2], 370.0F, 560.0F, Color.WHITE, FontHelper.buttonLabelFont, roundTableEffect, settingsPanel,
                (label) -> {
                }, (me) -> {
            roundTableEffect = me.enabled;
            saveData.setBool("roundTableEffect", roundTableEffect);
            try {
                saveData.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        settingsPanel.addUIElement(startCommandSpellEffectOption);
        settingsPanel.addUIElement(startUniformOption);
        settingsPanel.addUIElement(roundTableEffectOption);
        return settingsPanel;
    }

    public static void renderCombatUiElements(SpriteBatch sb) {
        if (!AbstractDungeon.overlayMenu.combatDeckPanel.isHidden && noble != null && AbstractDungeon.player.chosenClass == ThmodClassEnum.Master_CLASS) {
            noble.render(sb);
        }
    }

    @Override
    public void receiveEditCharacters() {
        //添加角色到MOD中
        BaseMod.addCharacter(new master("Master"), FGO_MASTER_BUTTON, MARISA_PORTRAIT, Master_CLASS);
        BaseMod.addCharacter(new master_male("fgo_master_male"), FGO_MASTER_MALE_BUTTON, FGO_MASTER_MALE_PORTRAIT, FGO_MASTER_MALE_CLASS);
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd("the fgo master") // 这里填写你在ModTheSpire.json中写的modid
                .packageFilter(fgoMasterBase.class) // 寻找所有和此类同一个包及内部包的类（本例子是所有卡牌）
                .setDefaultSeen(true) // 是否将卡牌标为可见
                .cards(); // 开始批量添加卡牌
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = TextureLoader.getTexture(getImagePath("badge.png"));
        try {
            saveData.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (saveData.has("startCommandSpellEffect")) {
            startCommandSpellEffect = saveData.getBool("startCommandSpellEffect");
        }
        if (saveData.has("startUniformEffect")) {
            startUniformEffect = saveData.getBool("startUniformEffect");
        }
        if (saveData.has("roundTableEffect")) {
            roundTableEffect = saveData.getBool("roundTableEffect");
        }

        final ModPanel settingsPanel = getModPanel();
        BaseMod.registerModBadge(badgeTexture, "master", "Yom", "Porting master to latest version.", settingsPanel);

        master.fgoNp = 0;
        //顶部宝具牌预览。
        BaseMod.addTopPanelItem(new FgoCardPoolTopPanelItem());
        //药水。
        BaseMod.addPotion(ElixirofRejuvenation.class, Color.CYAN, Color.CYAN, null, ElixirofRejuvenation.ID, ThmodClassEnum.Master_CLASS);
        BaseMod.addPotion(ExtremelySpicyMapoTofu.class, Color.ORANGE, Color.RED, null, ExtremelySpicyMapoTofu.ID, ThmodClassEnum.Master_CLASS);
        BaseMod.addPotion(AngraMainyu.class, Color.PURPLE, Color.BLACK, null, AngraMainyu.ID, ThmodClassEnum.Master_CLASS);
        BaseMod.addPotion(StarPotion.class, Color.YELLOW, Color.ORANGE, null, StarPotion.ID, ThmodClassEnum.Master_CLASS);
        //事件。
        BaseMod.addEvent(masterMod.makeId("WinterEvent"), WinterEvent.class, TheCity.ID);
        //BaseMod.addEvent("FGOLibrary", FGOLibrary.class, "TheCity");
        BaseMod.addEvent(masterMod.makeId("ConflictEvent"), ConflictEvent.class, TheBeyond.ID);
        BaseMod.addEvent(masterMod.makeId("ProofAndRebuttalEvent"), ProofAndRebuttalEvent.class, Exordium.ID);
        BaseMod.addEvent(masterMod.makeId("ManofChaldea"), ManofChaldea.class, TheBeyond.ID);
        BaseMod.addEvent(masterMod.makeId("Beyondthe"), Beyondthe.class, TheBeyond.ID);
        BaseMod.addEvent(masterMod.makeId("DailyLifeattheBeyond"), DailyLifeattheBeyond.class, TheCity.ID);
        BaseMod.addEvent(masterMod.makeId("DevilSlot"), DevilSlot.class, TheBeyond.ID);
        //BaseMod.addEvent((new AddEventParams.Builder("FGOLibrary", FGOLibrary.class)).dungeonID("TheCity").playerClass(Master_CLASS).create());
        BaseMod.addEvent((new AddEventParams.Builder(masterMod.makeId("TestTheWaters"), TestTheWaters.class))
                .dungeonID(Exordium.ID)
                .playerClass(Master_CLASS)
                .create());
        BaseMod.addEvent((new AddEventParams.Builder(masterMod.makeId("TestTheWatersAct2"), TestTheWatersAct2.class))
                .dungeonID(TheCity.ID)
                .playerClass(Master_CLASS)
                .create());
        BaseMod.addEvent((new AddEventParams.Builder(masterMod.makeId("TestTheWatersAct3"), TestTheWatersAct3.class))
                .dungeonID(TheBeyond.ID)
                .playerClass(Master_CLASS)
                .create());

        BaseMod.addMonster(Moss.ID, Moss.NAME, () -> new MonsterGroup(new AbstractMonster[]{new Moss()}));
        BaseMod.addMonsterEncounter(Exordium.ID, new MonsterInfo(Moss.ID, 2));

        BaseMod.addMonster(FaerieKnightGawain.ID, FaerieKnightGawain.NAME, () -> new MonsterGroup(new AbstractMonster[]{new FaerieKnightGawain()}));
        BaseMod.addEliteEncounter(Exordium.ID, new MonsterInfo(FaerieKnightGawain.ID, 1));

        BaseMod.addMonster(FaerieKnightLancelot.ID, FaerieKnightLancelot.NAME, () -> new MonsterGroup(new AbstractMonster[]{new FaerieKnightLancelot()}));
        BaseMod.addEliteEncounter(Exordium.ID, new MonsterInfo(FaerieKnightLancelot.ID, 1));

        BaseMod.addMonster(Emiya.ID, Emiya.NAME, () -> new MonsterGroup(new AbstractMonster[]{new Emiya()}));
        BaseMod.addBoss(TheCity.ID, Emiya.ID,
                "fgoMasterResources/images/monster/map_emiya.png",
                "fgoMasterResources/images/monster/map_emiya_outline.png");

        BaseMod.addMonster(CalamityofNorwich.ID, CalamityofNorwich.NAME, () -> new MonsterGroup(new AbstractMonster[]{new CalamityofNorwich()}));
        BaseMod.addBoss(Exordium.ID, CalamityofNorwich.ID,
                "fgoMasterResources/images/monster/map_avalon_le_fae.png",
                "fgoMasterResources/images/monster/map_avalon_le_fae_outline.png");

        BaseMod.addMonster(QueenMorgan.ID, QueenMorgan.NAME, () -> new MonsterGroup(new AbstractMonster[]{new QueenMorgan()}));
        BaseMod.addBoss(TheCity.ID, QueenMorgan.ID,
                "fgoMasterResources/images/monster/map_avalon_le_fae.png",
                "fgoMasterResources/images/monster/map_avalon_le_fae_outline.png");

        BaseMod.addMonster(Cernunnos.ID, Cernunnos.NAME, () -> new MonsterGroup(new AbstractMonster[]{new Cernunnos()}));
        BaseMod.addBoss(TheBeyond.ID, Cernunnos.ID,
                "fgoMasterResources/images/monster/map_avalon_le_fae.png",
                "fgoMasterResources/images/monster/map_avalon_le_fae_outline.png");
    }

    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "_zh";
        }

        String json = Gdx.files.internal("fgoMasterResources/localization/fgo_keywords" + lang + ".json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("fgomaster", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "_zh";
        } else {
            lang = "";
        }

        BaseMod.loadCustomStringsFile(CardStrings.class, "fgoMasterResources/localization/fgo_cards" + lang + ".json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "fgoMasterResources/localization/fgo_powers" + lang + ".json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "fgoMasterResources/localization/fgo_relics" + lang + ".json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "fgoMasterResources/localization/fgo_ui" + lang + ".json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "fgoMasterResources/localization/fgo_event" + lang + ".json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, "fgoMasterResources/localization/fgo_Orb" + lang + ".json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "fgoMasterResources/localization/fgo_potion" + lang + ".json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, "fgoMasterResources/localization/fgo_monster" + lang + ".json");
        BaseMod.loadCustomStringsFile(TutorialStrings.class, "fgoMasterResources/localization/fgo_tutorial" + lang + ".json");
        BaseMod.loadCustomStringsFile(BlightStrings.class, "fgoMasterResources/localization/fgo_blight" + lang + ".json");
    }

    @Override
    public void receiveEditRelics() {
        //将自定义的遗物添加到这里
        BaseMod.addRelicToCustomPool(new CommandSpell(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new HalloweenRoyalty(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new MidsummerNightDream(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new SuitcaseFgo(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new Salem(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new LockChocolateStrawberry(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new ArchetypeORT(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new SkullCandy(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new MisoPotato(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new MechanicalProtector(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new DecisiveBattleUniform(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new AstrologicalTeapot(), AbstractCardEnum.Master_COLOR);

        BaseMod.addRelicToCustomPool(new Avenger(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new OblivionCorrection(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new SelfReplenishmentMagic(), AbstractCardEnum.Master_COLOR);
        BaseMod.addRelicToCustomPool(new BowInsignia(), AbstractCardEnum.Master_COLOR);

        BaseMod.addRelic(new SurpriseChocolate(), RelicType.SHARED);
        BaseMod.addRelic(new SaveStone(), RelicType.SHARED);
        BaseMod.addRelic(new BlessRockChoco(), RelicType.SHARED);
        BaseMod.addRelic(new PoisonApple(), RelicType.SHARED);
        BaseMod.addRelic(new FanNight(), RelicType.SHARED);
        BaseMod.addRelic(new BurgerKing(), RelicType.SHARED);
        BaseMod.addRelic(new DreamChip(), RelicType.SHARED);
        BaseMod.addRelic(new CosmicMedallion(), RelicType.SHARED);
        BaseMod.addRelic(new BB(), RelicType.SHARED);
        BaseMod.addRelic(new LockChocolate(), RelicType.SHARED);

        BaseMod.addRelicToCustomPool(new UnregisteredSpiritOrigin(), AbstractCardEnum.Master_COLOR);
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(makeId("MASTER_CHOOSE"), "fgoMasterResources/sound/MASTER_CHOOSE.mp3");
        BaseMod.addAudio("MASTER_INVICTUS_SPIRITUS", "fgoMasterResources/sound/MASTER_INVICTUS_SPIRITUS.mp3");
        BaseMod.addAudio("MASTER_CURSE", "fgoMasterResources/sound/MASTER_CURSE.wav");
        BaseMod.addAudio("DUMUZID", "fgoMasterResources/sound/Dumuzid.ogg");
        BaseMod.addAudio(makeId("GUN2"), "fgoMasterResources/sound/hermit_gun2.ogg");
        BaseMod.addAudio(makeId("NP_START_1"), "fgoMasterResources/sound/NP_START_1.wav");
        BaseMod.addAudio(makeId("CommandSpellSFX"), "fgoMasterResources/sound/CommandSpellSFX.wav");
    }

    @Override
    public void receiveCardUsed(AbstractCard card) {
        if (AbstractDungeon.player instanceof master) {
            int npGain = calculateNpGain(card);

            if (npGain > 0) {
                AbstractDungeon.actionManager.addToBottom(new FgoNpAction(npGain));
            }

            if (card.hasTag(CardTagsEnum.Noble_Phantasm)) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeId("NP_START_1")));
                AbstractDungeon.topLevelEffects.add(new FateRemindEffect(CardCrawlGame.languagePack.getCardStrings(card.cardID).NAME));
            }
        }
    }

    private int calculateNpGain(AbstractCard card) {
        if (card.costForTurn == 0) {
            return 0;
        }

        int npGain = card.costForTurn == -1 ? EnergyPanel.totalCount : card.costForTurn;
        int npMultiplier = getNpMultiplier(master.fgoNp);

        if (AbstractDungeon.player.hasPower(NPRatePower.POWER_ID)) {
            npMultiplier *= 2;
        }

        return npGain * npMultiplier;
    }

    private int getNpMultiplier(int fgoNp) {
        if (fgoNp >= 200) {
            return 10;
        } else if (fgoNp >= 100) {
            return 7;
        } else {
            return 5;
        }
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        FgoMasterHelper.DerFreischutzThisCombat = 0;
        if (AbstractDungeon.player instanceof master) {
            //前六层的圆桌骑士的援助。
            if (masterMod.roundTableEffect && AbstractDungeon.floorNum <= 6) {
                AbstractDungeon.topLevelEffects.add(new FateRemindEffect(CardCrawlGame.languagePack.getUIString("fatemaster:InSonorousEffectCard").TEXT[0]));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EvasionPower(AbstractDungeon.player, 1), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 1), 1));
                AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 5));
            }
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom r) {
        FgoMasterHelper.DerFreischutzThisCombat = 0;
        if (AbstractDungeon.player instanceof master) {
            //在每场战斗结束时宝具值变为0。
            AbstractDungeon.actionManager.addToTop(new FgoNpAction(-300));
        }
    }

    @Override
    public int receiveOnPlayerDamaged(int i, DamageInfo damageInfo) {
        if (AbstractDungeon.player instanceof master) {
            if (damageInfo.type != DamageInfo.DamageType.THORNS &&
                    damageInfo.type != DamageInfo.DamageType.HP_LOSS &&
                    damageInfo.owner != null &&
                    damageInfo.owner != AbstractDungeon.player &&
                    AbstractDungeon.currMapNode != null &&
                    AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                    i != 99999) {
                if (AbstractDungeon.player.hasPower(NPRatePower.POWER_ID)) {
                    if (AbstractDungeon.player.hasRelic(Avenger.ID)) {
                        AbstractDungeon.actionManager.addToBottom(new FgoNpAction(i + i / 10 * 3));
                    } else {
                        AbstractDungeon.actionManager.addToBottom(new FgoNpAction(i));
                    }
                } else {
                    if (AbstractDungeon.player.hasRelic(Avenger.ID)) {
                        AbstractDungeon.actionManager.addToBottom(new FgoNpAction(i / 2 + i / 10 * 3));
                    } else {
                        AbstractDungeon.actionManager.addToBottom(new FgoNpAction(i / 2));
                    }
                }
            }
        }
        return i;
    }

    @Override
    public void receiveStartGame() {
        noble = new NoblePhantasmButton(
                AbstractDungeon.player.hb.x + AbstractDungeon.player.hb.width + 20.0F * Settings.scale,
                AbstractDungeon.player.hb.cY - AbstractDungeon.player.hb.height / 2.0F + 17.0F * Settings.scale
        );
    }

    @Override
    public void receivePostDungeonUpdate() {
        if (noble != null
                && !AbstractDungeon.overlayMenu.combatDeckPanel.isHidden
                && AbstractDungeon.player.chosenClass == ThmodClassEnum.Master_CLASS) {
            noble.update();
        }
    }

    @Override
    public void receivePostDungeonInitialize() {
        if (AbstractDungeon.player instanceof master && !AbstractDungeon.player.hasBlight(fufu.ID)) {
            AbstractBlight blight = new fufu();

            blight.spawn(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
            blight.obtain();
            blight.isObtained = true;
            blight.isAnimating = false;
            blight.isDone = false;
            blight.flash();
        }
    }
}