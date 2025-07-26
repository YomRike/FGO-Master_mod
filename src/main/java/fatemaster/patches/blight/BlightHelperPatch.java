package fatemaster.patches.blight;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.helpers.BlightHelper;
import fatemaster.blight.fufu;

@SuppressWarnings("unused")
public class BlightHelperPatch {
	@SpirePatch(clz = BlightHelper.class, method = "getBlight", paramtypez = {String.class})
	public static class GetBlightPatch {
		@SpirePrefixPatch
		public static SpireReturn<AbstractBlight> Prefix(String id) {
			if (id.equals(fufu.ID))
				return SpireReturn.Return(new fufu());

			return SpireReturn.Continue();
		}
	}
}
