package alexndr.api.content.blocks;

import alexndr.api.config.IConfigureBlockHelper;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.core.SimpleCoreAPI;
import alexndr.api.helpers.game.TooltipHelper;
import alexndr.api.registry.ContentCategories;
import alexndr.api.registry.Plugin;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class SimpleStairs extends BlockStairs implements IConfigureBlockHelper<SimpleStairs>
{
    protected Plugin plugin;
    protected ContentCategories.Block category;
    protected ConfigBlock entry;
    protected String name;

    /**
     * Creates a simple stair block.
     * @param plugin The plugin the stairs belong to
     * @param modelState BlockState of the block the stairs are made of.
     * @param category The category of the stairs block
     */
    public SimpleStairs(String name, Plugin plugin, IBlockState modelState, ContentCategories.Block category)
    {
        super(modelState);
		this.name = name;
		this.plugin = plugin;
        this.category = category;
        this.useNeighborBrightness = true;
		setUnlocalizedName(name);
		setRegistryName(plugin.getModId(), name);
    }
    
 	public void registerItemModel(Item itemBlock) {
		SimpleCoreAPI.proxy.registerItemRenderer(plugin, itemBlock, 0, name);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(this.getRegistryName());
	}

   @Override
	public ConfigBlock getConfigEntry() {
		return this.entry;
	}
	
    @Override
    public SimpleStairs setConfigEntry(ConfigBlock entry) 
    {
    	// most properties are obtained from the block the stair as made of, and not configured
    	// and set separately.
		this.entry = entry;
		this.setLightLevel(entry.getLightValue());
		// this.setCreativeTab(entry.getCreativeTab());
		this.setAdditionalProperties();
		return this;
    }

    @Override
    public SimpleStairs addToolTip(String toolTip)
    {
        TooltipHelper.addTooltipToBlock(this, toolTip);
        return this;
    }

    @Override
    public void setAdditionalProperties() {}
    
} // end class()
