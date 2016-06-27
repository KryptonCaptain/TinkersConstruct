package tconstruct.items.tools;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import mods.battlegear2.api.PlayerEventChild;
import mods.battlegear2.api.weapons.IBattlegearWeapon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import tconstruct.library.tools.Weapon;
import tconstruct.tools.TinkerTools;

@Optional.InterfaceList({
    @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.weapons.IBattlegearWeapon")
})

public class Cutlass extends Weapon implements IBattlegearWeapon
{
    public Cutlass()
    {
        super(4);
        this.setUnlocalizedName("InfiTool.Cutlass");
    }

    @Override
    public Item getHeadItem ()
    {
        return TinkerTools.swordBlade;
    }

    @Override
    public Item getAccessoryItem ()
    {
        return TinkerTools.fullGuard;
    }

    @Override
    public String getIconSuffix (int partType)
    {
        switch (partType)
        {
        case 0:
            return "_cutlass_blade";
        case 1:
            return "_cutlass_blade_broken";
        case 2:
            return "_cutlass_handle";
        case 3:
            return "_cutlass_guard";
        default:
            return "";
        }
    }

    @Override
    public String getEffectSuffix ()
    {
        return "_cutlass_effect";
    }

    @Override
    public String getDefaultFolder ()
    {
        return "cutlass";
    }

    @Override
    public int durabilityTypeAccessory ()
    {
        return 1;
    }
    
    /*---- Battlegear Support START ----*/

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean sheatheOnBack(ItemStack item)
    {
        return false;
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
