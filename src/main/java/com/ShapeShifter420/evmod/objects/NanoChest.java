package com.ShapeShifter420.evmod.objects;

import com.ShapeShifter420.evmod.gui.EntityType;
import com.ShapeShifter420.evmod.gui.TileEntityBase_C;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class NanoChest extends Block{
    public NanoChest(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        System.out.println(EntityType.NANOCHEST);
        //TileEntity ex = EntityType.EXAMPLE_CHEST.get().create();
        TileEntity ex = EntityType.NANOCHEST.get().create();//BlockList.example_chest.getDefaultState().createTileEntity(world);
        System.out.println(4);
        return ex;
    } //BlockList.just_cheast.getBlock().createTileEntity(state,world);

    @Override
    public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                           Hand handIn, BlockRayTraceResult result) {

        if (!worldIn.isRemote) {
            System.out.println(2);
            TileEntity tile = worldIn.getTileEntity(pos);
            System.out.println(tile instanceof TileEntityBase_C);
            System.out.println(worldIn.isBlockPowered(pos));
            if (tile instanceof TileEntityBase_C && worldIn.isBlockPowered(pos)) { //tile instanceof TileEntityBase;
                System.out.println(40);
                NetworkHooks.openGui((ServerPlayerEntity) player, (TileEntityBase_C) tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }
}
