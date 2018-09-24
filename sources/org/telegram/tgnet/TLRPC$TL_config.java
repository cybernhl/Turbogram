package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_config extends TLObject {
    public static int constructor = 840162234;
    public String autoupdate_url_prefix;
    public boolean blocked_mode;
    public int call_connect_timeout_ms;
    public int call_packet_timeout_ms;
    public int call_receive_timeout_ms;
    public int call_ring_timeout_ms;
    public int caption_length_max;
    public int channels_read_media_period;
    public int chat_size_max;
    public int date;
    public ArrayList<TLRPC$TL_dcOption> dc_options = new ArrayList();
    public String dc_txt_domain_name;
    public boolean default_p2p_contacts;
    public int edit_time_limit;
    public int expires;
    public int flags;
    public int forwarded_count_max;
    public String gif_search_username;
    public boolean ignore_phone_entities;
    public String img_search_username;
    public int lang_pack_version;
    public String me_url_prefix;
    public int megagroup_size_max;
    public int message_length_max;
    public int notify_cloud_delay_ms;
    public int notify_default_delay_ms;
    public int offline_blur_timeout_ms;
    public int offline_idle_timeout_ms;
    public int online_cloud_timeout_ms;
    public int online_update_period_ms;
    public boolean phonecalls_enabled;
    public int pinned_dialogs_count_max;
    public boolean preload_featured_stickers;
    public int push_chat_limit;
    public int push_chat_period_ms;
    public int rating_e_decay;
    public boolean revoke_pm_inbox;
    public int revoke_pm_time_limit;
    public int revoke_time_limit;
    public int saved_gifs_limit;
    public String static_maps_provider;
    public int stickers_faved_limit;
    public int stickers_recent_limit;
    public String suggested_lang_code;
    public boolean test_mode;
    public int this_dc;
    public int tmp_sessions;
    public String venue_search_username;
    public int webfile_dc_id;

    public static TLRPC$TL_config TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_config result = new TLRPC$TL_config();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_config", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        boolean z;
        this.flags = stream.readInt32(exception);
        if ((this.flags & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.phonecalls_enabled = z;
        if ((this.flags & 8) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.default_p2p_contacts = z;
        if ((this.flags & 16) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.preload_featured_stickers = z;
        if ((this.flags & 32) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.ignore_phone_entities = z;
        if ((this.flags & 64) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.revoke_pm_inbox = z;
        if ((this.flags & 256) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.blocked_mode = z;
        this.date = stream.readInt32(exception);
        this.expires = stream.readInt32(exception);
        this.test_mode = stream.readBool(exception);
        this.this_dc = stream.readInt32(exception);
        if (stream.readInt32(exception) == 481674261) {
            int count = stream.readInt32(exception);
            int a = 0;
            while (a < count) {
                TLRPC$TL_dcOption object = TLRPC$TL_dcOption.TLdeserialize(stream, stream.readInt32(exception), exception);
                if (object != null) {
                    this.dc_options.add(object);
                    a++;
                } else {
                    return;
                }
            }
            this.dc_txt_domain_name = stream.readString(exception);
            this.chat_size_max = stream.readInt32(exception);
            this.megagroup_size_max = stream.readInt32(exception);
            this.forwarded_count_max = stream.readInt32(exception);
            this.online_update_period_ms = stream.readInt32(exception);
            this.offline_blur_timeout_ms = stream.readInt32(exception);
            this.offline_idle_timeout_ms = stream.readInt32(exception);
            this.online_cloud_timeout_ms = stream.readInt32(exception);
            this.notify_cloud_delay_ms = stream.readInt32(exception);
            this.notify_default_delay_ms = stream.readInt32(exception);
            this.push_chat_period_ms = stream.readInt32(exception);
            this.push_chat_limit = stream.readInt32(exception);
            this.saved_gifs_limit = stream.readInt32(exception);
            this.edit_time_limit = stream.readInt32(exception);
            this.revoke_time_limit = stream.readInt32(exception);
            this.revoke_pm_time_limit = stream.readInt32(exception);
            this.rating_e_decay = stream.readInt32(exception);
            this.stickers_recent_limit = stream.readInt32(exception);
            this.stickers_faved_limit = stream.readInt32(exception);
            this.channels_read_media_period = stream.readInt32(exception);
            if ((this.flags & 1) != 0) {
                this.tmp_sessions = stream.readInt32(exception);
            }
            this.pinned_dialogs_count_max = stream.readInt32(exception);
            this.call_receive_timeout_ms = stream.readInt32(exception);
            this.call_ring_timeout_ms = stream.readInt32(exception);
            this.call_connect_timeout_ms = stream.readInt32(exception);
            this.call_packet_timeout_ms = stream.readInt32(exception);
            this.me_url_prefix = stream.readString(exception);
            if ((this.flags & 128) != 0) {
                this.autoupdate_url_prefix = stream.readString(exception);
            }
            if ((this.flags & 512) != 0) {
                this.gif_search_username = stream.readString(exception);
            }
            if ((this.flags & 1024) != 0) {
                this.venue_search_username = stream.readString(exception);
            }
            if ((this.flags & 2048) != 0) {
                this.img_search_username = stream.readString(exception);
            }
            if ((this.flags & 4096) != 0) {
                this.static_maps_provider = stream.readString(exception);
            }
            this.caption_length_max = stream.readInt32(exception);
            this.message_length_max = stream.readInt32(exception);
            this.webfile_dc_id = stream.readInt32(exception);
            if ((this.flags & 4) != 0) {
                this.suggested_lang_code = stream.readString(exception);
            }
            if ((this.flags & 4) != 0) {
                this.lang_pack_version = stream.readInt32(exception);
            }
        } else if (exception) {
            throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        int i;
        stream.writeInt32(constructor);
        this.flags = this.phonecalls_enabled ? this.flags | 2 : this.flags & -3;
        this.flags = this.default_p2p_contacts ? this.flags | 8 : this.flags & -9;
        this.flags = this.preload_featured_stickers ? this.flags | 16 : this.flags & -17;
        this.flags = this.ignore_phone_entities ? this.flags | 32 : this.flags & -33;
        this.flags = this.revoke_pm_inbox ? this.flags | 64 : this.flags & -65;
        if (this.blocked_mode) {
            i = this.flags | 256;
        } else {
            i = this.flags & -257;
        }
        this.flags = i;
        stream.writeInt32(this.flags);
        stream.writeInt32(this.date);
        stream.writeInt32(this.expires);
        stream.writeBool(this.test_mode);
        stream.writeInt32(this.this_dc);
        stream.writeInt32(481674261);
        int count = this.dc_options.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            ((TLRPC$TL_dcOption) this.dc_options.get(a)).serializeToStream(stream);
        }
        stream.writeString(this.dc_txt_domain_name);
        stream.writeInt32(this.chat_size_max);
        stream.writeInt32(this.megagroup_size_max);
        stream.writeInt32(this.forwarded_count_max);
        stream.writeInt32(this.online_update_period_ms);
        stream.writeInt32(this.offline_blur_timeout_ms);
        stream.writeInt32(this.offline_idle_timeout_ms);
        stream.writeInt32(this.online_cloud_timeout_ms);
        stream.writeInt32(this.notify_cloud_delay_ms);
        stream.writeInt32(this.notify_default_delay_ms);
        stream.writeInt32(this.push_chat_period_ms);
        stream.writeInt32(this.push_chat_limit);
        stream.writeInt32(this.saved_gifs_limit);
        stream.writeInt32(this.edit_time_limit);
        stream.writeInt32(this.revoke_time_limit);
        stream.writeInt32(this.revoke_pm_time_limit);
        stream.writeInt32(this.rating_e_decay);
        stream.writeInt32(this.stickers_recent_limit);
        stream.writeInt32(this.stickers_faved_limit);
        stream.writeInt32(this.channels_read_media_period);
        if ((this.flags & 1) != 0) {
            stream.writeInt32(this.tmp_sessions);
        }
        stream.writeInt32(this.pinned_dialogs_count_max);
        stream.writeInt32(this.call_receive_timeout_ms);
        stream.writeInt32(this.call_ring_timeout_ms);
        stream.writeInt32(this.call_connect_timeout_ms);
        stream.writeInt32(this.call_packet_timeout_ms);
        stream.writeString(this.me_url_prefix);
        if ((this.flags & 128) != 0) {
            stream.writeString(this.autoupdate_url_prefix);
        }
        if ((this.flags & 512) != 0) {
            stream.writeString(this.gif_search_username);
        }
        if ((this.flags & 1024) != 0) {
            stream.writeString(this.venue_search_username);
        }
        if ((this.flags & 2048) != 0) {
            stream.writeString(this.img_search_username);
        }
        if ((this.flags & 4096) != 0) {
            stream.writeString(this.static_maps_provider);
        }
        stream.writeInt32(this.caption_length_max);
        stream.writeInt32(this.message_length_max);
        stream.writeInt32(this.webfile_dc_id);
        if ((this.flags & 4) != 0) {
            stream.writeString(this.suggested_lang_code);
        }
        if ((this.flags & 4) != 0) {
            stream.writeInt32(this.lang_pack_version);
        }
    }
}
