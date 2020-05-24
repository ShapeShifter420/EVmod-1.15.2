package com.ShapeShifter420.evmod.gui;

import com.ShapeShifter420.evmod.EVmod;
import com.ShapeShifter420.evmod.block.BlockList;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EVmod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientSub{

    @SubscribeEvent
    public static void ClientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModContainerTypes.BASE_COMPUTER.get(), BaseComputerGui::new);
        RenderTypeLookup.setRenderLayer(BlockList.BASE_COMPUTER.get(), RenderType.func_228639_c_());
        ScreenManager.registerFactory(ModContainerTypes.NANOCHEST.get(), BaseChestGui::new);
        RenderTypeLookup.setRenderLayer(BlockList.NANOCHEST.get(), RenderType.func_228639_c_());//ALERT
    }
}
