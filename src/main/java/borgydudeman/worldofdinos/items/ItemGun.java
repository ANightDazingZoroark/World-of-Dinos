package borgydudeman.worldofdinos.items;

import javax.annotation.Nullable;

import borgydudeman.worldofdinos.Main;
import borgydudeman.worldofdinos.init.ModItems;
import borgydudeman.worldofdinos.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGun extends ItemBow implements IHasModel{	
	public ItemGun(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		maxStackSize = 1;
		setCreativeTab(CreativeTabs.COMBAT);
		ModItems.ITEMS.add(this);
		
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter(){
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn){
				if (entityIn == null){
					return 0.0F;
				}
				else{
					return entityIn.getActiveItemStack().getItem() != ModItems.Tranq_Gun ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
				}
			}
		});
		this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter(){
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn){
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
	}

	private ItemStack findAmmo(EntityPlayer player){
		if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))){
			return player.getHeldItem(EnumHand.OFF_HAND);
		}
		else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))){
			return player.getHeldItem(EnumHand.MAIN_HAND);
		}
		else{
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i){
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (this.isArrow(itemstack)){
					return itemstack;
				}
			}

			return ItemStack.EMPTY;
		}
	}
	
	public float getZoom(EntityLivingBase entity) {
		return 1-MathHelper.clamp(this.getMaxItemUseDuration(null) - entity.getItemInUseCount(), 0, 20)/60;//zooms from normal fov to 2/3 normal fov in one second
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");		
	}

}
