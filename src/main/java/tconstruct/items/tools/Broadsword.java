package tconstruct.items.tools;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import mods.battlegear2.api.PlayerEventChild;
import mods.battlegear2.api.weapons.IBattlegearWeapon;
import tconstruct.library.tools.Weapon;
import tconstruct.tools.TinkerTools;

@Optional.InterfaceList({
    @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.weapons.IBattlegearWeapon")
})

public class Broadsword extends Weapon implements IBattlegearWeapon
{
    public Broadsword()
    {
        super(4);
        this.setUnlocalizedName("InfiTool.Broadsword");
    }

    @Override
    public Item getHeadItem ()
    {
        return TinkerTools.swordBlade;
    }

    @Override
    public Item getAccessoryItem ()
    {
        return TinkerTools.wideGuard;
    }

    @Override
    public float getDurabilityModifier ()
    {
        return 1.2f;
    }

    @Override
    public String getIconSuffix (int partType)
    {
        switch (partType)
        {
        case 0:
            return "_sword_blade";
        case 1:
            return "_sword_blade_broken";
        case 2:
            return "_sword_handle";
        case 3:
            return "_sword_accessory";
        default:
            return "";
        }
    }

    @Override
    public String getEffectSuffix ()
    {
        return "_sword_effect";
    }

    @Override
    public String getDefaultFolder ()
    {
        return "broadsword";
    }

    /*
     * @Override
     * 
     * @SideOnly(Side.CLIENT) public void onUpdate (ItemStack stack, World
     * world, Entity entity, int par4, boolean par5) { super.onUpdate(stack,
     * world, entity, par4, par5); if (entity instanceof EntityPlayerSP) {
     * EntityPlayerSP player = (EntityPlayerSP) entity; if (player.itemInUse !=
     * null && player.itemInUse.getItem() == this) {
     * player.movementInput.moveForward *= 5.0F; player.movementInput.moveStrafe
     * *= 5.0F; } } }
     */
    
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
