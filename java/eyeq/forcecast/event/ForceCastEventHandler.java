package eyeq.forcecast.event;

import eyeq.util.entity.EntityUtils;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import eyeq.forcecast.item.ItemArmorForceCast;

public class ForceCastEventHandler {
    @SubscribeEvent
    public void onPlayerPickupXp(PlayerPickupXpEvent event) {
        ItemStack armor = EntityUtils.getArmor(event.getEntityPlayer(), EntityEquipmentSlot.CHEST);
        if(!(armor.getItem() instanceof ItemArmorForceCast)) {
            return;
        }
        event.getOrb().xpValue *= 2;
    }
}
