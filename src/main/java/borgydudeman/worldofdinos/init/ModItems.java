package borgydudeman.worldofdinos.init;

import java.util.ArrayList;
import java.util.List;

import borgydudeman.worldofdinos.items.ItemBase;
import borgydudeman.worldofdinos.items.ItemGun;
import net.minecraft.item.Item;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item Tranq_Dart = new ItemBase("Tranq_Dart");
	
	public static final Item Tranq_Gun = new ItemGun("Tranquilizer_Gun");
}
