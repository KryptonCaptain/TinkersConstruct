package tconstruct.items.tools;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.*;
import mods.battlegear2.api.PlayerEventChild;
import mods.battlegear2.api.weapons.IBattlegearWeapon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import tconstruct.TConstruct;
import tconstruct.library.tools.Weapon;
import tconstruct.tools.*;
import tconstruct.tools.logic.EquipLogic;

@Optional.InterfaceList({
    @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.weapons.IBattlegearWeapon")
})

public class BattleSign extends Weapon implements IBattlegearWeapon
{
    public BattleSign()
    {
        super(1);
        this.setUnlocalizedName("InfiTool.Battlesign");
    }

    @Override
    public String getToolName ()
    {
        return "Battlesign";
    }

    @Override
    public Item getHeadItem ()
    {
        return TinkerTools.signHead;
    }

    @Override
    public Item getAccessoryItem ()
    {
        return null;
    }

    @Override
    public int getPartAmount ()
    {
        return 2;
    }

    @Override
    public void registerPartPaths (int index, String[] location)
    {
        headStrings.put(index, location[0]);
        brokenPartStrings.put(index, location[1]);
        handleStrings.put(index, location[2]);
    }

    @Override
    public String getIconSuffix (int partType)
    {
        switch (partType)
        {
        case 0:
            return "_battlesign_head";
        case 1:
            return "_battlesign_head_broken";
        case 2:
            return "_battlesign_handle";
        default:
            return "";
        }
    }

    @Override
    public String getEffectSuffix ()
    {
        return "_battlesign_effect";
    }

    @Override
    public String getDefaultFolder ()
    {
        return "battlesign";
    }

    @Override
    public boolean onItemUse (ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ)
    {
        if (side == 0 || !player.isSneaking())
        {
            return false;
        }
        else if (!world.getBlock(x, y, z).getMaterial().isSolid())
        {
            return false;
        }
        else
        {
            if (side == 1)
            {
                ++y;
            }

            if (side == 2)
            {
                --z;
            }

            if (side == 3)
            {
                ++z;
            }

            if (side == 4)
            {
                --x;
            }

            if (side == 5)
            {
                ++x;
            }

            if (!player.canPlayerEdit(x, y, z, side, stack))
            {
                return false;
            }
            else if (!TinkerTools.battlesignBlock.canPlaceBlockAt(world, x, y, z))
            {
                return false;
            }
            else
            {
                world.setBlock(x, y, z, TinkerTools.battlesignBlock, 0, 3);
                TinkerTools.battlesignBlock.onBlockPlacedBy(world, x, y, z, player, stack);

                EquipLogic logic = (EquipLogic) world.getTileEntity(x, y, z);
                logic.setEquipmentItem(stack);
                --stack.stackSize;

                if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
                {
                    player.openGui(TConstruct.instance, ToolProxyClient.battlesignTextID, world, x, y, z);
                }

                return true;
            }
        }
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
