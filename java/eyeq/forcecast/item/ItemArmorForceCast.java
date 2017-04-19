package eyeq.forcecast.item;

import eyeq.forcecast.ForceCast;
import eyeq.util.UItemArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemArmorForceCast extends UItemArmor {
    private static final ResourceLocation armorName = new ResourceLocation(ForceCast.MOD_ID, "force_cast");

    public ItemArmorForceCast(int renderIndex, EntityEquipmentSlot slot) {
        super(ArmorMaterial.IRON, renderIndex, slot, armorName);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type) {
        if(slot == EntityEquipmentSlot.CHEST && type == null) {
            return super.getArmorTexture(itemStack, entity, slot, type);
        }
        return null;
    }

    @Override
    protected void onArmorTickChest(World world, EntityPlayer player, ItemStack itemStack) {
        player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1, 1));
        player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 1, 0));
    }
}
