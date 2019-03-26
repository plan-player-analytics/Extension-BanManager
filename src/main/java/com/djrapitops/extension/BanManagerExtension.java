/*
    Copyright(c) 2019 Risto Lahtela (Rsl1122)

    The MIT License(MIT)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files(the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions :
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
*/
package com.djrapitops.extension;

import com.djrapitops.plan.extension.DataExtension;
import com.djrapitops.plan.extension.FormatType;
import com.djrapitops.plan.extension.annotation.*;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;
import me.confuser.banmanager.BmAPI;
import me.confuser.banmanager.data.PlayerBanData;
import me.confuser.banmanager.data.PlayerMuteData;

import java.util.UUID;

/**
 * DataExtension for BanManager plugin.
 * <p>
 * Adapted from PluginData implementation by Vankka.
 *
 * @author Rsl1122
 */
@PluginInfo(name = "BanManager", iconName = "gavel", iconFamily = Family.SOLID, color = Color.BROWN, updatePlayerDataOnLeave = true)
public class BanManagerExtension implements DataExtension {

    public BanManagerExtension() {
    }

    private String abUUID(UUID uuid) {
        return uuid.toString().replace("-", "");
    }

    @BooleanProvider(
            text = "Banned",
            description = "Is the player banned on BanManager",
            priority = 100,
            conditionName = "banned",
            iconName = "gavel",
            iconColor = Color.RED
    )
    public boolean isBanned(UUID playerUUID) {
        return BmAPI.isBanned(playerUUID);
    }

    @Conditional("banned")
    @StringProvider(
            text = "Banned by",
            description = "Who banned the player",
            priority = 99,
            iconName = "user",
            iconColor = Color.RED,
            playerName = true
    )
    public String banIssuer(UUID playerUUID) {
        return getCurrentBan(playerUUID).getActor().getName();
    }

    private PlayerBanData getCurrentBan(UUID playerUUID) {
        return BmAPI.getCurrentBan(playerUUID);
    }

    @Conditional("banned")
    @NumberProvider(
            text = "Date",
            description = "When the ban was issued",
            priority = 98,
            iconName = "calendar",
            iconFamily = Family.REGULAR,
            iconColor = Color.RED,
            format = FormatType.DATE_YEAR
    )
    public long banIssueDate(UUID playerUUID) {
        return getCurrentBan(playerUUID).getCreated();
    }

    @Conditional("banned")
    @NumberProvider(
            text = "Ends",
            description = "When the ban expires",
            priority = 96,
            iconName = "calendar-check",
            iconFamily = Family.REGULAR,
            iconColor = Color.RED,
            format = FormatType.DATE_YEAR
    )
    public long banExpireDate(UUID playerUUID) {
        return getCurrentBan(playerUUID).getExpires();
    }

    @Conditional("banned")
    @StringProvider(
            text = "Reason",
            description = "Why the ban was issued",
            priority = 95,
            iconName = "comment",
            iconFamily = Family.REGULAR,
            iconColor = Color.RED
    )
    public String banReason(UUID playerUUID) {
        return getCurrentBan(playerUUID).getReason();
    }

    @BooleanProvider(
            text = "Muted",
            description = "Is the player muted on BanManager",
            priority = 50,
            conditionName = "muted",
            iconName = "bell-slash",
            iconColor = Color.DEEP_ORANGE
    )
    public boolean isMuted(UUID playerUUID) {
        return BmAPI.isMuted(playerUUID);
    }

    @Conditional("muted")
    @StringProvider(
            text = "Muted by",
            description = "Who muted the player",
            priority = 49,
            iconName = "user",
            iconColor = Color.DEEP_ORANGE,
            playerName = true
    )
    public String muteIssuer(UUID playerUUID) {
        return getCurrentMute(playerUUID).getActor().getName();
    }

    private PlayerMuteData getCurrentMute(UUID playerUUID) {
        return BmAPI.getCurrentMute(playerUUID);
    }

    @Conditional("muted")
    @NumberProvider(
            text = "Date",
            description = "When the mute was issued",
            priority = 48,
            iconName = "calendar",
            iconFamily = Family.REGULAR,
            iconColor = Color.DEEP_ORANGE,
            format = FormatType.DATE_YEAR
    )
    public long muteIssueDate(UUID playerUUID) {
        return getCurrentMute(playerUUID).getCreated();
    }

    @Conditional("muted")
    @NumberProvider(
            text = "Ends",
            description = "When the mute expires",
            priority = 46,
            iconName = "calendar-check",
            iconFamily = Family.REGULAR,
            iconColor = Color.DEEP_ORANGE,
            format = FormatType.DATE_YEAR
    )
    public long muteExpireDate(UUID playerUUID) {
        return getCurrentMute(playerUUID).getExpires();
    }

    @Conditional("muted")
    @StringProvider(
            text = "Reason",
            description = "Why the mute was issued",
            priority = 45,
            iconName = "comment",
            iconFamily = Family.REGULAR,
            iconColor = Color.DEEP_ORANGE
    )
    public String muteReason(UUID playerUUID) {
        return getCurrentMute(playerUUID).getReason();
    }
}