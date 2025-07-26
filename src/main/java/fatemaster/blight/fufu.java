package fatemaster.blight;

import basemod.patches.whatmod.WhatMod;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.BlightStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import fatemaster.Action.ChooseSkinAction;
import fatemaster.characters.master;
import fatemaster.masterMod;

import java.util.Arrays;
import java.util.stream.Collectors;

public class fufu extends AbstractBlight {
	public static final String ID = masterMod.makeId(fufu.class.getSimpleName());
	private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString(ID);
	private boolean ClickStart = false;
	private boolean Click = false;

	public fufu() {
		super(ID, blightStrings.NAME,
				(WhatMod.enabled ?
						Arrays.stream(WhatMod.findModName(fufu.class).split("(?<!\\()\\s+(?![^()]*\\))"))
								.map(s -> "#p" + s)
								.collect(Collectors.joining(" ")) + " NL "
						: "")
						+ blightStrings.DESCRIPTION[0],
				"",
				true
		);

		this.img = ImageMaster.loadImage("fgoMasterResources/images/relics_Master/fufu.png");
		this.outlineImg = ImageMaster.loadImage("fgoMasterResources/images/relics_Master/outline/fufu.png");
	}

	protected void onRightClick() {
		if (AbstractDungeon.player instanceof master && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
			AbstractDungeon.actionManager.addToTop(new ChooseSkinAction());
		}
	}

	@Override
	public void update() {
		super.update();
		if (this.ClickStart && InputHelper.justReleasedClickRight) {
			if (this.hb.hovered) {
				this.Click = true;
			}

			this.ClickStart = false;
		}

		if (this.isObtained && this.hb != null && this.hb.hovered && InputHelper.justClickedRight) {
			this.ClickStart = true;
		}

		if (this.Click) {
			this.Click = false;
			onRightClick();
		}
	}
}
