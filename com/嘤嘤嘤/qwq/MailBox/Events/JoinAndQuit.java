package com.嘤嘤嘤.qwq.MailBox.Events;

import static com.嘤嘤嘤.qwq.MailBox.GlobalConfig.success;
import com.嘤嘤嘤.qwq.MailBox.MailBox;
import static com.嘤嘤嘤.qwq.MailBox.MailBox.MailListPlayerId;
import static com.嘤嘤嘤.qwq.MailBox.MailBox.MailListSystemId;
import com.嘤嘤嘤.qwq.MailBox.VexView.MailBoxHud;
import lk.vexview.api.VexViewAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinAndQuit implements Listener {
    
    private boolean enVexView;
    private boolean enHud;
    
    public JoinAndQuit(boolean enVexView, boolean enHud){
        this.enVexView = enVexView;
        this.enHud = enHud;
        if(enHud) Bukkit.getConsoleSender().sendMessage(success+"-----[MailBox]:已启用邮箱HUD");
    }
    
    // 玩家进入事件
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent evt){
        Player player = evt.getPlayer();
        // 获取可领取邮件列表
        MailBox.getUnMailList(player, "system");
        MailBox.getUnMailList(player, "player");
        MailBox.getUnMailList(player, "permission");
        // 移除HUD
        if(enVexView) VexViewAPI.removeHUD(player, MailBoxHud.id);
        // 设置HUD
        if(enHud) VexViewAPI.sendHUD(player, new MailBoxHud());
    }
    
    // 玩家退出事件
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent evt){
        Player player = evt.getPlayer();
        String Username = player.getName();
        // 将玩家移出邮件列表
        MailListSystemId.remove(Username);
        MailListPlayerId.remove(Username);
    }
    
}
