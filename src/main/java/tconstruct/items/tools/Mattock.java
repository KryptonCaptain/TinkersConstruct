package tconstruct.items.tools;

import java.util.List;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import mods.battlegear2.api.PlayerEventChild;
import mods.battlegear2.api.weapons.IBattlegearWeapon;
import tconstruct.library.tools.*;
import tconstruct.tools.TinkerTools;
import tconstruct.util.config.PHConstruct;

@Optional.InterfaceList({
    @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.weapons.IBattlegearWeapon")
})

public class Mattock extends DualHarvestTool implements IBattlegearWeapon
{
    public Mattock()
    {
        super(3);
        this.setUnlocalizedName("InfiTool.Mattock");
    }

    @Override
    protected Material[] getEffectiveMaterials ()
    {
        return axeMaterials;
    }

    @Override
    protected Material[] getEffectiveSecondaryMaterials ()
    {
        return shovelMaterials;
    }

    @Override
    protected String getHarvestType ()
    {
        return "axe";
    }

    @Override
    protected String getSecondHarvestType ()
    {
        return "shovel";
    }

    static Material[] axeMaterials = { Material.wood, Material.cactus, Material.plants, Material.vine, Material.gourd };
    static Material[] shovelMaterials = { Material.grass, Material.ground, Material.clay };

    @Override
    public Item getHeadItem ()
    {
        return TinkerTools.hatchetHead;
    }

    @Override
    public Item getAccessoryItem ()
    {
        return TinkerTools.shovelHead;
    }

    @Override
    public int durabilityTypeAccessory ()
    {
        return 2;
    }

    @Override
    public String getIconSuffix (int partType)
    {
        switch (partType)
        {
        case 0:
            return "_mattock_head";
        case 1:
            return "_mattock_head_broken";
        case 2:
            return "_mattock_handle";
        case 3:
            return "_mattock_back";
        default:
            return "";
        }
    }

    @Override
    public String getEffectSuffix ()
    {
        return "_mattock_effect";
    }

    @Override
    public String getDefaultFolder ()
    {
        return "mattock";
    }

    /* Mattock specific */

    @Override
    public boolean onItemUse (ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ)
    {
        NBTTagCompound tags = stack.getTagCompound().getCompoundTag("InfiTool");
        if (tags.getBoolean("Broken"))
            return false;

        return AbilityHelper.hoeGround(stack, player, world, x, y, z, side, random);
    }

    @Override
    public void buildTool (int id, String name, List list)
    {
        if (!PHConstruct.denyMattock || allowCrafting(id))
        {
            super.buildTool(id, name, list);
        }
    }

    private boolean allowCrafting (int head)
    {
        int[] nonMetals = { 0, 1, 3, 4, 5, 6, 7, 8, 9, 17 };
        for (int i = 0; i < nonMetals.length; i++)
        {
            if (head == nonMetals[i])
                return false;
        }
        return true;
    }

    /*---- Battlegear Support START ----*/

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean sheatheOnBack(ItemStack item)
    {
        return true;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean isOffhandHandDual(ItemStack off) {
        return true;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean offhandAttackEntity(PlayerEventChild.OffhandAttackEvent event, ItemStack mainhandItem, ItemStack offhandItem) {
        return true;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean offhandClickAir(PlayerInteractEvent event, ItemStack mainhandItem, ItemStack offhandItem) {
        return true;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean offhandClickBlock(PlayerInteractEvent event, ItemStack mainhandItem, ItemStack offhandItem) {
        return true;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public void performPassiveEffects(Side effectiveSide, ItemStack mainhandItem, ItemStack offhandItem) {
        // unused
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean allowOffhand(ItemStack mainhand, ItemStack offhand) {
    	return true;
    }

    /*---- Battlegear Support END ----*/
    
}