package eyeq.forcecast;

import eyeq.util.client.model.UModelCreator;
import eyeq.util.client.model.UModelLoader;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.oredict.UOreDictionary;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import eyeq.forcecast.event.ForceCastEventHandler;
import eyeq.forcecast.item.ItemArmorForceCast;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.io.File;

import static eyeq.forcecast.ForceCast.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
@Mod.EventBusSubscriber
public class ForceCast {
    public static final String MOD_ID = "eyeq_forcecast";

    @Mod.Instance(MOD_ID)
    public static ForceCast instance;

    public static Item forceCast;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ForceCastEventHandler());
        addRecipes();
        if(event.getSide().isServer()) {
            return;
        }
        renderItemModels();
        createFiles();
    }

    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        forceCast = new ItemArmorForceCast(0, EntityEquipmentSlot.CHEST).setUnlocalizedName("forceCast");

        GameRegistry.register(forceCast, resource.createResourceLocation("force_cast"));
    }

    public static void addRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(forceCast), "III", "L L", "III",
                'I', UOreDictionary.OREDICT_IRON_INGOT, 'L', Items.LEAD));
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemModels() {
        UModelLoader.setCustomModelResourceLocation(forceCast);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-ForceCast");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, forceCast, "Force Cast");
        language.register(LanguageResourceManager.JA_JP, forceCast, "強制ギプス");

        ULanguageCreator.createLanguage(project, MOD_ID, language);

        UModelCreator.createItemJson(project, forceCast, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
    }
}
