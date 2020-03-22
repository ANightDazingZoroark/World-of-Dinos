package borgydudeman.worldofdinos.util.handlers;

import borgydudeman.worldofdinos.init.ModItems;
import borgydudeman.worldofdinos.items.ItemGun;
import borgydudeman.worldofdinos.util.IHasModel;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item: ModItems.ITEMS) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
	}
	
	@SubscribeEvent
	public void zoom(FOVUpdateEvent event) {
		if(event.getEntity().getActiveItemStack() != null) {
			if(event.getEntity().getActiveItemStack().getItem() == ModItems.Tranq_Gun) {
				event.setNewfov(event.getFov()*(((ItemGun)event.getEntity().getActiveItemStack().getItem()).getZoom(event.getEntity())));
			}
		}
	}
}
